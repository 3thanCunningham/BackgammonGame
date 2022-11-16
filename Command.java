package backgammon;

public class Command {
	
	private enum CommandType {QUIT, ROLL};
	private CommandType commandType;
	
	Command (String input) {
		String inputFormatted = input.trim().toUpperCase();
		
		if (inputFormatted.equals("Q")) {
			commandType = CommandType.QUIT;
		}
		
		if (inputFormatted.equals("R")) {
			commandType = CommandType.ROLL;
		}
		
	}
	
	public boolean isQuit() {
		return commandType == CommandType.QUIT;
	}
	
	public boolean isRoll() {
		return commandType == CommandType.ROLL;
	}
}