package dungeonRPGPackage;

/**
 * @author jordan
 */
public class Potion extends Item{

	private double healAmount;			//the percentage of the hero's hp that gets restored
	
	public Potion(String name, String description, double healAmount) {
		super(name, description);
		this.healAmount = healAmount;
	}
	
	public double getHealAmount(){
		return this.healAmount;
	}
}
