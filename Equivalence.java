
public class Equivalence {
	//If symmetric difference is empty then they are equivalent
	
	public static void calcEquivalence(DFA d1, DFA d2) {
		
		
		//Check if either dfa has no accept states, thus accepting no strings
		//Even if both have no accept states it still doesn't satisfy our definition
		//if both have no accept states then they accept no languages
		//or, to put it another what L(M) = {} and L(M') = {} so they're equivalent
		//check this using xor
				if(d1.getFinalStates().size() == 0 ^ d2.getFinalStates().size() == 0) {
					System.out.println("not equivalent");
					return;
				}else {
					
				}
		
		DFA diff = SymmDiff.calcDiffNoPrint(d1, d2);

		
		if(!Nonempty.dfsNoPrint(diff)) {
			System.out.println("equivalent");
		}else {
			System.out.println("not equivalent");
		}
	}
}
