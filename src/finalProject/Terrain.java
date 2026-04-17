package finalProject;

import java.awt.Color;

/**
 * Enum representing the different terrain types a node can have on the game
 * map. Each different terrain has it's own color. Each terrain type has a
 * specific time cost
 */

public enum Terrain {

	MEADOW(0, new Color(126, 185, 124)), FOREST(0, new Color(0, 100, 0)), MOUNTAIN(0, new Color(112, 128, 144)),
	RIVER(0, new Color(20, 25, 83)), BRIDGE(0, new Color(101, 67, 33));

	private final int timeSpent;
	private Color color;

	private Terrain(int timeSpent, Color color) {
		this.timeSpent = timeSpent;
		this.color = color;
	}

	/**
	 * 
	 * @return the time it costs to use this tile
	 */
	public int getTimeSpent() {
		return timeSpent;
	}

	/**
	 * 
	 * @return color of the terrain tile
	 */
	Color getColor() {
		return color;
	}

}
