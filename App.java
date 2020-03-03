import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		//This is the main app file, it reads in the input and passes it to the relevant program
		
		if(args.length != 2) {
			System.out.println("Please specify filenames. E.g. java App file1.txt file2.txt");
			return;
		}
		
		String filename = args[0];
		String filename2 = args[1];
		File file = new File(filename);
		File file2 = new File(filename2);
		
		ArrayList<String> d1 = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(file);
			
			while(sc.hasNext()) {
				
				d1.add(sc.nextLine()); //Make arraylist of textfile
				
			}
				
		} catch (FileNotFoundException e) {
			
			System.out.println("File not found! Please enter valid filename");
			return;
		}
		
		
		//Extract data from arraylist
		
		
		String[] states;
		
		states = d1.get(1).strip().split("\\s+"); //Split states using regex
		
		String[] alphabet;
		
		alphabet = d1.get(3).strip().split("\\s+");
		
		
		
		//Transition function lines = num of states
		
		Map<String,String[]> transitions = new HashMap<String,String[]>();
		
		int transEnd = 0; //Will store the line number of the final transition so we know where to start from
		
		//Map each state to its transitions, tranisitions.get(state) returns a list of states in order of the alphabet symbols
		for(int i = 0; i < states.length; i++) {
			transitions.put(states[i], d1.get(i+4).strip().split("\\s+"));
			transEnd = i+5;
		}
		
		String startState = d1.get(transEnd);
		
		String[] finalStates = d1.get(transEnd + 2).strip().split("\\s+");
		
		
		
		
	}

}
