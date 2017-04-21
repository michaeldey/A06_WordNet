package a06_WordNet;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Outcast {
	
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet)	// constructor takes a WordNet object
	{          
		   this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns) // given an array of WordNet nouns, return an outcast
	{   
		int[] distances = new int[nouns.length];
		int max = 0;
		for(int i = 0; i < nouns.length; i++){
			int sumOfDistances = 0;
			System.out.println("\n"+i+":");
			for(int j = 0; j < nouns.length; j++){
				int dist = wordnet.distance(nouns[i], nouns[j]);
				sumOfDistances = sumOfDistances + dist;//current sum + new distance
				System.out.println(dist);
			}
			distances[i] = sumOfDistances;
			if(sumOfDistances > max){
				max = sumOfDistances;
			}
		}
		
		return "";
	}
	
	public static void main(String[] args){	 // see test client below 
		//takes from the command line the name of a synset file, the name of a hypernym file,
		//followed by the names of outcast files, and prints out an outcast in each file:
		WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
		Outcast outcast = new Outcast(wordnet);
	    String[] nouns = {"apple", "pear", "peach", "banana", "lime", "lemon", "blueberry", "strawberry", "mango", "watermelon", "potato"};
	    System.out.println(outcast.outcast(nouns));
   }
}
