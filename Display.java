package backgammon;

import java.util.*;
import java.io.File;  // Import the File class

public class Display {
	
	Scanner in;
	Scanner in2;
	Scanner sc;
	File file;
	boolean fileIsOpen;

	Display (){
		in = new Scanner(System.in);
		fileIsOpen = false;
	}
	
	public String getName() {
		System.out.print("Enter your name: ");
		String name = in.nextLine();
		return name;
	}
	
	public int getMatchLength(Game game) {
		in2 = new Scanner(System.in);
		int matchLength;
			do {
			System.out.print("\nEnter match length (odd): ");	
			matchLength = in2.nextInt();
			game.setMatchLength(matchLength);
			} while (game.getMatchLength()%2 ==0);
			
			return matchLength;
		
	}
	
	public void displayBoard(Board board, Player player[], int matchLength, int x) {
		
		System.out.println("\n  -----------------------------------------------------------------");
		if(player[x].getBoardType() == BoardType.ANTICLOCKWISE && !player[x].hasCube()) {
		System.out.println("  Match Length: " + matchLength + "      " + player[x].getName()+"'s score: " + player[x].getScore() + "      " + player[x].getName() + "'s Bear-Off: " + board.getBear(2));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  12   11   10   9    8    7   | BAR |  6    5    4    3    2    1 ");
		}
		else if(player[x].getBoardType() == BoardType.ANTICLOCKWISE && player[x].hasCube()){
		System.out.println("  Match Length: " + matchLength + "      " + player[x].getName()+"'s score: " + player[x].getScore() + "      " + player[x].getName() + "'s Bear-Off: " + board.getBear(2));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  12   11   10   9    8    7   | BAR |  6    5    4    3    2    1    " + player[x].getName() + "'s Double cube: " + board.getDoubleCube());
		}
		
		else if(player[x].getBoardType() == BoardType.CLOCKWISE && player[x].hasCube()) {
		System.out.println("  Match Length: " + matchLength +"      " + player[x].getName()+"'s score: " + player[x].getScore() + "      " + player[x].getName() + "'s Bear-Off: " + board.getBear(1));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24   " + player[x].getName() + "'s Double cube: " + board.getDoubleCube());
		}
		else {
		System.out.println("  Match Length: " + matchLength +"      " + player[x].getName()+"'s score: " + player[x].getScore() + "      " + player[x].getName() + "'s Bear-Off: " + board.getBear(1));
		System.out.println("  -----------------------------------------------------------------");
		System.out.print("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24 ");
		}
		System.out.println("\n  -----------------------------------------------------------------");
		int MaxSize = board.maxStackSize();
		
		for(int m = 0; m < MaxSize ; ++m) {
			for(int n = 12; n <= 23; ++n) {
				
				 if(n == 17) {
				    	if( board.StackIndexEmpty(n,m) == true)
				    	{
				    		System.out.print("      |     | ");
				    	}
				    	else
						System.out.print(board.getStack(n).get(m).toString()+" |     | ");
					}
					
				    else if( board.StackIndexEmpty(n,m) == true)
						System.out.print("     ");
				
				else 
					System.out.print(board.getStack(n).get(m).toString());
					
			}
			System.out.println();
		}
		
		
		if(board.hasCheckerOnBar() && !player[x].hasCube() && !player[(x+1)%2].hasCube()) {
			System.out.println("                                " + board.getBar().toString() + "                                  Double cube: 64");
		}
		else if(board.hasCheckerOnBar()) {
			System.out.println("                                " + board.getBar().toString());
		}
		else if( !player[x].hasCube() && !player[(x+1)%2].hasCube()) {
		System.out.println("                                                                      Double cube: 64");
		}
		else if(player[x].hasCube() || player[(x+1)%2].hasCube()) {
			System.out.println();
		}
		
		
		for(int i=MaxSize-1;  i>=0 ; --i) {
			for(int j = 11; j >= 0; --j) {
				
				
			    if(j == 6) {
			    	if( board.StackIndexEmpty(j,i) == true)
			    	{
			    		System.out.print("      |     | ");
			    	}
			    	else
					System.out.print(board.getStack(j).get(i).toString()+" |     | ");
				}
				
			    else if( board.StackIndexEmpty(j,i) == true)
					System.out.print("     ");
				
				else 
					System.out.print(board.getStack(j).get(i).toString());
					
			}
			System.out.println();
		}
		System.out.println("  -----------------------------------------------------------------");
		
		if(player[x].getBoardType() == BoardType.ANTICLOCKWISE && player[(x+1)%2].hasCube()) {
		System.out.println("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24   " + player[(x+1)%2].getName() + "'s Double cube: " + board.getDoubleCube());
		}
		else if(player[x].getBoardType() == BoardType.ANTICLOCKWISE) {
		System.out.println("  13   14   15   16   17   18  | BAR |  19   20   21   22   23   24 ");
		}
		else if (player[x].getBoardType() == BoardType.CLOCKWISE && player[(x+1)%2].hasCube()) {
			System.out.println("  12   11   10   9    8    7   | BAR |   6    5    4    3    2    1   " + player[(x+1)%2].getName() + "'s Double cube: " + board.getDoubleCube());
		}
		else {
			System.out.println("  12   11   10   9    8    7   | BAR |   6    5    4    3    2    1 ");
		}
		System.out.println("  -----------------------------------------------------------------\n  Stuck?, enter 'h' for hints!\n");
	}
	
	
	public String getCommand(Player player) {
		System.out.print("\n" + player.getName() + " enter command: ");
		String input = in.nextLine();
		return input;
	}
	
	public void openingScreen(){
		System.out.println("\n-------------WELCOME TO BACKGAMMON - LETS PLAY!---------------\n");
	}
	
	public void openFile(String filename) {
		try  
		{  
		file = new File(filename);   
		sc = new Scanner(file);     //file to be scanned   
		fileIsOpen=true;
		}  
		catch(Exception e)  
		{  
		e.printStackTrace();  
		}  
	}
	
	public String getCommandFromFile() {
		String input = sc.nextLine();
		if(input.isBlank()) {
			fileIsOpen=false;
		}
		return input;
	}
	
	public boolean hasNextLine() {
		boolean retVal = false;
		if(fileIsOpen) {
			retVal = sc.hasNextLine();
		}
		
	return retVal;
	}
	
	public boolean isFileOpen() {
		return fileIsOpen;
	}
	
	public void closeFile() {
		fileIsOpen=false;
	}
	
	public void giveCommandHints() {
		System.out.println("\nEnter 'r' to roll");
		System.out.println("Enter 'p' for your pip count");
		System.out.println("Enter 'q' to quit");
		System.out.println("Enter 'dice <int><int>' to set dice roll to given numbers");
		System.out.println("Enter 'test <filename>' to perform commands in a given text file");
		System.out.println("Enter 'double' to offer a double to the other player");
		System.out.println();
	}
	
	public void announceScore(Score score, String name, int points) {
		System.out.println("\n" + name + " won this Game with a " + score.toString() + " and a score of: " + points + "! Get ready for the next one!\n");
	}

}




