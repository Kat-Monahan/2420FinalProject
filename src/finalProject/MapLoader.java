package finalProject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import edu.princeton.cs.algs4.Graph;

/**
 * Reads in a file to create the graph
 *
 * @author Bowen Berthelson
 */
public class MapLoader {

	private static final int SIZE = 10;

	/**
	 * Takes in a file to determine player starting location, hiker starting
	 * location, time limit hike move probability.
	 *
	 * @author Bowen Berthelson
	 *
	 * @param filename
	 * @return GameConfig record
	 * @throws IOException
	 */
	public static GameConfig load(String filename) throws IOException {
		Position playerStart = null; // player starting location
		Position hikerLastKnown = null; // hiker starting location
		Set<Position> blocked = new HashSet<>(); // can we use HashSet? if not, 2d boolean array works.
		double hikerMoveProb = 0.25; // chance the hiker will move each time
		int timeLimit = 100; // time limit before game over

// create new FileReader
		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = br.readLine()) != null) { // reads in the line
				line = line.trim();

// Skip empty lines and comments
				if (line.isEmpty() || line.startsWith("#")) {
					continue;
				}

// Parse player start: "0 0"
				if (playerStart == null) {
					String[] parts = line.split("\\s+"); // splits the white space [0, 0]
					if (parts.length >= 2) {
						int r = Integer.parseInt(parts[0]);
						int c = Integer.parseInt(parts[1]);
						playerStart = new Position(r, c); // (0, 0)
						continue;
					}
				}

// Parse blocked cells:
				String[] parts = line.split("\\s+");
				if (parts.length >= 2) {
					int r = Integer.parseInt(parts[0]);
					int c = Integer.parseInt(parts[1]);
					blocked.add(new Position(r, c)); // HashSet or 2d boolean array holds the blocked cells now.
				}
			}
		}

// Build the graph with only walkable edges
		Graph graph = buildGraph(blocked);

		return new GameConfig(graph, playerStart, blocked, timeLimit, hikerMoveProb);
// a GameConfig class or record can be returned. could be useful info to pass to the class using Swing
	}

	/**
	 * Creates edges only between unblocked neighbors.
	 *
	 * @author Bowen Berthelson
	 */
	private static Graph buildGraph(Set<Position> blocked) {
		Graph g = new Graph(SIZE * SIZE);

		int[] dRow = { -1, 0, 1, 0 }; // up, right, down, left
		int[] dCol = { 0, 1, 0, -1 };

		for (int r = 0; r < SIZE; r++) { // go through all 100 nodes
			for (int c = 0; c < SIZE; c++) {
				Position p = new Position(r, c);
				if (blocked.contains(p))
					continue; // skip blocked cells entirely. we don't want any edges here.

				int v = toVertex(r, c); // v stores the coordinates as a number that Graph will recognize.

				for (int d = 0; d < 4; d++) { // check the 4 possible directions
					int nr = r + dRow[d];
					int nc = c + dCol[d];

					if (nr >= 0 && nr < SIZE && nc >= 0 && nc < SIZE) { // makes sure to stay in bounds
						Position neighbor = new Position(nr, nc);
						if (!blocked.contains(neighbor)) { // if the neighbor vertex is unblocked
							int w = toVertex(nr, nc); // grab it's "address"
							g.addEdge(v, w); // undirected edge is placed into the graph
						}
					}
				}
			}
		}
		return g;
	}

	/**
	 * Transforms coordinates into a single int that Graph can use.
	 *
	 * @author Bowen Berthelson
	 * @param row
	 * @param col
	 * @return
	 */
	private static int toVertex(int row, int col) {
		return row * SIZE + col; // 0 to 99
	}

// Optional helper
	public static Position fromVertex(int v) {
		return new Position(v / SIZE, v % SIZE);
	}
}