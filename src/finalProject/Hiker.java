package finalProject;

/**
 * Represents the hiker who can move randomly hidden on the game graph
 */

public class Hiker {

	private int currentNode;
	private int lastKnownNode;
	private HikerStatus status;

	public Hiker(int currentNode) {
		this.currentNode = currentNode;
		this.lastKnownNode = currentNode; 
		this.status = HikerStatus.ALIVE;
	}

	public void moveRandom(GameGraph graph) {
		// TODO

	}

	public int getCurrentNode() {
		return currentNode;
	}

	public int getLastKnownNode() {
		return lastKnownNode;
	}

	public HikerStatus getStatus() {
		return status;

	}

	public void setStatus(HikerStatus status) {
		this.status = status;
	}

}
