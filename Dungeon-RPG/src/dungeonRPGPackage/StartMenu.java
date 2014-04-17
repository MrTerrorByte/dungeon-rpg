package dungeonRPGPackage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel for the RPG's start up menu
 * @author rrienton
 *
 */
@SuppressWarnings("serial")
public class StartMenu extends JPanel {
	
	public StartMenu(){
		// set layout to gridbaglayout
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		// Add welcome to game label
		JLabel welcomeLabel = new JLabel("Welcome to Dungeon RPG!");
		constraints.gridx = 1;
		constraints.gridy = 0;
		this
		this.getContentPane().add(welcomeLabel, constraints);

				// add please enter names label
				pleaseEnterNamesLabel = new JLabel("Please Enter Unique Player Names:");
				constraints.gridx = 1;
				constraints.gridy = 2;
				startFrame.getContentPane().add(pleaseEnterNamesLabel, constraints);

				// add player 1 black label
				playerOneBlackLabel = new JLabel("Player 1/Black:");
				constraints.gridx = 0;
				constraints.gridy = 3;
				startFrame.getContentPane().add(playerOneBlackLabel, constraints);

				// add player 2 white label
				playerTwoWhiteLabel = new JLabel("Player 2/White:");
				constraints.gridx = 2;
				constraints.gridy = 3;
				startFrame.getContentPane().add(playerTwoWhiteLabel, constraints);
			
				// add player 1 textfield
				playerOneNameField = new JTextField("Alice");
				constraints.gridx = 0;
				constraints.gridy = 4;
				startFrame.getContentPane().add(playerOneNameField, constraints);

				// add player 2 textfield
				playerTwoNameField = new JTextField("Bob");
				constraints.gridx = 2;
				constraints.gridy = 4;
				startFrame.getContentPane().add(playerTwoNameField, constraints);

				// add start button
				startButton = new JButton("Start Game");
				startButton.setVerticalTextPosition(AbstractButton.CENTER);
				startButton.setPreferredSize(new Dimension(100, 40));
				startButton.setActionCommand("start");
				startButton.addActionListener(this);
				constraints.gridx = 1;
				constraints.gridy = 6;
				startFrame.getContentPane().add(startButton, constraints);

				// Display the window.
				startFrame.setSize(STARTFRAMEWIDTH, STARTFRAMEWIDTH);
				// sets the window to open in the center
				startFrame.setLocationRelativeTo(null);
				startFrame.setVisible(true);
	}
}
