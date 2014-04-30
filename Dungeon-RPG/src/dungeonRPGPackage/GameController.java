package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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

import dungeonRPGPackage.Map.Tile;

/**
 * Main Game Loop
 * @author rrienton
 *
 */
public class GameController{
	private Hero hero;
	private Weapon weapon; 
	private Shield shield;
	private int tileSize = Map.FRAMEWIDTH/Map.ARRAYSIZE;
	private static JFrame dungeonFrame = new JFrame("Dungeon RPG");
    private static DungeonPanel dungeonPanel;
    public static BattlePanel battlePanel;
    private BufferedImage heroImage, grassImage, caveImage, floorImage, waterImage, treeImage, lavaImage, dragonImage,
    						shopImage;
    private boolean gameOver = false;
    
    /**
     * Constructs a GameController, which means the JFrame that displays the Map and Hero.
     */
    public GameController()
    {   
        grassImage = null;
        caveImage = null;
        floorImage = null;
        try {
            grassImage = ImageIO.read(new File("src/images/grass.png"));
            floorImage = ImageIO.read(new File("src/images/floor.png"));
            caveImage = ImageIO.read(new File("src/images/cave.png"));
            waterImage = ImageIO.read(new File("src/images/water.png"));
            treeImage = ImageIO.read(new File("src/images/tree.png"));
            lavaImage = ImageIO.read(new File("src/images/lava.png"));
            dragonImage = ImageIO.read(new File("src/images/dragon.png"));
            shopImage = ImageIO.read(new File("src/images/shopkeeperFront.png"));
        } catch (IOException e) {
        	System.out.println("Image doesnt exist");
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

    /**
     * When a battle arises, load in the monster image. Then remove the current panel
     * to add in the battle panel. Then validate the frame, so it reflects the change.
     */
    public void addBattlePanel(){
    	Monster monster = new Monster("Reaper", hero.getMapIndex()-1, null);
    	try {
			monster.setImage(ImageIO.read(new File("src/images/death_scythe_front.png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
    	dungeonFrame.remove(dungeonPanel);
    	battlePanel = new BattlePanel(hero, monster);
    	LoadingPanel loadingPanel = new LoadingPanel();
    	dungeonFrame.add(loadingPanel);
    	dungeonFrame.validate();
    }
	
	/**
	 * This panel is for the map
	 * @author jordan
	 *
	 */
	@SuppressWarnings("serial")
	public class DungeonPanel extends JPanel implements KeyListener{
		@Override
		public void paint(Graphics g){
	        this.requestFocus();
            Graphics2D tileGraphics = (Graphics2D) g;
            
            //if game is over
            if(hero.isGameOver()) {
            	tileGraphics.setColor(Color.BLACK);
            	tileGraphics.fillRect(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
            	tileGraphics.setColor(Color.WHITE);
	            Font font = new Font("Dialog", Font.BOLD, 40);
	            tileGraphics.setFont(font);
	            tileGraphics.drawString("GAME OVER", 170, 200);
	            tileGraphics.drawString("You were defeated!", 130, 300);
	            tileGraphics.drawImage(dragonImage, 300, 350, tileSize, tileSize, null);
	            Font font2 = new Font("Dialog", Font.BOLD, 20);
	            tileGraphics.setFont(font2);
	            tileGraphics.drawString("Press the Space Bar to Restart", 100, 500);
            }
            else {
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
                        	case WATER:
                        		tileGraphics.drawImage(waterImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        		break;
                        	case TREE:
                        		tileGraphics.drawImage(treeImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        		break;
                        	case LAVA:
                        		tileGraphics.drawImage(lavaImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        		break;
                        	case DRAGON:
                        		tileGraphics.drawImage(dragonImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        		break;
                        	case SHOP:
                        		tileGraphics.drawImage(grassImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        		tileGraphics.drawImage(shopImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
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
		 * Restarts the game.
		 */
		private void restartGame(){
			dungeonFrame.remove(dungeonPanel);
			StartMenu startMenu = new StartMenu();
	        dungeonFrame.add(startMenu);
	        dungeonFrame.revalidate();
		}
		
		@Override
		/**
		 * Handles movement when arrow keys are pressed and when the escape button is pressed
		 * @param arg0 KeyEvent
		 */
		public void keyPressed(KeyEvent arg0) {
			//press up key
			if(arg0.getKeyCode() == KeyEvent.VK_UP){
				if(hero.isGameOver()){
					return;
				}
				if(hero.move(hero.getX(), hero.getY()-1) == 1){
					addBattlePanel();
				}
				heroImage = hero.getBackImage();
				dungeonPanel.repaint();
			}
			//press down key
			else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
				if(hero.isGameOver()){
					return;
				}
				if(hero.move(hero.getX(), hero.getY()+1) == 1){
					addBattlePanel();
				}
				heroImage = hero.getFrontImage();
				dungeonPanel.repaint();
			}
			//press left key
			else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
				if(hero.isGameOver()){
					return;
				}
				if(hero.move(hero.getX()-1, hero.getY()) == 1){
					addBattlePanel();
				}
				heroImage = hero.getLeftImage();
				dungeonPanel.repaint();
			}
			//press right key
			else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
				if(hero.isGameOver()){
					return;
				}
				if(hero.move(hero.getX()+1, hero.getY()) == 1){
					addBattlePanel();
				}
				heroImage = hero.getRightImage();
				dungeonPanel.repaint();
			}
			//press space bar
			else if(arg0.getKeyCode() == KeyEvent.VK_SPACE){
				if(hero.isGameOver()){
					restartGame();
				}
				//if the shopkeeper is in front of hero
				else if(hero.getMap().getTileArray()[hero.getX()][hero.getY()+1] == Tile.SHOP){
					//open shop panel
					
				}
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
		private boolean gender = hero.MALE;		
		
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
			
			BufferedImage image = null;
			try {
	            image = ImageIO.read(new File("src/images/maleFrontStanding.png"));
	        } catch (IOException e) {
	        	System.out.println("Hero image Doesnt exist");
	        }
			maleIcon = new ImageIcon(image);
			
			// add hero image label
			heroImageLabel = new JLabel(maleIcon);
			constraints.gridx = 1;
			constraints.gridy = 5;
			this.add(heroImageLabel, constraints);

			try {
	            image = ImageIO.read(new File("src/images/femaleFrontStanding.png"));
	        } catch (IOException e) {
	        	System.out.println("Hero image Doesnt exist");
	        }
			femaleIcon = new ImageIcon(image);
				
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
			startButton.setPreferredSize(new Dimension(200, 40));
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
				//set up dungeon panel and create Hero
				weapon = new Weapon("Sword","Sword",50,10);
				shield = new Shield("Shield","Shield",100,10);
		    	hero = new Hero(nameField.getText(), gender, weapon, shield);
		        heroImage = hero.getFrontImage();
		        
		        dungeonFrame.remove(this);
		    	dungeonPanel = new DungeonPanel();
		    	dungeonPanel.addKeyListener(dungeonPanel);
		        dungeonPanel.setBounds(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		        dungeonFrame.add(dungeonPanel);
		        dungeonFrame.revalidate();
		        dungeonPanel.setVisible(true);
		        dungeonPanel.requestFocus();
			}
			//radio buttons for choosing Hero
			if("Male".equals(event.getActionCommand())) {
				heroImageLabel.setIcon(maleIcon);
				gender = hero.MALE;
			}
			if("Female".equals(event.getActionCommand())) {
				heroImageLabel.setIcon(femaleIcon);
				gender = hero.FEMALE;
			}
		}
	}
}
