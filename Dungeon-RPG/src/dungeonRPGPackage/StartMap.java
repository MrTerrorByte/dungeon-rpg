package dungeonRPGPackage;

import dungeonRPGPackage.Map.Tile;

/**
 * Map for the entrance of the cave
 * @author rrienton
 *
 */
public class StartMap extends Map {

	/**
	 * Same as Map constructor
	 * @param entranceLocX
	 * @param entranceLocY
	 * @param exitLocX
	 * @param exitLocY
	 */
	public StartMap(int entranceLocX, int entranceLocY, int exitLocX,
			int exitLocY) {
		super(entranceLocX, entranceLocY, exitLocX, exitLocY);
		for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	if(col > 5){
            		this.getTileArray()[row][col] = Tile.GRASS;
            	}
            	else if(row > 4 && row < 7 && col > 3){
            		this.getTileArray()[row][col] = Tile.NONE;
            	}
            	else{
            		this.getTileArray()[row][col] = Tile.ROCK;
            	}
            }
        }
	}

}
