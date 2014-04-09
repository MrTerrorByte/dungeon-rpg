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
	 * If the hero can move to the destTile, then move it.
	 * 
	 * @return 0 if move was succesful, else return -1
	 */
	public int move(Tile destTile){
		if(canMove(destTile) == false){
			return -1;
		}
		else{
			this.tile = destTile;
			return 0;
		}
	}
	
	/**
	 * Checks whether the hero can move to the destTile or not.
	 * 
	 * @param destTile : The destination tile that is being tested.
	 * @return true if the hero can move to the destTile, false otherwise.
	 */
	private boolean canMove(Tile destTile){
		if(destTile == null || destTile.isBlocked() == true){
			return false;
		}
		
		return true;
	}
	
}
