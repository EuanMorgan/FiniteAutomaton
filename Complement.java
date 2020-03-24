import java.util.ArrayList;
import java.util.Arrays;

public class Complement {
	
	private static boolean print = true;
	
	public static DFA calcComplement(DFA d1) {
		
		//Complement: Non-accept states become accept and vice versa
		
		ArrayList<String> fStates = new ArrayList<String>();
		
		for(String i : d1.getStates()) {
			
				if(!Arrays.asList(d1.getFinalStates()).contains(i)) {
					fStates.add(i);
				}
			
		}
		
		String[] newFStates = new String[fStates.size()];
		
		newFStates = fStates.toArray(newFStates);
		
		d1.setFinalStates(newFStates);
		
		
		if(print==true) d1.printAll();
		
		
		return d1;
	}
	
	public static DFA calcComplementNoPrint(DFA d1) {
		print = false;
		return calcComplement(d1);
	}
}
