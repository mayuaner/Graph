/**
 * Yuanyuan Ma
 * yyma@brandeis.edu
 * COSI 12B PA5
 */
package edu.brandeis.cs12b.pa5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * The router class takes in a Graph object and then
 * allows the user to find routes between places represented by
 * the graph.
 * 
 * Router::getRoute(String start, String end): 
 * returns an Iterable over vertexs representing a valid route between 
 * the given start vertex and the given ending vertex. 
 * For example, if getRoute("1", "5") was called with the graph above, 
 * a valid Iterable would return ["1", "4", "5"] or ["1", "2", "5"], 
 * 
 * Router::getShortestRoute(String start, String end): 
 * returns an Iterable over the vertexes representing the shortest valid route 
 * (the route with the lowest sum of edge weights) between the start and the end. 
 * For example, getShortestRoute("1", "5") should return an Iterable over ["1", "2", "5"]. 
 */
public class Router {

	private Graph graph;
	private Comparator<Pair<String>> comparator;
	private Map<String, String> predecessor;
	private Set<String> visited;
	/**
	 * Constructs a new Router object with the given graph
	 * @param graph the graph to route on
	 */
	public Router(Graph graph) {
		this.graph = graph;
		this.comparator = new Comparator<Pair<String>> () {
			public int compare(Pair<String> a, Pair<String> b)
			{
				if (a.second < b.second) return -1;
				else if(a.second > b.second) return 1;
				else return 0;
			}
		};
	}

	/**
	 * Returns an iterable over the vertices along a valid path
	 * between vertex "start" and vertex "end" in the graph.
	 * 
	 * If no route exists, return null.
	 * 
	 * 
	 * @param start the start position
	 * @param end the desired end position
	 * @return the vertices along a path
	 */
	public Iterable<String> getRoute(String start, String end) {
		visited = new HashSet<String>();
		predecessor = new HashMap<String, String>();
		if (dfs(start, end)) return backtracking(start, end);
		else return null;
	}
	
	/**
	 * depth-first search 
	 * @param curr
	 * @param end
	 * @return
	 */
	private boolean dfs(String curr, String end) {
		if (curr.equals(end)) return true;
		visited.add(curr);
		Iterable<String> connectedEdges = graph.getConnectedEdges(curr);
		for (String to: connectedEdges) {
			if (visited.contains(to)) continue;
			predecessor.put(to, curr);
			if (dfs(to, end)) return true;
		}
		return false;
	}
	
	private Iterable<String> backtracking(String start, String end) {
		LinkedList<String> paths = new LinkedList<String>();
		
		String ptr = end;
		while (!ptr.equals(start)) {
			paths.addFirst(ptr);
			ptr = predecessor.get(ptr);
		}
		paths.addFirst(ptr);
		return paths;
	}

	/**
	 * Same as the above method, but the route returned
	 * must be a valid path and there must not be any path shorter
	 * than it (it must be the shortest path, potentially not a unique one).
	 * 
	 * You should use Dijkstra's algorithm: https://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
	 * 
	 * @param start the starting location
	 * @param end the end location
	 * @return the shortest path between start and end
	 */
	
	public Iterable<String> getShortestRoute(String start, String end) {
		PriorityQueue<Pair<String>> pq = new PriorityQueue<Pair<String>>(this.comparator);
		Map<String, Integer> distance = new HashMap<String, Integer>();
		visited = new HashSet<String>();
		predecessor = new HashMap<String, String>();
		Iterable<String> verticesCollection = graph.getVertices();

		for (String vertex: verticesCollection) {
			distance.put(vertex, Integer.MAX_VALUE);
		}
		distance.replace(start, 0); //starting vertex has 0
		pq.add(new Pair<String>(start, 0));
		boolean found = false;
		while (!pq.isEmpty()) {
			Pair<String> temp = pq.poll();//the smallest weight in the rest of the vertices 
			if (temp.first.equals(end)) {found = true; break;}
			if (visited.contains(temp.first)) continue;
			visited.add(temp.first.toString());
			Iterable<String> connectedEdges = graph.getConnectedEdges(temp.first.toString());//adj list
			for (String to: connectedEdges) {
				int nextDist = distance.get(to);
				int currDist = graph.getEdgeWeight(temp.first.toString(), to);
				//update the current path value. 
				if (temp.second + currDist < nextDist) {
					distance.replace(to, temp.second + currDist);
					pq.add(new Pair<String>(to, temp.second + currDist));
					predecessor.put(to, temp.first.toString());
				}
			}
		}
		if (found) return backtracking(start, end);
		else return null;
	}
}
