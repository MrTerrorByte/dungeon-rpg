package dungeonRPGPackage;
/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Map map;
	private Tile tile;				//the tile that the hero is standing on
	private double maxHealth;		//the maximum health the hero has
	private double currHealth;		//how much health the hero currently has
	private Item inventory[][];		//hero's inventory which holds items like weapons and potions
	
	/**
	 * Constructor for hero: sets default maxHealth value to 100 and initializes the inventory.
	 */
	public Hero(){
		this.maxHealth = 100;
		this.inventory = new Item[10][10];
	}
	
	/**
	 * 
	 * @param destX : the x-coordinate of the destination tile
	 * @param destY : the y-coordinate of the destination tile
	 * @return : 0 on success, and -1 on failure
	 */
	public int move(int destX, int destY){
		if(canMove(destX, destY) == false){
			return -1;
		}
		else{
			this.tile = this.map.tileArray[destX][destY];
			return 0;
		}
	}
	
	/**
	 * Tests whether or not the hero can move to the map tile specified
	 * by destX and destY.
	 * 
	 * @param destX : the x-coordinate of the destination tile
	 * @param destY : the y-coordinate of the destination tile
	 * @return : true if the hero can move to the tile, otherwise false
	 */
	private boolean canMove(int destX, int destY){
		if(destX < 0 || destY < 0 || destX >= this.map.getWidth() || destY >= this.map.getHeight()){
			return false;
		}
		
		return !(this.map.tileArray[destX][destY].isBlocked());
	}
	
	public Tile getTile(){
		return this.tile;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	public double getCurrHealth(){
		return this.currHealth;
	}
	
	public double getMaxHealth(){
		return this.maxHealth;
	}
	
	public void setCurrHealth(double health){
		this.currHealth = health;
	}
	
	public void setMaxHealth(double maxHealth){
		this.maxHealth = maxHealth;
	}
	
}
