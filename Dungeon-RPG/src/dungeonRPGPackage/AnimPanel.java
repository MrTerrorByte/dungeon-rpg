package dungeonRPGPackage;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimPanel extends JPanel implements ActionListener{
		private Timer timer;
		private int slashCount = 1;
		private static final int DELAY = 50;
		private Hero hero;
		private Monster monster;
		private BufferedImage cut1, cut2, cut3, cut4, cut5, cut6, cut7;
		
		public AnimPanel(Hero hero, Monster monster){
			this.hero = hero;
			this.monster = monster;
			timer = new Timer(DELAY, this);
			timer.setRepeats(true);
	        timer.setCoalesce(true);
			timer.start();
			try {
				initImages();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void initImages() throws IOException{
			cut1 = ImageIO.read(new File("src/images/cut1.png"));
			cut2 = ImageIO.read(new File("src/images/cut2.png"));
			cut3 = ImageIO.read(new File("src/images/cut3.png"));
			cut4 = ImageIO.read(new File("src/images/cut4.png"));
			cut5 = ImageIO.read(new File("src/images/cut5.png"));
			cut6 = ImageIO.read(new File("src/images/cut6.png"));
			cut7 = ImageIO.read(new File("src/images/cut7.png"));
		}
		@Override
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D gr = (Graphics2D)g;
			drawBattle(gr);			
		}
		
		public void drawBattle(Graphics2D gr){
			gr.drawImage(BattlePanel.resize(monster.getImage(), 150, 150), 400, 50, 150, 150, null);
			gr.drawImage(BattlePanel.resize(hero.getBackImage(),  150,  150), 30, 300, 150, 150, null);
			if(slashCount == 1){
				gr.drawImage(cut1, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 2){
				gr.drawImage(cut2, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 3){
				gr.drawImage(cut3, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 4){
				gr.drawImage(cut4, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 5){
				gr.drawImage(cut5, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 6){
				gr.drawImage(cut6, 200, 200, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 7){
				gr.drawImage(cut7, 200, 200, 200, 200, null);
				slashCount = 1;
				GameController.getDungeonFrame().remove(this);
				GameController.getDungeonFrame().add(GameController.battlePanel);
				GameController.battlePanel.repaint();
				GameController.getDungeonFrame().validate();
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	}