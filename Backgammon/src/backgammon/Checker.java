package backgammon;

public class Checker {
	private CheckerColour colour;

	Checker (){
		colour = CheckerColour.RED;
	}
	
	Checker (CheckerColour colour){
		this.colour = colour;
	}
	
	public CheckerColour getColour() {
		return colour;
	}
	
}

