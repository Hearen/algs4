package chap1.section5;

public abstract class FindUnion {
    abstract void union(int p, int q);
    abstract int find(int p);
    boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
    abstract int count();
}
