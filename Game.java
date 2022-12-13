/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

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
	private int matchLength;

	Game(){
		isTurnOver=false;
	}
	
	Game(Board board, BoardType boardType, CheckerColour colour){
		this.board = board;
		isGameOver = false;
		this.boardType = boardType;
		playerColour = colour;
		hintsString = new ArrayList<String>();	//arraylist stores possible legal moves
		hintsInteger = new ArrayList<Integer>();	// integer array list used to cross reference moves with indexes of stacks on board
		letters = new ArrayList<String>();
		fromPointOne=0;
		toPointTwo=0;
		isTurnOver = false;
		matchLength = 0;
	}
	
	public void findHints(int roll) {
		String hint = "";
		
		/* if-else statement used to enforce rule that if player has a checker on the bar 
		 * then it should be removed first and no other moves should be available */
		if(board.hasCheckerOnBar() && board.getBar().getColour()==playerColour) {
			if(boardType==BoardType.CLOCKWISE) {
				if(board.getStack(roll-1).isEmpty() || board.getStackColour(roll-1)==playerColour) {
					hint = "(  BAR , " + (roll) + " )";
					hintsString.add(hint);
					hintsInteger.add(26); // bar indicated by index 26
					hintsInteger.add(roll);
				}
			}
			else {
				if(board.getStack(24-roll).isEmpty() || board.getStackColour(24-roll)==playerColour) {
					hint = "(  BAR , " + (roll) + " )";
					hintsString.add(hint);
					hintsInteger.add(26);
					hintsInteger.add(25-roll);
				}
			}
		}
		else {
		/* if home board is full then checkers can be borne-off */
		if(board.isHomeBoardFull(playerColour)) {
			if(boardType==BoardType.CLOCKWISE) {
				if((!board.isStackEmpty(24-roll)) && (board.getStackColour(24-roll)==playerColour)) {
					hint = "( "+ (25-roll) + " , BEAR-OFF )";
					hintsString.add(hint);
					/*
					 * bear-off indicated by points equalling eachother
					 */
					hintsInteger.add(25-roll);
					hintsInteger.add(25-roll);
				}
			}
			else {
				if((!board.isStackEmpty(roll-1)) && (board.getStackColour(roll-1)==playerColour)) {
					hint = "( "+ (25-roll) + " , BEAR-OFF )";
					hintsString.add(hint);
					hintsInteger.add(roll);
					hintsInteger.add(roll);
				}
			}
		}
		
		
		/* calculate remaining legal moves using dice roll */
		/* for each point, check if stack is the same colour as the current player */
		/* then check if checkers on this stack can be legally moved by the dice roll */
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
		/* code format altered depening on direction of movement */
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
		
		/* if home board is full and no moves are available using exact dice roll
		 * then checker on highest point must be borne off
		 * for-loop below finds highest point containing checker
		 */
		if(board.isHomeBoardFull(playerColour) && hintsString.isEmpty()) {
			if(boardType==BoardType.CLOCKWISE) {
				for(int i=18;i<NUMBER_OF_POINTS;i++) {
					if((!board.isStackEmpty(i)) && (board.getStackColour(i)==playerColour)) {
						hint = "( "+ (i+1) + " , BEAR-OFF )";
						hintsString.add(hint);
						hintsInteger.add(i+1);
						hintsInteger.add(i+1);
						break;
					}
				}
			}
			else {
				for(int i=5;i>=0;i--) {
					if((!board.isStackEmpty(i)) && (board.getStackColour(i)==playerColour)) {
						hint = "( "+ (24-i) + " , BEAR-OFF )";
						hintsString.add(hint);
						hintsInteger.add(i+1);
						hintsInteger.add(i+1);
						break;
					}
				}
			}
		}
	}
	

	
	public void giveHints() {
		
		System.out.println("\nPossible Moves: ");
		char letter = 'A';
		/* enhanced for-loop prints all hints from hintsString arraylist with letter added to differentiate moves */
		for ( String hint : hintsString ) {
		    System.out.println( letter + " - " + hint );
		    letters.add(String.valueOf(letter));
		    letter++;	//character incremented for each move
		}
	}
	
	public boolean isMoveAvailable() {
		/*
		 * if the hints arraylist is empty, no hints have been added meaning no legal moves are available
		 */
		return !hintsString.isEmpty();
	}
	
	public boolean isInputValid(String input) {
		/*
		 * checks that user input correctly corresponds to letter code of available moves
		 */
		
		boolean isInputValid = false;
		
		if(!input.isEmpty()) {
		String inputFormatted = input.trim().toUpperCase();
		for(int i=0; i<letters.size();i++) {
			if(inputFormatted.charAt(0)==letters.get(i).charAt(0)) {
				isInputValid=true;
			}
		}
		}
		return isInputValid;
	}
	
	
	
	public void getMove(String input) {
		/*
		 * extracts stack indexes from chosen user command
		 */
		
		String inputFormatted = input.trim().toUpperCase();
		int index=letters.indexOf(inputFormatted);
		index*=2;
		
		fromPointOne = hintsInteger.get(index);
		toPointTwo = hintsInteger.get(index+1);
		
		fromPointOne-=1;
		toPointTwo-=1;
	}
	
	public void Move() {
		/*
		 * moves checker from pointOne to pointTwo ( or bears off/ removes from bar )
		 */
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
		/*
		 * dice roll that was used calculated by finding how many points checker was moved
		 */
		int roll = Math.abs(toPointTwo-fromPointOne);
		
		return roll;
	}
	
	public boolean isOver() {
		/*
		 * game ends when all 15 checkers are borne off
		 */
		if(board.getBear(1)==15 || board.getBear(2)==15) {
			isGameOver=true;
		}
		return isGameOver;
	}
	
	public boolean isMovetoBar() {
		/*
		 * if checker is moving to a point that contains an opponents checker, then the opponents checker will be moved to bar
		 * this code determines if this is the case
		 */
		boolean isMovetoBar=false;
		if(!board.getStack(toPointTwo).isEmpty() && fromPointOne<24) {
		if((board.getStackColour(fromPointOne)!=board.getStackColour(toPointTwo))){
			isMovetoBar = true;
		}
		}
		return isMovetoBar;
	}
	
	public void MovetoBar() {
		board.addToBar(toPointTwo);
	}
	
	public void endTurn(Boolean turn) {
		this.isTurnOver = turn;
	}
	
	public boolean isTurnOver() {
		return isTurnOver;
	}
	
	public void BearOff() {
		board.getStack(fromPointOne).pop();
		board.bearOff(playerColour);
	}
	
	public void setMatchLength(int matchLength) {
		this.matchLength = matchLength;
	}
	
	public int getMatchLength() {
		return matchLength;
	}
	
public boolean isCheckerOnHomeBoard(BoardType boardType) {
	
	/*
	 * code that checks if players checker is positioned on opponents home board at end of game
	 * used to determine if game has ended in single/gammon/backgammon
	 */
	
	boolean checkerOnHomeBoard = false;
	
	if(boardType==BoardType.CLOCKWISE) {
		for(int i=18;i<24;i++) {
			if(!board.getStack(i).isEmpty()){
				checkerOnHomeBoard=true;
			}
		}
	}
	else {
		for(int i=5;i>=0;i--) {
			if(!board.getStack(i).isEmpty()){
				checkerOnHomeBoard = true;
			}
		}
	}
	
	return checkerOnHomeBoard;
}
}
