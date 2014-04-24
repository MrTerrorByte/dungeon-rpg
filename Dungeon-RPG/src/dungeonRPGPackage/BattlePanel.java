package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import dungeonRPGPackage.GameController.DungeonPanel;

/**
 * This is the panel where battles take place
 * @author jordan
 *
 */
public class BattlePanel extends JPanel implements KeyListener{
	
	public static final int DELAY = 50, HEROX = 30, HEROY = 300, MONSTERX = 370, MONSTERY = 30, OFFSET = 20;
	private Hero hero;
	private Monster monster;
	private boolean attacking;
	private AnimPanel animPanel;
	private PotAnimPanel potAnimPanel;
	private ImageIcon heroIcon, monsterIcon;
	private Image heroImage, monsterImage;
	public Timer timer;
	
	/**
	 * initializes all the JLabels and JButtons
	 * 
	 * @param hero : the hero that we are loading
	 * @param monster : the monster that we are loading
	 * @param dungeonFrame 
	 */
	public BattlePanel(Hero hero, Monster monster){
		animPanel = new AnimPanel(hero, monster);
		potAnimPanel = new PotAnimPanel(hero, monster);
		attacking = false;
		this.hero = hero;
		this.monster = monster;
		this.setLayout(null);
		heroIcon = new ImageIcon(resize(hero.getBackImage(), 150, 150));
		heroImage = heroIcon.getImage();
		
		monsterIcon = new ImageIcon(resize(monster.getImage(), 150, 150));
		monsterImage = monsterIcon.getImage();
		
		this.addKeyListener(this);
	}

	@Override
	public void paint(Graphics g){
		this.requestFocus();
		g = (Graphics2D)g;
		super.paint(g);
		g.setFont(new Font(null, Font.PLAIN, 17));
		g.drawString(monster.getName(), HEROX, MONSTERY);
		g.drawString(hero.getName(), MONSTERX, HEROY);
		g.drawString("HP: "+monster.getCurrHealth()+"/"+monster.getMaxHealth(), HEROX, MONSTERY+2*OFFSET);
		g.drawString("Lv "+monster.getLevel(), HEROX, MONSTERY+OFFSET);
		g.drawString("Shield Lv "+hero.getShield().getLevel()+" Exp:"+hero.getShield().getCurrExp()+"/"+hero.getShield().getReqExp(), MONSTERX, HEROY+OFFSET);
		g.drawString("Weapon Lv "+hero.getWeapon().getLevel()+" Exp:"+hero.getWeapon().getCurrExp()+"/"+hero.getWeapon().getReqExp(), MONSTERX, HEROY+OFFSET*2);
		g.drawString("HP: "+hero.getCurrHealth()+"/"+hero.getMaxHealth(), MONSTERX, HEROY+OFFSET*3);
		g.drawImage(heroImage, HEROX, HEROY, 150, 150, null);
		g.drawString("Press 'a' to attack", OFFSET, Map.FRAMEHEIGHT-4*OFFSET);
		g.drawString("Press 'p' to use potion, "+hero.getPotionCount()+"x left",OFFSET, Map.FRAMEHEIGHT-3*OFFSET);
		g.drawImage(monsterImage, MONSTERX, MONSTERY, 150, 150, null);
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		JFrame dungeonFrame = GameController.getDungeonFrame();
		if(e.getKeyCode() == KeyEvent.VK_A){
			dungeonFrame.remove(this);
			dungeonFrame.add(animPanel);
			animPanel.timer.start();
			dungeonFrame.revalidate();
		}
		else if(e.getKeyCode() == KeyEvent.VK_P){
			if(hero.getPotionCount() > 0){
				dungeonFrame.remove(this);
				dungeonFrame.add(potAnimPanel);
				potAnimPanel.timer.start();
				dungeonFrame.revalidate();
			}
		}
	}
	
	
		
}
