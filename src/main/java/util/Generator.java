package util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lib.StdRandom;

public class Generator {

    public static int[] generateRandomUniqueArrays(int size, int low, int high) {
        Set<Integer> intSet = new HashSet<>();
        while(intSet.size() < size) {
            intSet.add(StdRandom.uniform(low, high));
        }
        return intSet.stream().mapToInt(Integer::intValue).toArray();
    }
}
