package backgammon;

public class Checker {
private CheckerColour colour;

	Checker(){
	colour = CheckerColour.WHITE;
	}

	Checker (CheckerColour colour){
	this.colour = colour;
	}

	public CheckerColour getColour() {
	return colour;
	}

	public String toString() {
			if(colour==CheckerColour.WHITE) {
				return DisplayColour.WHITE + "  O  " + DisplayColour.RESET;
			}
			else 
				return DisplayColour.RED + "  O  " + DisplayColour.RESET;
			}
}