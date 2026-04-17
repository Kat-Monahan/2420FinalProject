package finalProject;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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

		JLabel title = new JLabel("Lost Hiker Game", SwingConstants.CENTER);
		title.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 36));
		title.setOpaque(true);
		title.setBackground(new Color(255, 200, 50));
		title.setBorder(BorderFactory.createEmptyBorder(15, 10, 15, 10));

		playButton = new JButton("Start Game");
		playButton.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 28));
		playButton.setBackground(new Color(100, 180, 80));
		playButton.setOpaque(true);
		playButton.setBorder(BorderFactory.createEmptyBorder(15, 40, 15, 40));

		JPanel centerPanel = new JPanel(new BorderLayout());
		centerPanel.setBackground(new Color(0, 128, 64));
		centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 40, 10, 40));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 128, 64));
		buttonPanel.add(playButton);

		add(title, BorderLayout.NORTH);
		add(centerPanel, BorderLayout.CENTER);

		JTextArea rules = new JTextArea();
		rules.setEditable(false);
		rules.setFont(new Font("Rockwell", Font.PLAIN, 15));
		rules.setBackground(new Color(255, 200, 50));
		rules.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1),
				BorderFactory.createEmptyBorder(15, 20, 15, 20)));
		rules.setText("Rules:\n\n" + " Find the lost hiker before the time expires.\n"
				+ "  Every time you move, time will be consumed.\n"
				+ "  The hiker has a chance to wander when the timer goes down.\n"
				+ "  Moving through forest and mountain tiles takes longer.\n"
				+ "  You only know the hiker's last known position.\n"
				+ "  Use the Cheat button to find the quickest path.\n" + " Good luck!");

		centerPanel.add(rules, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);
	}

	public JButton getPlayButton() {
		return playButton;
	}
}