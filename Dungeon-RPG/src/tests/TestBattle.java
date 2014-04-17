package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeonRPGPackage.Battle;
import dungeonRPGPackage.Hero;
import dungeonRPGPackage.Map;
import dungeonRPGPackage.Monster;
import dungeonRPGPackage.Potion;
import dungeonRPGPackage.Shield;
import dungeonRPGPackage.Weapon;

public class TestBattle {

	Map map = new Map(0, 0, 25, 25);
	Weapon weapon = new Weapon("Sword","Sword",50,10);
	Shield shield = new Shield("Shield","Shield",100,10);
	Hero hero = new Hero("Jordan", map, weapon, shield);
	
	@Test
	public void testEquip() {
		assertEquals(200, (int)hero.getCurrHealth()); //with shield's hpboost
		Monster monster = new Monster("", 0, null);
		
		boolean Turn = true;
		while(monster.getCurrHealth() > 0){
			Battle.attack(hero, monster, Turn);
			Turn = !Turn;
		}
		assertEquals(180, (int)hero.getCurrHealth()); //monster hit hero once in the battle
		assertEquals(50, weapon.getCurrExp()); //sword gained exp
		assertEquals(50, shield.getCurrExp()); //shield gained exp
		assertEquals(1100, hero.getGold());
		
		monster = new Monster(null, 0, null);
		Turn = true;
		while(monster.getCurrHealth() > 0){
			Battle.attack(hero, monster, Turn);
			Turn = !Turn;
		}
		
		assertEquals(100, weapon.getCurrExp());
		assertEquals(1, weapon.getLevel());
		assertEquals(1, shield.getLevel());
		assertEquals(220, (int)hero.getMaxHealth()); //shield leveled up, so hero's max hp increases
		assertEquals(1200, hero.getGold());
	}
	
	@Test
	public void testPotion(){
		Monster monster = new Monster(null, 1, null);
		
		boolean Turn = false;
		while(monster.getCurrHealth() > 0){
			Battle.attack(hero, monster, Turn);
			Turn = !Turn;
		}
		
		assertEquals(100, (int)hero.getCurrHealth()); //monster at lvl 1 hit hero once in the battle
		Battle.usePotion(hero, new Potion("", "", 0.25));
		assertEquals(150, (int)hero.getCurrHealth());
		Battle.usePotion(hero, new Potion("", "", 0.25));
		assertEquals(200, (int)hero.getCurrHealth());
		Battle.usePotion(hero, new Potion("", "", 0.25));
		assertEquals(200, (int)hero.getCurrHealth()); //can't heal pass max hp
	}

}
