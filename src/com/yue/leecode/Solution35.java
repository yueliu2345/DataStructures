package com.yue.leecode;

import javax.sound.midi.Soundbank;

public class Solution35 {

    public static void main(String[] args) {
        int arr[] = { 1, 8, 10, 89,1000,1000, 1234 };
        int i = searchInsert02(arr, 11111);
        System.out.println(i);
    }

    public static int searchInsert(int[] nums, int target) {

        int len = nums.length;
        for (int i=0;i<len;i++){
            if (target<=nums[i]) return i;
        }
        return len;
        //return binarySearch(nums, 0, nums.length-1, target);
    }
    public static int searchInsert02(int[] nums, int target) {

        int len = nums.length;
        int left=0;
        int right=len-1;

        while (left<=right){
            int mid = (left+right)/2;
            if (target>nums[mid]){
                left = mid+1;
            }else if (target<nums[mid]){
                right = mid-1;
            }else if (target==nums[mid]){
                return mid;
            }
            System.out.println("mid:"+mid);
        }
        System.out.println("left:"+left);
        System.out.println("right"+right);

        return left;
    }

}
