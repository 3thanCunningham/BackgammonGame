/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

class CommandTest {
	
	Command command = new Command();
	
	@Test
	@DisplayName("Checks a command is created")
	void testCommand() {
		assertTrue(command.isInvalid());
	}
	
	@Test
	@DisplayName("Checks that all commands can be set upper and lowercase")
	void testSetCommand() {
		command.setCommand("q");
		assertTrue(command.isQuit());
		command.setCommand("Q");
		assertTrue(command.isQuit());
		command.setCommand("r");
		assertTrue(command.isRoll());
		command.setCommand("R");
		assertTrue(command.isRoll());
		command.setCommand("p");
		assertTrue(command.isPipCount());
		command.setCommand("P");
		assertTrue(command.isPipCount());
		command.setCommand("h");
		assertTrue(command.isHint());
		command.setCommand("H");
		assertTrue(command.isHint());
		command.setCommand("y");
		assertTrue(command.isReplay());
		command.setCommand("Y");
		assertTrue(command.isReplay());
		command.setCommand("dice 12");
		assertTrue(command.isDice());
		command.setCommand("Dice 12");
		assertTrue(command.isDice());
		command.setCommand("test file.txt");
		assertTrue(command.isTest());
		command.setCommand("Test file.txt");
		assertTrue(command.isTest());
		command.setCommand("double");
		assertTrue(command.isDoubleCube());
		command.setCommand("Double");
		assertTrue(command.isDoubleCube());
		command.setCommand("accept");
		assertTrue(command.isAccept());
		command.setCommand("Accept");
		assertTrue(command.isAccept());
		command.setCommand("refuse");
		assertTrue(command.isRefuse());
		command.setCommand("Refuse");
		assertTrue(command.isRefuse());
		
	}
	
	@Test
	@DisplayName("Checks that invalid inputs are recognized")
	void testIsInvalid() {
		command.setCommand("abcd123");
		assertTrue(command.isInvalid());
		command.setCommand("abcd123");
		assertTrue(command.isInvalid());
	}
	
	@Test
	@DisplayName("Checks quit command")
	void testIsQuit() {
		command.setCommand("q");
		assertTrue(command.isQuit());
		command.setCommand("Q");
		assertTrue(command.isQuit());
	}

	@Test
	@DisplayName("Checks roll command")
	void testIsRoll() {
		command.setCommand("r");
		assertTrue(command.isRoll());
		command.setCommand("R");
		assertTrue(command.isRoll());	}

	@Test
	@DisplayName("Checks pip count command")
	void testIsPipCount() {
		command.setCommand("p");
		assertTrue(command.isPipCount());
		command.setCommand("P");
		assertTrue(command.isPipCount());
	}

	@Test
	@DisplayName("Checks hint command")
	void testIsHint() {
		command.setCommand("h");
		assertTrue(command.isHint());
		command.setCommand("H");
		assertTrue(command.isHint());
		}

	@Test
	@DisplayName("Checks replay command")
	void testIsReplay() {
		command.setCommand("y");
		assertTrue(command.isReplay());
		command.setCommand("Y");
		assertTrue(command.isReplay());
	}

	@Test
	@DisplayName("Checks that valid inputs are recognized")
	void testIsValid() {
		command.setCommand("abcd123");
		assertFalse(command.isValid());
		command.setCommand("H");
		assertTrue(command.isValid());
	}

	@Test
	@DisplayName("Checks dice test command")
	void testIsDice() {
		command.setCommand("dice 12");
		assertTrue(command.isDice());
		command.setCommand("Dice 12");
		assertTrue(command.isDice());
		}

	@Test
	@DisplayName("Checks file test command")
	void testIsTest() {
		command.setCommand("test file.txt");
		assertTrue(command.isTest());
		command.setCommand("test file.txt");
		assertTrue(command.isTest());
	}

	@Test
	@DisplayName("Checks doubles command")
	void testIsDoubleCube() {
		command.setCommand("double");
		assertTrue(command.isDoubleCube());
		command.setCommand("Double");
		assertTrue(command.isDoubleCube());
	}

	@Test
	@DisplayName("Checks accept command")
	void testIsAccept() {
		command.setCommand("accept");
		assertTrue(command.isAccept());
		command.setCommand("Accept");
		assertTrue(command.isAccept());	
	}

	@Test
	@DisplayName("Checks refuse command")
	void testIsRefuse() {
		command.setCommand("refuse");
		assertTrue(command.isRefuse());
		command.setCommand("Refuse");
		assertTrue(command.isRefuse());	
	}
}
