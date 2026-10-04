package finalProject;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.util.List;

/**
 * Side panel for buttons: Timer, Scan, Cheat Code, and Move History.
 * 
 * @author Bowen Berthelson
 * @author KatM
 */
public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private final JButton scanButton;
	private final JButton cheatCodeButton;
	private final JButton moveHistoryButton;
	private final JLabel timerLabel;
	private final Game game;
	private final JPanel buttonPanel;

	/**
	 * Sets up the control panel and wires all button actions.
	 * 
	 * @param game the current game instance
	 */
	public ControlPanel(Game game) {
		this.game = game;

		setBackground(new Color(0, 128, 0));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(180, 0));

		timerLabel = makeTimerLabel();
		buttonPanel = makeButtonPanel();
		scanButton = makeScanButton();
		cheatCodeButton = makeCheatButton();
		moveHistoryButton = makeMoveHistoryButton();

		buttonPanel.add(scanButton);
		buttonPanel.add(cheatCodeButton);
		buttonPanel.add(moveHistoryButton);

		add(timerLabel, BorderLayout.NORTH);
		add(buttonPanel, BorderLayout.CENTER);

		wireScanButton();
		wireCheatButton();
		wireMoveHistoryButton();
	}

	/**
	 * Wires up the scan button 
	 */
	private void wireScanButton() {
		scanButton.addActionListener(e -> performScan());
	}

	/**
	 * Wires up the cheat code button
	 */
	private void wireCheatButton() {
		cheatCodeButton.addActionListener(e -> {
			if (game == null)
				return;
			List<Position> path = game.getCheatPath();
			if (path.isEmpty()) {
				JOptionPane.showMessageDialog(this, "No path to hiker!", "Cheat Code", JOptionPane.WARNING_MESSAGE);
			} else {
				GameWindow window = (GameWindow) SwingUtilities.getWindowAncestor(this);
				if (window != null && window.getGamePanel() != null) {
					window.getGamePanel().highlightCheatPath(path);
				}
			}
		});
	}

	/**
	 * Wires up the move history button 
	 */
	private void wireMoveHistoryButton() {
		moveHistoryButton.addActionListener(e -> {
			if (game != null) {
				game.printMoveHistory();
			}
		});
	}

	/**
	 * Calls scan and refreshes the map
	 */
	private void performScan() {
		if (game == null)
			return;
		game.performScan();
		GameWindow window = (GameWindow) SwingUtilities.getWindowAncestor(this);
		if (window != null && window.getGamePanel() != null) {
			window.getGamePanel().refresh();
		}
	}

	/**
	 * Updates the timer label
	 */
	public void updateTime(int time) {
		timerLabel.setText("Time: " + time);
	}

	/**
	 * Turns off scan button when game is over
	 * 
	 * @param enabled
	 */
	public void setScanEnabled(boolean enabled) {
		scanButton.setEnabled(enabled);
	}

	/**
	 * Creates the label with starting time for the game clock.
	 * 
	 * @return timer label
	 */
	private JLabel makeTimerLabel() {
		JLabel label = new JLabel("Time: 160");
		label.setForeground(new Color(255, 0, 0));
		label.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 18));
		label.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		label.setBackground(new Color(255, 200, 50));
		label.setOpaque(true);
		return label;
	}

	/**
	 * Creates the panel that holds the buttons.
	 * 
	 * @return the button panel
	 */
	private JPanel makeButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 128, 0));
		buttonPanel.setLayout(new GridLayout(4, 1, 5, 8));
		return buttonPanel;
	}

	/**
	 * Creates the scan button.
	 * 
	 * @return the scan button
	 */
	private JButton makeScanButton() {
		JButton scanButton = new JButton("SCAN HERE");
		scanButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 14));
		scanButton.setBackground(new Color(255, 140, 0));
		scanButton.setForeground(Color.BLACK);
		return scanButton;
	}

	/**
	 * Creates the cheat code button.
	 * 
	 * @return the cheat code button
	 */
	private JButton makeCheatButton() {
		JButton button = new JButton("Cheat Code");
		button.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 12));
		button.setBackground(new Color(255, 200, 50));
		return button;
	}

	/**
	 * Creates the move history button.
	 * 
	 * @return the move history button
	 */
	private JButton makeMoveHistoryButton() {
		JButton button = new JButton("Move History");
		button.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 12));
		button.setBackground(new Color(255, 200, 50));
		return button;
	}
}