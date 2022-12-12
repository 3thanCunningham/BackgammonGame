package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

class BackGammonTest {
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

	
	@Test
	void testMain() throws IOException{
		System.out.println("main");
	    String[] args = null;
	    final InputStream original = System.in;
	    final FileInputStream fips = new FileInputStream(new File("testfile.txt"));
	    System.setIn(fips);
	    BackGammon.main(args);
	    System.setIn(original);
	    assertTrue(outContent.toString().contains("Thanks for playing! Goodbye."));
	    
	}

}
