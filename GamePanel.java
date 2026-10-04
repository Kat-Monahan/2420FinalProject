package finalProject;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * Displays the 10x10 game map as a grid of JButtons. Uses terrain data loaded
 * from the map file.
 * 
 * @author Bowen Berthelson
 * @author KatM
 */
public class GamePanel extends JPanel {

	// Fields
	private static final long serialVersionUID = 1L;
	private final int rows = 10;
	private final int cols = 10;
	private JButton[][] buttons;
	private final Game game;

	private List<Position> currentCheatPath = null;
	private JButton currentPlayerButton; // for highlighting player

	/**
	 * GamePanel constructor. receives the Game object which contains loaded terrain
	 * data.
	 */
	public GamePanel(Game game) {
		this.game = game;
		buttons = new JButton[rows][cols];
		setLayout(new java.awt.GridLayout(rows, cols));

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				JButton cell = new JButton();
				buttons[r][c] = cell;
				cell.setBorder(BorderFactory.createEtchedBorder()); // BorderFactory witchcraft. It works... Somehow?
				cell.setCursor(new Cursor(Cursor.HAND_CURSOR));
				cell.setFocusPainted(false);

				final int row = r;
				final int col = c;

				cell.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						handleMove(row, col); // Every button can have functionality
					}
				});
				add(cell);
			}
		}
	}

	/**
	 * Paints the map using terrain data from the loaded GameConfig.
	 */
	public void paintMap() {
		Terrain[][] terrainMap = game.getTerrainMap(); // Now loaded from file

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				Terrain t = terrainMap[r][c];
				buttons[r][c].setBackground(t.getColor());
				buttons[r][c].setText(""); // clear any "?" or text
				buttons[r][c].setForeground(Color.BLACK);
			}
		}

		refreshMarkers(); // add player and hiker visuals
	}

	/**
	 * Updates player position and sets icons for tiles
	 */
	public void refreshMarkers() {
		// Clear previous highlights
		if (currentPlayerButton != null) {
			currentPlayerButton.setIcon(null);
			currentPlayerButton.setBorder(BorderFactory.createEtchedBorder());
		}

		// Player tile with icon
		int playerNode = game.getTeamNode();
		Position playerPos = MapLoader.fromVertex(playerNode);
		if (playerPos != null) {
			currentPlayerButton = buttons[playerPos.getRow()][playerPos.getCol()];
			currentPlayerButton.setBorder(BorderFactory.createLineBorder(new Color(255, 215, 0), 5));
			setTileIcon(currentPlayerButton, "src/Resources/sarTeam.png");

		}
		// Hiker last known position tile with icon
		int hikerNode = game.getHikerLastKnown();
		Position hikerPos = MapLoader.fromVertex(hikerNode);
		if (hikerPos != null) {
			setTileIcon(buttons[hikerPos.getRow()][hikerPos.getCol()], "src/Resources/lostHiker.png");
		}
	}

	/**
	 * Loads, scales, and sets an icon on the given button.
	 * 
	 * @param button    the tile button to update
	 * @param imagePath the path to the image resource
	 */
	private void setTileIcon(JButton button, String imagePath) {
		Image image = new ImageIcon(imagePath).getImage();
		Image scaled = image.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		button.setIcon(new ImageIcon(scaled));
	}

	/**
	 * Highlights the cheat path (bright green borders). Stores the path for
	 * clearing later.
	 */
	public void highlightCheatPath(List<Position> path) {
		clearCheatPath(); // clear any old path first

		if (path == null || path.isEmpty())
			return; // SARTeam on top of Hiker

		this.currentCheatPath = new ArrayList<>(path); // store copy

		for (Position p : path) {
			JButton btn = buttons[p.getRow()][p.getCol()];
			btn.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 6)); // bright green
		}
	}

	/**
	 * Clears any currently highlighted cheat path.
	 */
	public void clearCheatPath() {
		if (currentCheatPath == null)
			return;

		for (Position p : currentCheatPath) {
			JButton btn = buttons[p.getRow()][p.getCol()];
			btn.setBorder(BorderFactory.createEtchedBorder()); // reset to normal
		}
		currentCheatPath = null;
	}

	/**
	 * Full refresh after a move or scan.
	 */
	public void refresh() {
		checkGameOver();
		clearCheatPath();
		paintMap();
		updateTimer();
	}

	/**
	 * Handle button click for attempted move.
	 */
	private void handleMove(int r, int c) {
		if (game.isGameOver()) {
			return;
		}
		Terrain terrain = game.getTerrainMap()[r][c];
		if (!terrain.isPassable())
			return; // no move because river

		int targetNode = MapLoader.toVertex(r, c); // from coordinates (x,y) to id (0-99)
		int cost = terrain.getTimeSpent();

		boolean moved = game.performMove(targetNode, cost);

		if (moved) {
			refresh(); // This will clear the cheat path
		}
	}

	/**
	 * Update the timer in the ControlPanel.
	 */
	private void updateTimer() {
		java.awt.Window window = SwingUtilities.getWindowAncestor(this);
		if (window instanceof GameWindow) {
			GameWindow gw = (GameWindow) window;
			ControlPanel cp = gw.getControlPanel();
			if (cp != null) {
				cp.updateTime(game.getTimeRemaining());
			}
		}
	}

	// returns all buttons
	public JButton[][] getButtons() {
		return buttons;
	}

	public void checkGameOver() {
		if (game.isGameOver()) {
			GameWindow window = (GameWindow) SwingUtilities.getWindowAncestor(this);
			if (window != null) {
				window.showScreen(game.hasWon() ? "WIN" : "LOSE");
			}
		}
	}
}