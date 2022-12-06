package backgammon;
import java.util.*;

public class Board {
	 
	public static final int NUM_STACKS = 24;
	private List<Stack<Checker>> stacks;
	private Stack<Checker> bar;
	private int playerOneBear, playerTwoBear;
	private Checker WhiteChecker;
	private Checker RedChecker;
	
	Board(){
		playerOneBear =0;
		playerTwoBear =0;
		
		WhiteChecker = new Checker(CheckerColour.WHITE);
		RedChecker = new Checker(CheckerColour.RED);
		stacks = new ArrayList<>(NUM_STACKS);
		bar = new Stack<>();
		for(int i=0;i<NUM_STACKS;i++) {
			stacks.add(new Stack<>());		
			}
		
		Checker WhiteChecker = new Checker(CheckerColour.WHITE);
		Checker RedChecker = new Checker(CheckerColour.RED);
		
		for(int i=0;i<5;i++) {
			stacks.get(5).push(RedChecker);
			stacks.get(12).push(RedChecker);
			
			stacks.get(11).push(WhiteChecker);
			stacks.get(18).push(WhiteChecker);
		}
		
		for( int i=0;i<3;i++) {
			stacks.get(7).push(RedChecker);
			stacks.get(16).push(WhiteChecker);	
		}
		
		for( int i=0;i<2;i++) {
			stacks.get(0).push(WhiteChecker);
			stacks.get(23).push(RedChecker);	
		}
	}
	
	public Stack<Checker> getStack(int index){
		return stacks.get(index);
	}
	
	public int getStackSize(int index) {
		return stacks.get(index).size();
	}
	
	public boolean isStackEmpty(int index) {
		return stacks.get(index).isEmpty();
	}
	
	public CheckerColour getStackColour(int index) {
		return stacks.get(index).peek().getColour();
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
	
	public void move(int point1, int point2) {
		
		if(stacks.get(point1).peek().getColour()==CheckerColour.RED) {
			stacks.get(point1).pop();
			stacks.get(point2).push(RedChecker);
		}
		else {
			stacks.get(point1).pop();
			stacks.get(point2).push(WhiteChecker);
		}
	}
	
	public void addToBar(int point) {
		if(stacks.get(point).peek().getColour()==CheckerColour.RED) {
			bar.push(RedChecker);
		}
		else {
			bar.push(WhiteChecker);
		}
		
		stacks.get(point).pop();
		
	}
	
	public void removeFromBar() {
		bar.pop();
	}
	
	public boolean hasCheckerOnBar(){
		return !bar.isEmpty();
	}
	
	public Checker getBar() {
		return bar.peek();
	}
	
	public boolean isGameOver() {
		boolean isGameOver = false;
		
		if(playerOneBear==15 || playerTwoBear==15) {
			isGameOver = true;
		}
		return isGameOver;
	}
}

