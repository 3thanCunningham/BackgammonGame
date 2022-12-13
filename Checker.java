/*
brightspace team 13
Ethan Cunningham: Github ID -> ethncunningham
Jerome Manzano:	  Github ID -> jerome-manzano	
*/

package backgammon;

/*
 * This class models a single checker
 */

public class Checker {
private CheckerColour colour;

	Checker(){
	// New checker is initially white until changed
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
				// Prints white on console
				return DisplayColour.WHITE + "  O  " + DisplayColour.RESET;
			}
			else 
				// Prints red on console
				return DisplayColour.RED + "  O  " + DisplayColour.RESET;
			}
}