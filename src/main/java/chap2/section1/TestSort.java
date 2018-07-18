package chap2.section1;

import chap1.section4.StopWatch;
import util.Generator;

import java.util.Arrays;

import static chap2.section1.SortUtil.isAscended;

public class TestSort {
    public static void main(String... args) {
        int N = 10_000;
        StopWatch timer;
        Integer[] newArr;
        Integer[] arr = Arrays.stream(Generator.generateRandomUniqueArrays(N, -N, N)).boxed().toArray(Integer[]::new);
        System.out.println(Arrays.deepToString(arr));

        newArr = Arrays.copyOf(arr, arr.length);
        timer = new StopWatch();
//        System.out.println(Arrays.deepToString(newArr));
        InsertionSort.sort(newArr);
        assert isAscended(newArr) : "insertionSort failed!";
        System.out.println("insertionSort: " + timer);

        newArr = Arrays.copyOf(arr, arr.length);
        timer = new StopWatch();
//        System.out.println(Arrays.deepToString(newArr));
        SelectionSort.sort(newArr);
        assert isAscended(newArr) : "selectionSort failed!";
        System.out.println("selectionSort: " + timer);

        newArr = Arrays.copyOf(arr, arr.length);
        timer = new StopWatch();
        System.out.println(Arrays.deepToString(newArr));
        ShellSort.sort(newArr); // ToDo: make a dive;
        assert isAscended(newArr) : "shellSort failed!";
        System.out.println(Arrays.deepToString(newArr));
        System.out.println("shellSort: " + timer);
    }
}
