package backgammon;
import java.util.*;

public class Game {
	private static final int NUMBER_OF_POINTS = 24;
	private ArrayList<String> hintsString;
	private ArrayList<Integer> hintsInteger;
	private ArrayList<String> letters;
	private Board board;
	private boolean isGameOver;
	private BoardType boardType;
	private CheckerColour playerColour;
	private int fromPointOne, toPointTwo;
	private boolean isTurnOver;

	Game(){
		isTurnOver=false;
	}
	
	Game(Board board, BoardType boardType, CheckerColour colour){
		this.board = board;
		isGameOver = false;
		this.boardType = boardType;
		playerColour = colour;
		hintsString = new ArrayList<String>();
		hintsInteger = new ArrayList<Integer>();
		letters = new ArrayList<String>();
		fromPointOne=0;
		toPointTwo=0;
		isTurnOver = false;
	}
	
	public void findHints(int roll) {
		String hint = "";
		
		if(board.hasCheckerOnBar() && board.getBar().getColour()==playerColour) {
			if(boardType==BoardType.CLOCKWISE) {
				if(board.getStack(roll-1).isEmpty() || board.getStackColour(roll-1)==playerColour) {
					hint = "(  BAR , " + (roll) + " )";
					hintsString.add(hint);
					hintsInteger.add(26);
					hintsInteger.add(roll);
				}
			}
			else {
				if(board.getStack(24-roll).isEmpty() || board.getStackColour(24-roll)==playerColour) {
					hint = "(  BAR , " + (25-roll) + " )";
					hintsString.add(hint);
					hintsInteger.add(26);
					hintsInteger.add(25-roll);
				}
			}
		}
		else {
		if(board.isHomeBoardFull(playerColour)) {
			if(boardType==BoardType.CLOCKWISE) {
				if(!board.isStackEmpty(24-roll)) {
					hint = "( "+ (25-roll) + " , BEAR-OFF )";
					hintsString.add(hint);
					hintsInteger.add(25-roll);
					hintsInteger.add(25-roll);
				}
			}
			else {
				if(!board.isStackEmpty(roll-1)) {
					hint = "( "+ (roll) + " , BEAR-OFF )";
					hintsString.add(hint);
					hintsInteger.add(roll);
					hintsInteger.add(roll);
				}
			}
		}
		
		
	
		if(boardType==BoardType.CLOCKWISE) {
			
		for(int i=0; i<NUMBER_OF_POINTS;i++) {
			
			int point1 = i+1;
			int point2;
			
			if((!board.getStack(i).isEmpty()) && (i+roll < NUMBER_OF_POINTS) ) {
			
			if(playerColour==board.getStackColour(i)) {
				
				if(board.getStack(i+roll).isEmpty()) {
					point2 = point1+roll;
					hint = "( "+ point1 + " , " + point2 + " )";
					hintsString.add(hint);
					hintsInteger.add(point1);
					hintsInteger.add(point2);
				}
				else if(playerColour==board.getStackColour(i+roll) || (board.getStackSize(i+roll) == 1) ){
					point2 = point1+roll;
					hint = "( "+ point1 + " , " + point2 + " )";
					hintsString.add(hint);
					hintsInteger.add(point1);
					hintsInteger.add(point2);
				}
				
			}
			
		}
		}
		}
		else {
			for(int i=NUMBER_OF_POINTS-1; i>=0;i--) {
				
				int point1=i+1;
				int point2;
				
				if((!board.getStack(i).isEmpty()) && (i-roll >=0) ) {
				
				if(playerColour==board.getStackColour(i)) {
					
					if(board.getStack(i-roll).isEmpty()) {
						point2 = point1-roll;
						hint = "( "+ (25-point1) + " , " + (25-point2) + " )";
						hintsString.add(hint);
						hintsInteger.add(point1);
						hintsInteger.add(point2);
					}
					else if(playerColour==board.getStackColour(i-roll) || (board.getStackSize(i-roll) == 1) ){
						point2 = point1-roll;
						hint = "( "+ (25-point1) + " , " + (25-point2) + " )";
						hintsString.add(hint);
						hintsInteger.add(point1);
						hintsInteger.add(point2);
					}
					
				}
				
			}
			}
				
			}
		}
	}
	

	
	public void giveHints() {
		
		System.out.println("Possible Moves: ");
		char letter = 'A';
		for ( String hint : hintsString ) {
		    System.out.println( letter + " - " + hint );
		    letters.add(String.valueOf(letter));
		    letter++;
		}
	}
	
	public boolean isMoveAvailable() {
		return !hintsString.isEmpty();
	}
	
	public boolean isInputValid(String input) {
		
		boolean isInputValid = false;
		String inputFormatted = input.trim().toUpperCase();
		
		for(int i=0; i<letters.size();i++) {
			if(inputFormatted.charAt(0)==letters.get(i).charAt(0)) {
				isInputValid=true;
			}
		}
		return isInputValid;
	}
	
	
	
	public void getMove(String input) {
		
		String inputFormatted = input.trim().toUpperCase();
		int index=letters.indexOf(inputFormatted);
		index*=2;
		
		fromPointOne = hintsInteger.get(index);
		toPointTwo = hintsInteger.get(index+1);
		
		fromPointOne-=1;
		toPointTwo-=1;
	}
	
	public void Move() {
		if(toPointTwo==fromPointOne) {
			board.getStack(fromPointOne).pop();
			board.bearOff(playerColour);
		}
		else if(fromPointOne==25){
			board.removeFromBar();
			board.pushToStack(toPointTwo, playerColour);
		}
		else {
		board.move(fromPointOne, toPointTwo);
		}
	}
	
	
	public int diceRollUsed(String input) {
		int roll = Math.abs(toPointTwo-fromPointOne);
		
		return roll;
	}
	
	public boolean isOver() {
		if(board.getBear(1)==15 || board.getBear(2)==15) {
			isGameOver=true;
		}
		return isGameOver;
	}
	
	public boolean isMovetoBar() {
		boolean isMovetoBar=false;
		if(!board.getStack(toPointTwo).isEmpty()) {
		if((board.getStackColour(fromPointOne)!=board.getStackColour(toPointTwo))){
			isMovetoBar = true;
		}
		}
		return isMovetoBar;
	}
	
	public void MovetoBar() {
		board.addToBar(toPointTwo);
	}
	
	public void endTurn() {
		isTurnOver = true;
	}
	
	public boolean isTurnOver() {
		return isTurnOver;
	}
	
	public void BearOff() {
		board.getStack(fromPointOne).pop();
		board.bearOff(playerColour);
	}
}
