package dungeonRPGPackage;

/**
 * @author jordan
 */
public class Shield extends Item {

	private double hpBoost;			//equipping a shield increases the hero's maxHp by this amount
	private int level;				//current level of the shield
	private int currExp;			//how much exp the shield currently has
	private int reqExp;				//how much exp is required to level up
	private double hpGain;			//how much the hpBoost is increased after leveling up.
	
	public Shield(String name, String description, double hpBoost, double hpGain) {
		super(name, description);
		this.currExp = 0;
		this.level = 0;
		this.reqExp = 100;
		this.hpBoost = hpBoost;
		this.hpGain = hpGain;
	}
	
	/**
	 * This method is called after defeating monsters. If the exp gained is
	 * enough, then levelUp() is called to increase the level, required exp,
	 * and hpGain.
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
	 * Required experience and hpGain increase exponentially based on level.
	 */
	public void levelUp(){
		this.level++;
		reqExp = (int)(100*Math.pow(level,2)+200);
		hpBoost += 10*Math.pow(level, 2)+hpGain;
	}
	
	public double getHpBoost() {
		return hpBoost;
	}

	public void setHpBoost(double hpBoost) {
		this.hpBoost = hpBoost;
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
