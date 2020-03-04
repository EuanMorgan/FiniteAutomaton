import java.util.ArrayList;
import java.util.Arrays;

public class Complement {
	public static void calcComplement(DFA d1) {
		
		ArrayList<String> fStates = new ArrayList<String>();
		
		for(String i : d1.getStates()) {
			
				if(!Arrays.asList(d1.getFinalStates()).contains(i)) {
					fStates.add(i);
				}
			
		}
		
		String[] newFStates = new String[fStates.size()];
		
		newFStates = fStates.toArray(newFStates);
		
		d1.setFinalStates(newFStates);
		
		
		
		d1.printAll();
	}
}
