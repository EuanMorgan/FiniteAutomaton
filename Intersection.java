import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Intersection {
	
	private static boolean print = true;
	
	public static DFA calcIntersection(DFA d1, DFA d2) {
		
		//Calculate intersection
		
		ArrayList<String> states = new ArrayList<String>();
		
		String[] alphabet = d1.getAlphabet();

		//Get all pairs of states, split them with whitespace so we know where an original state from D1 or D2 came from, this gives us an ordered pair (F,S) where F is in D1 and S is in D2
		
		for(String s : d1.getStates()) {
			for(String s2 : d2.getStates()) {
				states.add(s.replaceAll("\\s","") + " "  + s2.replaceAll("\\s",""));
			}
		}
		//Get the start state by combining the start states of the two DFA's
		String startState = d1.getStartState() + " " + d2.getStartState();
		
		ArrayList<String> finalStates = new ArrayList<String>();
		//Combine all final states
		for(String s : d1.getFinalStates()) {
			for(String s2 : d2.getFinalStates()) {
				finalStates.add(s.replaceAll("\\s","") + " " + s2.replaceAll("\\s",""));
			}
		}

		Map<String,Map<String,String>> newTransitions = new HashMap<String,Map<String, String>>();
		
		//get transitions
		//A transition for a state Ax would be acquired by setting it as a key in the map
		//The value of the key is a nested map which contains each alphabet character as a key
		//And the state you arrive at by reading that symbol from the current state
		//Since we know which part of the state Ax comes from D1 and which part comes from D2 we can get the correct transitions from each
		for(String intStates : states) {

			newTransitions.put(intStates.replaceAll("\\s",""), new HashMap<String,String>());
			for(String alpha : alphabet) {
				
				newTransitions.get(intStates.replaceAll("\\s","")).put(alpha, d1.getTransitions().get(intStates.split(" ")[0]).get(alpha) + d2.getTransitions().get(intStates.split(" ")[1]).get(alpha));
			}
			
		}
		//Return the resultant DFA
		DFA D3 = new DFA(states,alphabet,newTransitions,startState,finalStates);
		
		if(print)D3.printAll();
		
		return D3;
	
	}
	
	public static DFA calcIntersectionNoPrint(DFA d1, DFA d2) {
		print = false;
		return calcIntersection(d1,d2);
	}
}