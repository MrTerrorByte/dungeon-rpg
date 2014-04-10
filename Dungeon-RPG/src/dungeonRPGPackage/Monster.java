package dungeonRPGPackage;

public class Monster {
	
	private double maxHealth;		//the maximum health the monster has
	private double currHealth;		//how much health the monster currently has
	private int level;
	private double attack;			//how much damage it does
	private int gold;				//how much gold it gives when it's defeated
	
	/**
	 * Constructor for monster: sets default maxHealth value to 100 multiplied by its level.
	 */
	public Monster(int level){
		this.level = level;
		this.attack = 
		this.maxHealth = 100*(Math.pow(this.level, 2));
		this.gold = (int) (100*(Math.pow(this.level, 2)));
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
}
