package backgammon;

import java.util.*;

public class Display {
	
	Scanner in;

	Display (){
		in = new Scanner(System.in);
	}
	
	
	public String getName() {
		System.out.print("Enter your name: ");
		String name = in.nextLine();
		return name;
	}
	
	public void displayBoard(Board board, Player player) {
		
		if(player.getBoardType() == BoardType.ANTICLOCKWISE) {
		System.out.println("\n  12   11   10   9    8    7        6    5    4    3    2    1 ");
		System.out.println("  ------------------------------------------------------------");
		int MaxSize = board.maxStackSize();
		for(int i = 0;  i < MaxSize ; ++i) {
			for(int j = 11; j >= 0; --j) {
				
				
			    if(j == 6) {
			    	if( board.StackIndexEmpty(j,i) == true)
			    	{
			    		System.out.print("  *   || ");
			    	}
			    	else
					System.out.print(board.getStack(j).get(i).toString()+" || ");
				}
				
			    else if( board.StackIndexEmpty(j,i) == true)
					System.out.print("  *  ");
				
				else 
					System.out.print(board.getStack(j).get(i).toString());
					
			}
			System.out.println();
		}
		System.out.println();
		
		for(int m = MaxSize-1; m >= 0 ; --m) {
			for(int n = 12; n <= 23; ++n) {
				
				 if(n == 17) {
				    	if( board.StackIndexEmpty(n,m) == true)
				    	{
				    		System.out.print("  *   || ");
				    	}
				    	else
						System.out.print(board.getStack(n).get(m).toString()+" || ");
					}
					
				    else if( board.StackIndexEmpty(n,m) == true)
						System.out.print("  *  ");
				
				else 
					System.out.print(board.getStack(n).get(m).toString());
					
			}
			System.out.println();
		}
		System.out.println("  ------------------------------------------------------------");
		System.out.println("  13   14   15   16   17   18      19   20   21   22   23   24 \n");
		}
		
		else {
			System.out.println("\n  13   14   15   16   17   18      19   20   21   22   23   24 ");
			System.out.println("  ------------------------------------------------------------");
			int MaxSize = board.maxStackSize();
			for(int i = 0;  i < MaxSize ; ++i) {
				for(int j = 11; j >= 0; --j) {
					
					
				    if(j == 6) {
				    	if( board.StackIndexEmpty(j,i) == true)
				    	{
				    		System.out.print("  *   || ");
				    	}
				    	else
						System.out.print(board.getStack(j).get(i).toString()+" || ");
					}
					
				    else if( board.StackIndexEmpty(j,i) == true)
						System.out.print("  *  ");
					
					else 
						System.out.print(board.getStack(j).get(i).toString());
						
				}
				System.out.println();
			}
			System.out.println();
			
			for(int m = MaxSize-1; m >= 0 ; --m) {
				for(int n = 12; n <= 23; ++n) {
					
					 if(n == 17) {
					    	if( board.StackIndexEmpty(n,m) == true)
					    	{
					    		System.out.print("  *   || ");
					    	}
					    	else
							System.out.print(board.getStack(n).get(m).toString()+" || ");
						}
						
					    else if( board.StackIndexEmpty(n,m) == true)
							System.out.print("  *  ");
					
					else 
						System.out.print(board.getStack(n).get(m).toString());
						
				}
				System.out.println();
			}
			System.out.println("  ------------------------------------------------------------");
			System.out.println("  12   11   10   9    8    7        6    5    4    3    2    1 \n");
			}
		}
	
	
	public String getCommand(Player player) {
		System.out.print(player.getName() + " enter command: ");
		String input = in.nextLine();
		input.trim().toUpperCase();
		return input;
	}
	
}


