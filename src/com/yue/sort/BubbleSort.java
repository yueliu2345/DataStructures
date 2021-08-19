package com.yue.sort;

import java.util.Arrays;

//冒泡排序
public class BubbleSort {
    public static void main(String[] args) {
        int arr[]={3,9,-1,10,2};
        //提前将len储存好，防止每次for循环都要计算length浪费时间，使用空间换时间
        int len = arr.length;
        //标志位，优化冒泡排序
        boolean flag = false;


        //一共需要冒泡len-1次即可完成排序
        for(int i=0;i<len-1;i++){
            //因为上一次冒泡已经将前i为都固定到了正确的位置上，只需要将泡冒到len-1的位置即可
            for (int j=0;j<len-i-1;j++){
                if (arr[j]>arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                    flag=true;
                }
            }
            //如果这一趟冒泡中没有发生位置交换，说明此时数组已经排序完成，退出循环即可
            if (!flag) break;
            flag = false;
            System.out.println(Arrays.toString(arr));
        }

    }
}
