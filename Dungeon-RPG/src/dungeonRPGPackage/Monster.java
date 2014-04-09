package dungeonRPGPackage;

public class Monster {
	
	private double maxHealth;		//the maximum health the monster has
	private double currHealth;		//how much health the monster currently has
	private int level;
	
	/**
	 * Constructor for monster: sets default maxHealth value to 100 multiplied by its level.
	 */
	public Monster(int level){
		this.maxHealth = 100*level;
		this.level = level;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
