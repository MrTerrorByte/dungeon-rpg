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

public class PotAnimPanel extends JPanel implements ActionListener{
	public static int DELAY = 50;
	public Timer timer;
	private Hero hero;
	private Monster monster;
	private BufferedImage heal1, heal2, heal3, heal4, heal5, heal6, heal7;
	private int healCount = 1;
	
	public PotAnimPanel(Hero hero, Monster monster){
		this.hero = hero;
		this.monster = monster;
		timer = new Timer(DELAY, this);
		timer.start();
		try {
			initImages();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void initImages() throws IOException{
		heal1 = ImageIO.read(new File("src/images/heal1.png"));
		heal2 = ImageIO.read(new File("src/images/heal2.png"));
		heal3 = ImageIO.read(new File("src/images/heal3.png"));
		heal4 = ImageIO.read(new File("src/images/heal4.png"));
		heal5 = ImageIO.read(new File("src/images/heal5.png"));
		heal6 = ImageIO.read(new File("src/images/heal6.png"));
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
		switch(healCount){
			case 1: gr.drawImage(heal1, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount++;
					break;
			case 2: gr.drawImage(heal2, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount++;
					break;
			case 3: gr.drawImage(heal3, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount++;
					break;
			case 4: gr.drawImage(heal4, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount++;
					break;
			case 5: gr.drawImage(heal4, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount++;
					break;
			case 6: gr.drawImage(heal4, BattlePanel.HEROX+offset, BattlePanel.HEROY+offset, 100, 100, null);
					healCount = 1;
					timer.stop();JFrame dungeonFrame = GameController.getDungeonFrame();
					Battle.usePotion(hero, new Potion("", "", 0.25));
					dungeonFrame.remove(this);
					dungeonFrame.add(GameController.battlePanel);
					GameController.battlePanel.repaint();
					dungeonFrame.revalidate();
			return;					
			default: break;
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

}
