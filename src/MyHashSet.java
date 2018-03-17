import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashSet<E> {
	
	private ArrayList<LinkedList<E>> buckets;
	private int size;
	private int tableLength;
	private final int DEFAULT_LENGTH = 16;
	
	public MyHashSet() {
		this.buckets = new ArrayList<>(DEFAULT_LENGTH);
		size = 0;
		tableLength = DEFAULT_LENGTH;
		
		for (LinkedList<E> bucket : buckets) {
			bucket = new LinkedList<E>();
		}
	}
	
	public MyHashSet(int initialLength) {
		this.buckets = new ArrayList<>(initialLength);
		size = 0;
		tableLength = initialLength;
		
		for (LinkedList<E> bucket : buckets) {
			bucket = new LinkedList<E>();
		}
	}
	
	public boolean contains(E e) {
		int hashCode = e.hashCode();
		if (hashCode < 0) { hashCode = -hashCode; }
		int bucketNumber = hashCode % tableLength;
		
		if (buckets.get(bucketNumber) == null) {
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
			if (hashCode < 0) { hashCode = -hashCode; }
			int bucketNumber = hashCode % tableLength;
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
		size--;
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public double loadFactor() {
		return (double) size/tableLength;
	}
	
	private void reHash(ArrayList<LinkedList<E>> buckets) {
		int newLength = (tableLength * 2) - 1;
		
		ArrayList<LinkedList<E>> newBuckets = new ArrayList<>(newLength);
		
		for (LinkedList<E> bucket : buckets) {
			if (buckets != null) {
				for (E element : bucket) {
					int hashCode = element.hashCode();
					if (hashCode < 0) { hashCode = -hashCode; }
					
					int newBucketNumber = hashCode % newLength;
					newBuckets.get(newBucketNumber).add(element);
				}
			}
		}
		buckets = newBuckets;
		tableLength = newLength;
	}
}
