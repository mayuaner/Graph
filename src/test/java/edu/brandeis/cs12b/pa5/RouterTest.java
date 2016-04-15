package edu.brandeis.cs12b.pa5;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class RouterTest {

	@Test
	public void route1Test() {
		Graph g = new Graph(GraphTest.graph1);
		Router r = new Router(g);

		Iterator<String> route = r.getRoute("a", "b").iterator();


		assertEquals("The first item in the route must be the start", "a", route.next());
		assertEquals("The last item in the route must be the end", "b", route.next());

		assertFalse("The route should only contain two points!", route.hasNext());
	}

	@Test
	public void route2Test() {
		Graph g = new Graph(GraphTest.graph2);
		Router r = new Router(g);
		Set<Edge> validEdges = new HashSet<Edge>();

		validEdges.add(new Edge("a", "b"));
		validEdges.add(new Edge("b", "c"));
		validEdges.add(new Edge("c", "a"));
		validEdges.add(new Edge("c", "d"));

		Iterator<String> route = r.getRoute("a", "d").iterator();

		assertTrue("Route had no edges", route.hasNext());
		String prev = route.next();
		assertEquals("Route must start with the proper vertex!", "a", prev);

		while (route.hasNext()) {
			String curr = route.next();

			Edge e = new Edge(prev, curr);
			assertTrue("Route contained " + e + ", but this is not a valid edge, or has already been used.", validEdges.contains(e));
			validEdges.remove(e);

			prev = curr;
		}

		assertEquals("Route must end with the proper vertex!", "d", prev);

	}

	@Test
	public void route3Test() {
		Graph g = new Graph(GraphTest.graph3);
		Router r = new Router(g);
		Set<Edge> validEdges = new HashSet<Edge>();


		validEdges.add(new Edge("Pollack Fine Arts", "The Rose Art Museum"));
		validEdges.add(new Edge("Ziv Quad", "Ridgewood Quad"));
		validEdges.add(new Edge("Spingold Theater Center", "Ziv Quad"));
		validEdges.add(new Edge("Shapiro Admissions Center", "Ridgewood Quad"));
		validEdges.add(new Edge("Village Residence Hall", "Ziv Quad"));
		validEdges.add(new Edge("Village Residence Hall", "Ridgewood Quad"));
		validEdges.add(new Edge("Faculty Center", "Pollack Fine Arts"));
		validEdges.add(new Edge("Faculty Center", "The Rose Art Museum"));
		validEdges.add(new Edge("Sherman Hall", "Shapiro Campus Center"));
		validEdges.add(new Edge("Irving Presidential Enclave", "Main Entrance"));
		validEdges.add(new Edge("Usdan Student Center", "Schwartz Hall"));
		validEdges.add(new Edge("Lemberg Center", "Usen Castle"));
		validEdges.add(new Edge("IBS", "Pollack Fine Arts"));
		validEdges.add(new Edge("IBS", "Spingold Theater Center"));
		validEdges.add(new Edge("Spingold Theater Center", "Pollack Fine Arts"));
		validEdges.add(new Edge("Spingold Theater Center", "Shapiro Admissions Center"));
		validEdges.add(new Edge("Faculty Center", "Shapiro Campus Center"));
		validEdges.add(new Edge("Slosberg Music Center", "Shapiro Admissions Center"));
		validEdges.add(new Edge("Ridgewood Quad", "Slosberg Music Center"));
		validEdges.add(new Edge("Village Residence Hall", "Slosberg Music Center"));
		validEdges.add(new Edge("Shapiro Admissions Center", "Shapiro Campus Center"));
		validEdges.add(new Edge("Irving Presidential Enclave", "Shapiro Campus Center"));
		validEdges.add(new Edge("Main Entrance", "Slosberg Music Center"));
		validEdges.add(new Edge("Sherman Hall", "Faculty Center"));
		validEdges.add(new Edge("Sherman Hall", "Rosenthal Quad"));
		validEdges.add(new Edge("Shapiro Campus Center", "Rosenthal Quad"));
		validEdges.add(new Edge("Irving Presidential Enclave", "Rosenthal Quad"));
		validEdges.add(new Edge("Rosenthal Quad", "Volen"));
		validEdges.add(new Edge("Rosenthal Quad", "Shapiro Science Center"));
		validEdges.add(new Edge("Volen", "Shapiro Science Center"));
		validEdges.add(new Edge("Irving Presidential Enclave", "Shapiro Science Center"));
		validEdges.add(new Edge("Volen", "Goldfarb Library"));
		validEdges.add(new Edge("Volen", "Schwartz Hall"));
		validEdges.add(new Edge("Goldfarb Library", "Schwartz Hall"));
		validEdges.add(new Edge("Goldfarb Library", "Usdan Student Center"));
		validEdges.add(new Edge("Usdan Student Center", "Rabb"));
		validEdges.add(new Edge("Rabb", "North Quad"));
		validEdges.add(new Edge("Usdan Student Center", "North Quad"));
		validEdges.add(new Edge("Usdan Student Center", "Lemberg Center"));
		validEdges.add(new Edge("Schwartz Hall", "Lemberg Center"));
		validEdges.add(new Edge("Usdan Student Center", "Usen Castle"));
		validEdges.add(new Edge("North Quad", "Usen Castle"));
		validEdges.add(new Edge("North Quad", "East Residence Hall"));
		validEdges.add(new Edge("Usen Castle", "East Residence Hall"));
		validEdges.add(new Edge("East Residence Hall", "Golding Health Center"));
		validEdges.add(new Edge("Foster Bio-Medical Research Center", "Main Entrance"));
		validEdges.add(new Edge("Golding Health Center", "Foster Bio-Medical Research Center"));
		validEdges.add(new Edge("Golding Health Center", "Usen Castle"));
		validEdges.add(new Edge("Foster Bio-Medical Research Center", "Shapiro Science Center"));
		validEdges.add(new Edge("Foster Bio-Medical Research Center", "Lemberg Center"));
		validEdges.add(new Edge("Foster Bio-Medical Research Center", "Usen Castle"));
		validEdges.add(new Edge("Shapiro Admissions Center", "Faculty Center"));
		validEdges.add(new Edge("Irving Presidential Enclave", "Shapiro Admissions Center"));
		validEdges.add(new Edge("Goldfarb Library", "Rabb"));
		validEdges.add(new Edge("IBS", "Ziv Quad"));

		Iterator<String> route = r.getRoute("Volen", "IBS").iterator();

		assertTrue("Route had no edges", route.hasNext());
		String prev = route.next();
		assertEquals("Route must start with the proper vertex!", "Volen", prev);

		while (route.hasNext()) {
			String curr = route.next();
			
			Edge e = new Edge(prev, curr);
			assertTrue("Route contained " + e + ", but this is not a valid edge, or has already been used.", validEdges.contains(e));
			validEdges.remove(e);

			prev = curr;
		}

		assertEquals("Route must end with the proper vertex!", "IBS", prev);
	}


	private class Edge {
		private String v1;
		private String v2;
		public Edge(String a, String b) {
			this.v1 = a;
			this.v2 = b;
		}

		public boolean connects(String a, String b) {
			return (a.equals(v1) && b.equals(v2)) ||
					(a.equals(v2) && b.equals(v1));
		}

		@Override
		public int hashCode() {
			String[] ordered = new String[] { v1, v2 };
			Arrays.sort(ordered);
			return Arrays.toString(ordered).hashCode();
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof Edge))
				return false;

			Edge other = (Edge) o;

			return connects(other.v1, other.v2);
		}

		@Override
		public String toString() {
			return "edge " + v1 + " -- " + v2;
		}
	}
	
	@Test
	public void shortestRoute1Test() {
		Graph g = new Graph(GraphTest.graph1);
		Router r = new Router(g);

		Iterable<String> route = r.getShortestRoute("a", "b");
		int weight = g.getPathLength(route);
		

		
		assertEquals("Incorrect path length", 20, weight);
	
		
	}
	
	@Test
	public void shortestRoute2Test() {
		Graph g = new Graph(GraphTest.graph2);
		Router r = new Router(g);

		Iterable<String> route = r.getShortestRoute("a", "d");
		int weight = g.getPathLength(route);
		
		
		assertEquals("Incorrect path length", 25, weight);
	
		
	}
	
	@Test
	public void shortestRoute3Test() {
		Graph g = new Graph(GraphTest.graph3);
		Router r = new Router(g);

		Iterable<String> route = r.getShortestRoute("Volen", "IBS");
		int weight = g.getPathLength(route);
		
		
		assertEquals("Incorrect path length", 174, weight);
	
		
	}



}
