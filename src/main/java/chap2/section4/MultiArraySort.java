package chap2.section4;

import static util.Generator.generateArrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.LongSummaryStatistics;
import java.util.Map;
import java.util.stream.Collectors;

import static chap2.section1.SortUtil.isAscended;

import chap1.section4.StopWatch;

public class MultiArraySort {
    enum SortTypeEnum {
        SEQUENTIAL,
        BUILT_IN_LIST,
        MIN_HEAP;
    }
    public static void main(String... args) {
        testArraysSorting();
    }

    private static void testArraysSorting() {
        int testTimes = 10;
        Map<SortTypeEnum, List<Long>> timerMap = new HashMap<>();
        for (int t = 0; t < testTimes; ++t) {
            int N = 5_00;
            int[][] arrs = new int[N][];
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < N; ++i) {
                arrs[i] = generateArrays(N, N + N/2, N, 5 * N, false);
                Arrays.sort(arrs[i]);
                list.addAll(Arrays.stream(arrs[i]).boxed().collect(Collectors.toList()));
            }
            StopWatch timer = new StopWatch();
            int[] arr;
            arr = sortArrs(arrs);
            System.out.println(timer + ": sortArrs" );
            timerMap.computeIfAbsent(SortTypeEnum.SEQUENTIAL, k -> new ArrayList<>()).add(timer.elapsedMillis());
//        System.out.println("sortArrs:" + Arrays.toString(arr));
            assert isAscended(arr) : "Sorting failed!";
            timer = new StopWatch();
            list.sort(Comparator.comparing(Integer::intValue));
            assert  isAscended(list.toArray(new Integer[list.size()]));
            System.out.println(timer + ": built-in sort");
            timerMap.computeIfAbsent(SortTypeEnum.BUILT_IN_LIST, k -> new ArrayList<>()).add(timer.elapsedMillis());
//        System.out.println("built-in sort: " + list);
            timer = new StopWatch();
            arr = sortArrsUsingMinPQ(arrs);
            assert isAscended(arr) : "SortUsingPQ failed!";
            System.out.println(timer + ": sortUsingPQ");
            timerMap.computeIfAbsent(SortTypeEnum.MIN_HEAP, k -> new ArrayList<>()).add(timer.elapsedMillis());
//        System.out.println("sortUsingPQ: " + Arrays.toString(arr));
        }
        timerMap.entrySet().stream().forEach(System.out::println);
        timerMap.entrySet().stream().forEach(entry -> {
            System.out.print(entry.getKey() + ": ");
            System.out.println(entry.getValue().stream().collect(Collectors.summarizingLong(Long::intValue)));
        });
    }

    private static int[] sortArrs(int[][] arrs) {
        int count = arrs.length;
        int[] indexes = new int[count]; // point to the next checking index for each array;
        int totalLen = 0;
        for (int i = 0; i < count; ++i) {
            indexes[i] = 0;
            totalLen += arrs[i].length;
        }
        int[] arr = new int[totalLen];
        for (int i = 0; i < totalLen; ++i) {
            int min = Integer.MAX_VALUE; // record the minimum for the current loop;
            int minIndex = -1; // point to the array with minimum;
            for (int j = 0; j < count; ++j) {
                if (indexes[j] != -1) {
                    if (arrs[j][indexes[j]] < min) {
                        min = arrs[j][indexes[j]];
                        minIndex = j;
                    }
                }
            }
            if (minIndex != -1) {
                arr[i] = min;
                indexes[minIndex]++;
                if (indexes[minIndex] == arrs[minIndex].length) {
                    indexes[minIndex] = -1;
                }
            } else {
                break;
            }
        }
        return arr;
    }

    private static int[] sortArrsUsingMinPQ(int[][] arrs) {
        int count = arrs.length;
        MinPQ<Integer> minPQ = new MinPQ<>(count);
        int[] indexes = new int[count];
        int totalLen = 0;
        for (int i = 0; i < count; ++i) {
            indexes[i] = 0;
            totalLen += arrs[i].length;
            minPQ.insert(i, arrs[i][indexes[i]]);
        }
        int[] arr = new int[totalLen];
        for (int i = 0; i < totalLen; ++i) {
            int minIndex = minPQ.delMin();
            arr[i] = arrs[minIndex][indexes[minIndex]++];
            if (indexes[minIndex] == arrs[minIndex].length) indexes[minIndex] = -1;
            if (indexes[minIndex] != -1) {
                minPQ.insert(minIndex, arrs[minIndex][indexes[minIndex]]);
            }
        }
        return arr;
    }

    static class MinPQ<Key extends Comparable<Key>> {
        int[] qp;
        int[] pq;
        Key[] keys;
        int index;

        public MinPQ(int size) {
            size++; // start with 1 while ignoring 0;
            pq = new int[size];
            qp = new int[size];
            keys = (Key[]) new Comparable[size];
            for (int i = 0; i < size; ++i) {
                qp[i] = -1;
            }
            index = 0;
        }

        public int delMin() {
            int minIndex = pq[1];
            exch(1, index--);
            qp[minIndex] = -1;
            keys[minIndex] = null;
            sink(1);
            return minIndex;
        }

        public void insert(int i, Key key) {
            pq[++index] = i;
            keys[i] = key;
            qp[i] = index;
            swim(index);
        }

        private void swim(int k) {
            while (k > 1 && less(k/2, k)) {
                exch(k/2, k);
                k /= 2;
            }
        }

        private void sink(int k) {
            int j = 2 * k;
            while (j <= index) {
                if (j < index && less(j, j + 1)) j++;
                if (less(k, j)) {
                    exch(k, j);
                    k = j;
                    j *= 2;
                } else break;
            }
        }

        private boolean less(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }
    }
}
