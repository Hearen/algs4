package chap1.section5;

import chap1.section1.demo.BasicGraphs;

public class QuickFindUnion extends FindUnion {
    int[] ids;
    int counter;
    BasicGraphs.VisualAccumulator visualAccumulator;
    public QuickFindUnion(int n) {
        ids = new int[n];
        for (int i = 0; i < n; ++i) ids[i] = i;
        counter = n;
//        visualAccumulator = new BasicGraphs().new VisualAccumulator(n, 900);
    }
    public int find(int p) {
        return ids[p];
    }

    public void union(int p, int q) {
        int visitCount = 0;
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return ;
        for (int i = 0; i < ids.length; ++i) {
            if (rootP == ids[i]) {
                ids[i] = rootQ;
                visitCount++;
            }
        }
        counter--;
//        visualAccumulator.add(visitCount);
    }

    public int count() {
        return counter;
    }
}
