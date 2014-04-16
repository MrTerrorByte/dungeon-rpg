package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import dungeonRPGPackage.Map.Tile;

/**
 * Main Game Loop
 * @author rrienton
 *
 */
public class GameController implements KeyListener{
	private BufferStrategy buffer;
	private Hero hero;
	private int tileSize = Map.FRAMEWIDTH/Map.ARRAYSIZE;
	//private Map dungeonMaps[];
    
    /**
     * Constructs a GameController, which means the JFrame that displays the Map and Hero.
     */
    public GameController()
    {
    	//dungeonMaps = new Map[10];
    	Map dungeonMap = new Map(5, 11, 5, 3);
    	Weapon weapon = new Weapon("Sword","Sword",0,0);
    	Shield shield = new Shield("Shield","Shield",0,0);
    	hero = new Hero("Hero", dungeonMap, weapon, shield);
    	
        JFrame dungeonFrame = new JFrame();
        
        dungeonFrame.setTitle("Dungeon-RPG");
        dungeonFrame.setLayout(null);
        dungeonFrame.setResizable(false);
        //dungeonFrame.setUndecorated(true);
        dungeonFrame.setBounds(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
        dungeonFrame.setLocationRelativeTo(null);
        dungeonFrame.setVisible(true);
        dungeonFrame.addKeyListener(this);

		dungeonFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

        for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	if(col > 5){
            		hero.getMap().getTileArray()[row][col] = Tile.GRASS;
            	}
            	else if(row > 4 && row < 7 && col > 3){
            		hero.getMap().getTileArray()[row][col] = Tile.NONE;
            	}
            	else{
            		hero.getMap().getTileArray()[row][col] = Tile.ROCK;
            	}
            }
        }

        dungeonFrame.createBufferStrategy(2);
        buffer = dungeonFrame.getBufferStrategy();

        gameLoop();
    }

    private void gameLoop()
    {
        while (true) {
            Graphics2D tileGraphics = (Graphics2D) buffer.getDrawGraphics();
            BufferedImage heroImage = null;
            BufferedImage grassImage = null;
            BufferedImage caveImage = null;
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
                caveImage = ImageIO.read(new File("src/images/cave.png"));
            } catch (IOException e) {
            	System.out.println("Cave image Doesnt exist");
            }
            
            for (int row = 0; row < Map.ARRAYSIZE; row++) {
                for (int col = 0; col < Map.ARRAYSIZE; col++) {
                    switch (hero.getMap().getTileArray()[row][col]) {
                        case GRASS:
                            tileGraphics.setColor(Color.GREEN);
                            tileGraphics.drawImage(grassImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                            break;
                        case ROCK:
                            //tileGraphics.setColor(Color.GRAY);
                            //tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
                        	tileGraphics.drawImage(caveImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
                        	break;
                        case NONE:
                        	tileGraphics.setColor(Color.BLACK);
                        	tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
                        	break;
                    }
                    //tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
                }
            }
            //tileGraphics.setColor(Color.RED);
            //tileGraphics.fillRect(hero.getX()*tileSize, hero.getY()*tileSize, tileSize, tileSize);
			tileGraphics.drawImage(heroImage, hero.getX()*tileSize, hero.getY()*tileSize, null);
            
            buffer.show();
            tileGraphics.dispose();
            
            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
    
    /*private void generateMaps(){
    	for(int mapNumber = 0; mapNumber < 10; mapNumber++){
    		Map dungeonMap = 
    	}
    }*/

    public static void main(String[]args)
    {
        new GameController();
    }

	@Override
	/**
	 * Handles movement when arrow keys are pressed and when the escape button is pressed
	 * @param arg0 KeyEvent
	 */
	public void keyPressed(KeyEvent arg0) {
		//press up key
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			hero.move(hero.getX(), hero.getY()-1);
		}
		//press down key
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			hero.move(hero.getX(), hero.getY()+1);
		}
		//press left key
		else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			hero.move(hero.getX()-1, hero.getY());
		}
		//press right key
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			hero.move(hero.getX()+1, hero.getY());
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

}
