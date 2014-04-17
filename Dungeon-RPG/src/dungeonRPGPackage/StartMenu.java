package dungeonRPGPackage;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Panel for the RPG's start up menu
 * @author rrienton
 *
 */
@SuppressWarnings("serial")
public class StartMenu extends JPanel implements ActionListener {
	private JLabel heroImageLabel;
	private ImageIcon maleIcon, femaleIcon;
	
	
	public StartMenu(){
		// set layout to gridbaglayout
		this.setLayout(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();

		// Add welcome to game label
		JLabel welcomeLabel = new JLabel("Welcome to Dungeon RPG!");
		constraints.gridx = 1;
		constraints.gridy = 0;
		this.add(welcomeLabel, constraints);

		// add choose your hero label
		JLabel chooseHeroLabel = new JLabel("Choose Your Hero!");
		constraints.gridx = 1;
		constraints.gridy = 2;
		this.add(chooseHeroLabel, constraints);
		
		
		BufferedImage heroImage = null;
		try {
            heroImage = ImageIO.read(new File("src/images/maleFrontStanding.png"));
        } catch (IOException e) {
        	System.out.println("Hero image Doesnt exist");
        }
		maleIcon = new ImageIcon(heroImage);
		
		// add hero image label
		heroImageLabel = new JLabel(maleIcon);
		constraints.gridx = 1;
		constraints.gridy = 5;
		this.add(heroImageLabel, constraints);

		try {
            heroImage = ImageIO.read(new File("src/images/femaleFrontStanding.png"));
        } catch (IOException e) {
        	System.out.println("Hero image Doesnt exist");
        }
		femaleIcon = new ImageIcon(heroImage);
			
		// add player 1 textfield
		JTextField playerOneNameField = new JTextField("Jordan");
		constraints.gridx = 1;
		constraints.gridy = 15;
		this.add(playerOneNameField, constraints);

		//radio buttons
		JRadioButton maleButton = new JRadioButton("Male");
	    maleButton.setActionCommand("Male");
	    maleButton.setSelected(true);

	    JRadioButton femaleButton = new JRadioButton("Female");
	    femaleButton.setActionCommand("Female");
		
	    ButtonGroup heroButtons = new ButtonGroup();
	    heroButtons.add(maleButton);
	    heroButtons.add(femaleButton);
	    
	    maleButton.addActionListener(this);
	    femaleButton.addActionListener(this);
	    constraints.gridx = 0;
	    constraints.gridy = 8;
	    this.add(maleButton, constraints);
	    constraints.gridx = 2;
	    constraints.gridy = 8;
	    this.add(femaleButton, constraints);
	    
		// add start button
		JButton startButton = new JButton("Start Game");
		startButton.setVerticalTextPosition(AbstractButton.CENTER);
		startButton.setPreferredSize(new Dimension(100, 40));
		startButton.setActionCommand("start");
		startButton.addActionListener(this);
		constraints.gridx = 1;
		constraints.gridy = 20;
		this.add(startButton, constraints);

		// Display the window.
		this.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// pressed start button
		if ("start".equals(event.getActionCommand())) {
			// turn start menu invisible
			this.setVisible(false);
		}
		//radio buttons for choosing Hero
		if("Male".equals(event.getActionCommand())) {
			heroImageLabel.setIcon(maleIcon);
		}
		if("Female".equals(event.getActionCommand())) {
			heroImageLabel.setIcon(femaleIcon);
		}
	}
}
