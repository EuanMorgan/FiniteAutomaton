import java.util.*;
public class SymmDiff {
	public static boolean print = true;
	public static DFA calcDiff(DFA d1, DFA d2) {
		
		
		//(Comp(D1) n D2) u (D2 n Comp(D1))
		
		//Create copy of original DFAs as they get changed when calculating complement
		//initD1 = initial dfa, not to be confused with IntD1 which is the result of the intersection
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
		
		DFA union = Intersection.calcIntersection(IntD1, IntD2);

		
		ArrayList<String> newFStates = new ArrayList<String>();

		//Get accept states of both intersections
		//Final states are pairs (s,j) where s is a final state in DFA 1 or of DFA 2
		for(String d1Accept : IntD1.getFinalStates()) {
			for(String d2States : IntD2.getStates()) {
				newFStates.add(d1Accept + d2States);
			}
		}
		
		for(String d1States : IntD1.getStates()) {
			for(String d2Accept : IntD2.getFinalStates()) {
				newFStates.add(d1States + d2Accept);
			}
		}
		
		//Convert to set to get rid of unwanted values
		Set<String> setFinalStates = new HashSet<String>();
		for(String s : newFStates) {
			setFinalStates.add(s);
		}
		
		newFStates.clear();
		newFStates.addAll(setFinalStates);
		
		union.setFinalStates(newFStates);

		
		if(print)union.printAll();
		
		return union;
	}
	
	public static DFA calcDiffNoPrint(DFA d1, DFA d2) {
		print = false;
		
		return calcDiff(d1,d2);
	}
}
