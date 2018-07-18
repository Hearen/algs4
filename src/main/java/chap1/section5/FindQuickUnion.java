package chap1.section5;

public class FindQuickUnion extends FindUnion {
    int[] ids;
    int count;
    public FindQuickUnion(int n) {
        ids = new int[n];
        count = n;
    }

    public int find(int p) {
        while (ids[p] != p) p = ids[p];
        return ids[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            ids[rootP] = rootQ;
            count--;
        }
    }

    public int count() {
        return count;
    }
}
