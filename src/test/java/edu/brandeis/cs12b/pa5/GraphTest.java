package edu.brandeis.cs12b.pa5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GraphTest {
	
	public static final String graph1 = "graph g {\n\"a\" -- \"b\" [ label = \"20\" ];\n}";
	public static final String graph2 = "graph g {\n" + 
			"   \"a\" -- \"b\" [ label = \"40\" ];\n" + 
			"   \"b\" -- \"c\" [ label = \"50\" ];\n" + 
			"   \"c\" -- \"a\" [ label = \"10\" ];\n" + 
			"   \"c\" -- \"d\" [ label = \"15\" ];\n" + 
			"}";
	
	public static final String graph3 = "graph G {\n" + 
			"	\"Pollack Fine Arts\" -- \"The Rose Art Museum\" [ label=\"33\" ];\n" + 
			"	\"Ziv Quad\" -- \"Ridgewood Quad\" [ label=\"28\" ];\n" + 
			"	\"Spingold Theater Center\" -- \"Ziv Quad\" [ label=\"28\" ];\n" + 
			"	\"Shapiro Admissions Center\" -- \"Ridgewood Quad\" [ label=\"30\" ];\n" + 
			"	\"Village Residence Hall\" -- \"Ziv Quad\" [ label=\"30\" ];\n" + 
			"	\"Village Residence Hall\" -- \"Ridgewood Quad\" [ label=\"31\" ];\n" + 
			"	\"Faculty Center\" -- \"Pollack Fine Arts\" [ label=\"34\" ];\n" + 
			"	\"Faculty Center\" -- \"The Rose Art Museum\" [ label=\"35\" ];\n" + 
			"	\"Sherman Hall\" -- \"Shapiro Campus Center\" [ label=\"38\" ];\n" + 
			"	\"Irving Presidential Enclave\" -- \"Main Entrance\" [ label=\"37\" ];\n" + 
			"	\"Usdan Student Center\" -- \"Schwartz Hall\" [ label=\"51\" ];\n" + 
			"	\"Lemberg Center\" -- \"Usen Castle\" [ label=\"51\" ];\n" + 
			"	\"IBS\" -- \"Pollack Fine Arts\" [ label=\"30\" ];\n" + 
			"	\"IBS\" -- \"Spingold Theater Center\" [ label=\"28\" ];\n" + 
			"	\"Spingold Theater Center\" -- \"Pollack Fine Arts\" [ label=\"32\" ];\n" + 
			"	\"Spingold Theater Center\" -- \"Shapiro Admissions Center\" [ label=\"30\" ];\n" + 
			"	\"Faculty Center\" -- \"Shapiro Campus Center\" [ label=\"35\" ];\n" + 
			"	\"Slosberg Music Center\" -- \"Shapiro Admissions Center\" [ label=\"35\" ];\n" + 
			"	\"Ridgewood Quad\" -- \"Slosberg Music Center\" [ label=\"31\" ];\n" + 
			"	\"Village Residence Hall\" -- \"Slosberg Music Center\" [ label=\"32\" ];\n" + 
			"	\"Shapiro Admissions Center\" -- \"Shapiro Campus Center\" [ label=\"35\" ];\n" + 
			"	\"Irving Presidential Enclave\" -- \"Shapiro Campus Center\" [ label=\"40\" ];\n" + 
			"	\"Main Entrance\" -- \"Slosberg Music Center\" [ label=\"38\" ];\n" + 
			"	\"Sherman Hall\" -- \"Faculty Center\" [ label=\"37\" ];\n" + 
			"	\"Sherman Hall\" -- \"Rosenthal Quad\" [ label=\"39\" ];\n" + 
			"	\"Shapiro Campus Center\" -- \"Rosenthal Quad\" [ label=\"39\" ];\n" + 
			"	\"Irving Presidential Enclave\" -- \"Rosenthal Quad\" [ label=\"41\" ];\n" + 
			"	\"Rosenthal Quad\" -- \"Volen\" [ label=\"42\" ];\n" + 
			"	\"Rosenthal Quad\" -- \"Shapiro Science Center\" [ label=\"40\" ];\n" + 
			"	\"Volen\" -- \"Shapiro Science Center\" [ label=\"43\" ];\n" + 
			"	\"Irving Presidential Enclave\" -- \"Shapiro Science Center\" [ label=\"40\" ];\n" + 
			"	\"Volen\" -- \"Goldfarb Library\" [ label=\"47\" ];\n" + 
			"	\"Volen\" -- \"Schwartz Hall\" [ label=\"45\" ];\n" + 
			"	\"Goldfarb Library\" -- \"Schwartz Hall\" [ label=\"48\" ];\n" + 
			"	\"Goldfarb Library\" -- \"Usdan Student Center\" [ label=\"49\" ];\n" + 
			"	\"Usdan Student Center\" -- \"Rabb\" [ label=\"54\" ];\n" + 
			"	\"Rabb\" -- \"North Quad\" [ label=\"53\" ];\n" + 
			"	\"Usdan Student Center\" -- \"North Quad\" [ label=\"54\" ];\n" + 
			"	\"Usdan Student Center\" -- \"Lemberg Center\" [ label=\"51\" ];\n" + 
			"	\"Schwartz Hall\" -- \"Lemberg Center\" [ label=\"48\" ];\n" + 
			"	\"Usdan Student Center\" -- \"Usen Castle\" [ label=\"51\" ];\n" + 
			"	\"North Quad\" -- \"Usen Castle\" [ label=\"54\" ];\n" + 
			"	\"North Quad\" -- \"East Residence Hall\" [ label=\"54\" ];\n" + 
			"	\"Usen Castle\" -- \"East Residence Hall\" [ label=\"53\" ];\n" + 
			"	\"East Residence Hall\" -- \"Golding Health Center\" [ label=\"54\" ];\n" + 
			"	\"Foster Bio-Medical Research Center\" -- \"Main Entrance\" [ label=\"46\" ];\n" + 
			"	\"Golding Health Center\" -- \"Foster Bio-Medical Research Center\" [ label=\"52\" ];\n" + 
			"	\"Golding Health Center\" -- \"Usen Castle\" [ label=\"54\" ];\n" + 
			"	\"Foster Bio-Medical Research Center\" -- \"Shapiro Science Center\" [ label=\"48\" ];\n" + 
			"	\"Foster Bio-Medical Research Center\" -- \"Lemberg Center\" [ label=\"50\" ];\n" + 
			"	\"Foster Bio-Medical Research Center\" -- \"Usen Castle\" [ label=\"50\" ];\n" + 
			"	\"Shapiro Admissions Center\" -- \"Faculty Center\" [ label=\"35\" ];\n" + 
			"	\"Irving Presidential Enclave\" -- \"Shapiro Admissions Center\" [ label=\"37\" ];\n" + 
			"	\"Goldfarb Library\" -- \"Rabb\" [ label=\"51\" ];\n" + 
			"	\"IBS\" -- \"Ziv Quad\" [ label=\"25\" ];\n" + 
			"}\n" + 
			"";
	
	@Test
	public void edgeWeight1Test() {
		Graph g = new Graph(graph1);
		
		assertEquals(20, g.getEdgeWeight("a", "b"));
		assertEquals(20, g.getEdgeWeight("b", "a"));

	}
	
	@Test
	public void edgeWeight2Test() {
		Graph g = new Graph(graph2);
		
		assertEquals(40, g.getEdgeWeight("a", "b"));
		assertEquals(50, g.getEdgeWeight("c", "b"));
		assertEquals(15, g.getEdgeWeight("d", "c"));
		assertEquals(10, g.getEdgeWeight("c", "a"));

	}
	
	@Test
	public void edgeWeight3Test() {
		Graph g = new Graph(graph3);
		
		assertEquals(25, g.getEdgeWeight("IBS", "Ziv Quad"));
		assertEquals(54, g.getEdgeWeight("Usen Castle", "North Quad"));

	}
	
	
	@Test
	public void connectedEdges2Test() {
		Graph g = new Graph(graph2);
		Set<String> expectedEdges = new HashSet<String>();
		
		expectedEdges.add("b");
		expectedEdges.add("c");
		
		for (String s : g.getConnectedEdges("a")) {
			if (!expectedEdges.contains(s)) {
				fail("Vertex a should not be connected to vertex " + s);
			}
			
			expectedEdges.remove(s);
		}
		
		for (String leftOver : expectedEdges) {
			fail("Vertex a should be connected to vertex " + leftOver);
		}
		
	
	}
	
	@Test
	public void connectedEdges3Test() {
		Graph g = new Graph(graph3);
		Set<String> expectedEdges = new HashSet<String>();
		
		expectedEdges.add("Usdan Student Center");
		expectedEdges.add("North Quad");
		expectedEdges.add("East Residence Hall");
		expectedEdges.add("Golding Health Center");
		expectedEdges.add("Foster Bio-Medical Research Center");
		expectedEdges.add("Lemberg Center");
		
		for (String s : g.getConnectedEdges("Usen Castle")) {
			if (!expectedEdges.contains(s)) {
				fail("Vertex Usen Castle should not be connected to vertex " + s);
			}
			
			expectedEdges.remove(s);
		}
		
		for (String leftOver : expectedEdges) {
			fail("Vertex Usen Castle should be connected to vertex " + leftOver);
		}
		
	
	}

	
	@Test
	public void pathLength1Test() {
		Graph g = new Graph(graph1);
		String[] path = new String[] {"a", "b"};
		
		assertEquals(20, g.getPathLength(Arrays.asList(path)));
	}
	
	@Test
	public void pathLength2Test() {
		Graph g = new Graph(graph2);
		String[] path = new String[] {"a", "b", "c", "d"};
		
		assertEquals(105, g.getPathLength(Arrays.asList(path)));
	}
	
	@Test
	public void pathLength3Test() {
		Graph g = new Graph(graph3);
		String[] path = new String[] {"Ziv Quad", "Ridgewood Quad", "Village Residence Hall"};

		assertEquals(59, g.getPathLength(Arrays.asList(path)));

	}

}
