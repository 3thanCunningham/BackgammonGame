/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

/*
 * This Class constructs a player
 * Player variables are stored in this class
 */

public class Player {
	
	private BoardType pipNumbers;
	private String name;
	private CheckerColour colour;
	private int roll[];
	private int pipCount;
	private int score;
	private boolean hasCube;
	
	Player (String name){
		// Player variables
		this.name = name;
		pipCount = 0;
		roll = new int[2];
		colour = CheckerColour.RED;
		pipNumbers = BoardType.CLOCKWISE;
		score = 0;
		hasCube = false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRoll(int index) {
		return roll[index];
	}
	
	public void setRoll(int dice1, int dice2) {
		roll[0] = dice1;
		roll[1] = dice2;
	}
	
	public void setBoard(BoardType pipNumbers) {
		// Decides is player has clockwise or anticlockwise board
		this.pipNumbers = pipNumbers;
	}
	
	public BoardType getBoardType(){
		return pipNumbers;
	}
	
	public void setColour(CheckerColour colour) {
		this.colour = colour;
	}
	
	public CheckerColour getColour() {
		return colour;
	}
	
	public void setPipCount(int pipCount) {
		this.pipCount = pipCount;
	}
	
	public int getPipCount() {
		return pipCount;
	}
	
	public int calculatePipCount(Board board, Player player) {
		pipCount = 0;
		
		// Goes through the entire board and finds all pips of wanted colour
		// Distance of a pip from end of board is calculated with formula
		if(player.getBoardType() == BoardType.CLOCKWISE) {
		for(int i=0; i<= 23; ++i) {
			
			if( board.StackIndexEmpty(i,0) == true)
	    	{
	    		pipCount += 0;
	    	}
			
			else if(board.getStack(i).get(0).getColour()== CheckerColour.RED) {
				// Custom formula used to calculate red pip count
				pipCount += board.getStackSize(i) * (24-i);
			}
		}
		}
		
		else {
			for(int j=0; j<= 23; ++j) {
				
				if( board.StackIndexEmpty(j,0) == true)
		    	{
		    		pipCount += 0;
		    	}
				
				else if(board.getStack(j).get(0).getColour()== CheckerColour.WHITE) {
					// Custom formula used to calculate white pip count
					pipCount += board.getStackSize(j) * (j+1);
				}
			}
		}
		
		return pipCount;
	}
	
	public boolean isDouble() {
		// Check to see if player rolled same number
		boolean isDouble = false;
		if(roll[0]==roll[1]) {
			isDouble  = true;
		}
		return isDouble;
	}
	
	public void setScore(int score) {
		this.score += score;
	}
	
	public void upScore() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public Player isMatchWinner(Player player[]) {
		// Returns winner of match by checking scores
		if(player[0].getScore() > player[1].getScore()) {
			return player[0];
		}
		
		else
			return player[1];
	}
	
	public void announceMatchWinner(Player player[]) {
		// Different announcements for different win types
		if(player[0].getScore() > player[1].getScore()) {
			System.out.println(player[0].getName() + " Won the Match! Congratulations!");
		}
		
		else if(player[1].getScore() > player[0].getScore()) {
			System.out.println(player[1].getName() + " Won the Match! Congratulations!");
		}
		
		else
			System.out.println("The Match is Tied! No winner decided!");
	}
	
	// doubling cube methods
	public void giveCube() {
		hasCube = true;
	}
	
	public void takeCube() {
		hasCube = false;
	}
	
	public boolean hasCube() {
		return hasCube;
	}
	
	
}
	
