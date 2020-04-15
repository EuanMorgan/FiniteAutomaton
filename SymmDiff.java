
public class SymmDiff {
	public static boolean print = true;
	public static DFA calcDiff(DFA d1, DFA d2) {
		
		
		//Create copy of original DFA's as they get changed when calculating complement
		DFA initD1 = new DFA(d1);
		DFA initD2 = new DFA(d2);
		
		
		
		//First part of result
		//let a = (Complement(S) n T)
		DFA comp = Complement.calcComplementNoPrint(d1);
		DFA IntD1 = Intersection.calcIntersectionNoPrint(comp, initD2);
		
		
		//Second part of result
		//let b = (S n Complement(T))
		DFA comp2 = Complement.calcComplementNoPrint(d2);
		DFA IntD2 = Intersection.calcIntersectionNoPrint(initD1, comp2);
		
		
		
		//Final part of result
		//a u b
		//Calculated by taking intersection and then modifying the final states
		//Such that the set of all final states F* = set of all states (r,s)
		//such that r is a final state of IntD1 OR s is a final state of IntD2
		DFA union = Intersection.calcIntersectionNoPrint(comp, comp2);
		
		String[] newFStates = new String[IntD1.getFinalStates().length + IntD2.getFinalStates().length];
		
		int i = 0;
		for(String s : IntD1.getFinalStates()) {
			newFStates[i] = s;
			i++;
		}
		
		
		
		for(String s : IntD2.getFinalStates()) {
			newFStates[i] = s;
			i++;
		}
		

		
		union.setFinalStates(newFStates);
		
		if(print)union.printAll();
		
		return union;
	}
	
	public static DFA calcDiffNoPrint(DFA d1, DFA d2) {
		print = false;
		
		return calcDiff(d1,d2);
	}
}
