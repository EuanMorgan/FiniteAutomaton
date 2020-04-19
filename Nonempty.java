import java.util.*;

public class Nonempty {
	
	public static boolean pathFound = false;
	
	public static boolean print = true;
	
	
	public static void dfs(DFA d1) {
		
		String start = d1.getStartState();
		ArrayList<String> tmpDests = d1.getFinalStates();
		ArrayList<String> destinations = new ArrayList<String>();
		for(String s : tmpDests)destinations.add(s.replaceAll("\\s",""));
		Map<String, Map<String, String>> adjacent = d1.getTransitions();
		ArrayList<String> states = d1.getStates();
		
		ArrayList<String> visited = new ArrayList<String>();
		
		Stack<String> string = new Stack<String>();
		
		List<String> alphabet = Arrays.asList(d1.getAlphabet());
		
		
		
		
		search(start,destinations,adjacent,states, visited, string, alphabet);
		
		if(!pathFound) {
			if(print)System.out.println("language empty");
		}
		
	}
	
	public static boolean dfsNoPrint(DFA d1) {
		print = false;
		dfs(d1);
		
		if(pathFound) {
			return true;
		}else {
			return false;
		}
		
	}
	
	public static void search(String current, ArrayList<String> destinations, Map<String, Map<String, String>> adjacent, ArrayList<String> states, ArrayList<String> visited, Stack<String> string, List<String> alphabet) {
		if(pathFound)return;
		
		//Standard DFS
		//Add current item to visited list, if it is a final state return, else check adjacent paths and recurse.
		
		
		if(destinations.contains(current)) {
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
		

		
		visited.add(current.replaceAll("\\s",""));
	
			for(String alpha : alphabet) {
		
				if(!visited.contains(adjacent.get(current.replaceAll("\\s","")).get(alpha))) {
	
					String destination = adjacent.get(current.replaceAll("\\s","")).get(alpha);
					//push the new symbol to the stack, we use the index instead of hardcoding in case the input alphabet is b,a instead of a,b
					//recursion to continue searching
					string.push(alpha); 
					search(destination,destinations,adjacent,states, visited, string, alphabet);
				}
			}
		
		


			
		
		
		string.clear();
	}
	
}
