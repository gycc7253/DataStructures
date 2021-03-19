import java.util.LinkedList;
import java.util.List;

public class CustomMapGeneric<E, T> {

    int size;
    List<Entry<E, T>>[] arr;
    int numBuckets;

    public static void main(String[] args) {
        CustomMapGeneric<Integer, Integer> set = new CustomMapGeneric<>(4);
        set.put(5, 5);
        set.put(2, 2);
        set.put(10, 10);
        set.put(30, 30);
        set.put(40, 40);
        System.out.println(set);
        set.remove(6);
        set.remove(5);
        set.remove(2);
        System.out.println(set.containsKey(10));
        set.put(10, 11);
        System.out.println(set);


    }

    public CustomMapGeneric(int capacity) {
        this.size = 0;
        this.numBuckets = (int) (capacity / 0.75);
        this.arr = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            arr[i] = new LinkedList<>();
        }
    }

    public int size() {
        return this.size;
    }

    public boolean containsKey(E key) {
        int bucketIndex = key.hashCode() % numBuckets;
        List<Entry<E, T>> bucket = arr[bucketIndex];
        return bucket.stream().reduce(false, (a, b) -> (a || b.key.equals(key)), (a, b) -> a || b);
    }

    public Entry<E, T> getEntry(E key) {
        int bucketIndex = key.hashCode() % numBuckets;
        List<Entry<E, T>> bucket = arr[bucketIndex];
        return bucket.stream().reduce(null, (a, b) -> (b.key == key ? b : a), (a, b) -> (a == null ? b : a));
    }

    public T put(E key, T value) {
        int bucketIndex = key.hashCode() % numBuckets;
        List<Entry<E, T>> bucket = arr[bucketIndex];
        if (containsKey(key)) {
            Entry<E, T> entry = getEntry(key);
            T previousValue = entry.value;
            entry.value = value;
            return previousValue;
        } else {
            this.size++;
            bucket.add(new Entry<>(key, value));
            return null;
        }
    }

    public boolean remove(E key) {
        int bucketIndex = key.hashCode() % numBuckets;
        List<Entry<E, T>> bucket = arr[bucketIndex];
        if (!containsKey(key)) {
            return false;
        }
        this.size--;
        Entry<E, T> entry = getEntry(key);
        bucket.remove(entry);
        return true;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Set numBuckets: ").append(numBuckets).append("\n");
        str.append("Set size: ").append(size).append("\n");
        for (List<Entry<E, T>> list : arr) {
            str.append(list.toString()).append("\n");
        }
        return str.toString();
    }

    class Entry<K, V> {
        K key;
        V value;
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry<K, V> entry = (Entry<K, V>) obj;
            return entry.key == this.key && entry.value == this.value;
        }

        @Override
        public String toString() {
            return this.key + " : " + this.value;
        }
    }

}
