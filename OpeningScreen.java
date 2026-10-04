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
 * The opening screen shown when the game launches. Displays the game title,
 * rules, and a Start Game button.
 *
 * @author KatM
 */
public class OpeningScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton playButton;

	public OpeningScreen() {
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

		JLabel rules = rules();

		centerPanel.add(rules, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Holds the text for the rules of the game
	 * 
	 * @return the text centered in the label
	 */
	private JLabel rules() {
		JLabel rules = new JLabel("<html><center>Rules:<br><br>" + "Find the lost hiker before the time expires.<br>"
				+ "Every time you move, time will be consumed.<br>"
				+ "The hiker has a chance to wander when the timer goes down.<br>"
				+ "Moving through forest and mountain tiles takes longer.<br>"
				+ "You only know the hiker's last known position.<br>"
				+ "Use the Cheat button to find the quickest path.<br><br>" + "Good luck!</center></html>");
		rules.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		rules.setForeground(new Color(255, 200, 50)); 
		rules.setHorizontalAlignment(SwingConstants.CENTER);
		return rules;
	}

	/**
	 * Holds the title for the opening screen
	 * 
	 * @return the title
	 */
	private JLabel title() {
		JLabel title = new JLabel("Lost Hiker Game", SwingConstants.CENTER);
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 36));
		title.setOpaque(true);
		title.setBackground(new Color(255, 200, 50));
		title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));
		return title;
	}

	/**
	 * The start button to begin the game
	 */
	private void playButton() {
		playButton = new JButton("Start Game");
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