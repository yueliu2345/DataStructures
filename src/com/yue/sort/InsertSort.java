package com.yue.sort;

import java.util.Arrays;

//插入排序
public class InsertSort {
    public static void main(String[] args) {
        int arr[]={3,9,4,5,-2};
        //提前将len储存好，防止每次for循环都要计算length浪费时间，使用空间换时间
        int len = arr.length;

        //第一位只有一位数字，默认为其有序，从第二位开始
        for (int i=1;i<len;i++){
            int temp = arr[i];  //防止后面数组后移时数据丢失，提前创建中间变量储存数组
            int inserIndex = i-1;  //存放要插入的位置下标

            //在前面的有序部分寻找插入位置
            //insertIndex>=0让数组不越界，当inserIndex=-1时说名要将temp插入到0下标位置
            while(inserIndex>=0&&temp<arr[inserIndex]){
                arr[inserIndex+1] = arr[inserIndex];//将数组后移，腾出插入位置
                inserIndex--;
            }
            arr[inserIndex+1] = temp;

        }
        System.out.println(Arrays.toString(arr));
    }
}
