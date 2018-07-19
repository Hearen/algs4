package chap1.section5;

import chap1.section1.demo.BasicGraphs;

public class FindQuickUnion extends FindUnion {
    int[] ids;
    int counter;
    int visitCount = 0;
    BasicGraphs.VisualAccumulator visualAccumulator;

    public FindQuickUnion(int n) {
        ids = new int[n];
        for (int i = 0; i < n; ++i) ids[i] = i;
        counter = n;
//        visualAccumulator = new BasicGraphs().new VisualAccumulator(n, 100);
    }

    public int find(int p) {
        while (ids[p] != p) {
            p = ids[p];
            visitCount++;
        }
        return p;
    }

    public void union(int p, int q) {
        visitCount = 0;
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP != rootQ) {
            ids[rootQ] = rootP;
            counter--;
        }
//        visualAccumulator.add(visitCount);
    }

    public int count() {
        return counter;
    }
}
