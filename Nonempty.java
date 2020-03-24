import java.util.*;

public class Nonempty {
	private static Stack<String> path  = new Stack<String>();
	private static Set<String> onPath  = new HashSet<String>();
	private static boolean done = false;
	public static void calcNonEmptyness(DFA d1) {
		Map<String, String[]> t = d1.getTransitions();
		String[] s = d1.getStates();
		String ss = d1.getStartState();
		String[] fs = d1.getFinalStates();
		
		String[] alphabet = {"a","b"};
		
		int count = 0;
		
		ArrayList<String> toSearch = new ArrayList<String>();
		ArrayList<String> searched = new ArrayList<String>();
		
		for(String i : d1.getTransitions().get(ss)) {
			
			toSearch.add(i);
			
			for(String j : fs) {
				
				if(i.equals(j)) {
					
					System.out.println("language non-empty " + alphabet[count] + " accepted");
					return;
				}
			}
			
			count++;
		}
		count = 0;		
		
		
		for(int i = 0; i < fs.length; i++) {
			search(d1, ss, fs[i]);
			language.clear();
		}
		
		
		System.out.println("language empty");
		
	}
	
	static ArrayList<String> language = new ArrayList<String>();
	static ArrayList<String> n = new ArrayList<String>();
	public static void search(DFA d1, String Start, String End) {
		
		if(done)return;
		
		path.push(Start);
		onPath.add(Start);
		
		String[] alphabet = {"a","b"};
		if(Start.equals(End)) {
			if(language.size() > 0) {
				System.out.println("language non-empty " + language + " accepted");
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
		
		path.pop();
		onPath.remove(Start);
	}
	
}
