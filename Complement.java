import java.util.*;
import java.util.Arrays;

public class Complement {
	private static boolean print = true;
	
	public static DFA calcComplement(DFA d1) {
		
		//Complement: Non-accept states become accept and vice versa
		
		ArrayList<String> fStates = new ArrayList<String>();
		if(d1.getFinalStates().size() != 0) {
			for(String i : d1.getStates()) {
				
				if(!d1.getFinalStates().contains(i)) {
					fStates.add(i);
				}
			
		}
		}
		
		//S = set of states and F = set of final states.
		//If = {}, then Complement(F) = S;
		if(d1.getFinalStates().size() != 0) {
			d1.setFinalStates(fStates);
		}else {
			d1.setFinalStates(d1.getStates());
		}
		
		if(print) d1.printAll();

		return d1;
	}
	
	public static DFA calcComplementNoPrint(DFA d1) {
		//The NoPrint methods are used when we don't want to print out the value to the screen
		//i.e. when the method is needed as part of another method, and not as a final result
		print = false;
		return calcComplement(d1);
	}
}
