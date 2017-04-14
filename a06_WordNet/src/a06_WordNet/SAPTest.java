package a06_WordNet;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;

public class SAPTest {
	
	In in = new In("Digraph1.txt");
	Digraph DigraphNoCycle = new Digraph(in);
	
	In in2 = new In("Digraph1_cycle.txt");
	Digraph DigraphWithCycle = new Digraph(in2);

	@Test
	public void testSAP() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsDAG() {
		
		
		SAP noCycleSap = new SAP(DigraphNoCycle);
		SAP cycleSap = new SAP(DigraphWithCycle);
		
		assertTrue(noCycleSap.isDAG());
		assertFalse(cycleSap.isDAG());

	}

	@Test
	public void testIsRootedDAG() {
		fail("Not yet implemented");
	}

	@Test
	public void testLengthIntInt() {
		SAP sapL = new SAP(DigraphNoCycle);
		assertEquals(sapL.length(7, 3), 1); //check length of 1
		assertNotEquals(sapL.length(7, 3), 2); //check if we have a misfire on length of 1
		assertEquals(sapL.length(6, 0),3);
		assertEquals(sapL.length(8, 10), -1); //-1 if no path between the two
	}

	@Test
	public void testAncestorIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testLengthIterableOfIntegerIterableOfInteger() {
		fail("Not yet implemented");
	}

	@Test
	public void testAncestorIterableOfIntegerIterableOfInteger() {
		fail("Not yet implemented");
	}

}
