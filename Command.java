package backgammon;

public class Command {
	
	private enum CommandType {QUIT, ROLL, PIPCOUNT, HINT, INVALID, REPLAY};
	private CommandType commandType;
	
	Command(){
		commandType = CommandType.INVALID;
	}
	
	public void setCommand (String input) {
		
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
	
}