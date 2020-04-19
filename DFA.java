import java.util.ArrayList;
import java.util.Map;

public class DFA {
	private ArrayList<String> states;
	private String[] alphabet;
	
	private Map<String, Map<String, String>> transitions;
	
	private String startState;
	
	private ArrayList<String> finalStates;
	
	public DFA(ArrayList<String> s, String[] a, Map<String,Map<String,String>> t, String ss, ArrayList<String> fs) {
		states = s;
		alphabet = a;
		transitions = t;
		startState = ss;
		finalStates = fs;
		
	}
	
	public DFA(DFA d1) {
		//Copy constructor, used in symmetric difference to create copy of DFA to save original value
		states = d1.states;
		alphabet = d1.alphabet;
		transitions = d1.transitions;
		startState = d1.startState;
		finalStates = d1.finalStates;
	}

	public void setStates(ArrayList<String> s) {
		states = s;
	}
	
	public void setAlphabet(String[] a) {
		alphabet = a;
	}
	
	public void setTransitions(Map<String, Map<String, String>> t) {
		transitions = t;
	}
	
	public void setStartState(String s) {
		startState = s;
	}
	
	public void setFinalStates(ArrayList<String> f) {
		finalStates = f;
	}
	
	public ArrayList<String> getStates() {
		return states;
	}
	
	public String[] getAlphabet() {
		return alphabet;
	}
	
	public Map<String, Map<String, String>> getTransitions(){
		return transitions;
	}
	
	public String getStartState() {
		return startState;
	}
	
	public ArrayList<String> getFinalStates() {
		return finalStates;
	}
	
	public void printAll() {
		System.out.println(getStates().size());
		for(String i : getStates()) System.out.print(i.replaceAll("\\s","") + " ");
		System.out.println();
		
		
		System.out.println(getAlphabet().length);
		for (String i : getAlphabet()) System.out.print(i + " ");
		System.out.println();
		for(String i : getStates()) {

			i = i.replaceAll("\\s","");
			System.out.print(getTransitions().get(i).get(getAlphabet()[0]) + " ");
			System.out.println(getTransitions().get(i).get(getAlphabet()[1]));
		}
		
		System.out.println(getStartState().replaceAll("\\s",""));
		
		if(getFinalStates().size() != 0) {
			System.out.println(getFinalStates().size());
			for(String i : getFinalStates()) System.out.print(i.replaceAll("\\s","") + " ");
			System.out.println();
		}else {
			System.out.println(0);
		}
		
		
		
	}
	
	
	
	
}
