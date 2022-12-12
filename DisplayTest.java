package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisplayTest {
	private final ByteArrayOutputStream output = new ByteArrayOutputStream();
	private final PrintStream originalOutput = System.out;
	Display display = new Display();
	
	@Test
	@DisplayName("Check that object has been created")
	void testDisplay() {
		assertNotNull(display);
	}

	@Test
	void testGetName() throws IOException{
		String name;
	    final InputStream original = System.in;
	    final FileInputStream fips = new FileInputStream(new File("testfile2.txt"));
	    System.setIn(fips);
	    name = display.getName();
	    System.setIn(original);
	    assertEquals("Jerome", name);
	}
	
/*
	@Test
	void testGetMatchLength() {
		fail("Not yet implemented");
	}

	@Test
	void testDisplayBoard() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCommand() {
		fail("Not yet implemented");
	}
*/
	@Test
	@DisplayName("Check if correct opening screen is displayed")
	void testOpeningScreen() {
		System.setOut(new PrintStream(output));
		display.openingScreen();
		
		assertEquals("\n-------------WELCOME TO BACKGAMMON - LETS PLAY!---------------\n\r\n",output.toString());
		
		System.setOut(originalOutput);
	}
/*
	@Test
	void testOpenFile() {
		fail("Not yet implemented");
	}

	@Test
	void testGetCommandFromFile() {
		fail("Not yet implemented");
	}

	@Test
	void testHasNextLine() {
		fail("Not yet implemented");
	}

	@Test
	void testIsFileOpen() {
		fail("Not yet implemented");
	}

	@Test
	void testCloseFile() {
		fail("Not yet implemented");
	}
*/
	@Test
	@DisplayName("Check if correct hints are given")
	void testGiveCommandHints() {
		System.setOut(new PrintStream(output));
		display.giveCommandHints();
		
		assertEquals("\n"
				+ "Enter 'r' to roll\r\n"
				+ "Enter 'p' for your pip count\r\n"
				+ "Enter 'q' to quit\r\n"
				+ "Enter 'dice <int><int>' to set dice roll to given numbers\r\n"
				+ "Enter 'test <filename>' to perform commands in a given text file\r\n"
				+ "Enter 'double' to offer a double to the other player\r\n"
				+ "\r\n",output.toString());
		System.setOut(originalOutput);
	}

	@Test
	@DisplayName("Check if correct winner announcement is displayed")
	void testAnnounceScore() {
		System.setOut(new PrintStream(output));
		Score score = new Score();
		score.setScore(ScoreType.SINGLE);
		
		display.announceScore(score, "John" , 10); // Random test
		
		assertEquals("\nJohn won this Game with a single and a score of: 10! Get ready for the next one!\n\r\n",output.toString());
		System.setOut(originalOutput);
		
		output.reset();
		
		System.setOut(new PrintStream(output));
		score.setScore(ScoreType.GAMMON);
		
		display.announceScore(score, "Bob" , 6); // Random test
		
		assertEquals("\nBob won this Game with a gammon and a score of: 6! Get ready for the next one!\n\r\n",output.toString());
		System.setOut(originalOutput);
	}

}
