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

	Game(Board board, BoardType boardType, CheckerColour colour){
		this.board = board;
		isGameOver = false;
		this.boardType = boardType;
		playerColour = colour;
		hintsString = new ArrayList<String>();
		hintsInteger = new ArrayList<Integer>();
		letters = new ArrayList<String>();
	}
	
	public void findHints(int roll) {
		
		if(boardType==BoardType.ANTICLOCKWISE) {
			roll*=-1;
		}
		
		String hint = "";
		
		for(int i=0; i<NUMBER_OF_POINTS;i++) {
			
			int point1 = i+1;
			int point2;
			
			if((!board.getStack(i).isEmpty()) && (i+roll < NUMBER_OF_POINTS) && (i+roll > 0)) {
			
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
	
	public void Move(String input) {
		int point1, point2;
		
		String inputFormatted = input.trim().toUpperCase();
		int index=letters.indexOf(inputFormatted);
		index*=2;
		
		point1 = hintsInteger.get(index);
		point2 = hintsInteger.get(index+1);
		
		point1-=1;
		point2-=1;
		
		board.move(point1, point2);
	}
	
	public int diceRollUsed(String input) {
int point1, point2;
		
		String inputFormatted = input.trim().toUpperCase();
		int index=letters.indexOf(inputFormatted);
		index*=2;
		
		point1 = hintsInteger.get(index);
		point2 = hintsInteger.get(index+1);
		
		int roll = Math.abs(point2-point1);
		
		return roll;
	}
	
	public boolean isOver() {
		return isGameOver;
	}
}
