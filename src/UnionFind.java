import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class UnionFind {

    int[] arr;
    int[] sizearr;
    int count;

    public UnionFind(int capacity) {
        this.arr = new int[capacity];
        this.count = capacity;
        this.sizearr = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            arr[i] = i;
        }
        for (int i = 0; i < capacity; i++) {
            sizearr[i] = 1;
        }
    }

    public void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) {
            return;
        }
        if (sizearr[a] < sizearr[b]) {
            arr[aRoot] = bRoot;
            sizearr[bRoot] += sizearr[aRoot];
        } else {
            arr[bRoot] = aRoot;
            sizearr[aRoot] += sizearr[bRoot];
        }
        count--;
    }

    public int find(int a) {
        int originalA = a;
        while (a != arr[a]) {
            a = arr[a];
        }
        arr[originalA] = a;
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
