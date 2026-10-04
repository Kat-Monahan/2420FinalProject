package finalProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.Graph;

/**
 * Represents the hiker who can move randomly hidden on the game graph
 * 
 * @author Bowen Berthelson
 */

public class Hiker {

	private int currentNode;     // the hidden last know position
	private int lastKnownNode;   // what the player sees on the map
	private HikerStatus status;  // are they alive or dead?
	private final Random rand = new Random();  // random value to determine a move or no move.

	/**
	 * Hiker constructor. The current and last known node will always be the same on creation. 
	 * Hiker starts off alive.
	 * @param startNode
	 */
	public Hiker(int startNode) {
		this.currentNode = startNode;
		this.lastKnownNode = startNode; 
		this.status = HikerStatus.ALIVE;
	}

	/**
	 * The hiker will try to move to a random adjacent valid tile.
	 * Call this method every time an action that takes time is performed.
	 * 
	 * @param graph
	 */
	public void attemptMove(Graph graph, double moveProbability) {
		if(status != HikerStatus.ALIVE)  // do nothing if the hiker isn't alive
			return;
		
		if(rand.nextDouble() < moveProbability) {// generate a number between 0.0 and 1.0.
			// if the number is less than the chosen moveProbability, run the movement code.
			// Get possible moves from current position (up to 4)
            Iterable<Integer> neighbors = graph.adj(currentNode);    
            
            // Convert to list so positions can be selected randomly
            List<Integer> possibleMoves = new ArrayList<>();
            for (int neigh : neighbors) {
                possibleMoves.add(neigh);
            }
            
            if (!possibleMoves.isEmpty()) { // as long as there are neighbors, pick one to move to
                // Pick one at random
                int nextNode = possibleMoves.get(rand.nextInt(possibleMoves.size()));
                currentNode = nextNode;
            }
		}
	}

	/**
     * Called when player scans at lastKnownNode but hiker is not there.
     * Reveal the new location to the player.
     */
    public void updateLastKnown() {
        lastKnownNode = currentNode;
    }
    
    //getters
	public int getCurrentNode() {
		return currentNode;
	}

	public int getLastKnownNode() {
		return lastKnownNode;
	}

	public HikerStatus getStatus() {
		return status;

	}

	//setters
	public void setStatus(HikerStatus status) {
		this.status = status;
	}

	/**
	 * The returns true or false if the hiker is located at the provided node.
	 * @param node
	 * @return
	 */
	public boolean isAt(int node) {
        return currentNode == node;
    }
}