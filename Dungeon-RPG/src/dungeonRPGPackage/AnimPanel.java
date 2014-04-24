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

public class AnimPanel extends JPanel implements ActionListener{
	
		private static final int DELAY = 50;
		public Timer timer;
		private int slashCount = 1;
		private Hero hero;
		private Monster monster;
		private BufferedImage cut1, cut2, cut3, cut4, cut5, cut6, cut7;
		private boolean Turn;
		
		public AnimPanel(Hero hero, Monster monster){
			Turn = true;
			timer = new Timer(DELAY, this);
			timer.start();
			this.hero = hero;
			this.monster = monster;

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
			drawBattle(gr, Turn);			
		}
		
		/**
		 * Animates the attack depending on who is being attacked.
		 * For the final slash, stop the timer, and if it's the monster's turn,
		 * let him attack, and then switch back to the battle panel for further keypresses.
		 * 
		 * @param gr : graphics
		 * @param Turn : who's turn it is to attack
		 */
		public void drawBattle(Graphics2D gr, boolean Turn){
			gr.drawImage(BattlePanel.resize(monster.getImage(), 150, 150), BattlePanel.MONSTERX, BattlePanel.MONSTERY, 150, 150, null);
			gr.drawImage(BattlePanel.resize(hero.getBackImage(),  150,  150), BattlePanel.HEROX, BattlePanel.HEROY, 150, 150, null);
			int x, y;
			if(Turn){
				x = BattlePanel.MONSTERX;
				y = BattlePanel.MONSTERY;
			}
			else{
				x = BattlePanel.HEROX;
				y = BattlePanel.HEROY;
			}
			if(slashCount == 1){
				gr.drawImage(cut1, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 2){
				gr.drawImage(cut2, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 3){
				gr.drawImage(cut3, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 4){
				gr.drawImage(cut4, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 5){
				gr.drawImage(cut5, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 6){
				gr.drawImage(cut6, x, y, 200, 200, null);
				slashCount++;
			}
			else if(slashCount == 7){
				gr.drawImage(cut7, x, y, 200, 200, null);
				slashCount = 1;
				timer.stop();
				//if monster's turn
				if(Turn == false){
					JFrame dungeonFrame = GameController.getDungeonFrame();
					dungeonFrame.remove(this);
					//if Hero dies
					
					if(Battle.attack(hero, monster, Turn) == -1){
						dungeonFrame.add(GameController.getDungeonPanel());
						this.Turn = true;
						GameController.getDungeonPanel().repaint();
						dungeonFrame.revalidate();
						return;
					}
					
					dungeonFrame.add(GameController.battlePanel);
					GameController.battlePanel.repaint();
					dungeonFrame.revalidate();
					this.Turn = true;
					return;
				}
				endAnimation();
			}
		}
		
		/**
		 * After the attack animation is over, attack the monster. The turn will then switch, and
		 * the timer will then restart which causes paint to be called again.
		 */
		private void endAnimation(){
			JFrame dungeonFrame = GameController.getDungeonFrame();
			
			if(Battle.attack(hero, monster, true) == 1){
				dungeonFrame.remove(this);
				dungeonFrame.add(GameController.getDungeonPanel());
				dungeonFrame.revalidate();
				GameController.getDungeonPanel().repaint();
				return;
			}
			Turn = !Turn;
			timer.start();

		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}

	}