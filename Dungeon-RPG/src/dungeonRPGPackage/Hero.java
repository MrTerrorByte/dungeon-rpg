package dungeonRPGPackage;
/**
 * 
 * @author jordan
 *
 */
public class Hero {
	private Tile tile;				//the tile that the hero is standing on
	private double maxHealth;		//the maximum health the hero has
	private double currHealth;		//how much health the hero currently has
	
	/**
	 * Constructor for hero: sets default maxHealth value to 100.
	 */
	public Hero(){
		this.maxHealth = 100;
	}
	
	/**
	 * 
	 * @return 0 if move was succesful, else return -1
	 */
	public int move(Tile destTile){
		return 0;
	}
	
	/**
	 * 
	 * @param destTile 
	 * @return
	 */
	private boolean canMove(Tile destTile){
		return false;
	}
	
}
