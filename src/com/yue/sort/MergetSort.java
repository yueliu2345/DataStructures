package com.yue.sort;

import java.util.Arrays;

//归并排序
public class MergetSort {
    public static void main(String[] args) {
        int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2 };
        int temp[] = new int[arr.length];
        mergeSort(arr,0,arr.length-1,temp);
        System.out.println("归并排序后=" + Arrays.toString(arr));
    }

    //分+合方法
    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if(left<right){
            int mid = (left+right)>>1;
            mergeSort(arr, left, mid,temp);
            mergeSort(arr, mid+1, right,temp);
            //分成每个数组只有一个数字时，开始治
            merge(arr, left, mid, right, temp);
        }
    }

    //合并的方法
    /**
     尚硅谷 Java 数据结构和算法
     更多 Java –大数据 –前端 –python 人工智能 -区块链资料下载，可访问百度：尚硅谷官网 第 156页
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i=left;     //指向左边序列
        int j=mid+1;    //指向右边序列
        int t=0;        //指向中介数组

        //依次将两个有序数组合并到中介数组中，当有一边数组合并完之后推出循环
        while(i<=mid&&j<=right)
            if (arr[i]>arr[j])
                temp[t++] = arr[j++];
            else
                temp[t++] = arr[i++];

        //检查两边数组的数是否都已经放到中介数组中，没有的话将其依次放入
        while (i<=mid)
            temp[t++] = arr[i++];
        while (j<=right)
            temp[t++] = arr[j++];

        //将中介数组的内容拷贝到原数组中
        t=0;
        while (left<=right)
            arr[left++] = temp[t++];
    }

}
