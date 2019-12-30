package lab8;
import java.util.Set;
import java.util.HashSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
	private class Node {
		private K key;
		private V value;
		private Node left;
		private Node right;
		private Node parent;

		public Node(K k, V v) {
			key = k;
			value = v;
			left = null;
			right = null;
			parent = null;
		}
	}
	private Node root;
	private int size;

	public BSTMap() {
		root = null;
		size = 0;
	}

	public BSTMap(K key, V value) {
		root = new Node(key, value);
		size = 1;
	}

	/** Removes all of the mappings from this map. */
	@Override
	public void clear() {
		root = null;
    }

    /* Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
    	return containsKeyHelper(root, key);
    }

    public boolean containsKeyHelper(Node node, K key) {
    	if(root == null) {
    		return false;
    	}
    	int diff = key.compareTo(node.key);
    	if(diff == 0) {
    		return true;
    	}
    	else if(diff < 0) {
    		containsKeyHelper(node.left, key);
    	}
    	else if(diff > 0) {
    		containsKeyHelper(node.right, key);
    	}
    	return false;

    }

	/* Associates the specified value with the specified key in this map. */
	@Override
	public void put(K key, V value) {
    	root = putHelper(root, key, value, null);
    	size++;
    }

    public Node putHelper(Node node, K key, V value, Node parent) { 
    	int diff = key.compareTo(node.key);
    	if(node == null) {
    		node = new Node(key, value);
    		node.parent = parent;
    	}
    	else if(diff == 0) {
    		node.value = value;
    	}
    	else if(diff < 0) {
    		/** cannot use this.root.left here because 
    		  * this is type of BSTMap<K, V> and it can never change*/
    		node.left = putHelper(node.left, key, value, node);
    	}
    	else if(diff > 0) {
    		node.right = putHelper(node.right, key, value, node);
    	}
    	return node;
    }

	 /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key. 
     */
     @Override
	 public V get(K key) {
	 	return getHelper(root, key);
	 }

    public V getHelper(Node node, K key) {
    	if(root == null) {
    		return null;
    	}
    	int diff = key.compareTo(node.key);
    	if(diff == 0) {
    		return node.value;
    	}
    	else if(diff < 0) {
    		getHelper(node.left, key);
    	}
    	else if (diff > 0) {
    		getHelper(node.right, key);
    	}
    	return null;
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
    	return size;
    }

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
    	Set<K> keys = new HashSet<>(size);
    	return keySetHelper(keys, root);
    }

    public Set<K> keySetHelper(Set<K> keys, Node node) {
    	if(node != null){
    		keys.add(node.key);
    		keySetHelper(keys, node.right);
    		keySetHelper(keys, node.left);
    	}
    	return keys;
    }   

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an 
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
    	throw new UnsupportedOperationException("he");
    }
    // public V remove(Node node, K key) {
    // 	return removeHelper(root, key);
    // }

    // public V removeHelper(Node node, K key) {
    // 	if(node == null) {
    // 		throw new IllegalArgumentException("not in the map");
    // 	}
    // 	else if(key == node.key) {

    // 	}
    // }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
    	throw new UnsupportedOperationException("he");
    }

    // @Override
    // public Iterator<V> iterator() {
    // 	return new Iterator<V>();
    // }
}