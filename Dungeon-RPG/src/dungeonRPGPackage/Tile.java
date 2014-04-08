package dungeonRPGPackage;

/**
 * 
 * @author jordan
 *
 */
public class Tile {
	private int width;			//width of the tile
	private int height;			//height of the tile
	private boolean blocked;	//if blocked is true, then the hero cannot move to this tile
	
	/**
	 * Constructor for Tile: sets the width, height, and blocked-status based on the parameters
	 * @param width : width of the tile
	 * @param height : height of the tile
	 * @param blocked : whether the tile is blocked
	 */
	public Tile(int width, int height, boolean blocked){
		this.width = width;
		this.height = height;
		this.blocked = blocked;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public boolean isBlocked(){
		return blocked;
	}
}
