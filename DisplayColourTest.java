package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DisplayColourTest {
	private Checker redChecker;
	private Checker whiteChecker;
	
	@Test
	@DisplayName("Check display colours class")
	void test() {
		redChecker = new Checker(CheckerColour.RED);
		whiteChecker = new Checker(CheckerColour.WHITE);
		
		assertEquals(DisplayColour.RED + "  O  " + DisplayColour.RESET,redChecker.toString());
		assertEquals(DisplayColour.WHITE + "  O  " + DisplayColour.RESET,whiteChecker.toString());
	}

}
