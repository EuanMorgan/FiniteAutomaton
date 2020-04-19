import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		//This is the main app file, it reads in the input and passes it to the relevant program
		
		if(args.length == 0) {
			System.out.println("Invalid Input\nPlease specify a function (-c Complement, -i Intersection, -s Symmetric Difference, -n Non-Emptyness, -e Equivalence) Followed by one or more txt filenames");
			return;
		}
		
		if(args.length == 1) {
			System.out.println("Please specify filename(s)");
			return;
		}
		String filename = args[1];
		String filename2;
		
		
		
		if(args.length == 2) {
			filename2 = args[1];
		}else {
			filename2 = args[2];
			
		}
		
		File file = new File(filename);
		File file2 = new File(filename2);
		
		ArrayList<String> d1 = new ArrayList<String>();
		
		ArrayList<String> d2 = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNext()) {
				
				d1.add(sc.nextLine()); //Make arraylist of textfile
				
			}
			
			sc.close();
			
			sc = new Scanner(file2);
			
			while(sc.hasNext()) {
				d2.add(sc.nextLine());
			}
			
			sc.close();
				
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found! Please enter valid filename");
			return;
		}
		
		
		//Extract data from arraylist
		
		
		ArrayList<String> d1States = new ArrayList<String>();
		d1States.addAll(Arrays.asList(d1.get(1).strip().split("\\s+"))); //Split states using regex
		if(Integer.parseInt(d1.get(0).strip().split("\\s+")[0]) != (d1States.size()))System.out.println("WARNING: The amount of states specified does not match the total number of states provided, will run regardless");
		ArrayList<String> d2States = new ArrayList<String>();
		d2States.addAll(Arrays.asList(d2.get(1).strip().split("\\s+")));
		
		String[] d1Alphabet;
		d1Alphabet = d1.get(3).strip().split("\\s+");
		
		String[] d2Alphabet;
		d2Alphabet = d2.get(3).strip().split("\\s+");
		
		
		
		//Transition function lines = num of states
		
		Map<String,Map<String,String>> d1Transitions = new HashMap<String,Map<String, String>>();
		
		int d1TransEnd = 0; //Will store the line number of the final transition so we know where to start from

		//Map each state to its transitions, tranisitions.get(state) returns a nested map which maps the alphabet symbols to the states you end up if you follow them
		for(int i = 0; i < d1States.size(); i++) {
			d1Transitions.put(d1States.get(i), new HashMap<String,String>());
			d1Transitions.get(d1States.get(i)).put(d1Alphabet[0],d1.get(i+4).strip().split("\\s+")[0]);
			
			d1Transitions.get(d1States.get(i)).put(d1Alphabet[1],d1.get(i+4).strip().split("\\s+")[1]);
			d1TransEnd = i+5;
		}
		
		String d1StartState = d1.get(d1TransEnd);
		
		//Handle no accept states 
		
		ArrayList<String> d1FinalStates = new ArrayList<String>();
			
		try {
			d1FinalStates.addAll(Arrays.asList(d1.get(d1TransEnd + 2).strip().split("\\s+")));
		}catch(Exception e){
			//length = 0;
			
		}
		
		
		
		
		Map<String,Map<String,String>> d2Transitions = new HashMap<String,Map<String, String>>();
		
		int d2TransEnd = 0;
		
		for(int i = 0; i < d2States.size(); i++) {
			d2Transitions.put(d2States.get(i), new HashMap<String,String>());
			d2Transitions.get(d2States.get(i)).put(d2Alphabet[0],d2.get(i+4).strip().split("\\s+")[0]);
			d2Transitions.get(d2States.get(i)).put(d2Alphabet[1],d2.get(i+4).strip().split("\\s+")[1]);
			d2TransEnd = i+5;
		}
		
		String d2StartState = d2.get(d2TransEnd);
		
		ArrayList<String> d2FinalStates = new ArrayList<String>();
		
		try {
			d2FinalStates.addAll(Arrays.asList(d2.get(d2TransEnd + 2).strip().split("\\s+")));
		}catch(Exception e) {
			//length = 0
		}

		DFA D1 = new DFA(d1States, d1Alphabet, d1Transitions, d1StartState, d1FinalStates);
		
		DFA D2 = new DFA(d2States,d2Alphabet,d2Transitions,d2StartState,d2FinalStates);
		
		
		int numOfArgs = 3;
		
		switch(args[0]) {
			case "-c":
				
				numOfArgs = 2;
				if(check(numOfArgs,args.length)) break;
				System.out.println(">> calculating complement of " + filename + "\n");
				Complement.calcComplement(D1);
				break;
			case "-i":
				
				if(check(numOfArgs,args.length)) return;
				
				
				//Check if arrays use same language (sort them in case of a,b and b,a)
				//Unnecessary but just in case
				String[] temp = D1.getAlphabet().clone();
				String[] temp2 = D2.getAlphabet().clone();
				Arrays.sort(temp);
				Arrays.sort(temp2);
	
				
				if(!Arrays.equals(temp, temp2)) {
					System.out.println("Warning, the DFA's must use the same language!");
					return;
				}
				System.out.println(">> calculating intersection of " + filename + " with " + filename2 + "\n");
				Intersection.calcIntersection(D1, D2);
				break;
			case "-s":
				
				if(check(numOfArgs,args.length)) return;
				System.out.println(">> calculating symmetric difference between " + filename + " and " + filename2 + "\n");
				SymmDiff.calcDiff(D1, D2);
				break;
			case "-n":
				
				numOfArgs = 2;
				if(check(numOfArgs,args.length)) return;
				System.out.println(">> calculating non-emptiness of " + filename + "\n");
				Nonempty.dfs(D1);
				break;
			case "-e":
				
				if(check(numOfArgs,args.length)) return;
				System.out.println(">> calculating equivalence of " + filename + " and " + filename2 + "\n");
				Equivalence.calcEquivalence(D1, D2);
				break;
			
			default:
				System.out.println("Unrecognised function " + args[0] + "\nPlease choose one of -c Complement, -i Intersection, -s Symmetric Difference, -n Non-Emptyness, -e Equivalence");
		}
		}
	
	public static boolean check(int n, int a) {
		if(n != a) {
			System.out.println("Invalid Input! Please choose one of -c Complement, -i Intersection, -s Symmetric Difference, -n Non-Emptyness, -e Equivalence. Followed by one or more txt filenames");
			System.out.println("E.g. java App -Functionletter file.txt file2.txt");
			return true;
		}
		
		return false;
	}
	

}
