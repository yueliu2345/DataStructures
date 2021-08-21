package com.yue.dynamic;

import java.util.Arrays;

//动态规划解决背包问题
public class KnapsackProblem {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};//物品的重量
        int[] val = {1500, 3000, 2000}; //物品的价值 这里 val[i] 就是前面讲的 v[i]
        int m = 4; //背包的容量
        int n = val.length;//物品的个数


        //创建二维数组，
        //v[i][j] 表示在前 i 个物品中能够装入容量为 j 的背包中的最大价值
        int[][] v = new int[n+1][m+1];

        //为了记录放入商品的情况，我们定一个二维数组
        int[][] path =new int[n+1][m+1];

        //处理第一行，第一列都设置为0
        for(int i=0;i<v.length;i++) v[i][0]=0;
        for(int i=0;i<v[0].length;i++) v[0][i]=0;

        //当 w[i]> j 时：v[i][j]=v[i-1][j] //当准备加入新增的商品的容量大于当前背包的容量时，就直接使用上一个单元格的装入策略
        //当 j>=w[i]时： v[i][j]=max{v[i-1][j], val[i]+v[i-1][j-w[i]]}
        //j是横行，i是竖行(i-1表示当前是哪个商品)
        for (int i =1;i<v.length;i++){
            for (int j=1;j<v[0].length;j++){
                if (w[i-1]>j) v[i][j]=v[i-1][j];
                else v[i][j]=Math.max(v[i-1][j],val[i-1]+v[i-1][j-w[i-1]]);
            }
        }

        //输出一下 v 看看目前的情况
        for(int i =0; i < v.length;i++) {
            for(int j = 0; j < v[i].length;j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

    }


}
