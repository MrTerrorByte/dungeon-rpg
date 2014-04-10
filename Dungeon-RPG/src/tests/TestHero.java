package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeonRPGPackage.Hero;
import dungeonRPGPackage.Map;
import dungeonRPGPackage.Map.Tile;
import dungeonRPGPackage.Shield;
import dungeonRPGPackage.Weapon;

/**
 * Tests Hero Class
 * @author rrienton
 *
 */
public class TestHero {

	Map map = new Map(0, 0, 25, 25);
	Weapon weapon = new Weapon("Sword","Sword",0,0);
	Shield shield = new Shield("Shield","Shield",0,0);
	Hero hero = new Hero("Jordan", map, weapon, shield);
	
	@Test
	/**
	 * Tests Hero Movement
	 */
	public void testMove() {
		//move Hero to a valid space
		//set 5,5 to a valid grass space
		hero.getMap().getTileArray()[5][5] = Tile.GRASS;
		assertEquals("Hero can move to a grass tile",0, hero.move(5,5));
		
		//move Hero to an invalid space
		//move to 1,1 which is a rock space
		assertEquals("Hero cannot move to a rock tile", -1, hero.move(1, 1));
		
		//move Hero too far left
		assertEquals("Hero cannot move out of bounds", -1, hero.move(-10, 5));
		
		//move Hero too far right
		assertEquals("Hero cannot move out of bounds", -1, hero.move(20,5));
		
		//move Hero too far up
		assertEquals("Hero cannot move out of bounds", -1, hero.move(2, 20));
		
		//mover Hero too far down
		assertEquals("Hero cannot move out of bounds", -1, hero.move(2, -10));
	}

}
