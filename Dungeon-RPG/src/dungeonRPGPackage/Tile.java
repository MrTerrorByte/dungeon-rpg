package dungeonRPGPackage;

/**
 * 
 * @author jordan
 *
 */
public class Tile {
	private int width;			//width of the tile
	private int height;			//height of the tile
	private int row;			//tile row in the map's tileArray
	private int column;			//tile column in the map's tileArray
	private boolean blocked;	//if blocked is true, then the hero cannot move to this tile
	
	/**
	 * Constructor for Tile: sets the width, height, and blocked-status based on the parameters
	 * @param width : width of the tile
	 * @param height : height of the tile
	 * @param blocked : whether the tile is blocked
	 */
	public Tile(int width, int height, int row, int column, boolean blocked){
		this.width = width;
		this.height = height;
		this.row = row;
		this.column = column;
		this.blocked = blocked;
	}
	
	public int getWidth() {
		return this.width;
	}

	public int getHeight() {
		return this.height;
	}

	public int getRow(){
		return this.row;
	}
	
	public int getColumn(){
		return this.column;
	}
	
	public void setRow(int row){
		this.row = row;
	}
	
	public void setColumn(int column){
		this.column = column;
	}
	
	public boolean isBlocked(){
		return blocked;
	}
	
	public void setBlocked(boolean blocked){
		this.blocked = blocked;
	}
}
