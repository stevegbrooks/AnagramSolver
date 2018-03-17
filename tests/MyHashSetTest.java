import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MyHashSetTest {
	private MyHashSet<String> hashSet;
	private MyHashSet<String> hashSet2;
	
	@Before
	public void setUp() throws Exception {
		hashSet = new MyHashSet<>();
		final int INITIAL_LENGTH = 31;
		hashSet2 = new MyHashSet<>(INITIAL_LENGTH);
		String[] stuffToAdd = {"zeke", "harry", "astronaut", "dweevel", "citizen", "thorn"};
		
		for (int i = 0; i < stuffToAdd.length; i++) {
			hashSet.add(stuffToAdd[i]);
			hashSet2.add(stuffToAdd[i]);
		}
	}
	
	/**
	 * TESTS FOR CONTAINS - Hit both 'true' and 'false' conditions.
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
		double expectedLoadFactor = 0.39;
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
				+ "but instead returned false", hashSet.contains("zeke"));
	}
	
	@Test
	public void testRemoveFalse() {
		assertFalse("Error: 'Steve' is not in the hashSet, so this should return false, "
				+ "but instead returned true", hashSet.contains("Steve"));
	}

}
