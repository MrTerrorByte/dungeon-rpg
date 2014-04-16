package dungeonRPGPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BattlePanel extends JPanel {
	private BufferedImage heroImage;
	private BufferedImage enemyImage;
	private JLabel heroName, monsterName;
	
	public BattlePanel(Hero hero, Monster monster){
		heroImage = hero.getImage();
		enemyImage = monster.getImage();
		
	}
}
