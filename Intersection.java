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
				states.add(s+s2);
			}
		}
		
		String startState = states.get(0);
		
		ArrayList<String> finalStates = new ArrayList<String>();
		
		for(String s : d1.getFinalStates()) {
			for(String s2 : d2.getFinalStates()) {
				finalStates.add(s+s2);
			}
		}

		Map<String,Map<String,String>> newTransitions = new HashMap<String,Map<String, String>>();
		
		//Output transitions
		
		for(String intStates : states) {
			newTransitions.put(intStates, new HashMap<String,String>());
			for(String alpha : alphabet) {				
				newTransitions.get(intStates).put(alpha, d1.getTransitions().get(intStates.substring(0, 1)).get(alpha) + d2.getTransitions().get(intStates.substring(1, 2)).get(alpha));
			}
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