package com.yue.sort;

import java.util.Arrays;

//选择排序
public class SelectSort {
    public static void main(String[] args) {
        int arr[]={3,9,-1,10,2};
        //提前将len储存好，防止每次for循环都要计算length浪费时间，使用空间换时间
        int len = arr.length;

        //选择len-1次即可完成排序
        for (int i=0;i<len-1;i++){
            //假如第i个为最大
            int maxIndex = i;
            //将arr[i]与后面的数据依次比较，寻找最小的值的下标
            for (int j=i;j<len;j++)
                if (arr[maxIndex]<arr[j])
                    maxIndex=j;
                //将最小值与第i个值交换位置
            int temp = arr[i];
            arr[i] = arr[maxIndex];
            arr[maxIndex] = temp;
        }

        System.out.println(Arrays.toString(arr));

    }
}
