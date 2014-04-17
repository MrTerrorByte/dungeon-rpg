package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 * Main Game Loop
 * @author rrienton
 *
 */
public class GameController implements KeyListener{
	private Hero hero;
	private int tileSize = Map.FRAMEWIDTH/Map.ARRAYSIZE;
	private static JFrame dungeonFrame = new JFrame("Dungeon RPG");
    private static DungeonPanel dungeonPanel;
    private BufferedImage heroImage, grassImage, caveImage, floorImage;
    
    /**
     * Constructs a GameController, which means the JFrame that displays the Map and Hero.
     */
    public GameController()
    {   
    	Weapon weapon = new Weapon("Sword","Sword",50,10);
    	Shield shield = new Shield("Shield","Shield",100,10);
    	hero = new Hero(null, false, weapon, shield);
    	
        grassImage = null;
        caveImage = null;
        floorImage = null;
        try {
            grassImage = ImageIO.read(new File("src/images/grass.png"));
        } catch (IOException e) {
        	System.out.println("Grass image Doesnt exist");
        }
        try {
            floorImage = ImageIO.read(new File("src/images/floor.png"));
        } catch (IOException e) {
        	System.out.println("Floor image Doesnt exist");
        }
        try {
            caveImage = ImageIO.read(new File("src/images/cave.png"));
        } catch (IOException e) {
        	System.out.println("Cave image Doesnt exist");
        }

		dungeonFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

        StartMenu startMenu = new StartMenu();
        dungeonFrame.add(startMenu);
		
        dungeonFrame.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		dungeonFrame.setResizable(false);
		dungeonFrame.setVisible(true);
        dungeonFrame.validate();
        
    	dungeonPanel = new DungeonPanel();
        dungeonPanel.addKeyListener(this);
        dungeonPanel.setBounds(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
        dungeonFrame.add(dungeonPanel);
    }
    
    public static JFrame getDungeonFrame(){
    	return dungeonFrame;
    }
    
    public static DungeonPanel getDungeonPanel(){
    	return dungeonPanel;
    }
    
    public static void main(String[]args)
    {
        new GameController();
    }

    public void addBattlePanel(){
    	Monster monster = new Monster("Reaper", hero.getMapIndex()-1, null);
    	try {
			monster.setImage(ImageIO.read(new File("src/images/death_scythe_front.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	dungeonFrame.remove(dungeonPanel);
    	BattlePanel battlePanel = new BattlePanel(hero, monster);
    	dungeonFrame.add(battlePanel);
    	dungeonFrame.validate();
    }
    
	@Override
	/**
	 * Handles movement when arrow keys are pressed and when the escape button is pressed
	 * @param arg0 KeyEvent
	 */
	public void keyPressed(KeyEvent arg0) {
		//press up key
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			if(hero.move(hero.getX(), hero.getY()-1) == 1){
				addBattlePanel();
			}
			dungeonPanel.repaint();
		}
		//press down key
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(hero.move(hero.getX(), hero.getY()+1) == 1){
				addBattlePanel();
			}
			dungeonPanel.repaint();
		}
		//press left key
		else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			if(hero.move(hero.getX()-1, hero.getY()) == 1){
				addBattlePanel();
			}
			dungeonPanel.repaint();
		}
		//press right key
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			if(hero.move(hero.getX()+1, hero.getY()) == 1){
				addBattlePanel();
			}
			dungeonPanel.repaint();
		}
		//press esc
		else if(arg0.getKeyCode() == KeyEvent.VK_ESCAPE){
			System.exit(0);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("serial")
	public class DungeonPanel extends JPanel{
		@Override
		public void paint(Graphics g){
	        this.requestFocus();
            Graphics2D tileGraphics = (Graphics2D) g;
            
            for (int row = 0; row < Map.ARRAYSIZE; row++) {
                for (int col = 0; col < Map.ARRAYSIZE; col++) {
                    switch (hero.getMap().getTileArray()[row][col]) {
                        case GRASS:
                            tileGraphics.drawImage(grassImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                            break;
                        case ROCK:
                        	tileGraphics.drawImage(caveImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        	break;
                        case FLOOR:
                        	tileGraphics.drawImage(floorImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        	break;
                        case NONE:
                        	tileGraphics.setColor(Color.BLACK);
                        	tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
                        	break;
                    }
                }
            }
			tileGraphics.drawImage(heroImage, hero.getX()*tileSize, hero.getY()*tileSize, null);
            
		}
	}
	
	/**
	 * Panel for the RPG's start up menu
	 * @author rrienton
	 *
	 */
	@SuppressWarnings("serial")
	public class StartMenu extends JPanel implements ActionListener {
		private JLabel heroImageLabel;
		private ImageIcon maleIcon, femaleIcon;
		private JTextField nameField;
		private Boolean gender;		//true == male
		
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
			nameField = new JTextField("Jordan");
			constraints.gridx = 1;
			constraints.gridy = 15;
			this.add(nameField, constraints);

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
		    	hero.setName(nameField.getText());
		    	heroImage = hero.getImage();
		        dungeonPanel.setVisible(true);
		        dungeonPanel.requestFocus();
			}
			//radio buttons for choosing Hero
			if("Male".equals(event.getActionCommand())) {
				heroImageLabel.setIcon(maleIcon);
				hero.setGender(Hero.MALE);
			}
			if("Female".equals(event.getActionCommand())) {
				heroImageLabel.setIcon(femaleIcon);
				hero.setGender(Hero.FEMALE);
			}
		}
	}
}
