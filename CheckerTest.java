/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.Test;

class CheckerTest {
	private boolean check;
	private Checker redChecker;
	private Checker whiteChecker;
	
	@BeforeEach
	void setUp() {
		redChecker = new Checker(CheckerColour.RED);
		whiteChecker = new Checker(CheckerColour.WHITE);
	}

	@Test
	@DisplayName("Checks that object has been made")
	void testChecker() {
		
		redChecker = new Checker();
		assertNotNull(redChecker);
		
		whiteChecker = new Checker();
		assertNotNull(whiteChecker);
	}

	@Test
	@DisplayName("Checks that checker colour can be set")
	void testCheckerCheckerColour() {
		
		assertEquals(CheckerColour.RED,redChecker.getColour());
		assertEquals(CheckerColour.WHITE,whiteChecker.getColour());
	}

	@Test
	@DisplayName("Checks that checker colour can be gotten")
	void testGetColour() {
		
		if(redChecker.getColour() == CheckerColour.RED && whiteChecker.getColour() == CheckerColour.WHITE) {
			check = true;
		}
		
		else
			check =false;
		
		assertTrue(check);
	}

	@Test
	@DisplayName("Checks toString prints correctly")
	void testToString() {
		
		if (redChecker.toString() == DisplayColour.RED + "  O  " + DisplayColour.RESET &&
			whiteChecker.toString() == DisplayColour.WHITE + "  O  " + DisplayColour.RESET) {
			check = true;
		}
		
		else
			check = false;
		
		assertTrue(check);
		
	}
	
	

}
