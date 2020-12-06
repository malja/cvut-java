package solution;

public class MapEntry<K, V> {
    private K key;
    private V value;
    private int hash;

    public MapEntry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hash = calculateHash(key);
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setKey(K key) {
        this.key = key;
        hash = calculateHash(key);
    }

    public void setValue(V value) {
        this.value = value;
    }

    public int getHash() {
        return hash;
    }

    private int calculateHash(K key) {
        return key.hashCode();
    }
}
