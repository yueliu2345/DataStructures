package com.yue.nowcoder;

import com.sun.xml.internal.bind.v2.model.core.ID;

import java.rmi.server.UnicastRemoteObject;

/**
 * 一个机器人在m×n大小的地图的左上角（起点）。
 * 机器人每次向下或向右移动。机器人要到达地图的右下角（终点）。
 * 可以有多少种不同的路径从起点走到终点？
 */
public class Nc34 {


    public static int uniquePaths (int m, int n) {
        int map[][] = new int[m][n];
        //初始化数组
            for (int i=0;i<n;i++) map[0][i] = 1;
            for (int i=0;i<m;i++) map[i][0] = 1;

        for(int i=1;i<m;i++)
            for (int j=1;j<n;j++)
                map[i][j] = map[i-1][j]+map[i][j-1];

        return map[m-1][n-1];
        //递归方法
        //if(m==1||n==1) return 1;
        //else return uniquePaths(m-1,n)+uniquePaths(m,n-1);
    }

    public static void main(String[] args) {
        System.out.println(uniquePaths(3, 2));
    }
}
