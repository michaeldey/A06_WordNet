package a06_WordNet;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
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
		   if (BFS.hasPathTo(w)) return BFS.distTo(w);//get distance from v to w
		   else return -1;
	   }

	   // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
	   public int ancestor(int v, int w)
	   {
		   Queue qV = new Queue();
		   BST b
		   
	   }

	   // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
	   public int length(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   return 0;
	   }

	   // a common ancestor that participates in shortest ancestral path; -1 if no such path
	   public int ancestor(Iterable<Integer> v, Iterable<Integer> w)
	   {
		   return 0;
	   }

	   // do unit testing of this class
	   public static void main(String[] args)
	   {
		   In in = new In("Digraph1_cycle.txt");
		   Digraph testDigraph = new Digraph(in);
		   
		   SAP mySAP = new SAP(testDigraph);
		   System.out.println(mySAP.isDAG());
		   
	   }
	}
