package backgammon;

public class Dice {
	private final static double SIDES_ON_DICE = 6.0;    

	public int roll () {
		double roll = Math.random()*SIDES_ON_DICE+1.0;
		return (int) roll;
	}
	
}
