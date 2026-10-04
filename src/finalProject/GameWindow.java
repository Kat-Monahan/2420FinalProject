package finalProject;

import java.awt.CardLayout;
import java.awt.EventQueue;
import java.io.IOException;

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
	private Game game;

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
	 * Sets up the window with an opening screen and the game view. The Play button
	 * goes to the game play
	 */
	public GameWindow() {

		try {
			game = new Game("src/Resources/map.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 800);
		setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);

		OpeningScreen openingScreen = new OpeningScreen();
		WinScreen winScreen = new WinScreen();
		LoseScreen loseScreen = new LoseScreen();

		JPanel gameView = new JPanel(new java.awt.BorderLayout(10, 10));
		controlPanel = new ControlPanel(game);
		gamePanel = new GamePanel(game);
		gameView.add(controlPanel, java.awt.BorderLayout.WEST);
		gameView.add(gamePanel, java.awt.BorderLayout.CENTER);

		cardPanel.add(openingScreen, "OPENING");
		cardPanel.add(gameView, "GAME");
		cardPanel.add(winScreen, "WIN");
		cardPanel.add(loseScreen, "LOSE");

		add(cardPanel);

		openingScreen.getPlayButton().addActionListener(e -> {
			gamePanel.paintMap();
			cardLayout.show(cardPanel, "GAME");
		});

		winScreen.getPlayButton().addActionListener(e -> {
			gamePanel.paintMap();
			cardLayout.show(cardPanel, "OPENING");
			
		});
		
		loseScreen.getPlayButton().addActionListener(e -> {
			gamePanel.paintMap();
			cardLayout.show(cardPanel, "OPENING");
		});


	}

	public GamePanel getGamePanel() {
		return gamePanel;
	}

	public ControlPanel getControlPanel() {
		return controlPanel;
	}

	public void showScreen(String card) {
		cardLayout.show(cardPanel, card);
	}
}