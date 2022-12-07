package backgammon;

public class Player {
	
	private BoardType pipNumbers;
	private String name;
	private CheckerColour colour;
	private int roll[];
	private int pipCount;
	
	Player (String name){
		this.name = name;
		pipCount = 0;
		roll = new int[2];
		colour = CheckerColour.RED;
		pipNumbers = BoardType.CLOCKWISE;
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
	
	public int getPipCount(Board board, Player player) {
		pipCount = 0;
		
		if(player.getBoardType() == BoardType.CLOCKWISE) {
		for(int i=0; i<= 23; ++i) {
			
			if( board.StackIndexEmpty(i,0) == true)
	    	{
	    		pipCount += 0;
	    	}
			
			else if(board.getStack(i).get(0).getColour()== CheckerColour.RED) {
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
					pipCount += board.getStackSize(j) * (j+1);
				}
			}
		}
		
		return pipCount;
	}
	
	public boolean isDouble() {
		boolean isDouble = false;
		if(roll[0]==roll[1]) {
			isDouble  = true;
		}
		return isDouble;
	}
}
	
