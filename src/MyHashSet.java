import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashSet<E> {
	
	private ArrayList<LinkedList<E>> buckets;
	private int size;
	private int tableLength;
	private final int DEFAULT_LENGTH = 16;
	
	public MyHashSet() {
		buckets = new ArrayList<>();
		size = 0;
		tableLength = DEFAULT_LENGTH;
		
		for (int i = 0; i < tableLength; i++) {
			LinkedList<E> bucket = new LinkedList<E>();
			buckets.add(bucket);
		}
	}
	
	public MyHashSet(int initialLength) {
		buckets = new ArrayList<>();
		size = 0;
		tableLength = initialLength;
		
		for (int i = 0; i < tableLength; i++) {
			LinkedList<E> bucket = new LinkedList<E>();
			buckets.add(bucket);
		}
	}
	
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
	
	public int size() {
		return size;
	}
	
	public double loadFactor() {
		return (double) size/tableLength;
	}
	
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
					int newBucketNumber = hashCode % tableLength;
					if (newBucketNumber < 0) { newBucketNumber += tableLength; }

					newBuckets.get(newBucketNumber).add(e);
				}
			}
		}
		buckets = newBuckets;
		tableLength = newLength;
	}
}
