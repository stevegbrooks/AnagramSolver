import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {
	private Dictionary dict;
	
	@Before
	public void setUp() throws Exception {
		dict = new Dictionary("words.txt");
	}

	@Test
	public void testContainsTrue() {
		assertTrue("Error: this word does exist in the 'words.txt' file,"
				+ " so contains() should have returned true, but instead "
				+ "returned false", dict.contains("word"));
	}
	
	@Test
	public void testReadFileException() {
		try {
			dict = new Dictionary("fakeFile.txt");
			fail("Error: should not have been able to move on to "
					+ "this line of code without throwing an exception");
		} catch (Throwable t) {
			assertFalse("Error: dict2 should be empty, but it isn't", 
					dict.contains("some"));
		}
	}
}
