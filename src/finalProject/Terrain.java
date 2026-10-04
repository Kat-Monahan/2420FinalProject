package finalProject;

import java.awt.Color;

/**
 * Enum representing the different terrain types a node can have on the game
 * map. Each different terrain has it's own color. Each terrain type has a
 * specific time cost
 * 
 * @author KatM
 * @author Bowen Berthelson
 */

public enum Terrain {

    MEADOW(5, new Color(126, 185, 124)),
    FOREST(10, new Color(0, 100, 0)),
    MOUNTAIN(15, new Color(112, 128, 144)),
    RIVER(-1, new Color(20, 25, 83)),     // -1 = impassable. Bowen was here
    BRIDGE(5, new Color(101, 67, 33));

    private final int timeSpent;
    private final Color color;

    Terrain(int timeSpent, Color color) {
        this.timeSpent = timeSpent;
        this.color = color;
    }

    public int getTimeSpent() {
        return timeSpent;
    }

    public Color getColor() {
        return color;
    }

    /** Returns true if a player can move onto this tile */
    public boolean isPassable() {
        return timeSpent >= 0;
    }
}