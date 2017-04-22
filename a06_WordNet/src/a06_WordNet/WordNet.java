package a06_WordNet;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.ST;


public class WordNet {
	
		private ST<Integer,String> symbolTree = new ST<>();
		private ST<String, Integer> symbolTreeReversed = new ST<>();
		private Digraph G;
		private int vertexCount=0;
		private SAP sap;

	   // constructor takes the name of the two input files
	   public WordNet(String synsets, String hypernyms)
	   {
		   try{
			   In synIn = new In(synsets);
			   In hypIn = new In(hypernyms);			  
			   
			   createSynsetST(synIn); //create symbol table from synset file
			   //set up our Digraph
			   this.G = new Digraph(vertexCount);
			   createHypernymDigraph(hypIn);//create Digraph from hypernyms file
			   this.sap = new SAP(this.G);
		   }
		   catch(java.lang.IllegalArgumentException e)
		   {
			   System.out.println("Cannot read one or both files: "+e.getMessage());
		   }
	   }
	   
	   private void createSynsetST(In synIn)
	   {
		 //create a symbol tree based on Synsets file, splitting the first value as the key, subsequent values in the line as string array
		   while (synIn.hasNextLine())		//change this to a while statement after testing
		   {
			   vertexCount++;
			   String[] values = synIn.readLine().split(",");
			 
			 //load first value of line as an integer, and use that as the index
			 try
			 {
				 //send to symbol tree the first value in the line as an integer, the rest of the values are sent as a string array
				 symbolTree.put(Integer.parseInt(values[0]), values[1]);
				 symbolTreeReversed.put(values[1], Integer.parseInt(values[0]));
			 }catch(NumberFormatException e)
			 {
				 System.out.println("File is in incorrect format. First value in line must be an integer. " + e.getMessage());
			 }	 			 
		   } 
	   }
	   
	   private void createHypernymDigraph(In hypIn)
	   {
		   while (hypIn.hasNextLine())
		   {
			   String[] values = hypIn.readLine().split(",");
			   
			   try
			   {
				   int V = Integer.parseInt(values[0]);
				   for (int i = 1; i < values.length; i++)
				   {
					   int W = Integer.parseInt(values[i]);
					   this.G.addEdge(V, W);
				   }
			   }catch(NumberFormatException e)
			   {
				   System.out.println("File is in incorrect format. All values must be integers." + e.getMessage());
			   }
		   }
	   }

	   // returns all WordNet nouns
	   public Iterable<String> nouns()
	   {
		   return symbolTreeReversed.keys();
	   }

	   // is the word a WordNet noun?
	   public boolean isNoun(String word)
	   {
		   return symbolTreeReversed.contains(word);
	   }

	   // distance between nounA and nounB (defined below)
	   public int distance(String nounA, String nounB)
	   {
		   if(!this.symbolTreeReversed.contains(nounA) || !this.symbolTreeReversed.contains(nounB)){
			   throw new IllegalArgumentException("Nouns aren't contained in the synsets list.");
		   }
		   return sap.length(symbolTreeReversed.get(nounA), symbolTreeReversed.get(nounB));
	   }

	   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	   // in a shortest ancestral path (defined below)
	   public String sap(String nounA, String nounB)
	   {
		   if(!this.symbolTreeReversed.contains(nounA) || !this.symbolTreeReversed.contains(nounB)){
			   throw new IllegalArgumentException("Nouns aren't contained in the synsets list.");
		   }
		   return symbolTree.get(sap.ancestor(symbolTreeReversed.get(nounA), symbolTreeReversed.get(nounB)));
	   }

	   // do unit testing of this class
	   public static void main(String[] args)
	   {
		   String synsetFile = "synsets.txt"; 
		   String hypernymsFile = "hypernyms.txt";//38003 is the root
		   
		   WordNet wordNet = new WordNet(synsetFile, hypernymsFile);
		   System.out.println(wordNet.distance("zebra", "table"));
		   
		   
		  
	   }
	}
