package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CheckerColourTest {
	private Checker redChecker;
	private Checker whiteChecker;

	@Test
	@DisplayName("Check checker colour enums")
	void test() {
		redChecker = new Checker(CheckerColour.RED);
		whiteChecker = new Checker(CheckerColour.WHITE);
		
		assertEquals(CheckerColour.valueOf("RED"), redChecker.getColour());
		assertEquals(CheckerColour.valueOf("WHITE"), whiteChecker.getColour());
		
	}

}
