package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
	/* I tried to use ArrayList as the type of the
	 * array, but it fails. Because I still need to check each
	 * position of ArrayList, which means I need to write extra methods for
	 * Array list and that would be a little bit messy, so I will create a
	 * new class to reduce the complexity*/
	/* I find two ways, one is to use Arraylist to represent
	 * the type of the node.(no need to create Node next) The other is to use
	 * linked list.(easier to add, no need to resize) However, I cannot change those
	 * classes unless I rewrite them. So I will create a class with those charactrization
	 */
	private ExternalChain<K, V>[] array;
	Set<K> keySet;
	double maxLoadFactor = 3.0;
	int INITIALSIZE = 8;
	int capacity = INITIALSIZE;
	private int size;


	public MyHashMap() {
		array = new ExternalChain[INITIALSIZE];
		keySet = new HashSet();
		size = 0;
	}

	public MyHashMap (int initialSize) {
		array = new ExternalChain[initialSize];
		keySet = new HashSet();
		capacity = initialSize;
		size = 0;
	}

	public MyHashMap (int initialSize, double loadFactor) {
		array = new ExternalChain[initialSize];
		keySet = new HashSet();
		this.maxLoadFactor = loadFactor;
		capacity = initialSize;
		size = 0;
	}

	@Override
	public void clear() {
		array = null;
	}

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
    	return keySet.contains(key);
    }

	public int hashCode(K key) {
		int index = (key.hashCode() & 0x7fffffff) % this.capacity;
    	if(index < 0) {
    		index += capacity;
		}
    	return index;
	}

	/* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
    @Override
    public V get(K key) {
		int index = this.hashCode(key);
    	if(array[index] == null) {
    		return null;
		}
    	V value = array[index].get(key);
    	return value;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return size;
    }

    public void resize() {
    	int resizeCapacity = capacity * 2;
    	MyHashMap<K, V> newHashMap = new MyHashMap<>(resizeCapacity);

    	for(K key: this) {
    		V value = get(key);
    		newHashMap.put(key, value);
		}
    	array = newHashMap.array;
    	capacity = resizeCapacity;
	}

    /* Associates the specified value with the specified key in this map. 
     * change keySet */
    @Override
    public void put(K key, V value) {
    	int index = this.hashCode(key);
    	while(index >= capacity) {
    		resize();
    		index = this.hashCode(key);
		}
    	if(array[index] == null) {
    		array[index] = new ExternalChain(key, value);
		} else {
			array[index].put(key, value);
		}
    	keySet.add(key);
    	size ++;
    	//checking loadFactor
		double actualLoadFactor = this.size()/capacity;
    	while(actualLoadFactor > maxLoadFactor) {
    		resize();
			actualLoadFactor = this.size()/capacity;
		}
    }

    /* Returns a Set view of the keys contained in this map. */
    public Set<K> keySet() {
    	return keySet;
    }   

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an 
     * UnsupportedOperationException. */
    // V remove(K key);

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    // V remove(K key, V value);

	@Override
	public Iterator<K> iterator() {
		return new KeyIterator();
	}

	public class KeyIterator implements Iterator<K> {
		private Iterator<K> keyIterator;
		public KeyIterator() {
			keyIterator = keySet.iterator();
		}
		@Override
		public boolean hasNext() {
			return this.keyIterator.hasNext();
		}

		@Override
		public K next() {
			return this.keyIterator.next();
		}
	}




}