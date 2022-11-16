package backgammon;

public class BackGammon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//get player name
		//Board board = new Board();
		Player[] players = new Player[2];
		Display display = new Display();
		Dice diceOne = new Dice();
		Dice diceTwo = new Dice();
		
		players[0] = new Player(display.getName()); players[1] = new
		Player(display.getName());
		 
		System.out.println(players[0].getName());
		 
		
		System.out.println(diceOne.roll());
		System.out.println(diceTwo.roll());
		
		String input;
		Command command;
		do {
			input = display.getCommand();
			command = new Command(input);
		}
		while(!command.isQuit());
	}

}