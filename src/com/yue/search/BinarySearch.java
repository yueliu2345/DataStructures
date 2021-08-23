package com.yue.search;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        // int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
        // System.out.println("resIndex=" + resIndex);
        //int res = binarySearch(arr, 0, arr.length - 1, 1234);
        int res= binarySearchByWhile(arr, 12345);
        System.out.println("res=" + res);
    }

    // 二分查找算法
    /**
     *
     * @param arr   数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param findVal   要查找的值
     * @return 如果找到就返回下标，如果没有找到，就返回 -1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){
        if (left>right) return -1;
        int mid = (left+right)/2;
        if (findVal>arr[mid])   return binarySearch(arr, mid+1, right, findVal);
        else if (findVal<arr[mid]) return binarySearch(arr, left, mid-1, findVal);
        else return mid;
    }
    //while循环完成的二分查找
    //int mid = left + (right - left) /2;可以防止left+right超出int的范围
    //(left+right)>>1==(left+right>>1)/2,但前者比后者运算更快
    public static int binarySearchByWhile(int[] arr, int findVal){
        int left=0,right=arr.length-1;
        while (left<= right){
            int mid =(left+right)>>1;
            if (findVal>arr[mid])   left = mid+1;
            else if (findVal<arr[mid])  right = mid-1;
            else return mid;
        }
        return -1;
    }
}
