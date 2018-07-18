package chap1.section5;

public class BalancedFindUnion extends FindUnion {
    int[] ids;
    int[] sizes;
    int count;
    public BalancedFindUnion(int n) {
        ids = new int[n];
        sizes = new int[n];
        count = n;
    }

    public int find(int p) {
        while(ids[p] != p) p = ids[p];
        return p;
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            if (sizes[rootP] > sizes[rootQ]) {
                ids[rootQ] = rootP;
                sizes[rootP] += sizes[rootQ];
            } else {
                ids[rootP] = rootQ;
                sizes[rootQ] += sizes[rootP];
            }
            count--;
        }
    }

    public int count() {
        return count;
    }
}
