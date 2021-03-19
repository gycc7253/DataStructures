import java.util.LinkedList;
import java.util.List;

public class CustomSet {
    int size;
    List<Integer>[] arr;
    int numBuckets;

    public static void main(String[] args) {
        CustomSet set = new CustomSet(4);
        set.add(5);
        set.add(2);
        set.add(10);
        set.add(30);
        System.out.println(set);
        set.remove(6);
        set.remove(5);
        set.remove(2);
        System.out.println(set);
    }

    public CustomSet(int capacity) {
        this.size = 0;
        this.numBuckets = (int) (capacity / 0.75);
        this.arr = new LinkedList[numBuckets];
        for (int i = 0; i < numBuckets; i++) {
            arr[i] = new LinkedList<>();
        }
    }

    public boolean add(int element) {
        int bucketIndex = element % numBuckets;
        List<Integer> bucket = arr[bucketIndex];
        if (bucket.contains(element)) {
            return false;
        }
        this.size++;
        bucket.add(element);
        return true;
    }

    public boolean contains(int element) {
        int bucketIndex = element % numBuckets;
        return arr[bucketIndex].contains(element);
    }

    public boolean remove(int element) {
        int bucketIndex = element % numBuckets;
        List<Integer> bucket = arr[bucketIndex];
        if (!bucket.contains(element)) {
            return false;
        }
        this.size--;
        bucket.remove(Integer.valueOf(element));
        return true;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Set numBuckets: " + numBuckets + "\n";
        str += "Set size: " + size + "\n";
        for (List<Integer> list : arr) {
            str += list.toString() + "\n";
        }
        return str;
    }
}
