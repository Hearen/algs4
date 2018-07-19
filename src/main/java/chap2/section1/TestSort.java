package chap2.section1;

import chap1.section4.StopWatch;
import chap2.section2.MergeSort;
import util.Generator;

import java.util.Arrays;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.isAscended;

public class TestSort {
    private static final int TEST_TIMES = 5;
    enum SortTypeEnum {
        INSERTION,
        SELECTION,
        SHELL,
        MERGE;
    }

    private static void testSort(Comparable[] arr, SortTypeEnum typeEnum) {
        Integer[] newArr = (Integer[]) Arrays.copyOf(arr, arr.length);
        StopWatch timer = new StopWatch();
//        System.out.println(Arrays.deepToString(newArr));
        switch (typeEnum) {
            case INSERTION: InsertionSort.sort(newArr);  break;
            case SELECTION: SelectionSort.sort(newArr); break;
            case SHELL: ShellSort.sort(newArr); break;
            case MERGE: MergeSort.sort(newArr); break;
            default: break;
        }
        System.out.println("**** End Of Sort ");
        System.out.println(typeEnum.toString() + " " + timer);
        System.out.println(Arrays.deepToString(newArr));
        assert isAscended(newArr) : typeEnum + " failed!";
    }
    public static void main(String... args) {
        for (int j = 0; j < TEST_TIMES; ++j) {
            int N = 10_000;
            Integer[] arr = Arrays.stream(Generator.generateRandomUniqueArrays(N, 0, N * 5)).boxed().toArray(Integer[]::new);
            Arrays.sort(arr);
            for (int i = 0; i < arr.length / 2; ++i) {
                exch(arr, i, arr.length - i - 1);
            }
            System.out.println(Arrays.deepToString(arr));
            testSort(arr, SortTypeEnum.INSERTION);
            testSort(arr, SortTypeEnum.SELECTION);
            testSort(arr, SortTypeEnum.SHELL);
            testSort(arr, SortTypeEnum.MERGE);
        }
    }
}
