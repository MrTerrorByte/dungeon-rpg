package dungeonRPGPackage;

public class Battle {
	
	/**
	 * The attack method is called in battles.
	 * 
	 * @param hero : The hero to attack with or from
	 * @param monster : The monster to attack with or from
	 * @param Turn : When true, it is the hero's turn, otherwise it's the monster's turn
	 */
	public static int attack(Hero hero, Monster monster, boolean Turn){
		Weapon heroWeapon = hero.getWeapon();
		if(Turn){ //hero's turn
			monster.setCurrHealth(monster.getCurrHealth()- heroWeapon.getAttack());
			if(monster.getCurrHealth() <= 0){
				return endBattle("hero", hero, monster);
			}
			Turn = false;
		}
		else{ //monster's turn
			hero.setCurrHealth(hero.getCurrHealth() - monster.getAttack());
			if(hero.getCurrHealth() <= 0){
				return endBattle("monster", hero, monster);
			}
		}
		return -1;
	}
	
	/**
	 * If the hero has potions left, use it to replenish the hero's current health.
	 * 
	 * @param hero : the hero that is using the potion
	 * @param potion : the potion being used
	 */
	public static void usePotion(Hero hero, Potion potion){
		if(hero.getPotionCount() <= 0){
			return;
		}
		hero.setPotionCount(hero.getPotionCount()-1);
		hero.setCurrHealth(hero.getCurrHealth()+hero.getMaxHealth()*potion.getHealAmount());
	}
	
	/**
	 * This method is called after a battle is over.
	 * 
	 * @param victor : if the hero won, then he gets the exp and gold gains. if the monster won, then do endgame conditions.
	 * @param hero : the hero in the battle
	 * @param monster : the monster in the battle
	 */
	public static int endBattle(String victor, Hero hero, Monster monster){
		if(victor.equals("hero")){ //if the victor is hero, then have its weapon gain exp
			hero.getWeapon().gainExp(monster.getExpGain());
			hero.getShield().gainExp(monster.getExpGain());
			hero.updateHp();
			hero.setGold(hero.getGold() + monster.getGoldGain());
			return 1;
		}
		return -1;
	}
}
