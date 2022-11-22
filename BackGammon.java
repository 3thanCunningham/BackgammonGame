package backgammon;

public class BackGammon {

	public static void main(String[] args) {

		//get player name
		Board board = new Board();
		Player[] names = new Player[2];
		Display display = new Display();
		Dice dice = new Dice();
		names[0] = new Player(display.getName());
		names[1] = new Player(display.getName());
		
		Player[] player = dice.goesFirst(names[0], names[1]);
		player[0].setBoard(BoardType.CLOCKWISE);
		player[1].setBoard(BoardType.ANTICLOCKWISE);
		
		String input;
		Command command;
		
		do {
			input = display.getCommand(player[0]);
			command = new Command(input);
			
			if(command.isValid()) {
			
			if (command.isRoll()){
				display.displayBoard(board,player[0]);
				player[0].setRoll(dice.roll());
				System.out.println(player[0].getName() + " rolls " + player[0].getRoll() + "\n");
			} 
			
			else if (command.isHint()){
				System.out.println("\nHints:\nEnter 'R' or 'r' to roll \nEnter 'P' or 'p' for pipcount \nEnter 'Q' or 'q' to quit \n");
			}
			
			else if (command.isPipCount()){
				System.out.println("\n" + player[0].getName() + " has " + player[0].getPipCount() + " pips");
				System.out.println(player[1].getName() + " has " + player[1].getPipCount() + " pips\n");
			}
			}
			else {
				System.out.println("Invalid Syntax - Try Again\n");
			}
			
			input = display.getCommand(player[1]);
			command = new Command(input);
			
			if(command.isValid()) {
			
			if (command.isRoll()){
				display.displayBoard(board,player[1]);
				player[1].setRoll(dice.roll());
				System.out.println(player[1].getName() + " rolls " + player[1].getRoll()+ "\n");
				
			} 
			
			else if (command.isHint()){
				System.out.println("\nHints:\nEnter 'R' or 'r' to roll \nEnter 'P' or 'p' for pipcount \nEnter 'Q' or 'q' to quit \n");
			}
			
			else if(command.isPipCount()){
					System.out.println("\n" + player[0].getName() + " has " + player[0].getPipCount() + " pips");
					System.out.println(player[1].getName() + " has " + player[1].getPipCount() + " pips\n");
			}
				
			}
			else {
				System.out.println("Invalid Syntax - Try Again\n");
			}
		}
		while(!command.isQuit());
		System.out.print("GAME OVER!");
	}
	

}
