package backgammon;

public class Dice {
	private final static double SIDES_ON_DICE = 6.0;    

	public int roll () {
		double roll = Math.random()*SIDES_ON_DICE+1.0;
		return (int) roll;
	}
	
	public Player[] goesFirst(Player player1, Player player2){
	Dice dice = new Dice();
	int roll1, roll2;
	
	do {
		roll1 = dice.roll();
		System.out.println("\n" + player1.getName() + " rolls " + roll1);
		
		roll2 = dice.roll();
		System.out.println(player2.getName() + " rolls " + roll2);
	}
	
	while(roll1 == roll2);
	
	if(roll1 > roll2) {
		System.out.println("\n" + player1.getName()+" Goes First! \n");
		return new Player[] {player1,player2};
	}
	
	else {
		System.out.println("\n" + player2.getName()+" Goes First! \n");
		return new Player[] {player2,player1};
	}
	
}
	
}