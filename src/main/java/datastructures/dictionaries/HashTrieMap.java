package datastructures.dictionaries;

import cse332.interfaces.trie.TrieMap;
import cse332.types.BString;

import java.util.*;
import java.util.Map.Entry;

/**
 * See cse332/interfaces/trie/TrieMap.java
 * and cse332/interfaces/misc/Dictionary.java
 * for method specifications.
 */
public class HashTrieMap<A extends Comparable<A>, K extends BString<A>, V> extends TrieMap<A, K, V> {
    public class HashTrieNode extends TrieNode<Map<A, HashTrieNode>, HashTrieNode> {
        public HashTrieNode() {
            this(null);
        }

        public HashTrieNode(V value) {
            this.pointers = new HashMap<A, HashTrieNode>();
            this.value = value;
        }

        @Override
        public Iterator<Entry<A, HashTrieMap<A, K, V>.HashTrieNode>> iterator() {
            return pointers.entrySet().iterator();
        }
    }

    // Creates a new HashTrieMap
    public HashTrieMap(Class<K> KClass) {
        super(KClass);
        this.root = new HashTrieNode();
        this.size = 0;
    }

    // Inserts a new value at the specified key into the map
    // Returns the key's previous value
    @Override
    public V insert(K key, V value) {
        HashTrieNode current = (HashTrieNode) this.root;
        for (A character : key) {
            if (!current.pointers.containsKey(character)) {
                current.pointers.put(character, new HashTrieNode());
            }
            current = current.pointers.get(character);
        }
        V oldValue = current.value;
        current.value = value;
        if (oldValue == null) {
            this.size++;
        }
        return oldValue;
    }

    // Finds the value in the map at the specified key
    @Override
    public V find(K key) {
        HashTrieNode current = (HashTrieNode) this.root;
        for (A character : key) {
            if (!current.pointers.containsKey(character)) {
                return null;
            }
            current = current.pointers.get(character);
        }
        return current.value;
    }

    // Checks whether the key prefix exists in the map
    @Override
    public boolean findPrefix(K key) {
        HashTrieNode current = (HashTrieNode) this.root;
        if (current.pointers.isEmpty() && current.value == null) {
            return false;
        }
        for (A character : key) {
            if (!current.pointers.containsKey(character)) {
                return false;
            }
            current = current.pointers.get(character);
        }
        return true;
    }

    // Deletes the key-value pair at the specified key
    @Override
    public void delete(K key) {
        HashTrieNode current = (HashTrieNode) this.root;
        HashTrieNode toRemove = current;
        A removeChar = null;
        for (A character : key) {
            if (!current.pointers.containsKey(character)) {
                return;
            }
            // Marks the node for deletion
            if (current.pointers.size() > 1 || current.value != null) {
                toRemove = current;
                removeChar = character;
            }
            current = current.pointers.get(character);
        }
        if (current.value == null) {
            return;
        }
        if (!current.pointers.isEmpty()) {
            current.value = null;
        }
        else if (removeChar == null){
            toRemove.pointers.clear();
        }
        else {
            toRemove.pointers.remove(removeChar);
        }
        this.size--;
    }

    // Resets the map, removes all previous key-value pairs
    @Override
    public void clear() {
        this.root = new HashTrieNode();
        this.size = 0;
    }
}
