package backgammon;

public class BackGammon {
	public static final int NUMBER_OF_PLAYERS = 2;

	public static void main(String[] args) {
		
		Command command = new Command();
		
		do {
		Player[] names = new Player[3];
		Display display = new Display();
		display.openingScreen();
		Dice dice = new Dice();
		names[0] = new Player(display.getName());
		names[1] = new Player(display.getName());
		Score score = new Score();

		String input;
		
		Game game = new Game();
		
		int matchLength = display.getMatchLength(game);
				
			Board board = new Board();
			Player[] player = dice.goesFirst(names[0], names[1]);
			player[0].setBoard(BoardType.CLOCKWISE);
			player[1].setBoard(BoardType.ANTICLOCKWISE);
			player[0].setColour(CheckerColour.RED);
			player[1].setColour(CheckerColour.WHITE);

			do {
				do {
					
				for (int i = 0; i < NUMBER_OF_PLAYERS; i++) {
					
					if(board.isGameOver()) {
						break;
					}
					if(player[0].getScore()>=matchLength) {
						break;
					}
					else if(player[1].getScore()>=matchLength) {
						break;
					}
					
					display.displayBoard(board, player, matchLength, i);
					
					do {
						
						input = "";
						if(!display.isFileOpen()) {
							input = display.getCommand(player[i]);
							command.setCommand(input);
							
							if(command.isTest()) {
								display.openFile(input.substring(5));
								input = display.getCommandFromFile();
								command.setCommand(input);
							}
						}
						
						else if(display.hasNextLine()) {
							input = display.getCommandFromFile();
							command.setCommand(input);
						}
						else {
							display.closeFile();
						}

						if (command.isQuit()) {
							break;
						} else if (!command.isValid()) {
							System.out.println("\nInvalid Syntax - Try Again");
							game.endTurn(false);
							
						} else if (command.isHint()) {
							display.giveCommandHints();
							game.endTurn(false);
							
						} else if (command.isPipCount()) {
							System.out.println("\n" + player[i].getName()+ "'s Pip Count : " + player[i].calculatePipCount(board,player[i]));
							game.endTurn(false);
							
						} else if (command.isDoubleCube()) {
							
							if ((player[i].hasCube()==false && player[(i+1)%2].hasCube()==false) ||
								(player[i].hasCube()==true && player[(i+1)%2].hasCube()==false)){
							System.out.println("\n" + player[i].getName()+ " is offering a double, " + player[(i+1)%2].getName() + " Do you accept? (accept/refuse):");
							do {
							input = display.getCommand(player[(i+1)%2]);
							command.setCommand(input);
							if(!command.isValid()) {
								System.out.println("\nInvalid Response - Please enter accept/refuse:");
							}
							}while(!command.isValid());
							
							if(command.isAccept()) {
								board.doubleCube();
								player[i].takeCube();
								player[(i+1)%2].giveCube();
							}
							
							if(command.isRefuse()) {
								board.winGame(i);
								break;
							}
							
							game.endTurn(false);
							}
							
							else {
								System.out.println("\nYou don't have the cube, you can't double!");
								game.endTurn(false);
							}
							
							
							
						} else if (command.isRoll() || command.isDice()) {
							
							if(command.isDice()) {
							player[i].setRoll(Character.getNumericValue(input.charAt(5)) ,Character.getNumericValue(input.charAt(6)));
							}
							else {
							player[i].setRoll(dice.roll(), dice.roll());
							}
							
							System.out.print("\n" + player[i].getName() + " rolls : " + player[i].getRoll(0) + " " + player[i].getRoll(1));

							if (player[i].isDouble()) { 
								System.out.print(" " + player[i].getRoll(0) + " " + player[i].getRoll(0));
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

										display.displayBoard(board, player, matchLength, i);
										} 
									else {
										System.out.println("\nInvalid input - try again");
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
							System.out.println("\nInvalid Command");
						}
					} while (!game.isTurnOver());
				} 
				} while(!command.isQuit()&&!board.isGameOver());
				
				if (command.isQuit()) {
					break;
				}
				
				if (command.isRefuse()) {
					score.setScore(ScoreType.SINGLE);
					int doubler = score.getScore(score.getScoreType(), board.getDoubleCube());
					player[board.getWinner()].setScore(doubler);
					display.announceScore(score, player[board.getWinner()].getName(), doubler);
				}
				
				else if(board.getBear(1)>board.getBear(2)) {
					score.setScore(board.getScoreType(game.isCheckerOnHomeBoard(player[0].getBoardType())));
					int doubler = score.getScore(score.getScoreType(), board.getDoubleCube());
					player[0].setScore(doubler);
					display.announceScore(score, player[0].getName(), doubler);
					
					
					}
				else if (board.getBear(2)>board.getBear(1)){
					score.setScore(board.getScoreType(game.isCheckerOnHomeBoard(player[1].getBoardType())));
					int doubler = score.getScore(score.getScoreType(), board.getDoubleCube());
					player[1].setScore(doubler);
					display.announceScore(score, player[1].getName(), doubler);
				}
				
				if((player[0].getScore()>= matchLength || player[1].getScore()>= matchLength)) {
					break;
				}
				
				System.out.println("\n"+player[0].getName() + " New Score: " + player[0].getScore());
				System.out.println(player[1].getName() + " New Score: " + player[1].getScore());
				board = new Board();
				player = dice.goesFirst(names[0], names[1]);
				player[0].setBoard(BoardType.CLOCKWISE);
				player[1].setBoard(BoardType.ANTICLOCKWISE);
				player[0].setColour(CheckerColour.RED);
				player[1].setColour(CheckerColour.WHITE);
				player[0].takeCube();
				player[1].takeCube();
			}

			while (!command.isQuit()&& !(player[0].getScore()>= matchLength) && !(player[1].getScore() >= matchLength));
			
			System.out.println("\nMATCH OVER!\n");
			names[2] = player[0].isMatchWinner(player);
			names[2].announceMatchWinner(player);
			System.out.println("\nWould you like to play a New Match? (y/n): ");
			input = display.getCommand(names[2]);
			command.setCommand(input);
		}
		while (command.isReplay());
		System.out.println("\nThanks for playing! Goodbye.\n");
	
}
}
