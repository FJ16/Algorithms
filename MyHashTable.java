import java.util.LinkedList;
import java.util.List;

public class MyHashTable<K, V> {
    private static int MAX_SIZE = 256; // initial size
    private static final double LOADFACTOR = 0.75d; // static ensures this var is only one
    private List<Cell<K, V>>[] items; // array of Lists of Cell, list here is for hash collision
    private int size; // current size

    public MyHashTable() {
        this.items = (List<Cell<K, V>>[]) new LinkedList[MAX_SIZE]; // generic array instantiation
        this.size = 0;
    }

    public int hashCodeOfKey(K key) { // key -> index
        return key == null ? 0 : key.hashCode() % MAX_SIZE; // .hashCode() guarantee return value with >= 0;
        // map null to 0 index;
    }

    public void put(K key, V value) {

        Cell<K, V> newCell = new Cell(key, value);
        int index = hashCodeOfKey(key);

        if (items[index] == null) { // check the bucket
            items[index] = new LinkedList<Cell<K, V>>(); // new the list inside of the bucket
        }

        // duplication detection
        List<Cell<K, V>> slot = items[index];
        for (Cell<K, V> cell : slot) {
            if (cell.equals(newCell)) {
                slot.remove(cell);
                this.size--;
                break;
            }
        }

        this.size++;
        slot.add(newCell);

        // resize
        if (size >= MAX_SIZE * LOADFACTOR) {
            rehashing();
        }
    }

    public V get(K key) {
        int index = hashCodeOfKey(key);

        if (items[index] == null) return null;

        for (Cell<K, V> cell : items[index]) {
            if (cell.keyEquals(key)) { // 为啥不直接 cell.getKey().equals(key) ?
                return cell.getValue();
            }
        }

        return null;
    }

    private void rehashing() {
        MAX_SIZE *= 2;
        List<Cell<K, V>>[] newItems = (List<Cell<K, V>>[]) new LinkedList[MAX_SIZE];

        for (List<Cell<K, V>> slot : items) {
            if (slot != null) {
                for (Cell<K, V> cell : slot) {
                    int index = hashCodeOfKey(cell.getKey());
                    if (newItems[index] == null) {
                        newItems[index] = new LinkedList<Cell<K, V>>();
                    }
                    newItems[index].add(cell);
                }
            }
        }

        this.items = newItems;
    }
}

// inner class, only HashTable can only access the Cell or it could be static class(static class must be an inner class)
// static class:
class Cell<K, V> {
    private K key;
    private V value;

    public Cell(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int hashCode() { // override, it will be used when
        return this.key == null ? 0 : this.key.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        // In HashTable, we don't care about the duplicate value, so we only check the equation of the key
        if (o instanceof Cell<?, ?>) {
            // check if the Object o is a Cell instance, here the generic type is ?, since it can not be checked
            Cell<K, V> that = (Cell<K, V>) o; // cast the o to certain Cell object
            if (key == null) {
                return that.key == null;
            } else {
               return key.equals(that.key);
            }

        }
        else return false;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public boolean keyEquals(K key) {
        if (key == null) {
            return this.key == null;
        } else {
            return this.key.equals(key);
        }
    }
}