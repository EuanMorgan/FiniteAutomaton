
public class Equivalence {
	//If symmetric difference is empty then they equivalent
	
	public static void calcEquivalence(DFA d1, DFA d2) {
		DFA diff = SymmDiff.calcDiffNoPrint(d1, d2);

		
		
		if(Nonempty.dfsNoPrint(diff)) {
			System.out.println("equivalent");
		}else {
			System.out.println("not equivalent");
		}
	}
}
