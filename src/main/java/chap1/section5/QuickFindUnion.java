package chap1.section5;

public class QuickFindUnion extends FindUnion {
    int[] ids;
    int count;
    public QuickFindUnion(int n) {
        ids = new int[n];
        count = n;
    }
    public int find(int p) {
        return ids[p];
    }

    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return ;
        for (int i = 0; i < ids.length; ++i) {
            if (rootP == ids[i]) {
                ids[i] = rootQ;
            }
        }
        count--;
    }

    public int count() {
        return count;
    }
}
