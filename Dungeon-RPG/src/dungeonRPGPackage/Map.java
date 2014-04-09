package dungeonRPGPackage;
/**
 * Object for representing a Map in the dungeon
 * @author rrienton
 *
 */
public class Map {
	private int width;				//width of the map
	private int height;				//height of the map
	private int entranceLocX;		//x location of the entrance
	private int entranceLocY;		//y location of the entrance
	private int exitLocX;			//x location of the exit
	private int exitLocY;			//y location of the exit
	public Tile[][] tileArray;		//array of Tiles representing the Map
	
	/**
	 * Constructs a Map and it's corresponding tileArray.
	 * @param width
	 *		width of the map
	 * @param height
	 * 		height of the map
	 * @param entranceLocX
	 * 		x location of the entrance
	 * @param entranceLocY
	 * 		y location of the entrance
	 * @param exitLocX
	 * 		x location of the exit
	 * @param exitLocY
	 * 		y location of the exit
	 */
	public Map(int width, int height, int entranceLocX, int entranceLocY, int exitLocX, int exitLocY){
		this.width = width;
		this.height = height;
		this.entranceLocX = entranceLocX;
		this.entranceLocY = entranceLocY;
		this.exitLocX = exitLocX;
		this.exitLocY = exitLocY;
		this.tileArray = new Tile[width][height];
	}

	//getters and setters
	
	public int getWidth() {
		return width;
	}


	public void setWidth(int width) {
		this.width = width;
	}


	public int getHeight() {
		return height;
	}


	public void setHeight(int height) {
		this.height = height;
	}


	public int getEntranceLocX() {
		return entranceLocX;
	}


	public void setEntranceLocX(int entranceLocX) {
		this.entranceLocX = entranceLocX;
	}


	public int getEntranceLocY() {
		return entranceLocY;
	}


	public void setEntranceLocY(int entranceLocY) {
		this.entranceLocY = entranceLocY;
	}


	public int getExitLocX() {
		return exitLocX;
	}


	public void setExitLocX(int exitLocX) {
		this.exitLocX = exitLocX;
	}


	public int getExitLocY() {
		return exitLocY;
	}


	public void setExitLocY(int exitLocY) {
		this.exitLocY = exitLocY;
	}
}
