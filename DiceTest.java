package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class DiceTest {
	Dice dice = new Dice();
	
	@Test
	@DisplayName("Checks dice roll is interger from 1-6")
	void testRoll() {
		int x = dice.roll();
		boolean check;
		
		if(x >= 1 && x <=6) {
			check = true;
		}
		else
			check = false;
			
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks that method returns (Winner,Loser)")
	void testGoesFirst() {
		Player[] testPlayers = new Player[2];
		testPlayers[0] = new Player("Player One");
		testPlayers[1] = new Player("Player Two");
		boolean check;

		Player[] testedPlayers = dice.goesFirst(testPlayers[0], testPlayers[1]);
		
		if ((testedPlayers[0].getName() == "Player One") 
			|| (testedPlayers[0].getName() == "Player Two")
			&& (testedPlayers[1].getName() == "Player One")
			|| (testedPlayers[1].getName() == "Player Two")) {
			check = true;
		}
		
		else 
			check = false;
		
		assertTrue(check);
			
	}

}
