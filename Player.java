package backgammon;

public class Player {
	
	private BoardType pipNumbers;
	private String name;
	private int roll;
	private int pipCount;
	
	Player (String name){
		this.name = name;
		pipCount = 167;
		roll = 1;
		pipNumbers = BoardType.CLOCKWISE;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRoll() {
		return roll;
	}
	
	public void setRoll(int roll) {
		this.roll = roll;
	}
	
	public void setBoard(BoardType pipNumbers) {
		this.pipNumbers = pipNumbers;
	}
	
	public BoardType getBoardType(){
		return pipNumbers;
	}
	
	public void setPipCount(int pipCount) {
		this.pipCount = pipCount;
	}
	
	public int getPipCount() {
		return pipCount;
	}
}
	
