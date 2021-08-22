package com.yue.recursion;

import java.util.Arrays;
//8皇后问题
public class Queen8 {
    //定义一个 max 表示共有多少个皇后
    int max = 8;
    //定义数组 array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] arr = new int[max];
    static int count = 0;

    public static void main(String[] args) {
        Queen8 queen8 = new Queen8();
        queen8.check(0);
        System.out.printf("一共有%d 解法", count);
    }

    //防入第n个皇后
    public void check(int n){
        if (n==max) {
            count++;
            System.out.println(Arrays.toString(arr));
            return;
        }
        //依次放入皇后
        for (int i=0;i<max;i++){
            arr[n]=i;
            if (judge(n)) {//当判断当前皇后与前面的不冲突时，递归到下一层中，开始放下一个皇后
                arr[n]=i;
                check(n+1);
            }
            //当这一层的八个位置放皇后都冲突时，回溯到上一层的for循环中，移动上一个皇后
        }
    }

    //判断第n个皇后摆放的位置与前面的是否冲突
    //冲突返回false，不冲突返回true
    public boolean judge(int n){
        for (int i=0;i<n;i++){
            //判断与之前的皇后是否冲突
            //Math.abs(n-i)==Math.abs(arr[n]-arr[i])当y-y = x-x时说明为等腰三角形，在同一斜线上
            if (arr[n]==arr[i]||Math.abs(n-i)==Math.abs(arr[n]-arr[i])) return false;
        }
        return true;
    }

}
