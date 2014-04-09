package dungeonRPGPackage;

import javax.swing.JFrame;

public class GameController {

	public static void main(String[] args) {
		Map startMap = new Map(100, 100, 0, 0, 99, 99);
		MapPanel startPanel = new MapPanel(startMap);
		JFrame startFrame = new JFrame("Dungeon-RPG");
		
		startFrame.add(startPanel);
		// Display the window.
		//startFrame.setSize(STARTFRAMEWIDTH, STARTFRAMEWIDTH);
		// sets the window to open in the center
		startFrame.setLocationRelativeTo(null);
		startFrame.setVisible(true);
	}

}
