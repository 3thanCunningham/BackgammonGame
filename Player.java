package backgammon;

public class Player {
	private String name;
	private int roll;
	
	
	Player (String name){
		this.name = name;
		roll = 1;
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
	
}