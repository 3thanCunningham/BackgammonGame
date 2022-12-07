package backgammon;

public class BackGammon {
	public static final int NUMBER_OF_PLAYERS = 2;

	public static void main(String[] args) {

		
		Player[] names = new Player[2];
		Display display = new Display();
		display.openingScreen();
		Dice dice = new Dice();
		names[0] = new Player(display.getName());
		names[1] = new Player(display.getName());

		String input;
		Command command = new Command();
		Game game = new Game();
		
		do {
			Board board = new Board();
			Player[] player = dice.goesFirst(names[0], names[1]);
			player[0].setBoard(BoardType.CLOCKWISE);
			player[1].setBoard(BoardType.ANTICLOCKWISE);
			player[0].setColour(CheckerColour.RED);
			player[1].setColour(CheckerColour.WHITE);

			do {
				for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {

					display.displayBoard(board, player[i]);
					
					do {

						input = display.getCommand(player[i]);
						command.setCommand(input);

						if (command.isQuit()) {
							break;
						} else if (!command.isValid()) {
							System.out.println("\nInvalid Syntax - Try Again\n");
							game.endTurn(false);
							
						} else if (command.isHint()) {
							System.out.println("\nEnter 'r' to roll\nEnter 'p' for your pip count\nEnter 'q' to quit\n");
							game.endTurn(false);
							
						} else if (command.isPipCount()) {
							System.out.println("\n" + player[i].getName()+ "'s Pip Count : " + player[i].getPipCount(board,player[i]) + "\n");
							game.endTurn(false);
							
							
						} else if (command.isRoll()) {
							player[i].setRoll(dice.roll(), dice.roll());

							System.out.print(player[i].getName() + " rolls : " + player[i].getRoll(0) + " " + player[i].getRoll(1) +" ");

							if (player[i].isDouble()) { 
								System.out.print(player[i].getRoll(0) + " " + player[i].getRoll(0) + "\n");
							}
							 
							System.out.println();
						
							int moves = 2;
							
							if (player[i].isDouble()){ 
								moves = 4; 
							}
							
							
							do {
							game = new Game(board, player[i].getBoardType(), player[i].getColour());
							
							game.findHints(player[i].getRoll(0));
							if(moves==2 && !player[i].isDouble()) {
								game.findHints(player[i].getRoll(1));
							}
							
							game.giveHints();
							
							if(game.isMoveAvailable()) { 
									boolean isMoveDone = false; 
									do {
									input = display.getCommand(player[i]);
									command.setCommand(input);
									if(game.isInputValid(input)) {
										game.getMove(input);
										if(game.isMovetoBar()) {
											game.MovetoBar();
										}
										game.Move();
										isMoveDone=true;
										moves--;
										if(game.diceRollUsed(input)==player[i].getRoll(0)) {
											int rollLeft = player[i].getRoll(1); 
											player[i].setRoll(rollLeft, rollLeft);
										}
										display.displayBoard(board, player[i]);
										} 
									else {
										System.out.println("\nInvalid input - try again\n");
										} 
									} 
									while(!isMoveDone);
									}
								else { 
									System.out.println("\nNo Valid Moves - Skipping Your Turn\n"); 
									moves=0;
								}
							}
							while(moves>0);
							game.endTurn(true);
							}
						 else {
							System.out.println("\nInvalid Command\n");
						}
					} while (!game.isTurnOver());
				}
			}

			while (!command.isQuit() && !board.isGameOver());

			System.out.println("\nGAME OVER!\n");
			System.out.println("PLAYER 1. You decide. Would you like to play again? (y/n): ");
			input = display.getCommand(player[0]);
			command.setCommand(input);

		}
		while (command.isReplay());
		System.out.println("\nThanks for playing! Goodbye.\n");

}
}