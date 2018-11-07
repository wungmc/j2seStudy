package com.wung.algorithm;

import java.util.Arrays;

/**
 * 快速排序
 *
 * 原理：
 * 通过一趟扫描将要排序的数据分割成独立的两部分,
 * 其中一部分的所有数据都比另外一部分的所有数据都要小,
 * 然后再按此方法对这两部分数据分别进行快速排序,
 * 整个排序过程可以递归进行,以此达到整个数据变成有序序列
 *
 * http://www.cnblogs.com/kkun/archive/2011/11/23/2260270.html
 * http://bubkoo.com/2014/01/12/sort-algorithm/quick-sort/
 *
 * Created by wung on 2017/2/25.
 */
public class QuickSort {

    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            while (low < high && arr[high] > pivot) {
                high--;
            }
            arr[low] = arr[high];

            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[low] = pivot;
        return low;
    }

    public static void sort(int[] arr, int low, int high) {
        if (arr.length <= 1) {
            return;
        }

        if (low < high) {
            int loc = partition(arr, low, high);
            sort(arr, low, loc - 1);
            sort(arr, loc + 1, high);
        }
    }

    public static void main(String[] args) {
        int[] arr = {6, 2, 4, 1, 5, 9};
        QuickSort.sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }
}
