package com.yue.search;

public class InsertValueSearch {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        int res = insertValueSearch(arr, 0, arr.length - 1, 10);
        System.out.println("res=" + res);
    }

    public static int insertValueSearch(int[] arr,int left,int right,int findVal){

        //防止当查找一个特别大的数时数组越界
        if (left>right||findVal>arr[arr.length-1]||findVal<arr[0]) return -1;
        int mid = left + ((findVal-arr[left])/(arr[right]-arr[left]))*(right-left);;
        System.out.println(mid);
        if (findVal>arr[mid])  return insertValueSearch(arr, mid+1, right, findVal);
        else if (findVal<arr[mid]) return insertValueSearch(arr, left, mid-1, findVal);
        else return mid;
    }

}
