package chap2.section1;

public class SortUtil {
    public static int less(Comparable a, Comparable b) {
        return a.compareTo(b);
    }

    public static boolean isAscended(Comparable[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            if (less(arr[i], arr[i-1]) < 0) return false;
        }
        return true;
    }

    public static void exch(Comparable[] arr, int i, int j) {
        Comparable t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
