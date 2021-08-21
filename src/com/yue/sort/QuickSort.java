package com.yue.sort;

import java.util.Arrays;
import java.util.List;

//快速排序
public class QuickSort {
    public static void main(String[] args) {
        int arr[]={5,9,-1,10,2,6};
        quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    //快速排序
    public static void quickSort(int[] arr,int l,int r){
        if (l>=r) return;    //如果数组只有一个数字则退出函数
        int left=l,right=r;
        int pivot = arr[left];  //选择最左边的数字作为中介值
        while (left<right){
            //将右指针逐步向左移动，直到寻找到比中介值小的值，或者右指针移动到和左指针相同
            while (left<right&&arr[right]>=pivot) {
                right--;    //移动指针
            }
            if (left<right)
                arr[left]=arr[right];   //将右指针的值移动到做指针上

            //开始移动左指针，寻找比中介值大的值
            while (left<right&&arr[left]<=pivot){
                left++;     //移动指针
            }
            if (left<right)
                arr[right] = arr[left]; //将左指针的值赋给右指针
        }

        arr[left] = pivot;  //将中介值放到合适的位置
        //System.out.println(Arrays.toString(arr)+"left:"+left+"right:"+right);
        quickSort(arr, l, left-1);  //将中介值左边的部分递归这一过程，由于中介值已经在正确的位置上面了，所以不需要使用left，而是使用left-1，+1
        quickSort(arr, left+1, r);  //将中介值右边的部分递归这一过程
    }
}
