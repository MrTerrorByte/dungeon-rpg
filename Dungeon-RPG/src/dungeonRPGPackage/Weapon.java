package dungeonRPGPackage;

/**
 * @author jordan
 */
public class Weapon extends Item{
	
	private double attack;			//determines how much damage is dealt when hit by this weapon
	private int level;				//level of the weapon increases when the required experience is gained
	private int currExp;			//current amount of experience points the weapon has
	private int reqExp;				//the required amount of experience points needed to level up
	private double attackGain;		//determines how much attack is gained after levelling up
	
	public Weapon(String name, String description, double attack, double attackGain){
		super(name, description);
		this.level = 0;
		this.attack = attack;
		this.attackGain = attackGain;
		this.currExp = 0;
		this.reqExp = 100;
	}

	/**
	 * This method is called after defeating monsters. If the exp gained is
	 * enough, then levelUp() is called to increase the level, required exp,
	 * and attack.
	 * 
	 * @param expGain : is the amount of exp gained from defeating a monster
	 */
	public void gainExp(int expGain){
		this.currExp += expGain;
		if(this.currExp >= this.reqExp){
			levelUp();
		}
	}
	
	/**
	 * When the required exp is reached, this method is called to increase
	 * the level, required exp to get to the next level, and attack.
	 * Required experience and attack increase exponentially based on level.
	 */
	public void levelUp(){
		this.level++;
		reqExp = (int)(100*Math.pow(level,2)+200);
		attack += 10*Math.pow(level, 2)+attackGain;
	}
	
	public double getAttack() {
		return attack;
	}

	public void setAttack(double attack) {
		this.attack = attack;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getCurrExp() {
		return currExp;
	}

	public void setCurrExp(int currExp) {
		this.currExp = currExp;
	}

	public int getReqExp() {
		return reqExp;
	}

	public void setReqExp(int reqExp) {
		this.reqExp = reqExp;
	}
}
