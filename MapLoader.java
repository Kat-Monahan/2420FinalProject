package finalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Graph;

/**
 * Reads the map configuration file and builds the graph.
 * 
 * @author Bowen Berthelson
 */
public class MapLoader {

	private static final int SIZE = 10;

	/**
	 * Reads from the file: SARTeam location, Hiker location, and terrain grid.
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static GameConfig load(String filename) throws IOException {
	    Position playerStart = null;
	    Position hikerLastKnown = null;
	    Set<Position> blocked = new HashSet<>();
	    Terrain[][] terrainMap = new Terrain[SIZE][SIZE];

	    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
	        String line;
	        boolean headerDone = false;

	        // === PHASE 1: Read header (player start + hiker position) ===
	        while ((line = br.readLine()) != null) {
	            line = line.trim();
	            if (line.isEmpty() || line.startsWith("#")) continue;

	            String[] parts = line.split("\\s+");

	            // If we already finished header, break so we can start terrain grid
	            if (headerDone) break;

	            if (parts.length >= 2) {
	                try {
	                    int r = Integer.parseInt(parts[0]);
	                    int c = Integer.parseInt(parts[1]);

	                    if (playerStart == null) {
	                        playerStart = new Position(r, c);
	                        System.out.println("Player start: " + r + ", " + c);
	                        continue;
	                    }
	                    if (hikerLastKnown == null) {
	                        hikerLastKnown = new Position(r, c);
	                        System.out.println("Hiker last known: " + r + ", " + c);
	                        headerDone = true;        // Important!
	                        continue;
	                    }
	                    
	                } catch (NumberFormatException e) {
	                    // If we can't parse numbers, it's probably the start of terrain grid
	                    headerDone = true;
	                    break;
	                }
	            }
	        }

	        // === PHASE 2: Read 10 lines of terrain grid ===
	        for (int r = 0; r < SIZE; r++) {
	            if (line == null) line = br.readLine();   // use leftover line if any
	            if (line == null) {
	                throw new IOException("Missing terrain row " + r + " in map file");
	            }

	            String[] tiles = line.trim().split("\\s+");
	            if (tiles.length != SIZE) {
	                throw new IOException("Row " + r + " should have 10 tiles, but found " + tiles.length);
	            }

	            for (int c = 0; c < SIZE; c++) {
	                String tileName = tiles[c].trim().toUpperCase();
	                Terrain terrain = Terrain.valueOf(tileName);
	                terrainMap[r][c] = terrain;

	                if (!terrain.isPassable()) {
	                    blocked.add(new Position(r, c)); // Blocked means no edges
	                }
	            }

	            line = null; // reset for next iteration
	        }
	    }

	    // Debug print
	    System.out.println("\n=== TERRAIN MAP LOADED SUCCESSFULLY ===");
	    for (int r = 0; r < SIZE; r++) {
	        for (int c = 0; c < SIZE; c++) {
	            System.out.print(terrainMap[r][c] + " ");
	        }
	        System.out.println();
	    }

	    Graph graph = buildGraph(blocked);

	    return new GameConfig(graph, playerStart, hikerLastKnown, blocked, 
	                          terrainMap, 160, 0.25);                      // EVERYTHING PASSED HERE
	}
	
	/**
	 * Builds a graph that avoids blocked river tiles.
	 * 
	 * @param blocked
	 * @return
	 */
    private static Graph buildGraph(Set<Position> blocked) {
        Graph g = new Graph(SIZE * SIZE);

        int[] dRow = {-1, 0, 1, 0};  // witchcraft
        int[] dCol = {0, 1, 0, -1};

        for (int r = 0; r < SIZE; r++) {
            for (int c = 0; c < SIZE; c++) {
                Position p = new Position(r, c);
                if (blocked.contains(p)) continue; // river tile. skip.

                int v = toVertex(r, c); // coordinates(x,y) to id(0-99)

                for (int d = 0; d < 4; d++) {
                    int nr = r + dRow[d];
                    int nc = c + dCol[d];

                    if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE) { // stay in-bounds
                        Position neighbor = new Position(nr, nc);
                        if (!blocked.contains(neighbor)) {
                            int w = toVertex(nr, nc);
                            g.addEdge(v, w);     // Add edges as long as adjacent tile is not a river.
                        }
                    }
                }
            }
        }
        return g;
    }

    /**
     * Takes in coordinates (x,y) to id (0-99)
     * @param row
     * @param col
     * @return
     */
    public static int toVertex(int row, int col) {
        return row * SIZE + col;
    }

    /**
     * Takes in id (0-99) to coordinates (x,y)
     * @param v
     * @return
     */
    public static Position fromVertex(int v) {
        return new Position(v / SIZE, v % SIZE);
    }
}