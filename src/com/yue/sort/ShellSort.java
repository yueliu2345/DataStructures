package com.yue.sort;


import javax.naming.InsufficientResourcesException;
import java.util.Arrays;

//希尔排序
public class ShellSort {
    public static void main(String[] args) {
        int arr[]={3,9,-1,10,2,6};
        shellSort2(arr);
        System.out.println(Arrays.toString(arr));
    }


    //增强冒泡
    public static void shellSort(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                    // 遍历各组中所有的元素(共 gap 组，每组有个元素), 步长 gap
                for (int j = i - gap; j >= 0; j -= gap) {//每一组使用冒泡排序
                    // 如果当前元素大于加上步长后的那个元素，说明交换
                    if (arr[j] > arr[j + gap]) {
                        int temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }
    //增强插入
    public static void shellSort2(int[] arr){
        //提前将len储存好，防止每次for循环都要计算length浪费时间，使用空间换时间
        int len = arr.length;
        //gap表示分组的数目
        for (int gap = len/2;gap>0;gap/=2)
            for (int i = gap; i< len; i++){
                    int temp = arr[i];
                    int j = i;
                    while (j-gap>=0&&temp<arr[j-gap]){
                        arr[j] = arr[j-gap];
                        j = j-gap;
                    }
                    arr[j] = temp;
                }
            }
    }
