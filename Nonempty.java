import java.util.*;

public class Nonempty {
	private static Stack<String> path  = new Stack<String>();
	private static Set<String> onPath  = new HashSet<String>();
	private static boolean done = false;
	public static boolean print = true;
	public static boolean calcNonEmptyness(DFA d1) {
		Map<String, String[]> t = d1.getTransitions();
		String[] s = d1.getStates();
		String ss = d1.getStartState();
		String[] fs = d1.getFinalStates();
		
		String[] alphabet = {"a","b"};
		
		int count = 0;
		
		ArrayList<String> toSearch = new ArrayList<String>();
		ArrayList<String> searched = new ArrayList<String>();
		
		
		//Calculate if we can get to accept state from start state with one move
		
		for(String u : fs) {
			if(u.equals(ss)) {
				System.out.println("language non-empty e accepted");
				return true;
			}
			
		}
		
		
		for(String i : d1.getTransitions().get(ss)) {
			
			toSearch.add(i);
			
			for(String j : fs) {
				
				if(i.equals(j)) {
					
					if(print)System.out.println("language non-empty " + alphabet[count] + " accepted");
					return false;
				}
			}
			
			count++;
		}
		count = 0;		
		
		
		for(int i = 0; i < fs.length; i++) {

			search(d1, ss, fs[i]);
			language.clear();

			if(done) return false;
		}
		
		
		if(print)System.out.println("language empty");
		return true;
		
	}
	
	static ArrayList<String> language = new ArrayList<String>();
	static ArrayList<String> n = new ArrayList<String>();
	public static int zzz = 0;
	public static void search(DFA d1, String Start, String End) {

		if(done)return;
		
		path.push(Start);
		onPath.add(Start);
		
		String[] alphabet = {"a","b"};
		try {
			
		
		if(Start.equals(End)) {
			
			if(language.size() > 0) {
				if(print)System.out.println("language non-empty " + language + " accepted");
				done=true;
				return;
			}else {
				int count = 0;
				for(String w : d1.getTransitions().get(Start)) {
			
					if(!onPath.contains(w) || w.equals(d1.getStartState())) {
						
						language.add(alphabet[count]);
				
						
						search(d1,w,End);
						}
					count++;
				}
			}
			
		}else {
		
			int count = 0;
			for(String w : d1.getTransitions().get(Start)) {

				if(!onPath.contains(w) || w.equals(d1.getStartState())) {
					
					language.add(alphabet[count]);

					
					search(d1,w,End);
					}
				count++;
			}
			
		}
		}catch(Exception e){}

		path.pop();
		onPath.remove(Start);
	}
	
	public static boolean calcNonEmptynessNoPrint(DFA d1) {
		print = false;
		return calcNonEmptyness(d1);
	}
	
}
