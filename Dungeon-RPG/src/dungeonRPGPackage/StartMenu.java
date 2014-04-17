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
		this.add(welcomeLabel, constraints);

		// add please enter names label
		JLabel pleaseEnterNamesLabel = new JLabel("Please Enter Unique Player Names:");
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.add(pleaseEnterNamesLabel, constraints);

		// add player 1 black label
		JLabel playerOneBlackLabel = new JLabel("Player 1/Black:");
		constraints.gridx = 0;
		constraints.gridy = 3;
		this.add(playerOneBlackLabel, constraints);

		// add player 2 white label
		JLabel playerTwoWhiteLabel = new JLabel("Player 2/White:");
		constraints.gridx = 2;
		constraints.gridy = 3;
		this.add(playerTwoWhiteLabel, constraints);
			
		// add player 1 textfield
		JTextField playerOneNameField = new JTextField("Alice");
		constraints.gridx = 0;
		constraints.gridy = 4;
		this.add(playerOneNameField, constraints);

		// add player 2 textfield
		JTextField playerTwoNameField = new JTextField("Bob");
		constraints.gridx = 2;
		constraints.gridy = 4;
		this.add(playerTwoNameField, constraints);

		// add start button
		JButton startButton = new JButton("Start Game");
		startButton.setVerticalTextPosition(AbstractButton.CENTER);
		startButton.setPreferredSize(new Dimension(100, 40));
		startButton.setActionCommand("start");
		//startButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 6;
		this.add(startButton, constraints);

		// Display the window.
		this.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		this.setVisible(true);
	}
}
