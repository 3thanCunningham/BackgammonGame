/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BoardTypeTest {
	private Player[] player = new Player[2];
	
	@Test
	@DisplayName("Check Board Type enums")
	void testBoardType() {
		player[0] = new Player("Test Player");
		player[0].setBoard(BoardType.ANTICLOCKWISE);
		assertEquals(BoardType.valueOf("ANTICLOCKWISE"), player[0].getBoardType());
		
		player[1] = new Player("Test Player2");
		player[1].setBoard(BoardType.CLOCKWISE);
		assertEquals(BoardType.valueOf("CLOCKWISE"), player[1].getBoardType());
	}

}
