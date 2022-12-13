package backgammon;

/*
 * This class stores and performs syntax checking on a command
 */

public class Command {
	
	private enum CommandType {QUIT, ROLL, PIPCOUNT, HINT, INVALID, REPLAY, DICE, TEST, DOUBLE, ACCEPT, REFUSE};
	private CommandType commandType;
	
	Command(){
		commandType = CommandType.INVALID;
	}
	
	public void setCommand (String input) {
		
		// If input does not match any command it is invalid
		// Used to display syntax errors
		commandType = CommandType.INVALID;
		
		String inputFormatted = input.trim().toUpperCase();
		
		if (inputFormatted.equals("Q")) {
			commandType = CommandType.QUIT;
		}
		
		if (inputFormatted.equals("R")) {
			commandType = CommandType.ROLL;
		}
		
		if (inputFormatted.equals("P")) {
			commandType = CommandType.PIPCOUNT;
		}
		
		if (inputFormatted.equals("H")) {
			commandType = CommandType.HINT;
		}
		
		if (inputFormatted.equals("Y")) {
			commandType = CommandType.REPLAY;
		}
		
		if (inputFormatted.matches("DICE [1-6][1-6]")) {
			commandType = CommandType.DICE;
		}
		
		if (inputFormatted.matches("TEST .*")) {
			commandType = CommandType.TEST;
		}
		
		if (inputFormatted.matches("DOUBLE")) {
			commandType = CommandType.DOUBLE;
		}
		
		if (inputFormatted.matches("ACCEPT")) {
			commandType = CommandType.ACCEPT;
		}
		
		if (inputFormatted.matches("REFUSE")) {
			commandType = CommandType.REFUSE;
		}
		
	}
	
	public boolean isInvalid() {
		return commandType== CommandType.INVALID;
	}
	
	public boolean isQuit() {
		return commandType == CommandType.QUIT;
	}
	
	public boolean isRoll() {
		return commandType == CommandType.ROLL;
	}
	
	public boolean isPipCount() {
		return commandType == CommandType.PIPCOUNT;
	}
	
	public boolean isHint() {
		return commandType == CommandType.HINT;
	}
	
	public boolean isReplay() {
		return commandType == CommandType.REPLAY;
	}	
	
	public boolean isValid() {
		return commandType != CommandType.INVALID;
	}
	
	public boolean isDice() {
		return commandType == CommandType.DICE;
	}
	
	public boolean isTest() {
		return commandType == CommandType.TEST;
	}
	
	public boolean isDoubleCube() {
		return commandType == CommandType.DOUBLE;
	}
	
	public boolean isAccept() {
		return commandType == CommandType.ACCEPT;
	}
	
	public boolean isRefuse() {
		return commandType == CommandType.REFUSE;
	}
	
}