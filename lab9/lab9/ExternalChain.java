package lab9;

public class ExternalChain<K, V> {
    public class Node {
        K key;
        V value;
        Node next;

        public Node() {
            key = null;
            value = null;
            next = null;
        }

        public Node(K k, V v) {
            key = k;
            value = v;
            next = null;
        }
    }
    Node chain;
    int size;

    public ExternalChain(K key, V value) {
        chain = new Node(key, value);
        size = 1;
    }

    /* get(key) return the value corresponding to that key */
    public V get(K key) {
        return getHelper(chain, key);
    }
    public V getHelper(Node node, K key) {
        if(node == null) {
            return null;
        }
        V value;
        if(key == node.key) {
            value = node.value;
        } else {
            value = getHelper(node.next, key);
        }
        return value;
    }

    /** put key and value into the right place, if the key already exists,
     * replace the value
     */
    public void put(K key, V value) {
        putHelper(this.chain, key, value);
    }
    public void putHelper(Node node, K key, V value) {
        if(node == null) {
            node = new Node(key, value);
        }
        if(key.equals(node.key)) {
            node.value = value;
        }
        else {
            putHelper(node.next, key, value);
        }
    }

    public int size() {
        return size;
    }


}
