package finalProject;

import edu.princeton.cs.algs4.Queue;

public class SARTeam {

	private int currentNode;
	private Queue<Integer> moveHistory;
	private int timeRemaining;
	private boolean pathRevealed;

	public SARTeam(int startNode, int timeLimit) {
		this.currentNode = startNode;
		this.timeRemaining = timeLimit;
		this.moveHistory = new Queue<>();
		this.pathRevealed = false;
	}

	public void moveTo(int nodeIndex, int cost) {
		moveHistory.enqueue(currentNode);
		currentNode = nodeIndex;
		timeRemaining -= cost;
	}

	public int getCurrentNode() {
		return currentNode;
	}

	public Queue<Integer> getMoveHistory() {
		return moveHistory;
	}

	public int getTimeRemaining() {
		return timeRemaining;
	}

	public boolean isRevealed() {
		return pathRevealed;

	}
}
