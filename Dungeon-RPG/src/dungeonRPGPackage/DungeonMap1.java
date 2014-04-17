package dungeonRPGPackage;

import dungeonRPGPackage.Map.Tile;

/**
 * The first Dungeon Map.
 * @author rrienton
 *
 */
public class DungeonMap1 extends Map{

	/**
	 * Same constructor as Map.
	 * @param entranceLocX
	 * @param entranceLocY
	 * @param exitLocX
	 * @param exitLocY
	 */
	public DungeonMap1(int entranceLocX, int entranceLocY, int exitLocX,
			int exitLocY) {
		super(entranceLocX, entranceLocY, exitLocX, exitLocY);
		for (int row = 0; row < Map.ARRAYSIZE; row++) {
            for (int col = 0; col < Map.ARRAYSIZE; col++) {
            	if((col < 5 && 8 < row) || ((1 < col && col < 5) && (0 < row && row < 8))
            			|| ((col == 5 || col == 6) && row == 7) || ((6 < col && col < 10) && 0 < row) || col == 11){
            		this.getTileArray()[row][col] = Tile.ROCK;
            	}
            	else if((col == 4 && row == 6) || ((col == 5 || col == 6) && row == 11)){
            		this.getTileArray()[row][col] = Tile.NONE;
            	}
            	else{
            		this.getTileArray()[row][col] = Tile.FLOOR;
            	}
            }
        }
		this.getTileArray()[6][4] = Tile.NONE;
	}

}
