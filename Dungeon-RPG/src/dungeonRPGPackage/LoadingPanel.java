package dungeonRPGPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

	public class LoadingPanel extends JPanel implements ActionListener, KeyListener{
		private final int OFFSET = 20, DELAY = 300;
		public Timer timer;
		private int stringCount = 1;
		private boolean doneLoading = false;
		
		public LoadingPanel(){
			timer = new Timer(DELAY, this);
			timer.start();
			this.addKeyListener(this);
			this.setLayout(null);
			this.setBackground(Color.black);
		}
		
		@Override
		public void paint(Graphics g){
			Graphics2D gr = (Graphics2D)g;
			super.paint(gr);
			gr.setFont(new Font(null, Font.PLAIN, 25));
			gr.setColor(Color.white);
			this.requestFocus();
			switch(stringCount){
				case 1: gr.drawString("Prep",  Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						stringCount++;
						break;
				case 2: gr.drawString("Prepare",  Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						stringCount++;
						break;
				case 3: gr.drawString("Prepare for",  Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						stringCount++;
						break;
				case 4: gr.drawString("Prepare for bat",  Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						stringCount++;
						break;
				case 5:	gr.drawString("Prepare for battle!", Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						stringCount++;
						break;
				default: gr.drawString("Prepare for battle!", Map.FRAMEWIDTH/6+4*OFFSET, Map.FRAMEHEIGHT/2-OFFSET);
						 gr.drawString("Press the spacebar to continue", Map.FRAMEWIDTH/6, Map.FRAMEHEIGHT-10*OFFSET);
						 stringCount = 1;
						 doneLoading = true;
						 //timer.stop();
						 break;
			}
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(doneLoading == false){	
				repaint();
			}
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
			if(e.getKeyCode() == KeyEvent.VK_SPACE){
					JFrame dungeonFrame = GameController.getDungeonFrame();
				 	dungeonFrame.remove(this);
				 	dungeonFrame.add(GameController.battlePanel);
				 	dungeonFrame.revalidate();
			}
		}
		
	}