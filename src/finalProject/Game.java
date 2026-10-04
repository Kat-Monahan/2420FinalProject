package finalProject;

import java.util.ArrayList;
import java.util.List;

import edu.princeton.cs.algs4.DijkstraSP;
import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

/**
 * Main game controller. Coordinates SARTeam, Hiker, and the loaded map data.
 * 
 * @author Bowen Berthelson
 * @author KatM
 */
public class Game {

    private final Graph graph;
    private final SARTeam team;
    private final Hiker hiker;
    private final Terrain[][] terrainMap;  // BEHOLD, THE MIGHTY TERRAIN MAP
    private final double hikerMoveProb;

    // Move history will be managed by SARTeam, but we expose it cleanly from Game
    /**
     * The Game constructor reads in the file and collects graph info from the GameConfig class.
     * 
     * @param configFile
     * @throws java.io.IOException
     */
    public Game(String configFile) throws java.io.IOException {
        GameConfig config = MapLoader.load(configFile);  // This is where all the info comes from

        // Fill up the fields from GameConfig
        this.graph = config.getGraph();
        this.terrainMap = config.getTerrainMap();
        this.hikerMoveProb = config.getHikerMoveProbability();

        int startNode = MapLoader.toVertex(config.getPlayerStart().getRow(), // toVertex takes the pesky coordinates (x,y) and returns an id (0-99)
                                           config.getPlayerStart().getCol());
        this.team = new SARTeam(startNode, config.getTimeLimit());
        int hikerStart = MapLoader.toVertex(config.getHikerLastKnown().getRow(), // Same thing 
                                            config.getHikerLastKnown().getCol());
        this.hiker = new Hiker(hikerStart);
        
        // NOW. Hiker and SARTeam are on the board
    }

    public boolean performMove(int targetNode, int cost) {
        if (team.moveTo(targetNode, graph, cost)) { // Returns True False
            hiker.attemptMove(graph, hikerMoveProb);// SARTeam moves, but Hiker may or may not also move.
            return true;
        }
        return false;
    }

    /**
     * Prints the full move history to the console in readable format.
     */
    public void printMoveHistory() {
        Queue<Integer> history = team.getMoveHistory();
        
        System.out.println("=== MOVE HISTORY (" + history.size() + " moves) ===");
        
        if (history.isEmpty()) {
            System.out.println("No moves yet.");
            return;
        }

        // Make a copy so we don't destroy the original queue
        Queue<Integer> temp = new Queue<>();
        for (int node : history) {
            temp.enqueue(node);
        }

        int step = 1;
        while (!temp.isEmpty()) {
            int node = temp.dequeue();
            Position pos = MapLoader.fromVertex(node); // Takes the tile id (0-99) and turns it into coordinates (x,y)
            System.out.printf("Step %2d: (%d, %d)%n", step++, pos.getRow(), pos.getCol());
        }
        System.out.println("=====================================");
    }
    
    /**
     * Returns the shortest path from player to the hiker's CURRENT hidden position
     * using real terrain movement costs. Path disappears on move to avoid clutter.
     */
    public List<Position> getCheatPath() {
        int source = team.getCurrentNode(); // source
        int target = hiker.getCurrentNode();// target

        EdgeWeightedDigraph G = new EdgeWeightedDigraph(100); // A temporary super cool EdgeWeightedDigraph for... ALGORITHMS

        Terrain[][] terrain = getTerrainMap(); // Fill 2d array from GameConfig
        int[] dRow = {-1, 0, 1, 0}; // Witchcraft. Wouldn't be able to tell you why this works. Just... does.
        int[] dCol = {0, 1, 0, -1};

        // Build directed weighted graph
        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                if (!terrain[r][c].isPassable()) continue; // Skip the river tiles.

                int v = MapLoader.toVertex(r, c); // get the id

                //
                for (int d = 0; d < 4; d++) {
                    int nr = r + dRow[d];
                    int nc = c + dCol[d];
                    if (nr < 0 || nr >= 10 || nc < 0 || nc >= 10) continue; // Stay in bounds
                    if (!terrain[nr][nc].isPassable()) continue; // There is an adjacent river tile. NO CONNECTIONS.

                    int w = MapLoader.toVertex(nr, nc);
                    double cost = terrain[nr][nc].getTimeSpent();  // cost of entering tile (for the edges next line)

                    G.addEdge(new DirectedEdge(v, w, cost));
                }
            }
        }

        DijkstraSP sp = new DijkstraSP(G, source); // THE ALGORTHIMS. from source to any other vertex.

        if (!sp.hasPathTo(target)) {
            return new ArrayList<>();   // no path exists
        }

        // Extract vertices from the path (this was the buggy part)
        List<Position> path = new ArrayList<>();
        for (DirectedEdge edge : sp.pathTo(target)) {
            int node = edge.to();                    // get destination vertex of each edge
            path.add(MapLoader.fromVertex(node));    // Fill up the path with coordinates (x,y)
        }
        return path;
    }
    
    /**
     * Scan the tile the player is currently standing on.
     * - If hiker is here: WIN
     * - If not: Tell player they're wrong (and update last known position)
     */
    public String performScan() {
        int currentPos = team.getCurrentNode();

        if (hiker.isAt(currentPos)) {               // A scan was performed on the occupied tile.
            hiker.setStatus(HikerStatus.RESCUED);
            return "HIKER FOUND! You rescued them!"; // Might want to close the program or maybe a "try again?" button for restart?
        } 
        else {
            // Hiker is not here - update last known position to where we scanned
            hiker.updateLastKnown();
            return "No hiker here. Updated last known position.";
        }
    }

    // ==================== Getters for UI ====================

    public int getTeamNode() {
        return team.getCurrentNode();
    }

    public int getHikerLastKnown() {
        return hiker.getLastKnownNode();
    }

    public int getTimeRemaining() {
        return team.getTimeRemaining();
    }

    public Terrain[][] getTerrainMap() {
        return terrainMap;
    }

    public Graph getGraph() {
        return graph;
    }

    public SARTeam getTeam() {
        return team;
    }

    public Hiker getHiker() {
        return hiker;
    }
    
    /**
     * Returns true or false if game end condition met (death, rescue)
     * @return
     */
    public boolean isGameOver() {
        return team.isTimeUp() || hiker.getStatus() != HikerStatus.ALIVE;
    }

    /**
     * Returns true if game-win condition is met.
     * @return
     */
    public boolean hasWon() {
        return hiker.getStatus() == HikerStatus.RESCUED;
    }
}