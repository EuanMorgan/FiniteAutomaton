import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		//This is the main app file, it reads in the input and passes it to the relevant program
		
		
		
		
		
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
		
		
		String[] d1States;
		d1States = d1.get(1).strip().split("\\s+"); //Split states using regex
		
		String[] d2States;
		d2States = d2.get(1).strip().split("\\s+");
		
		String[] d1Alphabet;
		d1Alphabet = d1.get(3).strip().split("\\s+");
		
		String[] d2Alphabet;
		d2Alphabet = d2.get(3).strip().split("\\s+");
		
		
		
		//Transition function lines = num of states
		
		Map<String,String[]> d1Transitions = new HashMap<String,String[]>();
		
		int d1TransEnd = 0; //Will store the line number of the final transition so we know where to start from
		
		//Map each state to its transitions, tranisitions.get(state) returns a list of states in order of the alphabet symbols
		for(int i = 0; i < d1States.length; i++) {
			d1Transitions.put(d1States[i], d1.get(i+4).strip().split("\\s+"));
			d1TransEnd = i+5;
		}
		
		String d1StartState = d1.get(d1TransEnd);
		
		String[] d1FinalStates = d1.get(d1TransEnd + 2).strip().split("\\s+");
		
		
		
		Map<String,String[]> d2Transitions = new HashMap<String,String[]>();
		
		int d2TransEnd = 0;
		
		for(int i = 0; i < d2States.length; i++) {
			d2Transitions.put(d2States[i], d2.get(i+4).strip().split("\\s+"));
			d2TransEnd = i+5;
		}
		
		String d2StartState = d2.get(d2TransEnd);
		
		String[] d2FinalStates = d2.get(d2TransEnd + 2).strip().split("\\s+");
		
		
		DFA D1 = new DFA(d1States, d1Alphabet, d1Transitions, d1StartState, d1FinalStates);
		
		
		
		int numOfArgs = 3;
		
		switch(args[0]) {
			case "-c":
				numOfArgs = 2;
				if(check(numOfArgs,args.length)) break;
				Complement.calcComplement(D1);
				break;
			case "-i":
				if(check(numOfArgs,args.length)) return;
				System.out.println("Intersection");
				break;
			case "-s":
				if(check(numOfArgs,args.length)) return;
				System.out.println("Symmetric Difference");
				break;
			case "-n":
				numOfArgs = 2;
				if(check(numOfArgs,args.length)) return;
				System.out.println("Non-emptyness");
				break;
			case "-e":
				if(check(numOfArgs,args.length)) return;
				System.out.println("Equivalence");
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
