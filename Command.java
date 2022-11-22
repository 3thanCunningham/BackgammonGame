package backgammon;

public class Command {
	
	private enum CommandType {QUIT, ROLL, PIPCOUNT, HINT, INVALID};
	private CommandType commandType;
	
	Command (String input) {
		String inputFormatted = input.trim().toUpperCase();
		
		commandType = CommandType.INVALID;
		
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
	
	public boolean isValid() {
		return commandType != CommandType.INVALID;
	}
	
}