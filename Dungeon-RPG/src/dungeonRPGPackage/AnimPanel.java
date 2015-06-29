package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
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
 * This is the panel where battle annimations take place
 * @author jordan
 *
 */

public class AnimPanel extends JPanel implements ActionListener{
	
		private static final int DELAY = 75;
		public Timer timer;
		private int slashCount = 0;
		private Hero hero;
		private Monster monster;
		private BufferedImage[] cuts;
		private boolean Turn;
		
		public AnimPanel(Hero hero, Monster monster){
			Turn = true;
			timer = new Timer(DELAY, this);
			timer.start();
			this.hero = hero;
			this.monster = monster;
			cuts = new BufferedImage[7];

			try {
				initImages();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void initImages() throws IOException{
			for(int i = 0; i < 7; i++){
				int j= i+1;
				cuts[i] = ImageIO.read(new File("src/images/cut"+j+".png"));
			}
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
			if(slashCount <= 6){
				gr.drawImage(cuts[slashCount], x, y, 200, 200, null);
				slashCount++;
				if(slashCount == 6){
					slashCount = 0;
					
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
		}
		
		/**
		 * After the attack animation is over, attack the monster. The turn will then switch, and
		 * the timer will then restart which causes paint to be called again.
		 */
		private void endAnimation(){
			JFrame dungeonFrame = GameController.getDungeonFrame();
			
			if(Battle.attack(hero, monster, true) == 1){
				if(monster.getName().equals("Smog")){
					dungeonFrame.remove(this);
					WinPanel winPanel = new WinPanel();
					dungeonFrame.add(winPanel);
					dungeonFrame.revalidate();
					winPanel.repaint();
					timer.stop();
				}
				else{
					dungeonFrame.remove(this);
					dungeonFrame.add(GameController.getDungeonPanel());
					dungeonFrame.revalidate();
					GameController.getDungeonPanel().repaint();
					timer.stop();
					return;
				}
			}
			Turn = !Turn;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			repaint();
		}

	}