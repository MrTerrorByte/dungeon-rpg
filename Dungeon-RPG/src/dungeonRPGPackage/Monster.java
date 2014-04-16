package dungeonRPGPackage;

import java.awt.image.BufferedImage;

public class Monster {
	
	private double maxHealth;		//the maximum health the monster has
	private double currHealth;		//how much health the monster currently has
	private int level;
	private double attack;			//how much damage it does
	private int gold;				//how much gold it gives when it's defeated
	private int expGain;			//how much exp it gives when it's defeated
	private BufferedImage image;
	
	/**
	 * Constructor for monster: sets default maxHealth value to 100 multiplied by its level.
	 */
	public Monster(int level, BufferedImage image){
		this.level = level;
		this.attack = 5*Math.pow(level, 2) + 20;
		this.maxHealth = 100*(Math.pow(this.level, 2))+100;
		this.gold = (int) (100*(Math.pow(this.level, 2))+100);
		this.expGain = 10*(level)+50;
		this.currHealth = this.maxHealth;
		this.image = image;
	}
	
	public int getExpGain(){
		return this.expGain;
	}
	
	public int getGoldGain(){
		return this.gold;
	}
	
	public double getCurrHealth(){
		return this.currHealth;
	}
	
	public double getAttack(){
		return this.attack;
	}
	
	public void setCurrHealth(double currHealth){
		this.currHealth = currHealth;
	}
	
	public int getLevel(){
		return this.level;
	}
	
	public void setLevel(int level){
		this.level = level;
	}
	
	public BufferedImage getImage(){
		return image;
	}
	
	public void setImage(BufferedImage image){
		this.image = image;
	}
}
