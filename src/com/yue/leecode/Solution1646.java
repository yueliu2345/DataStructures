package com.yue.leecode;

//1646. 获取生成数组中的最大值

import javax.sound.midi.Soundbank;
import java.util.Arrays;

/**
 * 给你一个整数 n 。按下述规则生成一个长度为 n + 1 的数组 nums ：
 * nums[0] = 0
 * nums[1] = 1
 * 当 2 <= 2 * i <= n 时，nums[2 * i] = nums[i]
 * 当 2 <= 2 * i + 1 <= n 时，nums[2 * i + 1] = nums[i] + nums[i + 1]。
 */
public class Solution1646 {
    public static void main(String[] args) {
        Solution1646 solution1646 = new Solution1646();
        System.out.println(solution1646.getMaximumGenerated(3));
    }

    public int getMaximumGenerated(int n) {
        if (n<2) return n;
        int arr[] = new int[n+1];
        arr[0] = 0;
        arr[1]=1;
        int max=0;//当n等于2时
        for (int i=2;i<=n;i++){
            if (i%2==0) arr[i] = arr[i/2];
            else arr[i] = arr[i] = arr[(i-1)/2]+arr[(i-1)/2+1];
            max = Math.max(max,arr[i]);
        }
        return max;
    }
//    public  int getArrNum(int i) {
//        if (i==0) return arr[0];
//        else if (i==1) return arr[1];
//        else if (2*i>=2&&2*i<=n)    return arr[i];
//        else if (2*i+1>=2&&2*i+1<=n) return arr[i]+arr[i+1];
//        return 0;
//    }

}
