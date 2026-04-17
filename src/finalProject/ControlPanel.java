package finalProject;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.EtchedBorder;
import java.awt.Font;

/**
 * Side panel for buttons Has a timer, cheat code button and move history button
 * 
 * @author KatM
 */

public class ControlPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton cheatCodeButton;
	private JButton moveHistoryButton;
	private JLabel timerLabel;

	/**
	 * Create the panel.
	 */
	public ControlPanel() {
		setBackground(new Color(0, 128, 0));
		setLayout(new BorderLayout());
		setPreferredSize(new Dimension(150, 0));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setBackground(new Color(0, 128, 0));
		buttonPanel.setLayout(new GridLayout(4, 1, 5, 5));

		timerLabel = new JLabel("Time: ");
		timerLabel.setForeground(new Color(255, 0, 0));
		timerLabel.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 18));
		timerLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		timerLabel.setBackground(new Color(255, 200, 50));
		cheatCodeButton = new JButton("Cheat Code");
		cheatCodeButton.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 12));
		cheatCodeButton.setBackground(new Color(255, 200, 50));
		moveHistoryButton = new JButton("Move History");
		moveHistoryButton.setFont(new Font("Rockwell Extra Bold", Font.PLAIN, 12));
		moveHistoryButton.setBackground(new Color(255, 200, 50));

		buttonPanel.add(cheatCodeButton);
		buttonPanel.add(moveHistoryButton);

		add(buttonPanel, BorderLayout.SOUTH);

		timerLabel.setOpaque(true);
		add(timerLabel, BorderLayout.NORTH);
	}

	/**
	 * Updates the timer label to display the current time left
	 * 
	 * @param time the current time left
	 */
	public void updateTime(int time) {
		timerLabel.setText("Time: " + time);
	}

}
