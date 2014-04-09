package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeonRPGPackage.Hero;
import dungeonRPGPackage.Map;

public class TestHero {

	Hero hero = new Hero();
	Map map = new Map(100, 100, 0, 0, 0, 0);
	
	@Test
	public void testMove() {
		hero.setMap(map);
		map.tileArray[5][5].setBlocked(true);
		assertEquals(-1, hero.move(5,5));
		assertEquals(-1, hero.move(-1000, 1000));
		assertEquals(0, hero.move(0,0));
		assertEquals(map.tileArray[0][0], hero.getTile());
		
	}

}
