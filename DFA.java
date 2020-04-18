import java.util.Map;

public class DFA {
	private String[] states;
	private String[] alphabet;
	
	private Map<String, String[]> transitions;
	
	private String startState;
	
	private String[] finalStates;
	
	public DFA(String[] s, String[] a, Map<String,String[]> t, String ss, String[] fs) {
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

	public void setStates(String[] s) {
		states = s;
	}
	
	public void setAlphabet(String[] a) {
		alphabet = a;
	}
	
	public void setTransitions(Map<String, String[]> t) {
		transitions = t;
	}
	
	public void setStartState(String s) {
		startState = s;
	}
	
	public void setFinalStates(String[] f) {
		finalStates = f;
	}
	
	public String[] getStates() {
		return states;
	}
	
	public String[] getAlphabet() {
		return alphabet;
	}
	
	public Map<String, String[]> getTransitions(){
		return transitions;
	}
	
	public String getStartState() {
		return startState;
	}
	
	public String[] getFinalStates() {
		return finalStates;
	}
	
	public void printAll() {
		System.out.println(getStates().length);
		for(String i : getStates()) System.out.print(i + " ");
		System.out.println();
		
		
		System.out.println(getAlphabet().length);
		for (String i : getAlphabet()) System.out.print(i + " ");
		System.out.println();
		for(String i : getStates()) {
			
			for(String b : getTransitions().get(i)) System.out.print(b + " ");
			System.out.println();
		}
		
		System.out.println(getStartState());
		
		
		if(getFinalStates() != null) {
			System.out.println(getFinalStates().length);
			for(String i : getFinalStates()) System.out.print(i + " ");
			System.out.println();
		}else {
			System.out.println(0);
		}
		
		
		
	}
	
	
	
	
}
