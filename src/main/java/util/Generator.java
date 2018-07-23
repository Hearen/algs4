package util;

import java.util.HashSet;
import java.util.Set;

import lib.StdRandom;

public class Generator {
    private static final int NUMBER_RANGE = 10_000;

    public static int[] generateRandomUniqueArrays(int size, int low, int high) {
        Set<Integer> intSet = new HashSet<>();
        while (intSet.size() < size) {
            intSet.add(StdRandom.uniform(low, high));
        }
        return intSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public static int[] generateRandomSizeArrays(int minSize, int maxSize) {
        int N = StdRandom.uniform(minSize, maxSize);
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = StdRandom.uniform(-NUMBER_RANGE, NUMBER_RANGE);
        }
        return arr;
    }
}
