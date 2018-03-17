import java.util.ArrayList;
import java.util.LinkedList;

public class MyHashSet<E> {
	
	private ArrayList<LinkedList<E>> buckets;
	private int size;
	private int capacity;
	private final int DEFAULT_CAPACITY = 16;
	
	public MyHashSet() {
		this.buckets = new ArrayList<>(DEFAULT_CAPACITY);
		size = 0;
		capacity = DEFAULT_CAPACITY;
	}
	
	public MyHashSet(int initialCapacity) {
		this.buckets = new ArrayList<>(initialCapacity);
		size = 0;
		capacity = initialCapacity;
	}
	
	public boolean contains(E e) {
		int bucketNumber = e.hashCode() % capacity;
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
		size++;
		return false;
	}
	
	public boolean remove(E e) {
		size--;
		return false;
	}
	
	public int size() {
		return size;
	}
	
	public double getLoadFactor() {
		return (double) size/capacity;
	}
}
