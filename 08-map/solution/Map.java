package solution;

import java.util.Arrays;

public class Map<K, V> {
    // Number of elements in 'entries' array
    private int size = 0;
    // What is the default number of elements in the 'entries' array
    private int DEFALT_SIZE = 20;
    // Prepare array of MapEntries
    private MapEntry<K, V>[] entries = new MapEntry[DEFALT_SIZE];

    /**
     * Check if the map contains specific key.
     * @return True if key is found.
     */
    public boolean containsKey(K key) {
        int hash = key.hashCode();

        for (MapEntry entry : entries) {
            if (entry != null && entry.getHash() == hash) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get value by its key. If key does not exist, returns null.
     * @param key Value key for the lookup.
     * @return Value stored with given key, or null when the key does not exist.
     */
    public V get(K key) {
        return getOrDefault(key, null);
    }

    /**
     * Get value by its key. If key does not exist, returns 'defaultValue'.
     * @param key Key for the lookup.
     * @param defaultValue Default value returned when 'key' does not exist.
     * @return Value stored with given key, or 'defaultValue' when the key does not exist.
     */
    public V getOrDefault(K key, V defaultValue) {
        int hash = key.hashCode();

        for (MapEntry entry : entries) {
            // Both are numbers, no need for .equals()
            if (entry.getHash() == hash) {
                V val = (V)entry.getValue();
                return val;
            }
        }

        return defaultValue;
    }

    public int size() {
        return size;
    }

    /**
     * Insert new value into map.
     * @param key Key used as an index.
     * @param value Value stored with the key.
     */
    public void put(K key, V value) {
        if (containsKey(key)) {
            return;
        }

        growIfNecessary();
        entries[size++] = new MapEntry<K, V>(key, value);
    }

    /**
     * Remove specific key.
     * @param key
     */
    public void remove(K key) {
        if (!containsKey(key)) {
            return;
        }

        int hash = key.hashCode();

        for (int i = 0; i < size; i++) {
            if (entries[i].getHash() == hash) {
                entries[i] = null;
                size--;

                // I could have just finish this for loop and condense the values
                // in it. But it was not that readable.
                condense(i);
                break;
            }
        }

        shrinkIfNecessary();
    }

    /**
     * Resize the internal array if necessary.
     */
    private void growIfNecessary() {
        // Is there no more free space in the array?
        if (size == entries.length){
            // Double the size
            int newLength = entries.length * 2;
            // Copy old values to the new array with specified lenght
            entries = Arrays.copyOf(entries, newLength);
        }
    }

    /**
     * Move all elements of internal array by one index to fill gap after removing
     * one element.
     * @param start Index of the removed element. Only elements with higher index are
     * shifted down.
     */
    private void condense(int start) {
        for (int i = start; i < size; i++) {
            entries[i] = entries[i+1];
        }
    }

    /**
     * Resize internal array down, when necessary.
     */
    private void shrinkIfNecessary() {
        // Is the array allocated for double the number of used entries?
        if (size < entries.length/2) {
            int newLength = entries.length / 2;
            entries = Arrays.copyOf(entries, newLength);
        }
    }
}