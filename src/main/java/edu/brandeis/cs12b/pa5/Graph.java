/**
 * Yuanyuan Ma
 * yyma@brandeis.edu
 * COSI 12B PA5
 */

package edu.brandeis.cs12b.pa5;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

/**
 * getEdgeWeight(String a, String b): returns the weight of the edge between vertices a and b, if one exists. Otherwise, returns -1.
 *
 * getConnectedEdges(String s): returns an iterable over the vertices connected to the passed-in vertex.
 *
 * getPathLength(Iterable<String> i): returns the length of the path in the graph of going through the vertices specified in the iterable.
 *
 * getVertices(): returns an iterable over all the vertices in the graph.
 *
 */
public class Graph {	
	
	//nodes contains all the nodes and map is an adjacency list 
	private Set<String> nodes;
	private Map<String, Map<String, Integer>> map;

	/**
	 * Construct a new graph object using the fdp-formatted graph
	 * provided as a string.
	 * 
	 * The format looks like this:
	 * 
	 *  graph g {
	 *  "a" -- "b" [ label = "10" ];
	 *  "b" -- "c" [ label = "20" ];
	 *  }
	 *  
	 *  To represent that vertex A is connected to vertex B with
	 *  a weight of 10, and B is connected to C with weight 20.
	 *
	 * @param fdp the graph to construct
	 */
	public Graph(String fdp) {
		
		//regular expression patterns 
		String pattern = "\"(.*?)\" *?-- *?\"(.*?)\" *?\\[.*?\"(.*?)\" *?\\];";
		String from, to;
		int weight;
		nodes = new HashSet<String>();
		map = new HashMap<String, Map<String, Integer>>();
		Pattern r = Pattern.compile(pattern);
		
		//split the string into lines and parse each line
		//String[] lines = fdp.split(System.getProperty("line.separator"));
		Scanner sc = new Scanner(fdp);
		while (sc.hasNext()){
			String line = sc.nextLine();
			Matcher m = r.matcher(line);
			if (m.find()) {
				from = m.group(1);
				to = m.group(2);
				weight = Integer.parseInt(m.group(3));
				//undirected graph
				addEdge(from, to, weight);
				addEdge(to, from, weight);
			}
		}
		sc.close();
		/*
		for (String line: lines) {
			Matcher m = r.matcher(line);
			if (m.find()) {
				from = m.group(1);
				to = m.group(2);
				weight = Integer.parseInt(m.group(3));
				//undirected graph
				addEdge(from, to, weight);
				addEdge(to, from, weight);
			}
		}*/
	}
	
	public boolean addEdge(String a, String b, int weight) {
		Map<String, Integer> temp;
		nodes.add(a);
		nodes.add(b);
		if (map.containsKey(a)) {
			temp = map.get(a);
			temp.put(b, weight);
			//map.put(a, temp);
			map.replace(a, temp);
		} else {
			temp = new HashMap<String, Integer>();
			temp.put(b, weight);
			map.put(a, temp);
		}
		return true;
	}

	/**
	 * Get the weight of the edge between vertex a and vertex b.
	 * 
	 * If there is no edge connecting the two vertices, return -1.
	 * @param a one vertex
	 * @param b another
	 * @return the edge weight
	 */
	public int getEdgeWeight(String a, String b) {
		if (map.containsKey(a)) {
			Map<String, Integer> temp = map.get(a);
			if (temp.containsKey(b)) 
				return temp.get(b);
		}
		return -1;
	}

	/**
	 * Return an iterable object that will iterate over
	 * the vertices directly connected to vertex A
	 * @param a the vertex
	 * @return an iterator of strings that A is connected to
	 */
	public Iterable<String> getConnectedEdges(String a) {
		if (map.containsKey(a)) 
			return map.get(a).keySet();
		return null;
	}

	/**
	 * Gets the length of the path (sum of the edge weights)
	 * represented by the passed iterable. If the path is invalid,
	 * (jumps between non-connected vertices), return -1
	 * @param path an iterable of vertices
	 * @return the path length
	 */
	public int getPathLength(Iterable<String> path) {
		//get the iterator inside that iterable object path 
		Iterator<String> iterator = path.iterator();
		if (iterator.hasNext()) {
			int pathSum = 0;
			String from = iterator.next(), to;
			while (iterator.hasNext()) {
				to = iterator.next();
				int weight = getEdgeWeight(from, to);
				if (weight == -1) return -1;
				else pathSum += weight;
				from = to;
			}
			return pathSum;
		}
		return -1;
	}
	/**
	 * returns the size of the all the nodes 
	 * @return
	 */
	public int getVerticeNum() {
		return nodes.size();
	}
	
	/**
	 * Returns an iterable over all the vertices in the graph
	 * @return the vertices
	 */
	public Iterable<String> getVertices() {
		return nodes;
	}

}
