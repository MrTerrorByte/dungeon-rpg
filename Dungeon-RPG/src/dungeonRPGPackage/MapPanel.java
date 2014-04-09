package dungeonRPGPackage;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Object representing the GUI panel of a Map object.
 * @author rrienton
 *
 */
public class MapPanel extends JPanel {
	private Map mapObject;		//object representation for the Map GUI
	
	/**
	 * Constructs a MapPanel.
	 * @param mapObject
	 */
	public MapPanel(Map mapObject){
		this.mapObject = mapObject;
		this.setLayout(new GridLayout(700,700));
		ImageIcon icon = createImageIcon("images/cave.png");
		JLabel background = new JLabel(icon);
		this.add(background);
	}
	
	/**
	 * Returns an ImageIcon, or null if path was invalid.
	 * (Citing:http://docs.oracle
	 * .com/javase/tutorial/uiswing/components/icon.html)
	 * 
	 * @param path
	 *            path where image is located
	 * @return ImageIcon with our image, or null
	 */
	private ImageIcon createImageIcon(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}
}
