package dungeonRPGPackage;

import dungeonRPGPackage.Map.Tile;

/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Map map;				//current Map Hero is on
	private Map dungeonMaps[];		//all the Maps in the dungeon in order
	private double maxHealth;		//the maximum health the hero has
	private double currHealth;		//how much health the hero currently has
	private int potionCount;		//a hero starts with 5 potions, but can buy more later
	private Potion potionArray[];	//array that holds all the potions
	private String name;			//hero's name, picked by user
	private int x,y;				//hero's x and y coordinate on the map
	private Weapon weapon;			//hero's equipped weapon
	private Shield shield;			//hero's equipped shield
	private int gold;				//how much gold the hero has
	
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
		this.potionCount = 5;
		this.potionArray = new Potion[5];
		addPotions();
		this.x = map.getEntranceLocX();				//set Hero's initial x pos to Entrance X
		this.y = map.getEntranceLocY();				//set Hero's initial y pos to Entrance Y
		this.gold = 1000;
	}
	
	/**
	 * initializes the potions array.
	 */
	public void addPotions(){
		for(int index = 0; index < potionArray.length; index++){
			potionArray[index] = new Potion("potion","heals 25% hp",0.25);
		}
	}
	
	/**
	 * When the shield levels up, the hero's hp must be increased.
	 */
	public void updateHp(){
		this.maxHealth = 100+shield.getHpBoost();
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
		//if where we're moving is out of bounds
		if(destX < 0 || destY < 0 || destX >= this.map.getWidth() || destY >= this.map.getHeight()){
			return false;
		}
		//if where we're moving there is a rock
		if(map.getTileArray()[destX][destY] == Tile.ROCK){
			return false;
		}
		
		return true;
	}
	
	public Potion[] getPotionArray(){
		return this.potionArray;
	}
	
	public int getPotionCount(){
		return this.potionCount;
	}
	
	public void setPotionCount(int count){
		this.potionCount = count;
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
		if(this.currHealth > this.maxHealth)
			this.currHealth = this.maxHealth;
	}
	
	public void setMaxHealth(double maxHealth){
		this.maxHealth = maxHealth;
	}
	
	public Map getMap(){
		return this.map;
	}
	
	public int getGold(){
		return this.gold;
	}
	
	public void setGold(int gold){
		this.gold = gold;
	}
	
	public Weapon getWeapon(){
		return this.weapon;
	}
	
	public Shield getShield(){
		return this.shield;
	}
}
