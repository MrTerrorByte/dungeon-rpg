package dungeonRPGPackage;
/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Map map;
	private double maxHealth;		//the maximum health the hero has
	private double currHealth;		//how much health the hero currently has
	private Item inventory[][];		//hero's inventory which holds items like weapons and potions
	private String name;			//hero's name, picked by user
	private int x,y;				//hero's x and y coordinate on the map
	private Weapon weapon;			//hero's equipped weapon
	private Shield shield;			//hero's equipped shield
	
	/**
	 * Constructor for hero: sets default maxHealth value to 100 and initializes the inventory.
	 * Also equips a shield and weapon.
	 */
	public Hero(String name, Map map, Weapon weapon, Shield shield){
		this.name = name;
		this.maxHealth = 100+shield.getHpBoost();
		this.currHealth = maxHealth;
		this.weapon = weapon;
		this.shield = shield;
		this.map = map;
		this.inventory = new Item[10][10];
		this.x = map.getEntranceLocX();				//set Hero's initial x pos to Entrance X
		this.y = map.getEntranceLocY();				//set Hero's initial y pos to Entrance Y
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
			this.x = destX;
			this.y = destY;
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
		
		return true;
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public void setMap(Map map){
		this.map = map;
	}
	
	public int getX(){
		return this.x;
	}
	
	public void setX(int x){
		this.x = x;
	}
	
	public int getY(){
		return this.y;
	}
	
	public void setY(int y){
		this.y = y;
	}
	
	public double getCurrHealth(){
		return this.currHealth;
	}
	
	public double getMaxHealth(){
		return this.maxHealth;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setCurrHealth(double health){
		this.currHealth = health;
	}
	
	public void setMaxHealth(double maxHealth){
		this.maxHealth = maxHealth;
	}
	
}
