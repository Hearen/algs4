package chap2.section1;

import chap1.section4.StopWatch;
import chap2.section2.MergeSort;
import chap2.section3.QuickSort;
import util.Generator;

import java.util.*;
import java.util.stream.Collectors;

import static chap2.section1.SortUtil.exch;
import static chap2.section1.SortUtil.isAscended;

public class TestSort {
    static Map<SortTypeEnum, List<Long>> timerMap = new HashMap<>();
    private static final int TEST_TIMES = 10;
    enum SortTypeEnum {
        INSERTION,
        SELECTION,
        SHELL,
        MERGE,
        QUICK;
    }

    private static void testSort(Comparable[] arr, SortTypeEnum typeEnum) {
        Integer[] newArr = (Integer[]) Arrays.copyOf(arr, arr.length);
        StopWatch timer = new StopWatch();
        System.out.println("Array Size: " + arr.length);
//        System.out.println(Arrays.deepToString(newArr));
        switch (typeEnum) {
            case INSERTION: InsertionSort.sort(newArr);  break;
            case SELECTION: SelectionSort.sort(newArr); break;
            case SHELL: ShellSort.sort(newArr); break;
            case MERGE: MergeSort.sort(newArr); break;
            case QUICK: QuickSort.sort(newArr, QuickSort.SortType.SORT_3_WAY_INTERATION); break;
            default: break;
        }
//        System.out.println("**** End Of Sort ");
//        System.out.println(typeEnum.toString() + " " + timer);
        timerMap.computeIfAbsent(typeEnum, k -> new ArrayList<>()).add(timer.elapsedMillis());
//        System.out.println(Arrays.deepToString(newArr));
        assert isAscended(newArr) : typeEnum + " failed!";
    }
    public static void main(String... args) {
        for (int j = 0; j < TEST_TIMES; ++j) {
            int N = 20_000;
//            Integer[] arr = Arrays.stream(Generator.generateRandomUniqueArrays(N, -N, N)).boxed().toArray(Integer[]::new);
            Integer[] arr = Arrays.stream(Generator.generateRandomSizeArrays(N, N + N / 10)).boxed().toArray(Integer[]::new);
            Arrays.sort(arr); // test the worse case for quick sortBasicRecursion;
            for (int i = 0; i < arr.length / 2; ++i) {
                exch(arr, i, arr.length - i - 1);
            }
//            System.out.println(Arrays.deepToString(arr));
//            testSort(arr, SortTypeEnum.INSERTION);
//            testSort(arr, SortTypeEnum.SELECTION);
//            testSort(arr, SortTypeEnum.SHELL);
//            testSort(arr, SortTypeEnum.MERGE);
            testSort(arr, SortTypeEnum.QUICK);
//            QuickSort.sort(arr, QuickSort.SortType.SORT_3_WAY_RECURSION);
        }
//        timerMap.entrySet().stream().forEach(System.out::println);
        timerMap.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getKey() + ": " + entry.getValue()
                    .stream().collect(Collectors.summarizingLong(Long::longValue)) + "\n");
        });
    }
}
