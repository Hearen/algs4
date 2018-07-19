package chap1.section5;

import static util.IoUtils.getResourcePath;

import chap1.section4.StopWatch;
import lib.In;
import lib.StdOut;

public class TestFindUnion {
    private final static String SMALL_FILE = "tinyUF.txt"; // 2 independent areas;
    private final static String MEDIUM_FILE = "mediumUF.txt"; // 3 independent areas;
    private final static String LARGE_FILE = "largeUF.txt"; // 6 independent areas;

    public static void main(String... args) {
        testFindUnion(FindUnionEnum.QUICK_FIND);
        testFindUnion(FindUnionEnum.QUICK_UNION);
        testFindUnion(FindUnionEnum.BALANCED_FIND_UION);
    }

    private static void testFindUnion(FindUnionEnum findUnionEnum) {
        int[] arr = In.readInts(getResourcePath(LARGE_FILE));
        int i = 0;
        int N = arr[i++];
        FindUnion findUnion = null;
        if (findUnionEnum == FindUnionEnum.QUICK_FIND) findUnion = new QuickFindUnion(N);
        else if (findUnionEnum == FindUnionEnum.QUICK_UNION) findUnion = new FindQuickUnion(N);
        else if (findUnionEnum == FindUnionEnum.BALANCED_FIND_UION) findUnion = new BalancedFindUnion(N);
        StopWatch timer = new StopWatch();
        while (i < arr.length) {
            int p = arr[i++];
            int q = arr[i++];
            if (findUnion.isConnected(p, q)) continue;
            findUnion.union(p, q);
//            StdOut.println("Connecting " + p + " " + q);
        }
        StdOut.println(findUnion.count() + " independent areas");
        StdOut.println(timer);
    }

    enum FindUnionEnum {
        QUICK_FIND,
        QUICK_UNION,
        BALANCED_FIND_UION;
    }
}
