package backgammon;
import java.util.*;

public class Game {
	private static final int NUMBER_OF_POINTS = 24;
	private ArrayList<String> hintsString;
	private ArrayList<Integer> hintsInteger;
	private ArrayList<String> letters;
	private Board board;
	private boolean isGameOver;
	private boolean isDouble;
	private BoardType boardType;
	private int roll1, roll2;
	private CheckerColour playerColour;

	Game(Board board, BoardType boardType, int roll1, int roll2, boolean isDouble, CheckerColour colour){
		this.board = board;
		isGameOver = false;
		this.boardType = boardType;
		this.roll1 = roll1;
		this.roll2 = roll2;
		this.isDouble = isDouble;
		playerColour = colour;
		hintsString = new ArrayList<String>();
		hintsInteger = new ArrayList<Integer>();
		letters = new ArrayList<String>();
	}
	
	public void findHints() {
		
		if(boardType==BoardType.ANTICLOCKWISE) {
			roll1*=-1;
			roll2*=-1;
		}
		
		String hint = null;
		
		for(int i=0; i<NUMBER_OF_POINTS;i++) {
			
			int point1 = i+1;
			int point2;
			
			if((!board.getStack(i).isEmpty()) && (i+roll1 < NUMBER_OF_POINTS) && (i+roll2 < NUMBER_OF_POINTS) && (i+roll1 > 0) && (i+roll2 > 0)) {
			
			if(playerColour==board.getStackColour(i)) {
				
				if(board.getStack(i+roll1).isEmpty()) {
					point2 = point1+roll1;
					hint = "( "+ point1 + " , " + point2 + " )";
					hintsString.add(hint);
					hintsInteger.add(point1);
					hintsInteger.add(point2);
				}
				else if(playerColour==board.getStackColour(i+roll1) || (board.getStackSize(i+roll1) == 1) ){
					point2 = point1+roll1;
					hint = "( "+ point1 + " , " + point2 + " )";
					hintsString.add(hint);
					hintsInteger.add(point1);
					hintsInteger.add(point2);
				}
				
				if(board.getStack(i+roll2).isEmpty()) {
					point2 = point1+roll2;
					hint = "( "+ point1 + " , " + point2 + " )";
					hintsString.add(hint);
					hintsInteger.add(point1);
					hintsInteger.add(point2);
				}
				else if(playerColour==board.getStackColour(i+roll2) || (board.getStackSize(i+roll2) == 1)) {
					point2 = point1+roll2;
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
	
	public boolean isOver() {
		return isGameOver;
	}
}
