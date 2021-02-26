import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionFind {

    int[] arr;
    int count;

    public UnionFind(int capacity) {
        this.arr = new int[capacity];
        this.count = capacity;
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
        }
    }

    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot != bRoot) {
            arr[aRoot] = bRoot;
            count--;
        }
    }

    public int find(int a) {
        while (a != arr[a]) {
            a = arr[a];
        }
        return a;
    }

    public boolean connected(int a, int b) {
        return find(a) == find(b);
    }

    public int count() {
        return this.count;
    }

    public void print() {
        System.out.println(Arrays.toString(arr));
    }

}
