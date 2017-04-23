package a06_WordNet;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WordNetTest {

	//we will use these simplified files for testing
	String synsetFile = "synsetsTEST.txt";
	String hypernymsFile = "hypernymsTEST.txt";
	WordNet wN = new WordNet(synsetFile,hypernymsFile);
	
	
	@Test(expected = IllegalArgumentException.class) 
	public void testWordNetIncorrectFile() {	
  	
    	WordNet badWN1 = new WordNet("BADsynsetsTEST.txt","hypernymsTEST.txt");
    	WordNet badWN2 = new WordNet("synsetsTEST.txt","BADhypernymsTEST.txt");
    	WordNet badWN3 = new WordNet(null,"hypernymsTEST.txt");
    	WordNet badWN4 = new WordNet("synsetsTEST.txt",null);
    	WordNet badWN5 = new WordNet(null,null);
	}

	@Test
	public void testNouns() {
		String[] myNouns = new String[4];
		int i = 0;
		for (String m : wN.nouns())
		{
			myNouns[i++]=m;
		}
		assertEquals(myNouns[0], "a");
		assertEquals(myNouns[1], "b");
		assertEquals(myNouns[2], "c");
		assertEquals(myNouns[3], "d");
		assertNotEquals(myNouns[0], "f");
	}

	@Test
	public void testIsNoun() {
		assertTrue(wN.isNoun("a"));
		assertFalse(wN.isNoun("zzz"));
	}

	@Test
	public void testDistance() {
		assertEquals(3, wN.distance("a", "d"));
		assertEquals(2, wN.distance("a", "c"));
		assertEquals(1, wN.distance("a", "b"));
		assertEquals(0, wN.distance("a", "a"));
		assertEquals(2, wN.distance("b", "d"));
		assertNotEquals(3, wN.distance("a", "b"));
	}

	@Test
	public void testSap() {
		assertEquals("a", wN.sap("a", "d"));
		assertEquals("a", wN.sap("d", "a"));
		assertEquals("c", wN.sap("c", "d"));
		assertEquals("c", wN.sap("d", "c"));
		assertNotEquals("b", wN.sap("b", "a"));
	}

}
