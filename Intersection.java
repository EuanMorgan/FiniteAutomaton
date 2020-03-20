import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Intersection {
	public static void calcIntersection(DFA d1, DFA d2) {
		
	
		
		

		
		ArrayList<String> states = new ArrayList<String>();
		
		String[] alphabet = {"a","b"};
		
		
		
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
		
		
		ArrayList<String> temptrans = new ArrayList<String>();
		ArrayList<String> ttrans = new ArrayList<String>();
		
		
		
		Map<String,String[]> transitions = new HashMap<String,String[]>();
		
		for(String s : d1.getStates()) {
			
		
			for(String s1 : d1.getTransitions().get(s)) {
				
				temptrans.add(s1);
				
			}
		}
		
		for(String ss : d2.getStates()) {
			for(String s2 : d2.getTransitions().get(ss)) {
				ttrans.add(s2);
			}
		}
		
		//Find row for first letter of alphabet
		ArrayList<String> tempFirst = new ArrayList<String>();
		for(int d = 0; d < temptrans.size(); d+=2) {
			
		
			for(int i = 0; i < ttrans.size(); i+=2) {
				tempFirst.add(temptrans.get(d) + ttrans.get(i));
			}
		}
		
		
		//Find row for second letter of alphabet
		ArrayList<String> tempSecond = new ArrayList<String>();
		for(int d = 1; d < temptrans.size(); d+=2) {
			
			
			for(int i = 1; i < ttrans.size(); i+=2) {
				tempSecond.add(temptrans.get(d) + ttrans.get(i));
			}
		}
		
		
		
		for(int i = 0; i < states.size(); i++) {
			String[] xyz = {tempFirst.get(i), tempSecond.get(i)};
			transitions.put(states.get(i), xyz);
		}
		
		
		
		String[] newFStates = new String[states.size()];
		
		newFStates = states.toArray(newFStates);
		
		String[] newFinalStates = new String[finalStates.size()];
		
		newFinalStates = finalStates.toArray(newFinalStates);
		
		DFA D3 = new DFA(newFStates,alphabet,transitions,startState,newFinalStates);
		
		D3.printAll();
	
	}
}
