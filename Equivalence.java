
public class Equivalence {
	//If symmetric difference is empty then they equivalent
	
	public static void calcEquivalence(DFA d1, DFA d2) {
		
		
		//Check if either dfa has no accept states, thus accepting no strings
		//Even if both have no accept states it still doesn't satisfy our definition
		//if L(M) == L(M1) as neither have a language in the first place.
		if(d1.getFinalStates() == null || d2.getFinalStates() == null) {
			System.out.println("not equivalent");
			return;
		}
		
		
		
		DFA diff = SymmDiff.calcDiffNoPrint(d1, d2);
		

		
		if(!Nonempty.dfsNoPrint(diff)) {
			System.out.println("equivalent");
		}else {
			System.out.println("not equivalent");
		}
	}
}
