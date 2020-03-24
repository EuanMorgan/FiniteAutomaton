
public class SymmDiff {
	public static void calcDiff(DFA d1, DFA d2) {
		DFA comp = Complement.calcComplementNoPrint(d1);
		DFA IntD1 = Intersection.calcIntersectionNoPrint(comp, d2);
		
		DFA comp2 = Complement.calcComplementNoPrint(d2);
		DFA IntD2 = Intersection.calcIntersectionNoPrint(d1, comp2);
		
		
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
		
		union.printAll();
	}
}
