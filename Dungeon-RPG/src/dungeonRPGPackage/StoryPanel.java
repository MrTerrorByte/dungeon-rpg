package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import dungeonRPGPackage.GameController.DungeonPanel;

public class StoryPanel extends JPanel implements ActionListener, KeyListener{
	private int tileSize = Map.FRAMEWIDTH/Map.ARRAYSIZE;
	private BufferedImage dragonImage;
	private String[] story;
	private int storyIndex = 0;
	public Timer timer = new Timer(4500, this);
	
	public StoryPanel(){
		try {
			dragonImage = ImageIO.read(new File("src/images/dragon_battle.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		timer.start();
		story = new String[3];
		story[0] = "A Dragon has taken residence in a cave near your village.";
		story[1] = "Hundreds of people have already lost their lives to the Dragon.";
		story[2] = "You refuse to let the Dragon harm anymore people...";
		
		dragonImage = resize(dragonImage, 200, 200);
		
		this.addKeyListener(this);
        this.setSize(Map.FRAMEWIDTH, Map.FRAMEHEIGHT);
		this.setVisible(true);
	}
	
	@Override
	public void paint(Graphics g){
        this.requestFocus();
        Graphics2D tileGraphics = (Graphics2D) g;

        for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	tileGraphics.setColor(Color.BLACK);
                tileGraphics.fillRect(row*tileSize, col*tileSize, tileSize, tileSize);
            }
        }
        tileGraphics.setColor(Color.WHITE);
        Font font = new Font("Dialog", Font.BOLD, 15);
        tileGraphics.setFont(font);
        if(storyIndex < 3){
        	tileGraphics.drawString(story[storyIndex], 80, 150);
        }
        if(storyIndex == 2){
        	tileGraphics.drawImage(dragonImage, 190, 200, null);
          	tileGraphics.drawString("Press the Space Bar to Begin Your Quest", 125, 500);
        }
            	
        storyIndex++;   
	}
	
	 public static void main(String[]args){
	        new StoryPanel();
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
	public void actionPerformed(ActionEvent arg0) {
		if(storyIndex < 3){	
			this.repaint();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE && storyIndex > 2){
			JFrame dungeonFrame = GameController.getDungeonFrame();
			dungeonFrame.remove(this);
			dungeonFrame.add(GameController.getDungeonPanel());
			GameController.getDungeonPanel().repaint();
			dungeonFrame.revalidate();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
