package finalProject;

import java.util.Set;
import edu.princeton.cs.algs4.Graph;

/**
 * Holds all data loaded from the map config file.
 * Now includes the full 10x10 terrain map for colors and movement costs.
 * 
 * @author Bowen Berthelson
 */
public class GameConfig {

	// Fields
    public final Graph graph;
    public final Position playerStart;
    public final Position hikerLastKnown;
    public final Set<Position> blocked;
    public final Terrain[][] terrainMap;           
    public final int timeLimit;
    public final double hikerMoveProbability;

    /**
     * Constructor
     */
    public GameConfig(Graph graph, 
                      Position playerStart, 
                      Position hikerLastKnown,
                      Set<Position> blocked,
                      Terrain[][] terrainMap,
                      int timeLimit,
                      double hikerMoveProbability) {

        this.graph = graph;
        this.playerStart = playerStart;
        this.hikerLastKnown = hikerLastKnown;
        this.blocked = blocked;
        this.terrainMap = terrainMap;
        this.timeLimit = timeLimit;
        this.hikerMoveProbability = hikerMoveProbability;
    }

    // Getters
    public Graph getGraph() {
        return graph;
    }

    public Position getPlayerStart() {
        return playerStart;
    }

    public Position getHikerLastKnown() {
        return hikerLastKnown;
    }

    public Set<Position> getBlocked() {
        return blocked;
    }

    public Terrain[][] getTerrainMap() {
        return terrainMap;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public double getHikerMoveProbability() {
        return hikerMoveProbability;
    }
}