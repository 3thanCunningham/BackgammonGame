package backgammon;

public class Backgammon {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//get player name
		//Board board = new Board();
		Player[] players = new Player[2];
		Display display = new Display();
		Dice dice = new Dice();
		players[0] = new Player(display.getName());
		players[1] = new Player(display.getName());
		
		System.out.println(players[0].getName());
		
		System.out.print(dice.roll());
	}

}
