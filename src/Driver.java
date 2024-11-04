import java.util.Scanner;

public class Driver{
	public static void main(String[] args) {
		// Task 2: USA States
		USAState.importStates();
		USAState.printAllStates();
		//System.out.print("Type the name of the state you would like to look up or press x to exit: ");
		Scanner userInput = new Scanner(System.in);
		String userSelection = "";
		while(!userSelection.equals("x")) {
			try {
				System.out.print("Type the name of the state you would like to look up or press x to exit: ");
				userSelection = userInput.nextLine().trim();
				if(userSelection.equals("x")) {
					break;
				}
				USAState.getState(userSelection);
			}
			catch (Exception e){
				System.out.println("Cannot find a state matching input: "+userSelection);
			}
		}
	}
}

//sources:
//1.) https://www.sos.arkansas.gov/education/arkansas-history/history-of-the-flag/order-of-states-admission
//2.) https://www2.census.gov/programs-surveys/popest/geographies/2021/state-geocodes-v2021.xlsx
//3.) https://people.math.sc.edu/Burkardt/examples/graphics_examples_plotly1/capitals.csv