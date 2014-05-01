package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * JPanel for the Shop
 * @author rrienton
 *
 */
public class ShopPanel extends JPanel implements KeyListener {

	private Hero hero;
	private BufferedImage shopkeeperImage, heroImage, potionImage, backgroundImage, pointerImage, goldImage;
	private int tileSize = Map.FRAMEWIDTH/Map.ARRAYSIZE;
	private static final String WELCOME = "Welcome to my Shop! How Can I Help You Today?";
	private static final String POTION = "That's a Potion for 1000 Gold.";
	private static final String THANKS = "Thank You For Your Business!";
	private static final String NOTHING = "Nothing";
	private static final String NOTENOUGH = "You Don't Have Enough Gold!";
	private boolean upPressed = false;
	private boolean downPressed = true;
	private String shopkeepSays;
	
	public ShopPanel(Hero hero, BufferedImage shopkeeper){
		this.hero = hero;
		this.shopkeeperImage = resize(shopkeeper, 200, 200);
		
		try{
			potionImage = ImageIO.read(new File("src/images/potion.png"));
			backgroundImage = ImageIO.read(new File("src/images/grass.png"));
			pointerImage = ImageIO.read(new File("src/images/sword.png"));
			goldImage = ImageIO.read(new File("src/images/gold.png"));
        } catch (IOException e) {
        	System.out.println("Potion image doesnt exist");
		}
		
		shopkeepSays = WELCOME;
		
		this.addKeyListener(this);
		// Display the window.
		this.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
        this.requestFocus();
        Graphics2D tileGraphics = (Graphics2D) g;
        	
        //bottom white rectangle
       	tileGraphics.setColor(Color.BLACK);
		tileGraphics.drawRect(0, 400, 600, 200);
		tileGraphics.setColor(Color.WHITE);
		tileGraphics.fillRect(0, 400, 600, 200);
		
		for(int row = 0; row < Map.ARRAYSIZE; row++){
       		for(int col = 0; col < 8; col++){
       			
       			tileGraphics.drawImage(backgroundImage, row*tileSize, col*tileSize, tileSize, tileSize, null);
       		}
       	}
		
		//top right white rectangle
		tileGraphics.setColor(Color.BLACK);
		tileGraphics.drawRect(400, 0, 200, 300);
		tileGraphics.setColor(Color.WHITE);
		tileGraphics.fillRect(400, 0, 200, 300);
		
       	tileGraphics.drawImage(shopkeeperImage, 0, 0, null);
       	
        Font font = new Font("Dialog", Font.BOLD, 20);
        tileGraphics.setColor(Color.BLACK);
        tileGraphics.setFont(font);
        tileGraphics.drawString(shopkeepSays, 10, 440);
        tileGraphics.drawString("Potion", 465, 100);
        tileGraphics.drawImage(potionImage, 530, 70, tileSize, tileSize, null);
        tileGraphics.drawString(NOTHING, 465, 200);
        
        //instructions
        tileGraphics.drawString("Press \"enter\" to select your choice", 10, 500);
        
        //current gold
        tileGraphics.setFont(new Font("Dialog", Font.BOLD, 15));
        tileGraphics.drawString("Current Gold: " + hero.getGold(), 400, 550);
        tileGraphics.drawImage(goldImage, 530, 520, null);
        
        //set up the pointer
        if(upPressed){
            tileGraphics.drawImage(pointerImage, 420, 80, tileSize, tileSize, null);
        }
        if(downPressed){
        	tileGraphics.drawImage(pointerImage, 420, 180, tileSize, tileSize, null);
        }
	}
	
	/**
	 * resizes a buffered image
	 * 
	 * @param image : the image to resize
	 * @param width : the new width
	 * @param height : the new height
	 * @return the new image
	 * cite: http://stackoverflow.com/questions/14548808/scale-the-imageicon-automatically-to-label-size
	 * by David Kroukamp
	 */
	public static BufferedImage resize(BufferedImage image, int width, int height) {
	    BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
	    Graphics2D g2d = (Graphics2D) bi.createGraphics();
	    g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
	    g2d.drawImage(image, 0, 0, width, height, null);
	    g2d.dispose();
	    return bi;
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		//press up key
		if(arg0.getKeyCode() == KeyEvent.VK_UP){
			upPressed = true;
			downPressed = false;
			shopkeepSays = POTION;
			this.repaint();
		}
		//press down key
		else if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
			downPressed = true;
			upPressed = false;
			shopkeepSays = "";
			this.repaint();
		}
		//press enter key
		else if(arg0.getKeyCode() == KeyEvent.VK_ENTER){
			if(upPressed){
				//check hero has enough gold
				if(hero.getGold() - 1000 >= 0){
					hero.setGold(hero.getGold()-1000);
					hero.setPotionCount(hero.getPotionCount()+1);
					shopkeepSays = THANKS;
				}
				else{
					shopkeepSays = NOTENOUGH;
				}
				this.repaint();
			}
			else{
				JFrame dungeonFrame = GameController.getDungeonFrame();
				dungeonFrame.remove(this);
				dungeonFrame.add(GameController.getDungeonPanel());
				GameController.getDungeonPanel().repaint();
				dungeonFrame.revalidate();
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
