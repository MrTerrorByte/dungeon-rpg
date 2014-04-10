package dungeonRPGPackage;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

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
    	Map dungeonMap = new Map(25, 49, 0, 14);
    	Weapon weapon = new Weapon("Sword","Sword",0,0);
    	Shield shield = new Shield("Shield","Shield",0,0);
    	hero = new Hero("Hero", dungeonMap, weapon, shield);
    	
        JFrame dungeonFrame = new JFrame();
        
        dungeonFrame.setLayout(null);
        dungeonFrame.setResizable(false);
        dungeonFrame.setBounds(0, 0, Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
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
            		hero.getMap().tileArray[row][col] = Tile.GRASS;
            	}
            	else if(row > 20 && row < 31 && col > 10){
            		hero.getMap().tileArray[row][col] = Tile.NONE;
            	}
            	else{
            		hero.getMap().tileArray[row][col] = Tile.ROCK;
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
            
            for (int row = 0; row < Map.ARRAYSIZE; row++) {
                for (int col = 0; col < Map.ARRAYSIZE; col++) {
                    switch (hero.getMap().tileArray[row][col]) {
                        case GRASS:
                            tileGraphics.setColor(Color.GREEN);
                            break;
                        case ROCK:
                            tileGraphics.setColor(Color.GRAY);
                            break;
                        case NONE:
                        	tileGraphics.setColor(Color.BLACK);
                        	break;
                    }
                    tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
                }
            }
            tileGraphics.setColor(Color.RED);
            tileGraphics.fillRect(hero.getX()*tileSize, hero.getY()*tileSize, tileSize, tileSize);
			
            buffer.show();
            tileGraphics.dispose();
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
	public void keyPressed(KeyEvent arg0) {
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			hero.move(hero.getX(), hero.getY()-1);
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			hero.move(hero.getX(), hero.getY()+1);
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			hero.move(hero.getX()-1, hero.getY());
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			hero.move(hero.getX()+1, hero.getY());
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
