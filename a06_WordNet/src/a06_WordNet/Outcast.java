package a06_WordNet;

import java.util.ArrayList;

public class Outcast {
	
	private WordNet wordnet;
	
	public Outcast(WordNet wordnet)	// constructor takes a WordNet object
	{          
		   this.wordnet = wordnet;
	}
	
	public String outcast(String[] nouns) // given an array of WordNet nouns, return an outcast
	{   
		ArrayList<String> nounsUsed = new ArrayList<>();
		for(String n: nouns)
		{
			//Create an ArrayList of Strings that are in the synsets.txt
			if (wordnet.isNoun(n)) nounsUsed.add(n);
		}
		
		int max = 0;
		String maxString = "";
		for(int i = 0; i < nounsUsed.size(); i++)
		{
			int sumOfDistances = 0;
			for(int j = 0; j < nounsUsed.size(); j++)
			{

				int dist = wordnet.distance(nounsUsed.get(i), nounsUsed.get(j));
				sumOfDistances = sumOfDistances + dist;//current sum + new distance
			}
			
			if(sumOfDistances > max)
			{
				max = sumOfDistances;
				maxString = nounsUsed.get(i);
			}
		}
		return maxString;
	}
	
	public static void main(String[] args){	 // see test client below 
		//takes from the command line the name of a synset file, the name of a hypernym file,
		//followed by the names of outcast files, and prints out an outcast in each file:
		WordNet wordnet = new WordNet("synsets.txt", "hypernyms.txt");
		Outcast outcast = new Outcast(wordnet);
	    String[] nouns = {"apple", "pear", "peach", "banana", "lime", "blueberry", "strawberry", "mango", "watermelon", "table"};
	    String[] nouns1 = {"horse", "zebra", "bear", "table"};

	    System.out.println(outcast.outcast(nouns));
	    System.out.println(outcast.outcast(nouns1));
	    
   }
}
