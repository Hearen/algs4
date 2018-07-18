package chap1.section5;

import lib.StdIn;
import lib.StdOut;

public class TestFindUnion {
    enum FindUnionEnum {
        QUICK_FIND,
        QUICK_UNION,
        BALANCED_FIND_UION;
    }
    public static void main(String... args) {
        testFindUnion(FindUnionEnum.QUICK_FIND);
    }

    private static void testFindUnion(FindUnionEnum findUnionEnum) {
        int N = StdIn.readInt();
        FindUnion findUnion = null;
        if (findUnionEnum == FindUnionEnum.QUICK_FIND) findUnion = new QuickFindUnion(N);
        else if (findUnionEnum == FindUnionEnum.QUICK_UNION) findUnion = new FindQuickUnion(N);
        else if (findUnionEnum == FindUnionEnum.BALANCED_FIND_UION) findUnion = new BalancedFindUnion(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (findUnion.isConnected(p, q)) continue;
            findUnion.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(findUnion.count() + " components");
    }
}
