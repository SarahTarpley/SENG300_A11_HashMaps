import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class USAState {

	String stateAbbv;
	String name;
	String capital;
	String region;
	int population;
	Date stateHoodIncorp;
	int stateOrder;
	String stateFIPS;
	private static SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
	public static HashMap<String, USAState> StateList = new HashMap<>();
	
	
	public USAState(
			String stateAbbv,
			String name,
			String capital,
			String region,
			String population,
			String dateInput,
			String stateOrder,
			String stateFIPS
			) throws ParseException {
		this.stateAbbv = stateAbbv;
		this.name = name;
		this.capital = capital;
		this.region = region;
		this.population = Integer.valueOf(population);
		this.stateHoodIncorp = ft.parse(dateInput);
		this.stateOrder = Integer.valueOf(stateOrder);
		this.stateFIPS = stateFIPS;
		
		if(this.stateHoodIncorp == null) {
			throw new ParseException("Please enter the date in format 'yyyy-MM-dd'", 1);
		}
	}
	
	public void addState(){
		StateList.put(this.name, this);
	}
	
	public static USAState getState(String name) {
		StateList.get(name).print();
		return StateList.get(name);
	}
	
	public static void printAllStates() {
		StateList.forEach((k,v)-> v.print());
	}
	
	public static void importStates() {
		//Import 50 states data from csv
		try {
			Scanner sc = new Scanner(new File("States.csv"));
			sc.nextLine(); // discard header information
			int i = 0;
			sc.useDelimiter(",");
			String[] stateInfo = new String[8];
			while(sc.hasNext()) {
				stateInfo[i] = sc.next().trim();
				//System.out.println("Line Num: " +String.valueOf(i));
				//System.out.println(stateInfo[i]);
				if(i == 7 && i != 0) {
					//constructor order matches column header order
					USAState nextState = new USAState(
							stateInfo[0],
							stateInfo[1],
							stateInfo[2],
							stateInfo[3],
							stateInfo[4],
							stateInfo[5],
							stateInfo[6],
							stateInfo[7]
							);
					nextState.addState();
					i = 0;
				}
				else {
					i++;
				}
			}
			sc.close();
		}
		catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
		catch (Exception e){
			System.out.println(e);
		}
	}
	
	public void print() {
		System.out.println(
				  "State name: " + this.name + "\r\n"
				+ "State abbreviation: " + String.valueOf(this.stateAbbv) + "\r\n"
				+ "State capital: " + String.valueOf(this.capital) + "\r\n"
				+ "State region: " + this.region + "\r\n"
				+ "State population: " + String.valueOf(this.population) + "\r\n"
				+ "State incorporation date: " + ft.format(this.stateHoodIncorp) + "\r\n"
				+ "State order of incorporation: " + String.valueOf(this.stateOrder) + "\r\n"
				+ "State FIPS code: " + String.valueOf(this.stateFIPS) + "\r\n"
				);
	}
	
	
}

