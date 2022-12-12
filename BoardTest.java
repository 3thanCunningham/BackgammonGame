package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTest {
	private Board board = new Board();
	private Checker RedChecker;
	private Checker WhiteChecker;
	private boolean check;

	
	@Test
	@DisplayName("Checks that an object has been created")
	void testBoard() {
		assertNotNull(board);
	}

	@Test
	@DisplayName("Checks that a stack from board is returned")
	void testGetStack() {
		assertNotNull(board.getStack(5));
	}

	@Test
	@DisplayName("Checks that correct stack sizes are returned")
	void testGetStackSize() {
		// From initial pip positions of board
		assertEquals(5,board.getStackSize(5));
		assertEquals(5,board.getStackSize(12));
		assertEquals(5,board.getStackSize(11));
		assertEquals(5,board.getStackSize(18));
		assertEquals(3,board.getStackSize(16));
		assertEquals(3,board.getStackSize(7));
		assertEquals(2,board.getStackSize(23));
		assertEquals(2,board.getStackSize(0));
	}

	@Test
	@DisplayName("Checks that an empty stack is detected")
	void testIsStackEmpty() {
		// Check empty stacks
		assertTrue(board.isStackEmpty(1));
		assertTrue(board.isStackEmpty(2));
		
		// Check stacks with pips
		assertFalse(board.isStackEmpty(5));
		assertFalse(board.isStackEmpty(12));
	}

	@Test
	@DisplayName("Checks that correct colours of stacks are returned")
	void testGetStackColour() {
		// From initial pip positions of board
		assertEquals(CheckerColour.WHITE,board.getStackColour(5));
		assertEquals(CheckerColour.WHITE,board.getStackColour(12));
		assertEquals(CheckerColour.RED,board.getStackColour(11));
		assertEquals(CheckerColour.RED,board.getStackColour(18));
		assertEquals(CheckerColour.RED,board.getStackColour(16));
		assertEquals(CheckerColour.WHITE,board.getStackColour(7));
		assertEquals(CheckerColour.WHITE,board.getStackColour(23));
		assertEquals(CheckerColour.RED,board.getStackColour(0));
	}


	@Test
	@DisplayName("Checks that the lasrgest stack on board is returned")
	void testMaxStackSize() {
		// From initial board set up
		assertEquals(5,board.maxStackSize());
	}


	@Test
	@DisplayName("Checks that correct bear of player is returned")
	void testGetBear() {
		// Initial bears
		assertEquals(0,board.getBear(1));
		assertEquals(0,board.getBear(2));
	}

	
	@Test
	@DisplayName("Checks that correct bear of player is incremented")
	void testBearOff() {
		board.bearOff(CheckerColour.RED);
		assertEquals(1,board.getBear(1));
		
		board.bearOff(CheckerColour.WHITE);
		board.bearOff(CheckerColour.WHITE);
		assertEquals(2,board.getBear(2));
	}


	@Test
	@DisplayName("Checks that empty stack index is detected")
	void testStackIndexEmpty() {
		// From initial board set up
		assertFalse(board.StackIndexEmpty(0, 0));
		assertTrue(board.StackIndexEmpty(1, 0));
	}
	

	@Test
	@DisplayName("Checks pip movement method")
	void testMove() {
		// Move pip to empty stack
		board.move(0, 1);
		assertFalse(board.isStackEmpty(1));
		
		
		board.move(1, 2);
		assertFalse(board.isStackEmpty(2));
		// Check if stack pip left is now empty
		assertTrue(board.isStackEmpty(1)); 
	}


	@Test
	@DisplayName("Check method to add pips to bar")
	void testAddToBar() {
		// Bar is initially empty
		assertFalse(board.hasCheckerOnBar());
		
		// Put first two red pip on bar
		board.addToBar(0);
		board.addToBar(0);
		
		// Check there is an object in bar now
		assertNotNull(board.getBar());
		
		// Check the stack the two pips were from is now empty
		assertTrue(board.isStackEmpty(0));
		
	}

	@Test
	@DisplayName("Check to see if pip is removed from bar")
	void testRemoveFromBar() {
		// Bar is initially empty
		assertFalse(board.hasCheckerOnBar());
		
		// Push a pip onto bar
		board.addToBar(0);
		
		// Check there is an object in bar now
		assertNotNull(board.getBar());
		
		// Remove pip and check if bar is now empty
		board.removeFromBar();
		assertFalse(board.hasCheckerOnBar());
		
	}

	@Test
	@DisplayName("Check if a pip on the bar is detected")
	void testHasCheckerOnBar() {
		// Bar is initially empty
		assertFalse(board.hasCheckerOnBar());
				
		// Push a pip onto bar
		board.addToBar(0);
				
		// Check there is an object in bar now
		assertNotNull(board.hasCheckerOnBar());
	}

	@Test
	@DisplayName("Check that pip on bar can be returned")
	void testGetBar() {
		WhiteChecker = new Checker(CheckerColour.WHITE);
		RedChecker = new Checker(CheckerColour.RED);
		
		// Add red pip to bar and check
		board.addToBar(0);
		assertEquals(RedChecker.toString(),board.getBar().toString());
		board.removeFromBar();
		
		// Add white pip to bar and check
		board.addToBar(23);
		assertEquals(WhiteChecker.toString(),board.getBar().toString());
	}

	@Test
	@DisplayName("Check that checker can be pushed to a stack")
	void testPushToStack() {
		RedChecker = new Checker(CheckerColour.RED);
		WhiteChecker = new Checker(CheckerColour.WHITE);
		
		// Push white checker to empty stack and check
		board.pushToStack(1, WhiteChecker.getColour());
		assertEquals(WhiteChecker.getColour(),board.getStackColour(1));
		
		// Push red checker to empty stack and check
		board.pushToStack(2, RedChecker.getColour());
		assertEquals(RedChecker.getColour(),board.getStackColour(2));
	}

	@Test
	@DisplayName("Check to see if end of game is detected")
	void testIsGameOver() {
		
		// Bear off some checkers and check if game ends
		for(int i=0; i<8 ; ++i) {
			board.bearOff(CheckerColour.RED);
		}
		assertFalse(board.isGameOver());
		
		// Bear off all checkers and check if game ends
		for(int i=0; i<15 ; ++i) {
			board.bearOff(CheckerColour.WHITE);
		}
		assertTrue(board.isGameOver());
		
	}

	@Test
	@DisplayName("Check to see if Home Board is full")
	void testIsHomeBoardFull() {
		
		// Check that Home Boards are not full at start of game
		assertFalse(board.isHomeBoardFull(CheckerColour.RED));
		assertFalse(board.isHomeBoardFull(CheckerColour.WHITE));
		
		// Make home boards full
		for(int i = 0; i < 2 ; ++i) {
		board.move(0, 23);
		board.move(23, 0);
		}
		
		for(int j = 0; j < 3 ; ++j) {
		board.move(16, 23);
		board.move(7,0);
		}
		
		for(int k = 0; k < 5; ++k) {
		board.move(11,23);
		board.move(18,23);
		board.move(5,0);
		board.move(12,0);
		}
		
		// Check if method returns true
		assertTrue(board.isHomeBoardFull(CheckerColour.RED));
		assertTrue(board.isHomeBoardFull(CheckerColour.WHITE));
		
	}
	
	@Test
	@DisplayName("Check if Double Cube can be doubled")
	void testDoubleCube() {
		
		board.doubleCube();
		if(board.getDoubleCube()==2) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
		
		board.doubleCube();
		if(board.getDoubleCube()==4) {
			check = true;
		}
		else
			check = false;
		
		assertTrue(check);
	
	}

	@Test
	@DisplayName("Check if double cube value can be set")
	void testSetDoubleCube() {
		board.setDoubleCube(16);
		assertEquals(16,board.getDoubleCube());
	}

	@Test
	@DisplayName("Check if double cube returns correct value")
	void testGetDoubleCube() {
		board.doubleCube();
		assertEquals(2,board.getDoubleCube());
		
		board.doubleCube();
		assertEquals(4,board.getDoubleCube());
	}
	
	@Test
	@DisplayName("Check that Win Game method works")
	void testWinGame() {
		// Check game over is false at start of game
		assertFalse(board.isGameOver());
		
		// Call win method and check if one player's bear is 15 and game is over
		board.winGame(0);
		assertEquals(15,board.getBear(1));
		assertTrue(board.isGameOver());
	}
	
	@Test
	@DisplayName("Check that correct integer is returned for winner")
	void testGetWinner() {
		// Make Red winner and check
		for(int i = 0; i<15 ; ++i) {
			board.bearOff(CheckerColour.RED);
			}
		assertEquals(0,board.getWinner());
		
		// Make White winner
		Board board2 = new Board();
		for(int j = 0; j<15 ; ++j) {
			board2.bearOff(CheckerColour.WHITE);
			}
		assertEquals(1,board2.getWinner());

	}


	@Test
	@DisplayName("Checks if score type conditions are correct")
	void testGetScoreType() {
		// Check singles
		for(int i = 0; i<15 ; ++i) {
		board.bearOff(CheckerColour.RED);
		}
		board.bearOff(CheckerColour.WHITE);
		assertEquals(ScoreType.SINGLE,board.getScoreType(true));
		
		// Check Gammon
		Board board2 = new Board();
		for(int i = 0; i<15 ; ++i) {
		board2.bearOff(CheckerColour.RED);
		}
		assertEquals(ScoreType.GAMMON,board2.getScoreType(false));
		
		// Check Backgammon
		Board board3 = new Board();
		for(int i = 0; i<15 ; ++i) {
		board3.bearOff(CheckerColour.RED);
		}
		assertEquals(ScoreType.BACKGAMMON,board3.getScoreType(true));
		
		Board board4 = new Board();
		for(int i = 0; i<15 ; ++i) {
		board4.bearOff(CheckerColour.RED);
		}
		board4.addToBar(0);
		assertEquals(ScoreType.BACKGAMMON,board4.getScoreType(false));
		

	}

}
