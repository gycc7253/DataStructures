import java.util.LinkedList;
import java.util.List;

public class CustomSetGeneric<E> {

    int size;
    List<E>[] arr;
    int numBuckets;

    public static void main(String[] args) {
        CustomSetGeneric<Integer> set = new CustomSetGeneric<>(5);
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

    public CustomSetGeneric(int capacity) {
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

    public boolean contains(E element) {
        int bucketIndex = element.hashCode() % numBuckets;
        List<E> bucket = arr[bucketIndex];
        return bucket.contains(element);
    }

    public boolean add(E element) {
        int bucketIndex = element.hashCode() % numBuckets;
        List<E> bucket = arr[bucketIndex];
        if (bucket.contains(element)) {
            return false;
        }
        this.size++;
        bucket.add(element);
        return true;
    }

    public boolean remove(E element) {
        int bucketIndex = element.hashCode() % numBuckets;
        List<E> bucket = arr[bucketIndex];
        if (!bucket.contains(element)) {
            return false;
        }
        this.size--;
        bucket.remove(element);
        return true;
    }

    @Override
    public String toString() {
        String str = "";
        str += "Set numBuckets: " + numBuckets + "\n";
        str += "Set size: " + size + "\n";
        for (List<E> list : arr) {
            str += list.toString() + "\n";
        }
        return str;
    }

}
