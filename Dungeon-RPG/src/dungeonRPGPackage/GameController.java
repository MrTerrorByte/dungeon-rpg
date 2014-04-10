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
	//private Map dungeonMaps[];

    int x = 1;
    int y = 45;
    
    /**
     * Constructs a GameController, which means the JFrame that displays the Map and Hero.
     */
    public GameController()
    {
    	//dungeonMaps = new Map[10];
    	Map dungeonMap = new Map(25, 49, 0, 14);
    	//hero = new Hero();
    	
        JFrame dungeonFrame = new JFrame();
        
        dungeonFrame.setLayout(null);
        dungeonFrame.setResizable(false);
        dungeonFrame.setBounds(0, 0, 600, 600);
        dungeonFrame.setVisible(true);
        dungeonFrame.addKeyListener(this);

		dungeonFrame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

        for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	if(j > 20){
            		dungeonMap.tileArray[row][col] = Tile.GRASS;
            	}
            	else if(row > 20 && row < 31 && col > 10){
            		dungeonMap.tileArray[row][col] = Tile.NONE;
            	}
            	else{
            		dungeonMap.tileArray[row][col] = Tile.ROCK;
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
                    switch (dungeonMap.tileArray[row][col]) {
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
                    tileGraphics.fillRect(row*12, col*12, Map.ARRAYSIZE, Map.ARRAYSIZE);
                }
            }
            tileGraphics.setColor(Color.RED);
            tileGraphics.fillRect(x*12+6, y*12+6, Map.ARRAYSIZE, Map.ARRAYSIZE);
			
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
			if (y > 0){
				y--;
			}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			if(y < 49){
				y++;
			}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
			if(x > 0){
				x--;
			}
		}
		else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
			if(x < 49){
				x++;
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
