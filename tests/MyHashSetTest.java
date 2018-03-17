import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyHashSetTest {
	private MyHashSet<String> hashSet;
	private MyHashSet<String> hashSet2;
	String[] stuffToAdd = {"zeke", "harry", "astronaut", "dweevel", "citizen", "thorn"};
	
	@Before
	public void setUp() throws Exception {
		hashSet = new MyHashSet<>();
		final int INITIAL_LENGTH = 31;
		hashSet2 = new MyHashSet<>(INITIAL_LENGTH);
		
		for (int i = 0; i < stuffToAdd.length; i++) {
			hashSet.add(stuffToAdd[i]);
			hashSet2.add(stuffToAdd[i]);
		}
	}
	
	/**
	 * TESTS FOR CONTAINS - Hit both 'true' and 'false' conditions. The second 'false' test
	 * will try to get into the branch of the contains() method where it checks if the LL size is 0.
	 */
	@Test
	public void testContainsTrue() {
		assertTrue("Error: 'zeke' is in the hashSet, so this should return true, "
				+ "but instead returned false", hashSet.contains("zeke"));
	}
	
	@Test
	public void testContainsFalse() {
		assertFalse("Error: 'Steve' is not in the hashSet, so this should return false, "
				+ "but instead returned true", hashSet.contains("Steve"));
	}
	
	@Test
	public void testContainsFalse2() {
		assertFalse("Error: 'Ji' is not in the hashSet, so this should return false, "
				+ "but instead returned true", hashSet.contains("Ji"));
	}
	
	/**
	 * TESTS FOR ADD 
	 * First part: Hit both 'true' and 'false' conditions.
	 * Second part: Add enough strings to make loadFactor exceed the threshold of 0.70 and force
	 * an expansion.
	 */
	@Test
	public void testAddFalse1() {
		assertFalse("Error: 'zeke' is in the hashSet, so this should return false, "
				+ "but instead returned true", hashSet.add("zeke"));
	}
	
	@Test
	public void testAddTrue1() {
		assertTrue("Error: 'Steve' is not in the hashSet, so this should return true, "
				+ "but instead returned false", hashSet.add("Steve"));
	}
	
	@Test
	public void testAddUntilExpansion() {
		String[] stuffToAdd = {"twenty", "necromancer", "zombie", "angel", "mech", "pen"};
		
		for (int i = 0; i < stuffToAdd.length; i++) {
			hashSet.add(stuffToAdd[i]);
		}
		double expectedLoadFactor = (double) 12/31;
		double actualLoadFactor = hashSet.loadFactor();
		assertEquals("Error: adding the last element in 'stuffToAdd' should have caused an expansion, "
				+ "which should have made the loadFactor be reduced to " 
				+ expectedLoadFactor + "but was " + actualLoadFactor , 
				expectedLoadFactor, actualLoadFactor, 0.1);
	}
	
	/**
	 * TESTS FOR REMOVE
	 * First part: Hit both 'true' and 'false' conditions.
	 * Second part: check that contains() does/doesn't return true after a remove() call.
	 */
	@Test
	public void testRemoveTrue() {
		assertTrue("Error: 'zeke' is in the hashSet, so this should return true, "
				+ "but instead returned false", hashSet.remove("zeke"));
	}
	
	@Test
	public void testRemoveFalse() {
		assertFalse("Error: 'Steve' is not in the hashSet, so this should return false, "
				+ "but instead returned true", hashSet.remove("Steve"));
	}
	
	@Test
	public void testRemoveThenContains() {
		hashSet.remove("zeke");
		assertFalse("Error: 'zeke' was removed, so this should return false, "
				+ "but instead returned true", hashSet.contains("zeke"));
	}
	
	/**
	 * TEST FOR SIZE
	 */
	
	@Test
	public void testSize() {
		int expectedSize = stuffToAdd.length;
		int actualSize = hashSet.size();
		assertEquals("Error: size of HashSet should be " 
		+ expectedSize + " but was " + actualSize, expectedSize, actualSize);
	}
	
}
