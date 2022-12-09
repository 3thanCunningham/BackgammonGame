package backgammon;
import java.util.*;

public class Board {
	 
	public static final int NUM_STACKS = 24;
	private List<Stack<Checker>> stacks;
	private Stack<Checker> bar;
	private int playerOneBear, playerTwoBear;
	private Checker WhiteChecker;
	private Checker RedChecker;
	private int doubleCube;
	
	Board(){
		playerOneBear =0;
		playerTwoBear =0;
		doubleCube = 1;
		
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
			stacks.get(5).push(WhiteChecker);
			stacks.get(12).push(WhiteChecker);
			
			stacks.get(11).push(RedChecker);
			stacks.get(18).push(RedChecker);
		}
		
		for( int i=0;i<3;i++) {
			stacks.get(16).push(RedChecker);
			stacks.get(7).push(WhiteChecker);	
		}
		
		for( int i=0;i<2;i++) {
			stacks.get(23).push(WhiteChecker);
			stacks.get(0).push(RedChecker);	
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
		if(playerNo==1) {
		bear = playerOneBear;
		}
		if(playerNo==2) {
		bear = playerTwoBear;
		}
		return bear;
	}
	
	public void bearOff(CheckerColour colour) {
		if(colour==CheckerColour.RED) {
			++playerOneBear;
		}
		else {
			++playerTwoBear;
		}
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
	
	public void pushToStack(int index, CheckerColour colour) {
		if(colour==CheckerColour.RED) {
			stacks.get(index).push(RedChecker);
		}
		else {
			stacks.get(index).push(WhiteChecker);
		}
	}
	
	public boolean isGameOver() {
		boolean isGameOver = false;
		
		if(playerOneBear==15 || playerTwoBear==15) {
			isGameOver = true;
		}
		return isGameOver;
	}
	
	public boolean isHomeBoardFull(CheckerColour colour) {
		boolean isHomeBoardFull = false;
		
		int checkersInHomeBoard=0;
		
		if(colour==CheckerColour.WHITE) {
			for(int i=0;i<6;i++) {
				if(!stacks.get(i).empty() && stacks.get(i).peek().getColour()==CheckerColour.WHITE) {
					checkersInHomeBoard+=stacks.get(i).size();
				}
			}
			if(checkersInHomeBoard==15-playerTwoBear) {
				isHomeBoardFull=true;
			}
		}
		else {
			for(int i=18;i<24;i++) {
				if(!stacks.get(i).empty() && stacks.get(i).peek().getColour()==CheckerColour.RED) {
					checkersInHomeBoard+=stacks.get(i).size();
				}
		}
			if(checkersInHomeBoard==15-playerOneBear) {
				isHomeBoardFull=true;
			}
		}
			
		
		return isHomeBoardFull;
	}
	
	public void doubleCube() {
		this.doubleCube = 2*doubleCube;
	}
	
	public void setDoubleCube(int num) {
		this.doubleCube = num;
	}
	
	public int getDoubleCube() {
		return doubleCube;
	}
	
	public void winGame(int num) {
		if(num==0) {
			playerOneBear = 15;
		}
		else
			playerTwoBear = 15;
		
	}
	
	public ScoreType getScoreType(boolean isCheckerOnHomeBoard) { //board
		
		ScoreType score = ScoreType.SINGLE;
		
		if(playerTwoBear==0 || playerOneBear == 0) {
			score = ScoreType.GAMMON;
			if(!bar.isEmpty() || isCheckerOnHomeBoard) {
				score = ScoreType.BACKGAMMON;
			}		
		}
		
		return score;
	}
	
}

