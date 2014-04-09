package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import dungeonRPGPackage.Hero;
import dungeonRPGPackage.Map;

public class TestHero {

	Hero hero = new Hero();
	Map map = new Map(100, 100, 0, 0, 0, 0);
	
	@Test
	public void testCanMove() {
		map.tileArray[5][5].setBlocked(true);
		assertEquals(-1,hero.move(map.tileArray[5][5]));
	}

}
