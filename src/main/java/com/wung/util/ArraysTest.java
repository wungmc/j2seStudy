package com.wung.util;

import java.util.Arrays;

/**
 * Arrays Test
 * Created by wung on 2016/6/2.
 */
public class ArraysTest {
    public static void main(String[] args) {
        int[] a = {5, 3, 12, 6, 67, 7, 8, 89, 3};
        System.out.println(Arrays.toString(a)); // [5, 3, 12, 6, 67, 7, 8, 89, 3]

        Arrays.sort(a);
        System.out.println(Arrays.toString(a)); // [3, 3, 5, 6, 7, 8, 12, 67, 89]
        // 二分查找要求数组必须是升序的，否则返回结果不可预料。所以在调用 binarySearch 之前要先调用 sort
        System.out.println(Arrays.binarySearch(a, 5)); // 2

        int[] b = Arrays.copyOf(a, a.length);
        System.out.println(Arrays.toString(b)); // [3, 3, 5, 6, 7, 8, 12, 67, 89]

        System.out.println(Arrays.equals(a, b)); // true
        System.out.println(Arrays.equals(new int[]{3,5,1}, new int[]{3, 1, 5})); // false

    }
}
