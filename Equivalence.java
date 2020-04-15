
public class Equivalence {
	//If symmetric difference is empty then they equivalent
	
	public static void calcEquivalence(DFA d1, DFA d2) {
		DFA diff = SymmDiff.calcDiff(d1, d2);

		System.out.println(Nonempty.calcNonEmptyness(diff));
		
		if(Nonempty.calcNonEmptynessNoPrint(diff)) {
			System.out.println("equivalent");
		}else {
			System.out.println("not equivalent");
		}
	}
}
