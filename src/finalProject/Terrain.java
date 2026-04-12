package finalProject;

/**
 * Enum representing the different terrain types a node can have on the game
 * map. Each terrain type has a specific time cost
 */

public enum Terrain {

	MEADOW(0), FOREST(0), MOUNTAIN(0), RIVER(0), BRIDGE(0);

	private final int timeSpent;

	private Terrain(int timeSpent) {
		this.timeSpent = timeSpent;
	}

	/**
	 * 
	 * @return the time it costs to use this tile
	 */
	public int getTimeSpent() {
		return timeSpent;
	}

}
