package finalProject;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

/**
 * Represents the Search and Rescue team (the player).
 * Tracks position, move history, and remaining time.
 * 
 *  @author Bowen Bertheson
 */
public class SARTeam {

    private int currentNode;
    private final Queue<Integer> moveHistory;   // queue of previous positions (or moves)
    private int timeRemaining;
    private boolean pathRevealed;   // for cheat code feature

    /**
     * SARTeam constructor.
     * 
     * @param startNode
     * @param timeLimit
     */
    public SARTeam(int startNode, int timeLimit) {
        this.currentNode = startNode;
        this.timeRemaining = timeLimit;
        this.moveHistory = new Queue<>();
        this.pathRevealed = false;
    }

    /**
     * Move to a new node if it is a valid neighbor.
     * Returns true if move succeeded.
     */
    public boolean moveTo(int targetNode, Graph graph, int cost) {
    	if (timeRemaining - cost < 0) return false;

        // Check if target is a valid neighbor
        boolean isNeighbor = false;
        for (int neigh : graph.adj(currentNode)) {
            if (neigh == targetNode) {
                isNeighbor = true;
                break;
            }
        }

        if (!isNeighbor) {
            return false;   // invalid move
        }

        // Record the move (you can store from-node or just the new position)
        moveHistory.enqueue(currentNode);   // or enqueue targetNode

        currentNode = targetNode;
        timeRemaining -= cost;  

        return true;
    }

    // Simple overload to move without extra cost parameter
    /**
     * Overload moveTo method with default cost.
     * @param targetNode
     * @param graph
     * @return
     */
    public boolean moveTo(int targetNode, Graph graph) {
    	if (timeRemaining < 0) return false;
        return moveTo(targetNode, graph, 1);   // default cost = 1 move
    }

    // Getters
    public int getCurrentNode() {
        return currentNode;
    }

    public Queue<Integer> getMoveHistory() {
        return moveHistory;
    }

    public int getTimeRemaining() {
        return timeRemaining;
    }

    public boolean isPathRevealed() {
        return pathRevealed;
    }

    // Setter
    public void setPathRevealed(boolean revealed) {
        this.pathRevealed = revealed;
    }

    /**
     * Checks if the time is up.
     * @return
     */
    public boolean isTimeUp() {
        return timeRemaining <= 0;
    }
}