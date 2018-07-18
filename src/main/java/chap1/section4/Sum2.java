package chap1.section4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import chap1.section1.demo.BinarySearch;
import util.Generator;

public class Sum2 {
    public static void main(String... args) {
        int N = 200_000;
        int[] arr = Generator.generateRandomUniqueArrays(N, -N, N);
        int count = 0;
        StopWatch timer;

        timer = new StopWatch();
        count = basicCount(arr);
        System.out.println(count + " using plainSearch " + timer);

        timer = new StopWatch();
        count = hashCount(arr, 0);
        System.out.println(count + " using hash " + timer);

        timer = new StopWatch();
        count = twoPointerCount(arr, 0);
        System.out.println(count + " using twoPointer " + timer);

        timer = new StopWatch();
        count = searchCount(arr, 0);
        System.out.println(count + " using binarySearch " + timer);
    }

    private static int basicCount(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                if (arr[i] + arr[j] == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static int hashCount(int[] arr, int k) {
        Map<Integer, List<Integer>> numListMap = Arrays.stream(arr).boxed().collect(Collectors.groupingBy(l -> l));
        int count = 0;
        for (int l : arr) {
            count += numListMap.getOrDefault(k-l, new ArrayList<>()).size();
        }
        return count / 2;
    }

    // ToDo: unique numbers required;
    public static int searchCount(int[] arr, int k) {
        int[] newArr = copyAndSortArr(arr);
        int count = 0;
        for (int i = 0; i < newArr.length; ++i) {
            if (BinarySearch.rank(k-newArr[i], newArr) > i) {
                count++;
            }
        }
        return count;
    }

    public static int[] copyAndSortArr(int[] arr) {
        StopWatch timer = new StopWatch();
        int[] newArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(newArr);
        System.out.println("Copying and Sorting array " + timer);
        return newArr;
    }

    // ToDo: unique numbers required;
    public static int twoPointerCount(int[] arr, int k) {
        int[] newArr = copyAndSortArr(arr);
        int count = 0;
        int l = 0;
        int r = newArr.length - 1;
        int a = 0;
        while (l < r) {
            a = newArr[l] + newArr[r];
            if (a > k) r--;
            else if (a < k) l++;
            else {
                l++;
                r--;
                count++;
            }
        }
        return count;
    }
}
