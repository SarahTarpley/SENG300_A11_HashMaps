import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Driver{
	
    public static void HR_whatFlavors(List<Integer> cost, int money) {
    	// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/problem
    	// https://www.hackerrank.com/challenges/ctci-ice-cream-parlor/submissions/code/409244593
    	// set price as key to lookup flavor
        Hashtable<Integer, Integer> FlavorCosts = new Hashtable<Integer, Integer>(){{
            for(int i = 1; i <= cost.size(); i++){
                put(cost.get(i-1), i);
            }
        }};
        int f1 = 0;
        int f2 = 0;
        
        for(int i = 1; i <= cost.size(); i++){
        	Integer fcost = cost.get(i-1);
        	Integer remCost = null;
        	if(fcost < money) {
                f1 = i; // set f1 to first potential id
                remCost = money - fcost;
                // check if there is a flavor priced at the remaining cost
                if(!FlavorCosts.containsKey(remCost)) {
                	continue; // skip to next pass if not
                }
                // make sure not to select a duplicate flavor
                if(FlavorCosts.get(remCost) != i) {
                	f2 = FlavorCosts.get(remCost);
                    System.out.println(String.valueOf(f1) + " " + String.valueOf(f2)); // 1 based index
                    return;
                }
            }
        }
    }
    
    public static void HR_checkMagazine(List<String> magazine, List<String> note) {
    // https://www.hackerrank.com/challenges/ctci-ransom-note/problem
    // https://www.hackerrank.com/challenges/ctci-ransom-note/submissions/code/409245917
        Hashtable<String, Integer> hmagazine = new Hashtable<String, Integer>();
        for(int i = 0; i < magazine.size(); i++){
        	String word = magazine.get(i);
        	if(hmagazine.containsKey(word)) {
        		hmagazine.put(word, hmagazine.get(word)+1);
        	}
        	else {
        		hmagazine.put(word, 1);	
        	}
        }
        Hashtable<String, Integer> hnote = new Hashtable<String, Integer>();
        for(int i = 0; i < note.size(); i++){
        	String word = note.get(i);
        	if(!hmagazine.containsKey(word)) {
                System.out.println("No");
                return;
        	}
        	else if(hnote.containsKey(word)) {
        		
        		if(hmagazine.get(word) >= hnote.get(word)+1) {
            		hnote.put(word, hnote.get(word)+1);
        		}
        		else {
        			hnote.put(word, -1);
        			System.out.println("No");
                    return;
        		}
        	}
        	else {
        		hnote.put(word, 1);	
        	}
        }
        // only reaches here if it didn't fail to match
        System.out.println("Yes");
    }
	
    
    public static void Task02() {
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
		//sources:
		//1.) https://www.sos.arkansas.gov/education/arkansas-history/history-of-the-flag/order-of-states-admission
		//2.) https://www2.census.gov/programs-surveys/popest/geographies/2021/state-geocodes-v2021.xlsx
		//3.) https://people.math.sc.edu/Burkardt/examples/graphics_examples_plotly1/capitals.csv
    }
	
	public static void main(String[] args) {
		List<Integer> costs = Arrays.asList(4, 3, 2, 5, 7);
		HR_whatFlavors(costs, 8);
	}
}
