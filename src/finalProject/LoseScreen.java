package finalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * The death screen shown when the game ends after failing to find hiker. Displays the game title,
 * message, and a Start Again? button.
 *
 * @author KatM
 */
public class LoseScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton playButton;

	public LoseScreen() {
		setLayout(new BorderLayout(10, 10));
		setBackground(new Color(0, 128, 64));

		JLabel title = title();

		playButton();

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(0, 128, 64));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 128, 64));
		buttonPanel.add(playButton);

		add(title, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);

		JLabel warningMessage = warningMessage();

		centerPanel.add(warningMessage, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Holds the text for the rules of the game
	 * 
	 * @return the text centered in the label
	 */
	private JLabel warningMessage() {
		JLabel message = new JLabel("<html><center>YOU LOSE<br><br>" + "You did not find the hiker in time <br>"
				+ "Remember, tell a friend where you are hiking if going it alone <br>"
				+ "And what time you expect to return.<br>"
				+ "Check the weather and dress accordingly.<br>"
				+ "Charge your phone.<br>"
				+ "And don't go if you don't know.<br><br>" + "</center></html>");
		message.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		message.setForeground(new Color(255, 200, 50)); 
		message.setHorizontalAlignment(SwingConstants.CENTER);
		return message;
	}

	/**
	 * Holds the title for the end screen
	 * 
	 * @return the title
	 */
	private JLabel title() {
		JLabel title = new JLabel("HIKER PERISHED", SwingConstants.CENTER);
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 36));
		title.setOpaque(true);
		title.setBackground(new Color(255, 200, 50));
		title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
		return title;
	}

	/**
	 * The start button to restart the game
	 */
	private void playButton() {
		playButton = new JButton("Start Again?");
		playButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		playButton.setBackground(new Color(100, 180, 80));
		playButton.setOpaque(true);
		playButton.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));
	}

	/**
	 * Gets the playButton
	 * @return the button to begin the game
	 */
	public JButton getPlayButton() {
		return playButton;
	}
}