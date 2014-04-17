package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

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
    	dungeonPanel = new DungeonPanel();
    	Weapon weapon = new Weapon("Sword","Sword",50,10);
    	Shield shield = new Shield("Shield","Shield",100,10);
    	hero = new Hero("Hero", weapon, shield);
        
    	heroImage = null;
        grassImage = null;
        caveImage = null;
        floorImage = null;
        try {
            heroImage = ImageIO.read(new File("src/images/maleBackStanding.png"));
        } catch (IOException e) {
        	System.out.println("Hero image Doesnt exist");
        }
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
       
       
        //StartMenu startMenu = new StartMenu();
        //dungeonFrame.add(startMenu);
        //dungeonFrame.setUndecorated(true);
        dungeonPanel.setBounds(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
        //dungeonPanel.setLocationRelativeTo(null);
        dungeonPanel.setVisible(true);
        dungeonPanel.addKeyListener(this);

		dungeonFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

        
        
        dungeonFrame.add(dungeonPanel);
        dungeonFrame.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		dungeonFrame.setResizable(false);
		dungeonFrame.setVisible(true);
        dungeonFrame.validate();
        dungeonPanel.requestFocus();
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
}
