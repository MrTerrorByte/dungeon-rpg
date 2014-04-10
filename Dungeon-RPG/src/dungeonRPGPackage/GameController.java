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
    private Map dungeonMap;

    int x = 1;
    int y = 45;
    
    /**
     * Constructs a GameController, which means the JFrame that displays the Map and Hero.
     */
    public GameController()
    {
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

        tiles = new Tile[50][50];
        for (int i = 0; i < 50; i++) {
            for (int j = 0; j < 50; j++) {
            	if(j > 20){
            		tiles[i][j] = Tile.GRASS;
            	}
            	else if(i > 20 && i < 31 && j > 10){
            		tiles[i][j] = Tile.NONE;
            	}
            	else{
            		tiles[i][j] = Tile.ROCK;
            	}
            }
        }

        dungeonFrame.createBufferStrategy(2);
        buffer = dungeonFrame.getBufferStrategy();

        loop();
    }

    public void loop()
    {
        while (true) {
            Graphics2D tileGraphics = (Graphics2D) buffer.getDrawGraphics();
            
            for (int i = 0; i < 50; i++) {
                for (int j = 0; j < 50; j++) {
                    switch (tiles[i][j]) {
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
                    tileGraphics.fillRect(i*12, j*12, 50, 50);
                }
            }
            tileGraphics.setColor(Color.RED);
            tileGraphics.fillRect(x*12+6, y*12+6, 50, 50);
			
            buffer.show();
            tileGraphics.dispose();
        }
    }

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
