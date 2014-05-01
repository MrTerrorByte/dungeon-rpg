package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import dungeonRPGPackage.GameController.StartMenu;

public class WinPanel extends JPanel implements KeyListener{
	
	public WinPanel(){
		this.addKeyListener(this);
		this.setBackground(Color.black);;
	}
	
	@Override
	public void paint(Graphics g){
		super.paint(g);
		this.requestFocus();
		Graphics2D gr = (Graphics2D) g;
		gr.setFont(new Font("", Font.BOLD, 30));
    	gr.setColor(Color.white);
    	gr.drawString("Congratulations!!", 150, 100);
    	gr.drawString("You have slain the dragon!", 75, 200);
    	gr.setFont(new Font("", Font.PLAIN, 20));
    	try {
			gr.drawImage(ImageIO.read(new File("src/images/dead_dragon.png")), 200, 300, 200, 200, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	gr.drawString("Press spacebar to restart", 175, 530);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE){
			JFrame dungeonFrame = GameController.getDungeonFrame();
			JPanel dungeonPanel = GameController.getDungeonPanel();
			dungeonFrame.remove(dungeonPanel);
	        dungeonFrame.add(GameController.startMenu);
	        dungeonFrame.revalidate();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
