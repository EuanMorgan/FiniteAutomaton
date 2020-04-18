import java.util.*;

public class Nonempty {
	
	public static boolean pathFound = false;
	
	public static boolean print = true;
	
	
	public static void dfs(DFA d1) {
		
		String start = d1.getStartState();
		String[] destinations = d1.getFinalStates();
		Map<String, String[]> adjacent = d1.getTransitions();
		String[] states = d1.getStates();
		
		ArrayList<String> visited = new ArrayList<String>();
		
		Stack<String> string = new Stack<String>();
		
		List<String> alphabet = Arrays.asList(d1.getAlphabet());
		
		
		//sanity check
		
		if(destinations == null || destinations.length == 0) {
		
			if(print)System.out.println("language empty");
			return;
		}
		
		
		search(start,destinations,adjacent,states, visited, string, alphabet);
		
		if(!pathFound) {
			if(print)System.out.println("language empty");
		}
		
	}
	
	public static boolean dfsNoPrint(DFA d1) {
		print = false;
		dfs(d1);
		System.out.println("PATH FOUND = " + pathFound);
		if(pathFound) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static void search(String current, String[] destinations, Map<String, String[]> adjacent, String[] states, ArrayList<String> visited, Stack<String> string, List<String> alphabet) {
		if(pathFound)return;

		//Standard DFS
		//Add current item to visited list, if it is a final state return, else check adjacent paths and recurse.
		
		
		if(Arrays.asList(destinations).contains(current)) {
			if(print)System.out.print("language non-empty - ");
			if(string.size() == 0) {
				if(print)System.out.println("e accepted");
			}else {
				if(print)for(String s : string) System.out.print(s);
				if(print)System.out.println(" accepted");
			}
			
			
			pathFound=true;
			return;
		}
		

		
		visited.add(current);
		for(String dest : adjacent.get(current)) {

			if(!visited.contains(dest)) {

				//push the new symbol to the stack, we use the index instead of hardcoding in case the input alphabet is b,a instead of a,b
				//recursion to continue searching
				string.push(alphabet.get(Arrays.asList(adjacent.get(current)).indexOf(dest))); 
				search(dest,destinations,adjacent,states, visited, string, alphabet);
			}
			
			
		}
		
		string.clear();
		
		
	}
	
}
