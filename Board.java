package backgammon;
import java.util.*;

public class Board {
	 
	public static final int NUM_STACKS = 24;
	private List<Stack<Checker>> stacks;
	private int playerOneBear, playerTwoBear;

	Board(){
		playerOneBear =0;
		playerTwoBear =0;
		
		
		stacks = new ArrayList<>(NUM_STACKS);
		for(int i=0;i<NUM_STACKS;i++) {
			stacks.add(new Stack<>());		
			}
		
		Checker WhiteChecker = new Checker(CheckerColour.WHITE);
		Checker RedChecker = new Checker(CheckerColour.RED);
		
		for(int i=0;i<5;i++) {
			stacks.get(5).push(WhiteChecker);
			stacks.get(12).push(WhiteChecker);
			
			stacks.get(11).push(RedChecker);
			stacks.get(18).push(RedChecker);
		}
		
		for( int i=0;i<3;i++) {
			stacks.get(7).push(WhiteChecker);
			stacks.get(16).push(RedChecker);	
		}
		
		for( int i=0;i<2;i++) {
			stacks.get(0).push(RedChecker);
			stacks.get(23).push(WhiteChecker);	
		}
	}
	
	public Stack<Checker> getStack(int index){
		return stacks.get(index);
	}
	
	public int maxStackSize(){
		int maxStackSize = 0;
		for (Stack<Checker> stack : stacks) {
			if (stack.size() > maxStackSize) {
				maxStackSize = stack.size();
			}
		}
		return maxStackSize;
	}
	
	public int getBear(int playerNo) {
		int bear=0;
		switch(playerNo) {
		case 1: bear = playerOneBear;
		case 2: bear = playerTwoBear;
		}
		return bear;
	}
	
	public boolean StackIndexEmpty(int j, int i) {
		if(stacks.get(j).size() < i+1)
			return true;
		else
			return false;
	}
}

