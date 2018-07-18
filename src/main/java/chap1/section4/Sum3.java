package chap1.section4;

import chap1.section1.demo.BinarySearch;
import util.Generator;

public class Sum3 {
    //ToDo: unique numbers in the array is a must prerequisite;
    public static void main(String... args) {
        int N = 1_000;
        int[] arr = Generator.generateRandomUniqueArrays(N, -N, N);
        StopWatch timer;

        timer = new StopWatch();
        int count = basicCount(arr);
        System.out.println(count + " " + timer);

        timer = new StopWatch();
        count = binarySearchCount(arr, 0);
        System.out.println(count + " binary search " + timer);

        timer = new StopWatch();
        count = twoPointerCount(arr, 0);
        System.out.println(count + " two pointer " + timer);

        timer = new StopWatch();
        count = kSum(arr, 3, 0);
        System.out.println(count + " kSum - 3 " + timer);

//        timer = new StopWatch();
//        count = basic4SumCount(arr);
//        System.out.println(count + " 4Sum " + timer);

        timer = new StopWatch();
        count = kSum(arr, 4, 0);
        System.out.println(count + " kSum - 4 " + timer);
    }

    private static int basicCount(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                for (int k = j + 1; k < arr.length; ++k) {
                    if (arr[i] + arr[j] + arr[k] == 0) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    private static int basic4SumCount(int[] arr) {
        int count = 0;
        for (int i = 0; i < arr.length; ++i) {
            for (int j = i + 1; j < arr.length; ++j) {
                for (int k = j + 1; k < arr.length; ++k) {
                    for (int h = k + 1; h < arr.length; ++h) {
                        if (arr[i] + arr[j] + arr[k] + arr[h] == 0) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    private static int binarySearchCount(int[] arr, int k) {
        int[] newArr = Sum2.copyAndSortArr(arr);
        int count = 0;
        for (int i = 0; i < newArr.length; ++i) {
            for (int j = i + 1; j < newArr.length; ++j) {
                if (BinarySearch.rank(k - newArr[i] - newArr[j], newArr) > j) {
                    count++;
                }
            }
        }
        return count;
    }

    private static int twoPointerCount(int[] arr, int k) {
        int[] newArr = Sum2.copyAndSortArr(arr);
        int count = 0;
        for (int i = 0; i < newArr.length; ++i) {
            int l = i + 1;
            int r = newArr.length - 1;
            while (l < r) {
                int a = newArr[i] + newArr[l] + newArr[r];
                if (a > k) r--;
                else if (a < k) l++;
                else {
                    l++;
                    r--;
                    count++;
                }
            }
        }
        return count;
    }

    private static int kSumHelper(int[] arr, int start, int k, int val) {
        int total = 0;
        if (k == 2) {
            int l = start;
            int r = arr.length - 1;
            while (l < r) {
                int a = arr[l] + arr[r];
                if (a > val) r--;
                else if (a < val) l++;
                else {
                    l++;
                    r--;
                    total++;
                }
            }
        } else {
            for (int i = start, len = arr.length - k; i <= len; ++i) {
                total += kSumHelper(arr, i + 1, k - 1, val - arr[i]);
            }
        }
        return total;
    }

    private static int kSum(int[] arr, int k, int val) {
        int count = 0;
        if (k == 1) {
            for (int a : arr) {
                if (a == val) count++;
            }
            return count;
        }
        int[] newArr = Sum2.copyAndSortArr(arr);
        return kSumHelper(newArr, 0, k, val);
    }
}
