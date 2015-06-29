package dungeonRPGPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * This panel deals with the animation for using potions
 * @author jordan
 *
 */
public class PotAnimPanel extends JPanel implements ActionListener{
	public static int DELAY = 50;
	public Timer timer;
	private Hero hero;
	private Monster monster;
	private BufferedImage[] heals;
	private int healCount = 1;
	
	public PotAnimPanel(Hero hero, Monster monster){
		this.hero = hero;
		this.monster = monster;
		heals = new BufferedImage[6];
		timer = new Timer(DELAY, this);
		timer.start();
		try {
			initImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initImages() throws IOException{
		for(int i = 0; i < 6; i++){
			int j= i+1;
			heals[i] = ImageIO.read(new File("src/images/heal"+j+".png"));
		}
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D gr = (Graphics2D)g;
		drawHealing(gr);
	}
	
	public void drawHealing(Graphics2D gr){
		int offset = 30;
		gr.drawImage(BattlePanel.resize(monster.getImage(), 150, 150), BattlePanel.MONSTERX, BattlePanel.MONSTERY, 150, 150, null);
		gr.drawImage(BattlePanel.resize(hero.getBackImage(),  150,  150), BattlePanel.HEROX, BattlePanel.HEROY, 150, 150, null);
		if(healCount <= 5){
			gr.drawImage(heals[healCount], BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
			healCount++;
			if(healCount == 6){
					healCount = 0;
					timer.stop();JFrame dungeonFrame = GameController.getDungeonFrame();
					Battle.usePotion(hero, new Potion("", "", 0.25));
					dungeonFrame.remove(this);
					dungeonFrame.add(GameController.battlePanel);
					GameController.battlePanel.repaint();
					dungeonFrame.revalidate();
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
