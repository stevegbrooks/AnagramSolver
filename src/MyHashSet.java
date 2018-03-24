import java.util.ArrayList;
import java.util.LinkedList;
/**
 * This generic class allows one to implement a HashSet. It automatically resizes when the load factor
 * is greater than 0.70. Its HashTable is implemented with an ArrayList of LinkedLists, where the 
 * indices of the ArrayList refer to the HashTable buckets, and the LinkedLists hold the elements
 * of the HashSet.
 * 
 * @author sgb
 *
 * @param <E>
 */
public class MyHashSet<E> {
	
	private ArrayList<LinkedList<E>> buckets;
	private int size;
	private int tableLength;
	private final int DEFAULT_LENGTH = 16;
	
	/**
	 * No argument constructor. Creates a HashTable of length 16.
	 */
	public MyHashSet() {
		buckets = new ArrayList<>();
		size = 0;
		tableLength = DEFAULT_LENGTH;
		
		for (int i = 0; i < tableLength; i++) {
			LinkedList<E> bucket = new LinkedList<E>();
			buckets.add(bucket);
		}
	}
	/**
	 * This constructor creates a HashTable of a specified length.
	 * @param initialLength
	 */
	public MyHashSet(int initialLength) {
		buckets = new ArrayList<>();
		size = 0;
		tableLength = initialLength;
		
		for (int i = 0; i < tableLength; i++) {
			LinkedList<E> bucket = new LinkedList<E>();
			buckets.add(bucket);
		}
	}
	/**
	 * This method checks if an element specified exists within the HashSet. 
	 * @param e an element to look for
	 * @return true if the element exists, false otherwise.
	 */
	public boolean contains(E e) {
		
		int hashCode = e.hashCode();
		int bucketNumber = hashCode % tableLength;
		if (bucketNumber < 0) { bucketNumber += tableLength; }
		
		if (buckets.get(bucketNumber).size() == 0) {
			return false;
		} else {
			for (E element : buckets.get(bucketNumber)) {
				if (element.equals(e)) {
					return true;
				}
			}
			return false;
		}
	}
	/**
	 * This method adds an element to the HashSet. 
	 * @param e the element to add.
	 * @return true if the add was successful, false if the HashSet already contains the element.
	 */
	public boolean add(E e) {
		if (contains(e) == false) {

			int hashCode = e.hashCode();
			int bucketNumber = hashCode % tableLength;
			if (bucketNumber < 0) { bucketNumber += tableLength; }
			buckets.get(bucketNumber).add(e);
			size++;
			
			if (loadFactor() >= 0.70) {
				reHash(buckets);
			}
			
			return true;
		}
		return false;
	}
	/**
	 * This method removes an element from the HashSet.
	 * @param e the element to remove
	 * @return true if the element was successfully removed, false otherwise.
	 */
	public boolean remove(E e) {
		if (contains(e) == true) {
			int hashCode = e.hashCode();
			int bucketNumber = hashCode % tableLength;
			if (bucketNumber < 0) { bucketNumber += tableLength; }
			buckets.get(bucketNumber).remove(e);
			size--;
			return true;
		}
		return false;
	}
	/**
	 * @return the number of elements in the HashSet
	 */
	public int size() {
		return size;
	}
	/**
	 * @return the current load factor of the HashSet
	 */
	public double loadFactor() {
		return (double) size/tableLength;
	}
	/**
	 * This helper function roughly doubles the length of the HashTable. 
	 * It must first instantiate a new HashSet, and then fill it with the elements
	 * from the old HashSet. It runs in O(n) time.
	 *  
	 * @param oldBuckets the HashSet to be reHashed.
	 */
	private void reHash(ArrayList<LinkedList<E>> oldBuckets) {
		int newLength = (tableLength * 2) - 1;
		
		ArrayList<LinkedList<E>> newBuckets = new ArrayList<>(newLength);
		for (int i = 0; i < newLength; i++) {
			LinkedList<E> newBucket = new LinkedList<E>();
			newBuckets.add(newBucket);
		}
		
		for (LinkedList<E> oldBucket : oldBuckets) {
			if (oldBuckets != null) {
				for (E e : oldBucket) {
					int hashCode = e.hashCode();
					int newBucketNumber = hashCode % newLength;
					if (newBucketNumber < 0) { newBucketNumber += newLength; }

					newBuckets.get(newBucketNumber).add(e);
				}
			}
		}
		buckets = newBuckets;
		tableLength = newLength;
	}
}
