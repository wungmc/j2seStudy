package com.wung.algorithm;

import java.util.Arrays;

/**
 * 冒泡排序
 *
     以数组 arr = [5, 1, 4, 2, 8] 为例说明，加粗的数字表示每次循环要比较的两个数字：
     第一次外循环
     ( 5 1 4 2 8 ) → ( 1 5 4 2 8 )， 5 > 1 交换位置
     ( 1 5 4 2 8 ) → ( 1 4 5 2 8 )， 5 > 4 交换位置
     ( 1 4 5 2 8 ) → ( 1 4 2 5 8 )， 5 > 2 交换位置
     ( 1 4 2 5 8 ) → ( 1 4 2 5 8 )， 5 < 8 位置不变
     第二次外循环（除开最后一个元素8，对剩余的序列）
     ( 1 4 2 5 8 ) → ( 1 4 2 5 8 )， 1 < 4 位置不变
     ( 1 4 2 5 8 ) → ( 1 2 4 5 8 )， 4 > 2 交换位置
     ( 1 2 4 5 8 ) → ( 1 2 4 5 8 )， 4 < 5 位置不变
     第三次外循环（除开已经排序好的最后两个元素，可以注意到上面的数组其实已经排序完成，但是程序本身并不知道，所以还要进行后续的循环，直到剩余的序列为 1）
     ( 1 2 4 5 8 ) → ( 1 2 4 5 8 )
     ( 1 2 4 5 8 ) → ( 1 2 4 5 8 )
     第四次外循环（最后一次）
     ( 1 2 4 5 8 ) → ( 1 2 4 5 8 )
 *
 * http://bubkoo.com/2014/01/12/sort-algorithm/bubble-sort/
 *
 * Created by wung on 2017/2/25.
 */
public class BubbleSort {

    public static void sort(int[] arr) {
        int temp;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j+1]) {
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
            System.out.println(Arrays.toString(arr));
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 1, 4, 2, 8};
        System.out.println("before sort:" + Arrays.toString(arr));
        BubbleSort.sort(arr);
        System.out.println("after sort:" + Arrays.toString(arr));
    }
}
