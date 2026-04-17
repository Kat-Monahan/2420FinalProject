package finalProject;

import java.awt.CardLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main window for the Lost Hiker SAR game Has an opening screen and then the
 * game play visuals Contains the GamePanel (map) and ControlPanel
 * (buttons/timer).
 *
 * @author KatM
 */

public class GameWindow extends JFrame {
	private static final long serialVersionUID = 1L;
	private GamePanel gamePanel;
	private ControlPanel controlPanel;
	private CardLayout cardLayout;
	private JPanel cardPanel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow frame = new GameWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Sets up the window with an opening screen and the game view.
	 * The Play button goes to the game play 
	 */
	public GameWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		OpeningScreen openingScreen = new OpeningScreen();

		JPanel gameView = new JPanel(new java.awt.BorderLayout(10, 10));
		controlPanel = new ControlPanel();
		gamePanel = new GamePanel(10, 10);
		gameView.add(controlPanel, java.awt.BorderLayout.WEST);
		gameView.add(gamePanel, java.awt.BorderLayout.CENTER);

		cardPanel.add(openingScreen, "OPENING");
		cardPanel.add(gameView, "GAME");

		add(cardPanel);

		openingScreen.getPlayButton().addActionListener(e -> {
			gamePanel.paintMap();
			cardLayout.show(cardPanel, "GAME");
		});
	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}
}