/**
 * @author Jared Edwards, Michael Dey
 * CSIS 2420-004
 * Assignment: WordNet
 *
 */
package a06_WordNet;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.DepthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.BST;

public class SAP {
	
		private Digraph G;

	   /**
	 * @param G Digraph object (not necessarily a DAG)
	 */
	public SAP(Digraph G)
	   {
		   this.G = G;
		   if (!(G instanceof Digraph)){
			   throw new IllegalArgumentException("SAP not given a proper Digraph object");
		   }
	   }

	   /**
	 * @return True if digraph is a directed acyclic graph, False if not
	 * 
	 * Uses a DirectedCycle object to test if G has a cycle
	 */
	public boolean isDAG()
	   {
		   DirectedCycle DC = new DirectedCycle(G);
		   return !DC.hasCycle();
	   }

	   /**
	 * @return True if digraph is a rooted DAG
	 * 
	 * Searches through G and counts in rootcount all vertexes with an outdegree of 0
	 * if the rootcount !=1, we assume there is no one single root
	 */
	public boolean isRootedDAG()
	   {
		   if (isDAG()){
			   int rootCount = 0;
			   for(int v = 0; v < G.V(); v++){
				   if (G.outdegree(v) == 0) rootCount++;
			   }
			   if (rootCount != 1) return false;
			   else return true;
		   }
		   return false;
	   }

	   /**
	 * @param v first vertex to search for
	 * @param w second vertex to search for
	 * @return int value of length of shortest ancestral path between v and w; -1 if no such path
	 * 
	 * Uses Breadth First Search to find shortest ancestral path between v and w
	 */
	public int length(int v, int w)
	   {
		   BreadthFirstDirectedPaths BFS = new BreadthFirstDirectedPaths(G, v);//create BFS from v		   
		   if (BFS.hasPathTo(w))
		   {
			   return BFS.distTo(w);//get distance from v to w, only works if direct path from v to w
		   }
		   else {									// Else statement checks if v and w have a common ancestor
			   int comAnc = ancestor(v, w);
			   if(comAnc != -1)
			   {					// If there is a common ancestor, return length of v to comAnc + w to comAnc
				   BreadthFirstDirectedPaths BFSW = new BreadthFirstDirectedPaths(G, w);
				   return BFS.distTo(comAnc) + BFSW.distTo(comAnc);
			   }
		   }
		   return -1;
	   }

	   /**
	 * @param v vertex to search for
	 * @param w vertex to search for
	 * @return common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	 * 
	 * Creates a Stack<Integer> of all points from v to root
	 * Creates a BST<Integer, Integer> of all points from w to root
	 * Searches the BST for items from the stack, as soon as an item is found, we have found the common ancestor
	 */
	public int ancestor(int v, int w)
	   {
		   if (isRootedDAG()){		//both points end at same root
			   DepthFirstDirectedPaths dfpV = new DepthFirstDirectedPaths(G, v); 
			   DepthFirstDirectedPaths dfpW = new DepthFirstDirectedPaths(G, w); 
			   int root = findRoot(v);
			   Stack<Integer> sV =  (Stack<Integer>) dfpV.pathTo(root);
			   BST<Integer, Integer> bst = new BST<Integer, Integer>();
			   for(int i: dfpW.pathTo(root)){
				   bst.put(i, i);
			   }
			   for(int i: sV){
				   if(bst.contains(i)) return i;
			   }
		   }
		   
		   return -1;
		   
	   }

	   /**
	 * @param v vertex to search for
	 * @param w vertex to search for
	 * @return length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	 */
	public int length(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   if(isRootedDAG()){ //Brute Force to find length
			   BST<Integer, Integer> bst = new BST<>();
			   for(int i: v){
				   for(int j: w){
					   bst.put(length(i, j), ancestor(i, j));
				   }
			   }
			   return bst.min();
		   }
		   return -1;
	   }

	   /**
	 * @param v vertex to search for
	 * @param w vertex to search for
	 * @return a common ancestor that participates in shortest ancestral path; -1 if no such path
	 * 
	 * Searches all items in Iterable v for all shortest ancestral paths in Iterable w, 
	 * and returns the common ancestor with shortest ancestral path of both Iterators
	 */
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   int ancestor = -1;
		   if (isRootedDAG()){		//both points end at same root
			   int shortest = Integer.MAX_VALUE;
			   Stack<Integer> ancestors = new Stack<>();
			   BreadthFirstDirectedPaths bfdsV = new BreadthFirstDirectedPaths(G, v);
			   BreadthFirstDirectedPaths bfdsW = new BreadthFirstDirectedPaths(G, w);
			   
			   for (int i = 0; i< G.V(); i++){
				   if (bfdsV.hasPathTo(i) && bfdsW.hasPathTo(i)){
					   ancestors.push(i);
				   }
			   }
			   
			   for (Integer i: ancestors){
				   int distance = bfdsV.distTo(i) + bfdsW.distTo(i);
				   if (distance < shortest){
					   shortest = distance;
					   ancestor = i;
				   }
			   }
		   }
		   return ancestor;
	   }
	   

	   /**
	 * @param v given vertex
	 * @return The vertex number of the root node; -1 if no root found.
	 */
	private int findRoot(int v){
		   int root = -1;
		   for(int i = 0; i < G.V(); i++){
			   if (G.outdegree(i) == 0) root = i;
		   }
		   
		   return root;
		   
	   }

	   // do unit testing of this class
	   public static void main(String[] args)
	   {
		   In in = new In("Digraph1.txt");
		   Digraph testDigraph = new Digraph(in);
		   
		   Stack<Integer> stack1 = new Stack<Integer>();
		   Stack<Integer> stack2 = new Stack<Integer>();
		   stack1.push(8);
		   stack1.push(7);
		   stack2.push(2);
		   stack2.push(10);
		   
		   
		   SAP mySAP = new SAP(testDigraph);
		   //System.out.println(mySAP.isDAG());
		   //System.out.println(mySAP.findRoot(8)); 
		   System.out.println(mySAP.ancestor(stack1, stack2));
		   System.out.println(mySAP.length(stack1, stack2));
	   }
	}
