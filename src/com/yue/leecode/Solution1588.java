package com.yue.leecode;

import sun.security.provider.Sun;

import java.util.Arrays;

//给你一个正整数数组 arr ，请你计算所有可能的奇数长度子数组的和。
//子数组 定义为原数组中的一个连续子序列。
//请你返回 arr 中 所有奇数长度子数组的和 。
public class Solution1588 {

    public static void main(String[] args) {
        int arr[] = new int[]{1,4,2,5,3};
        System.out.println(sumOddLengthSubarrays03(arr));
    }

    public static int sumOddLengthSubarrays(int[] arr) {
        int len = arr.length;
        int sum =0;
        for(int i=1;i<=len;i+=2){
            for (int j=0;j<(len+1-i);j++){
                for (int x=j;x<i+j;x++){
                    sum+=arr[x];
                }
            }
        }
        return sum;
    }
    //前缀和方法
    public static int sumOddLengthSubarrays01(int[] arr) {
        int len = arr.length;
        int num[] = new int[len];
        int sum = 0;
        num[0]=arr[0];
        for (int i=1;i<len;i++) num[i] = num[i-1]+arr[i];
        System.out.println(Arrays.toString(num));

        for (int i=1;i<=len;i+=2)
            for (int j=0;j<(len+1-i);j++){
                if (j>0)
                    sum+= (num[j+i-1]-num[j-1]);
                else
                    sum+=num[j+i-1];
            }
        return sum;
    }
    //前缀和方法
    public static int sumOddLengthSubarrays03(int[] arr) {
        int len = arr.length;
        int num[] = new int[len+1];
        int sum = 0;
        for (int i=1;i<=len;i++) num[i] = num[i-1]+arr[i-1];
        System.out.println(Arrays.toString(num));

        for (int i=1;i<=len;i+=2)
            for (int j=0;j<(len+1-i);j++){
                    sum+= (num[j+i]-num[j]);
            }
        return sum;
    }
}
