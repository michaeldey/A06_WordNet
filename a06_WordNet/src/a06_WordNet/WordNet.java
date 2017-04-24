/**
 * @author Jared Edwards, Michael Dey
 * CSIS 2420-004
 * Assignment: WordNet
 *
 */
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


	   /**
	 * @param synsets String that indicates file name of synset file
	 * @param hypernyms String that indicates file name of hypernym file
	 * 
	 * Takes Two file names (1 for Synsets, and 1 for Hypernyms)
	 * Creates a symbol table from the synsets file
	 * 		When symbol table is created, it counts the vertexes
	 * Creates a Digraph from the vertex count
	 * Creates a Digraph from the hypernyms file
	 * Creates a SAP based on G
	 */
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
			   throw new java.lang.IllegalArgumentException("Cannot read one or both files.");
		   }
	   }
	   
	   /**
	 * @param synIn synset file name
	 * @throws java.lang.NumberFormatException if file cannot be read
	 * 
	 * This method creates and builds a symbol tree based on the synsets file
	 * It also keeps a count of the vertexes for use in the constructor
	 */
	private void createSynsetST(In synIn) throws java.lang.NumberFormatException
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
			 }catch(java.lang.NumberFormatException e)
			 {
				 throw new NumberFormatException("Synset error:First value in line must be an integer.");
			 }	 			 
		   } 
	   }
	   
	   /**
	 * @param hypIn hypernyms file
	 * @throws java.lang.NumberFormatException if file cannot be read properly
	 * 
	 * This method builds Digraph (G) by adding the edges indicated in the hypernyms file
	 */
	private void createHypernymDigraph(In hypIn) throws java.lang.NumberFormatException 
	   {
		   while (hypIn.hasNextLine())
		   {
			   String[] values = hypIn.readLine().split(",");
			   
			   try
			   {
				   int V = Integer.parseInt(values[0]);
				   //for all the integers in the hypernym file, build a Digraph out of G
				   for (int i = 1; i < values.length; i++)
				   {
					   int W = Integer.parseInt(values[i]);
					   this.G.addEdge(V, W);
				   }
			   }catch(java.lang.NumberFormatException e)
			   {
				   throw new java.lang.NumberFormatException("Hypernyms error: First value in file must be an integer");
			   }
		   }
	   }

	   /**
	 * @return all WordNet nouns
	 */
	public Iterable<String> nouns()
	   {
		   return symbolTreeReversed.keys();
	   }

	   /**
	 * @param word
	 * @return True if word is a noun
	 * 
	 * Searches the symbol tree for word, returns True if found, False if not found
	 */
	public boolean isNoun(String word)
	   {
		   return symbolTreeReversed.contains(word);
	   }

	   /**
	 * @param nounA String within the symbol tree
	 * @param nounB String within the symbol tree
	 * @return int value of distance between nounA and nounB
	 * 
	 * Searches through the Symbol tree for nounA and nounB
	 * uses sap to find the length between the two
	 */
	public int distance(String nounA, String nounB)
	   {
		   if(!this.symbolTreeReversed.contains(nounA) || !this.symbolTreeReversed.contains(nounB)){
			   throw new IllegalArgumentException("Nouns aren't contained in the synsets list.");
		   }
		   return sap.length(symbolTreeReversed.get(nounA), symbolTreeReversed.get(nounB));
	   }

	   /**
	 * @param nounA String within the symbol tree
	 * @param nounB String within the symbol tree
	 * @return String that is common ancestor of nounA and nounB
	 * 
	 * Searches the symbol tree using a synset (second field of synsets.txt) that is the common ancestor
	 * of nounA and nounB in a shortest ancestral path
	 */
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
