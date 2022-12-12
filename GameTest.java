package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.*;

class GameTest {
	private Game game;
	private Board board;
	private BoardType boardType;
	private CheckerColour colour;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;

	@BeforeEach
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@AfterEach
	public void restoreStreams() {
	    System.setOut(originalOut);
	}
	
	
	@BeforeEach
	void setUp() {
		board = new Board();
		boardType = BoardType.CLOCKWISE;
		colour = CheckerColour.RED;
		game = new Game(board, boardType, colour);
	}

	@Test
	@DisplayName("check that game object has been instantiated")
	void testGame() {
		game = new Game();
		assertNotNull(game);
	}

	@Test
	void testGameBoardBoardTypeCheckerColour() {
		board = new Board();
		boardType = BoardType.CLOCKWISE;
		colour = CheckerColour.RED;
		game = new Game(board, boardType, colour);
		assertNotNull(game);
	}

	@Test
	void testFindHints() {
		assertFalse(game.isMoveAvailable());
		
		int roll = 2;
		game.findHints(roll);
			
		assertTrue(game.isMoveAvailable());
	}

	@Test
	void testGiveHints() {
		int roll = 2;
		game.findHints(roll);
		game.giveHints();
		
		assertEquals("Possible Moves: \r\n"
				+ "A - ( 1 , 3 )\r\n"
				+ "B - ( 12 , 14 )\r\n"
				+ "C - ( 17 , 19 )\r\n"
				+ "D - ( 19 , 21 )", outContent.toString().trim());
	}

	@Test
	void testIsMoveAvailable() {
		assertFalse(game.isMoveAvailable());
		
		int roll = 2;
		game.findHints(roll);
			
		assertTrue(game.isMoveAvailable());
	}

	@Test
	void testIsInputValid() {
		
		int roll = 2;
		game.findHints(roll);
		game.giveHints();
			
		String input = "a";
		assertTrue(game.isInputValid(input));
	}

	@Test
	void testGetMove() {
		int i = board.getStackSize(0);
		
		int roll = 2;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		game.Move();
		
		int j = board.getStackSize(0);
	
		assertFalse(i==j);
		
	
		
	}

	@Test
	void testMove() {
		int i = board.getStackSize(0);
		int j = board.getStackSize(2);
		
		int roll = 2;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		game.Move();
		
		int x = board.getStackSize(0);
		int y = board.getStackSize(2);
	
		assertTrue((i-1==x)&&(j+1==y));
	}

	@Test
	void testDiceRollUsed() {
		int roll = 2;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		game.Move();
		
		int rollUsed = game.diceRollUsed(input);
		
		assertTrue(roll==rollUsed);
	}

	@Test
	void testIsOver() {
		assertFalse(game.isOver());
		board.winGame(0);
		assertTrue(game.isOver());
	}

	@Test
	void testIsMovetoBar() {
		int roll = 4;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		assertFalse(game.isMovetoBar());
		game.Move();
		
		game = new Game(board, BoardType.ANTICLOCKWISE, CheckerColour.WHITE);
		roll = 1;
		game.findHints(roll);
		game.giveHints();
		input = "c";
		game.getMove(input);
		assertTrue(game.isMovetoBar());
		
	}

	@Test
	void testMovetoBar() {
		int roll = 4;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		assertFalse(board.hasCheckerOnBar());
		game.Move();
		
		game = new Game(board, BoardType.ANTICLOCKWISE, CheckerColour.WHITE);
		roll = 1;
		game.findHints(roll);
		game.giveHints();
		input = "c";
		game.getMove(input);
		game.MovetoBar();
		assertTrue(board.hasCheckerOnBar());
	}

	@Test
	void testEndTurn() {
		assertFalse(game.isTurnOver());
		game.endTurn(true);
		assertTrue(game.isTurnOver());
	}

	@Test
	void testIsTurnOver() {
		assertFalse(game.isTurnOver());
		game.endTurn(true);
		assertTrue(game.isTurnOver());
	}

	@Test
	void testBearOff() {
		assertTrue(board.getBear(1)==0);
		
		int roll = 4;
		game.findHints(roll);
		game.giveHints();
		String input = "a";
		game.getMove(input);
		game.BearOff();
		assertTrue(board.getBear(1)==1);
	}

	@Test
	void testSetMatchLength() {
		assertTrue(game.getMatchLength()==0);
		game.setMatchLength(7);
		assertTrue(game.getMatchLength()==7);
	}

	@Test
	void testGetMatchLength() {
		assertTrue(game.getMatchLength()==0);
		game.setMatchLength(3);
		assertTrue(game.getMatchLength()==3);
	}

	@Test
	void testIsCheckerOnHomeBoard() {
		assertTrue(game.isCheckerOnHomeBoard(boardType));
	}

}
