package a06_WordNet;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Stack;

public class SAPTest {
	
	//setting up files to test the SAP class
	In in = new In("Digraph1.txt");				
	Digraph DigraphNoCycle = new Digraph(in);	//this Digraph has no cycle
	
	In in2 = new In("Digraph1_cycle.txt");
	Digraph DigraphWithCycle = new Digraph(in2); //this Digraph has a cycle

	@Test
	public void testIsDAG() {		
		SAP noCycleSap = new SAP(DigraphNoCycle);
		SAP cycleSap = new SAP(DigraphWithCycle);
		
		assertTrue(noCycleSap.isDAG());
		assertFalse(cycleSap.isDAG());

	}

	@Test
	public void testIsRootedDAG() {
		In in3 = new In("Digraph1NoRoot.txt");		//this Digraph has no root
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
		//create two Stacks to send to SAP
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		stack1.push(8);
		stack1.push(7);
		stack2.push(2);
		stack2.push(10);
		
		SAP mySAP = new SAP(DigraphNoCycle);			//we use this file to test against the Stacks
		assertEquals(mySAP.length(stack1, stack2),3);
		assertNotEquals(mySAP.length(stack1, stack2),4);		   
	}

	@Test
	public void testAncestorIterableOfIntegerIterableOfInteger() {
		//create two stacks to send to SAP
		Stack<Integer> stack1 = new Stack<Integer>();
		Stack<Integer> stack2 = new Stack<Integer>();
		stack1.push(8);
		stack1.push(7);
		stack2.push(2);
		stack2.push(10);
		
		SAP mySAP = new SAP(DigraphNoCycle);			//we use this file to test against the Stacks
		assertEquals(mySAP.ancestor(stack1, stack2),5);
		assertNotEquals(mySAP.ancestor(stack1, stack2),1);
	}

}
