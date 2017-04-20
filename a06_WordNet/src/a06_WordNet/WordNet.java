package a06_WordNet;

import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;


public class WordNet {

	   // constructor takes the name of the two input files
	   public WordNet(String synsets, String hypernyms)
	   {
		   In synIn = new In(synsets);
		   In hypIn = new In(hypernyms);
		   
		   String synLine, synIndex, synNoun;
		   Integer key;
		   if (synIn.hasNextLine())	//change this to a while statement after testing
		   {
			 String[] values = synIn.readLine().split(",");
			 
			 //load first value of line as an integer, and use that as the index
			 try
			 {
				 key = Integer.parseInt(values[0]);
				 
				 for (int i = 1; i<(values.length-1); i++)
				 {
					 System.out.println(i);
				 }
			 }catch(NumberFormatException e)
			 {
				 System.out.println("File is in incorrect format. First value in line must be an integer. " + e.getMessage());
			 }	 
			 
			 
		   }
	   }
	   
//	   private class WordNetNode(int index, String[] definitions)
//	   {
//		   
//	   }

	   // returns all WordNet nouns
	   public Iterable<String> nouns()
	   {
		   return null;
	   }

	   // is the word a WordNet noun?
	   public boolean isNoun(String word)
	   {
		   return false;
	   }

	   // distance between nounA and nounB (defined below)
	   public int distance(String nounA, String nounB)
	   {
		   return 0;
	   }

	   // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
	   // in a shortest ancestral path (defined below)
	   public String sap(String nounA, String nounB)
	   {
		   return null;
	   }

	   // do unit testing of this class
	   public static void main(String[] args)
	   {
		   String synsetFile = "synsets.txt";
		   String hypernymsFile = "hypernyms.txt";
		   
		   WordNet wordNet = new WordNet(synsetFile, hypernymsFile);
	   }
	}
