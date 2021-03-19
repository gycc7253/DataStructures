public class Test {

    public static void main(String[] args) {
        UnionFind uf = new UnionFind(11);
        System.out.println(uf.find(9));
        System.out.println(uf.find(10));
        uf.union(9, 10);
        uf.union(2, 3);
        uf.union(2, 9);
        uf.print();
        uf.find(2);
        uf.print();
        System.out.println(uf.connected(3, 10));
    }
}
