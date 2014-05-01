package dungeonRPGPackage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import dungeonRPGPackage.Map.Tile;

/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Map map;					//current Map Hero is on
	private Map dungeonMaps[];			//all the Maps in the dungeon in order
	private double maxHealth;			//the maximum health the hero has
	private double currHealth;			//how much health the hero currently has
	private int potionCount;			//a hero starts with 5 potions, but can buy more later
	private String name;				//hero's name, picked by user
	private int x,y;					//hero's x and y coordinate on the map
	private Weapon weapon;				//hero's equipped weapon
	private Shield shield;				//hero's equipped shield
	private int gold;					//how much gold the hero has
	private BufferedImage backImage;	//image of Hero from behind
	private BufferedImage frontImage;	//image of Hero from front
	private BufferedImage rightImage;	//image of Hero from the right
	private BufferedImage leftImage;	//image of Hero from the left
	private int mapIndex;				//index in Dungeon array that Hero is in
	private boolean gender;				//gender of the Hero
	private boolean gameOver = false;	//whether or not the game is over for this Hero
	
	final static boolean MALE = true;
	final static boolean FEMALE = false;
	final static int NUM_MAPS = 4;
	
	/**
	 * Constructor for hero: sets default maxHealth value to 100 and initializes the inventory.
	 * Also equips a shield and weapon.
	 */
	public Hero(String name, boolean gender, Weapon weapon, Shield shield){
		this.name = name;
		this.maxHealth = 100+shield.getHpBoost();
		this.currHealth = maxHealth;
		this.weapon = weapon;
		this.shield = shield;
		this.potionCount = 5;
		this.gold = 1000;
		this.gender = gender;
		
		//set up images for the Hero
		setupImages(gender);
		
		//setup Dungeon Maps
		MapGenerator generator = new MapGenerator();
		this.dungeonMaps = new Map[5];
    	dungeonMaps[0] = generator.generateMap("src/maps/startmap.txt", 5, 9, 6, 4);
    	dungeonMaps[1] = generator.generateMap("src/maps/dungeon1.txt", 6, 4, 11, 6);
    	dungeonMaps[2] = generator.generateMap("src/maps/dungeon2.txt", 11, 6, 8, 5);
    	dungeonMaps[3] = generator.generateMap("src/maps/dungeon3.txt", 8, 5, 2, 8);
    	dungeonMaps[4] = generator.generateMap("src/maps/bossmap.txt", 2, 8, 0, 0);
		this.map = dungeonMaps[0];					//set Hero's initial Map to the start map
		this.mapIndex = 0;
		this.x = map.getEntranceLocX();				//set Hero's initial x pos to Entrance X
		this.y = map.getEntranceLocY();				//set Hero's initial y pos to Entrance Y
	}
	
	/**
	 * Loads the correct images for the the Hero based on gender
	 * @param gender	gender of the Hero
	 */
	private void setupImages(boolean gender){
		//male
		if(gender == MALE){
			try {
				this.backImage = ImageIO.read(new File("src/images/maleBackStanding.png"));
				this.frontImage = ImageIO.read(new File("src/images/maleFrontStanding.png"));
				this.rightImage = ImageIO.read(new File("src/images/maleRightStanding.png"));
				this.leftImage = ImageIO.read(new File("src/images/maleLeftStanding.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//female
		else{
			try {
				this.backImage = ImageIO.read(new File("src/images/femaleBackStanding.png"));
				this.frontImage = ImageIO.read(new File("src/images/femaleFrontStanding.png"));
				this.rightImage = ImageIO.read(new File("src/images/femaleRightStanding.png"));
				this.leftImage = ImageIO.read(new File("src/images/femaleLeftStanding.png"));
			} catch (IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean isGender() {
		return gender;
	}

	public void setGender(boolean gender) {
		this.gender = gender;
		setupImages(gender);
	}

	public BufferedImage getBackImage() {
		return backImage;
	}

	public BufferedImage getFrontImage() {
		return frontImage;
	}

	public BufferedImage getRightImage() {
		return rightImage;
	}

	public BufferedImage getLeftImage() {
		return leftImage;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setShield(Shield shield) {
		this.shield = shield;
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
			//if Hero is at the exit location of the current map, move to next map
			if(this.x == map.getExitLocX() && this.y == map.getExitLocY()){
				if(mapIndex < NUM_MAPS){
					map = dungeonMaps[++mapIndex];
					return 0;
				}
			}
			else if(this.x == map.getEntranceLocX() && this.y == map.getEntranceLocY()){
				if(mapIndex > 0){
					map = dungeonMaps[--mapIndex];
					return 0;
				}
			}
			
			if(mapIndex > 0 && Math.random()*10.0 <= 0.5){
				return 1;
			}
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
		//if where we're moving there is a rock or water or tree or lava or dragon or shop
		if(map.getTileArray()[destX][destY] == Tile.ROCK || map.getTileArray()[destX][destY] == Tile.WATER
				|| map.getTileArray()[destX][destY] == Tile.TREE || map.getTileArray()[destX][destY] == Tile.LAVA
				|| map.getTileArray()[destX][destY] == Tile.DRAGON || map.getTileArray()[destX][destY] == Tile.SHOP){
			return false;
		}
		
		return true;
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
	
	public int getMapIndex(){
		return mapIndex;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
