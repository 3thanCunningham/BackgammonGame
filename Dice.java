package backgammon;

public class Dice {
	private final static double SIDES_ON_DICE = 6.0;    

	public int roll () {
		double roll = Math.random()*SIDES_ON_DICE+1.0;
		return (int) roll;
	}
	
public Player[] goesFirst(Player player1, Player player2){
	Dice dice = new Dice();
	
	do {
		player1.setRoll(dice.roll());
		System.out.println("\n" + player1.getName() + " rolls " + player1.getRoll());
		
		player2.setRoll(dice.roll());
		System.out.println(player2.getName() + " rolls " + player2.getRoll());
	}
	
	while(player1.getRoll() == player2.getRoll());
	
	if(player1.getRoll() > player2.getRoll()) {
		System.out.println("\n" + player1.getName()+" Goes First! \n");
		return new Player[] {player1,player2};
	}
	
	else {
		System.out.println("\n" + player2.getName()+" Goes First! \n");
		return new Player[] {player2,player1};
	}
	
}
	
}