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
		
		display.displayBoard(board);
		
		String input;
		Command command;
		
		do {
			input = display.getCommand(player[0]);
			command = new Command(input);
			if(command.isValid()) {
				
			if (command.isRoll());{
				player[0].setRoll(dice.roll());
				System.out.println(player[0].getName() + " rolls " + player[0].getRoll() + "\n");
			}
			
			if (command.isHint()){
				System.out.println("heres a hint");
			}
			
			
			input = display.getCommand(player[1]);
			command = new Command(input);
			if (command.isRoll());{
				player[1].setRoll(dice.roll());
				System.out.println(player[1].getName() + " rolls " + player[1].getRoll()+ "\n");
			}
			
			if (command.isHint()){
				System.out.println("heres a hint");
			}
			
			}
			else {
				System.out.println("Invalid Syntax - Try Again");
			}
		}
		while(!command.isQuit());
		System.out.print("GAME OVER!");
	}
	

}