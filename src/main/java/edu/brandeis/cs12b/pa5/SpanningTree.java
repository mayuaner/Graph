/**
 * Yuanyuan Ma
 * yyma@brandeis.edu
 * COSI 12B PA5
 */
package edu.brandeis.cs12b.pa5;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * getMST(Graph g): returns an Iterable over Edges 
 * making up the minimum spanning tree 
 * of the graph.
 * 
 *
 */
public class SpanningTree {
	
	private Comparator<Pair<Edge>> comparator;
	
	
	public class Edge {
		public String vertex1;
		public String vertex2;
		public int weight;
		
		public Edge(String v1, String v2, int w) {
			vertex1 = v1;
			vertex2 = v2;
			weight = w;
		}
	}
	
	public SpanningTree() {
		this.comparator = new Comparator<Pair<Edge>> () {
			public int compare(Pair<Edge> a, Pair<Edge> b)
			{
				if (a.second < b.second) return -1;
				else if(a.second > b.second) return 1;
				else return 0;
			}
		};
	}

	/**
	 * Calculate the minimum spanning tree of the graph,
	 * which is the set of edges with the smallest weight
	 * sum that connect all the vertices of the graph such that
	 * there is a path from each vertex to every other vertex.
	 * 
	 * @param g the graph
	 * @return an iterable over the edges in the MST
	 */
	public Iterable<Edge> getMST(Graph g) {
		//Prim's 
		PriorityQueue<Pair<Edge>> pq = new PriorityQueue<Pair<Edge>>(this.comparator);
		Iterator<String> allVertice = g.getVertices().iterator();
		Set<String> verticeCollection = new HashSet<String>();// the union-set used to store the vertices 
		Set<Edge> MST = new HashSet<Edge>();//the union-set used to store the edges
		String startVertex = allVertice.next();//the first of the iterable object 
		int verticeNum = g.getVerticeNum();
		verticeCollection.add(startVertex);
		Iterable<String> connectedEdges = g.getConnectedEdges(startVertex);
		for (String to: connectedEdges) {
			Edge edge = new Edge(startVertex, to, g.getEdgeWeight(startVertex, to));
			pq.add(new Pair<Edge>(edge, edge.weight));//add all the edges to the PQ
		}
		while (verticeCollection.size() < verticeNum) {
			Edge edge = pq.poll().first;
			if (verticeCollection.contains(edge.vertex2)) 
				continue;
			MST.add(edge);
			verticeCollection.add(edge.vertex2);
			connectedEdges = g.getConnectedEdges(edge.vertex2);
			for (String to: connectedEdges) {
				Edge tempEdge = new Edge(edge.vertex2, to, g.getEdgeWeight(edge.vertex2, to));
				pq.add(new Pair<Edge>(tempEdge, tempEdge.weight));
			}
		}
		
		return MST;
	}
}
