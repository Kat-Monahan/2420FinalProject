package finalProject;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import static finalProject.Terrain.*;

/**
 * Displays the 10x10 game map as a grid of JButtons. Each button represents a
 * terrain tile.
 *
 * @author KatM
 */
public class GamePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private int rows;
	private int cols;
	private JButton[][] buttons;

	/**
	 * Builds the grid of JButtons for the map.
	 *
	 * @param rows number of rows in the grid
	 * @param cols number of columns in the grid
	 */
	public GamePanel(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		buttons = new JButton[rows][cols];
		setLayout(new GridLayout(rows, cols));

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JButton cell = new JButton();
				buttons[r][c] = cell;
				add(cell);
			}
		}
	}

	/**
	 * Hardcoded terrain layout for the 10x10 map
	 */
	private static final Terrain[][] TERRAIN_MAP = {
			{ MOUNTAIN, MEADOW, MEADOW, MEADOW, MEADOW, RIVER, MEADOW, MEADOW, MOUNTAIN, MOUNTAIN },
			{ MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, RIVER, MEADOW, MOUNTAIN, MOUNTAIN, MEADOW },
			{ MEADOW, MEADOW, FOREST, FOREST, MEADOW, RIVER, MEADOW, MOUNTAIN, MEADOW, MEADOW },
			{ MEADOW, MEADOW, FOREST, FOREST, MEADOW, RIVER, RIVER, MEADOW, MEADOW, MEADOW },
			{ MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, RIVER, MEADOW, FOREST, FOREST },
			{ MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, BRIDGE, MEADOW, FOREST, FOREST },
			{ MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, RIVER, RIVER, MEADOW, MEADOW, MEADOW },
			{ MEADOW, MOUNTAIN, MOUNTAIN, MEADOW, MEADOW, RIVER, MEADOW, MEADOW, MEADOW, MEADOW },
			{ MEADOW, MOUNTAIN, MEADOW, MEADOW, MEADOW, RIVER, MEADOW, MEADOW, FOREST, FOREST },
			{ MEADOW, MEADOW, MEADOW, MEADOW, MEADOW, RIVER, MEADOW, MEADOW, FOREST, FOREST } };

	/**
	 * Colors each button based on its terrain type in the TERRAIN_MAP
	 */
	public void paintMap() {
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				buttons[r][c].setBackground(TERRAIN_MAP[r][c].getColor());
			}
		}
	}
}