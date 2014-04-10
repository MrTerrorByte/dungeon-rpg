package dungeonRPGPackage;

public class Battle {
	
	/**
	 * The attack method is called in battles.
	 * 
	 * @param hero : The hero to attack with or from
	 * @param monster : The monster to attack with or from
	 * @param Turn : When true, it is the hero's turn, otherwise it's the monster's turn
	 * @return : 0 on success, -1 on failure
	 */
	public static int attack(Hero hero, Monster monster, boolean Turn){
		Weapon heroWeapon = hero.getWeapon();
		if(Turn){ //hero's turn
			monster.setCurrHealth(monster.getCurrHealth()- heroWeapon.getAttack());
			if(monster.getCurrHealth() <= 0){
				endBattle("hero", hero, monster);
			}
			Turn = false;
		}
		else{ //monster's turn
			hero.setCurrHealth(hero.getCurrHealth() - monster.getAttack());
			if(hero.getCurrHealth() <= 0){
				endBattle("monster", hero, monster);
			}
		}
		return 0;
	}
	
	public static void endBattle(String victor, Hero hero, Monster monster){
		if(victor.equals("hero")){ //if the victor is hero, then have its weapon gain exp
			hero.getWeapon().gainExp(monster.getExpGain());
			hero.setGold(hero.getGold() + monster.getGoldGain());
		}
	}
}
