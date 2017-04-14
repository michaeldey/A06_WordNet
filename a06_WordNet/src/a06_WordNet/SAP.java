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

	   // constructor takes a digraph (not necessarily a DAG)
	   public SAP(Digraph G)
	   {
		   this.G = G;
	   }

	   // is the digraph a directed acyclic graph?
	   public boolean isDAG()
	   {
		   DirectedCycle DC = new DirectedCycle(G);
		   return !DC.hasCycle();
	   }

	   // is the digraph a rooted DAG?
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

	   // length of shortest ancestral path between v and w; -1 if no such path
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

	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
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

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
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

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   if (isRootedDAG()){		//both points end at same root
//			   BreadthFirstDirectedPaths bfpV = new BreadthFirstDirectedPaths(G, v); 
//			   BreadthFirstDirectedPaths bfpW = new BreadthFirstDirectedPaths(G, w); 
			   BST<Integer, Integer> bst = new BST<>();//BST Brute Force to find ancestor 
			   for(int i: v){
				   for(int j: w){
					   bst.put(length(i, j), ancestor(i, j));
				   }
			   }
			   return bst.get(bst.min());
			   
//			   int root = findRoot(0);
//			   Stack<Integer> sV =  (Stack<Integer>) bfpV.pathTo(root);
//			   BST<Integer, Integer> bst = new BST<Integer, Integer>();
//			   System.out.println("BST: ");
//			   for(int i: bfpW.pathTo(root)){
//				   System.out.println(i);
//				   bst.put(i, i);
//			   }
//			   System.out.println("\nStack: ");
//			   for(int i: sV){
//				   System.out.println(i);
//				   if(bst.contains(i)) return i;
//			   }
		   }
		   return -1;
	   }
	   
	   // The vertex number of the root node; -1 if no root found.
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
