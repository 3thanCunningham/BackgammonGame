package backgammon;

import java.util.*;

public class Display {
	
	Scanner in;

	Display (){
		in = new Scanner(System.in);
	}
	
	
	public String getName() {
		System.out.print("Enter your name: ");
		String name = in.nextLine();
		return name;
	}
}


