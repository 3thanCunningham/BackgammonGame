package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;




class PlayerTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOutput = System.out;
	private Player[] players = new Player[2];
	
	private boolean check;
	
	@BeforeEach
	void setUp() {
		players[0] = new Player("Test Player");
		
	}

	@Test
	@DisplayName("Checks that an object has been created")
	void testPlayer() {
		assertNotNull(players[0]);
	}

	
	@Test
	@DisplayName("Checks getName() returns correct string")
	void testGetName() {
		assertEquals("Test Player",players[0].getName());
	}

	@Test
	@DisplayName("Checks that players roll can be set to int")
	void testSetRoll() {
		players[0].setRoll(1, 6);
		
		if(players[0].getRoll(0)==1 && players[0].getRoll(1)==6) {
			check = true;
		}
		else 
			check = false;
		
		assertTrue(check);
	}
	
	@Test
	@DisplayName("Checks getRoll() returns correct integer")
	void testGetRoll() {
		players[0].setRoll(1, 6);
		
		assertEquals(1,players[0].getRoll(0));
		assertEquals(6,players[0].getRoll(1));
	}

	@Test
	@DisplayName("Check if board type can be set")
	void testSetBoard() {
		players[0].setBoard(BoardType.ANTICLOCKWISE);
		if(players[0].getBoardType() == BoardType.ANTICLOCKWISE) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
		
		players[0].setBoard(BoardType.CLOCKWISE);
		if(players[0].getBoardType() == BoardType.CLOCKWISE) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
		
	}

	@Test
	@DisplayName("Checks if correct board type is returned")
	void testGetBoardType() {
		players[0].setBoard(BoardType.ANTICLOCKWISE);
		
		assertEquals(BoardType.ANTICLOCKWISE,players[0].getBoardType());
		
		players[0].setBoard(BoardType.CLOCKWISE);
		
		assertEquals(BoardType.CLOCKWISE,players[0].getBoardType());

		
	}

	@Test
	@DisplayName("Checks if player can be set a checker colour")
	void testSetColour() {
		players[0].setColour(CheckerColour.RED);
		
		if (players[0].getColour()==CheckerColour.RED) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
		
		players[0].setColour(CheckerColour.WHITE);
		if (players[0].getColour()==CheckerColour.WHITE) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks if correct checker colour is returned")
	void testGetColour() {
		players[0].setColour(CheckerColour.RED);
		
		assertEquals(CheckerColour.RED,players[0].getColour());
		
		players[0].setColour(CheckerColour.WHITE);
		
		assertEquals(CheckerColour.WHITE,players[0].getColour());
	}


	@Test
	@DisplayName("Checks if a player's pipcount can be set")
	void testSetPipCount() {
		players[0].setPipCount(23);
		if(players[0].getPipCount()==23) {
			check = true;
		}
		else
			check =false;
		
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks if the correct pip count is returned")
	void testGetPipCount(){
		players[0].setPipCount(55);
		
		assertEquals(55,players[0].getPipCount());
	}
	
	@Test
	@DisplayName("Checks if the correct pip count is calculated")
	void testCalculatePipCount() {
		Board board = new Board();
		assertEquals(167,players[0].calculatePipCount(board, players[0]));
	}


	@Test
	@DisplayName("Checks to see if a double roll is detected")
	void testIsDouble() {
		players[0].setRoll(2, 2);
		assertTrue(players[0].isDouble());
		
		players[0].setRoll(1, 3);
		assertFalse(players[0].isDouble());
	}

	@Test
	@DisplayName("Checks to see if a player's score can be set")
	void testSetScore() {
		players[0].setScore(7);
		
		if(players[0].getScore()==7) {
			check = true;
		}
		else 
			check = false;
		
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks to see if a players score can be increase by one")
	void testUpScore() {
		players[0].setScore(9);
		players[0].upScore();
		assertEquals(10,players[0].getScore());
	}

	@Test
	@DisplayName("Checks to see if correct player score is returned")
	void testGetScore() {
		players[0].setScore(10);
		
		assertEquals(10,players[0].getScore());
	}

	@Test
	@DisplayName("Checks to see if correct winner is returned")
	void testIsMatchWinner() {
		players[1] = new Player("Test Player 2");
		players[0].setScore(8);
		players[1].setScore(3);
		
		assertEquals(players[0],players[0].isMatchWinner(players));
	}

	@Test
	@DisplayName("Checks to see if correct winner is announced")
	void testAnnounceMatchWinner() {
		players[1] = new Player("Test Player 2");
		players[0].setScore(8);
		players[1].setScore(3);
		
		System.setOut(new PrintStream(output));
		players[0].announceMatchWinner(players);
		
		assertEquals(players[0].getName() + " Won the Match! Congratulations!\r\n", output.toString());
		System.setOut(originalOutput);
		
		output.reset();
		
		players[0].setScore(3);
		players[1].setScore(9);
		
		System.setOut(new PrintStream(output));
		players[0].announceMatchWinner(players);
		
		assertEquals(players[1].getName() + " Won the Match! Congratulations!\r\n", output.toString());
		System.setOut(originalOutput);
	}

	@Test
	@DisplayName("Checks to see if cube is given to player")
	void testGiveCube() {
		players[0].giveCube();
		
		if(players[0].hasCube()==true) {
			check = true;
		}
		else
			check = false;
				
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks to see if cube is taken from player")
	void testTakeCube() {
		players[0].takeCube();
		
		if(players[0].hasCube()==false) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
	}

	@Test
	@DisplayName("Chcks to see if a player has a cube")
	void testHasCube() {
		players[0].giveCube();
		
		assertTrue(players[0].hasCube());
		
		players[0].takeCube();
		
		assertFalse(players[0].hasCube());
	}

}
