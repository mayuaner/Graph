package edu.brandeis.cs12b.pa5;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.stream.StreamSupport;

import org.junit.Test;

import edu.brandeis.cs12b.pa5.SpanningTree.Edge;

public class SpanningTreeTest {

	@Test
	public void mst1Test() {
		Graph g = new Graph(GraphTest.graph1);
		SpanningTree st = new SpanningTree();


		Iterator<Edge> tree = st.getMST(g).iterator();

		assertTrue("MST should contain at least one edge", tree.hasNext());

		Edge e = tree.next();

		assertFalse("MST should contain only one edge", tree.hasNext());

		assertEquals("Weight of the edge should be correct", 20, e.weight);

		assertTrue("Edge should connect A and B", 
				(e.vertex1.equals("a") && e.vertex2.equals("b")) ||
				(e.vertex1.equals("b") && e.vertex2.equals("a")));

	}

	@Test
	public void mst2Test() {
		Graph g = new Graph(GraphTest.graph2);
		SpanningTree st = new SpanningTree();

		int totalWeight = StreamSupport.stream(st.getMST(g).spliterator(), false)
				.mapToInt(e -> e.weight)
				.sum();
		
		assertEquals("Incorrect total tree weight", 65, totalWeight);

	}
	
	@Test
	public void mst3Test() {
		Graph g = new Graph(GraphTest.graph3);
		SpanningTree st = new SpanningTree();

		int totalWeight = StreamSupport.stream(st.getMST(g).spliterator(), false)
				.mapToInt(e -> e.weight)
				.sum();
		
		assertEquals("Incorrect total tree weight", 1030, totalWeight);

	}
}
