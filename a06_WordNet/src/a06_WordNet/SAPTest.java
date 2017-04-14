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
		In in3 = new In("Digraph1NoRoot.txt");
		Digraph DigraphNoRoot = new Digraph(in3);
		
		SAP noCycleSap = new SAP(DigraphNoCycle);
		SAP cycleSap = new SAP(DigraphWithCycle);
		SAP cycleNoRoot = new SAP(DigraphNoRoot);
		
		assertTrue(noCycleSap.isRootedDAG());
		assertFalse(cycleSap.isRootedDAG());
		assertFalse(cycleNoRoot.isRootedDAG());
	}

	@Test
	public void testLengthIntInt() {
		SAP sapL = new SAP(DigraphNoCycle);
		assertEquals(sapL.length(7, 3), 1); //check length of 1
		assertNotEquals(sapL.length(7, 3), 2); //check if we have a misfire on length of 1
		assertEquals(sapL.length(6, 0),3);
		assertEquals(sapL.length(8, 10), 3); //-1 if no path between the two
		assertEquals(sapL.length(8, 6), 4);
	}

	@Test
	public void testAncestorIntInt() {
		SAP sap = new SAP(DigraphNoCycle);
		assertEquals(sap.ancestor(6, 10), 1);
		assertEquals(sap.ancestor(11, 8), 5);
		assertNotEquals(sap.ancestor(11, 7), 5);
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
