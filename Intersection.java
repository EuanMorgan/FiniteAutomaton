import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Intersection {
	
	private static boolean print = true;
	
	public static DFA calcIntersection(DFA d1, DFA d2) {
		
		
		
		ArrayList<String> states = new ArrayList<String>();
		
		String[] alphabet = d1.getAlphabet();

		
		
		for(String s : d1.getStates()) {
			for(String s2 : d2.getStates()) {
				states.add(s.replaceAll("\\s","") + " "  + s2.replaceAll("\\s",""));
			}
		}
		
		String startState = d1.getStartState() + " " + d2.getStartState();
		
		ArrayList<String> finalStates = new ArrayList<String>();
		
		for(String s : d1.getFinalStates()) {
			for(String s2 : d2.getFinalStates()) {
				finalStates.add(s.replaceAll("\\s","") + " " + s2.replaceAll("\\s",""));
			}
		}

		Map<String,Map<String,String>> newTransitions = new HashMap<String,Map<String, String>>();
		
		//Output transitions
		//System.out.println(states);
		for(String intStates : states) {

			newTransitions.put(intStates.replaceAll("\\s",""), new HashMap<String,String>());
			for(String alpha : alphabet) {
				
				newTransitions.get(intStates.replaceAll("\\s","")).put(alpha, d1.getTransitions().get(intStates.split(" ")[0]).get(alpha) + d2.getTransitions().get(intStates.split(" ")[1]).get(alpha));
			}
			
			//System.out.println(newTransitions);
			
		}
		
		
		
		DFA D3 = new DFA(states,alphabet,newTransitions,startState,finalStates);
		
		if(print)D3.printAll();
		
		
		return D3;
	
	}
	
	public static DFA calcIntersectionNoPrint(DFA d1, DFA d2) {
		print = false;
		return calcIntersection(d1,d2);
	}
}