package com.yue.test;

import jdk.nashorn.internal.ir.IfNode;
import sun.print.SunMinMaxPage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Scanner;

public class MainTest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] s = input.split(" ");
        int arr[] = new int[s.length];
        for (int i=0;i<s.length;i++){
            arr[i] = Integer.parseInt(s[i]);
        }
        int M = sc.nextInt();
        int len = arr.length;
        int sum=0;
        for (int i=0;i<len;i++){
            for (int j=i+1;j<len;j++){
                if (arr[i]+arr[j]<=M)
                    sum++;
            }
        }
        System.out.println(sum);



    }

    public static int minSailCost (int[][] input) {

        int x = input[0].length;
        int y = input.length;
        int road[][] = new int[y][x];
        for (int i=0;i<y;i++){
            for (int j=0;j<x;j++){
                if (input[i][j]==2) input[i][j]=-999999;
            }
        }
        int sum = 0;
        for (int i=0;i<y;i++){
            for (int j=0;j<x;j++){
                sum+=input[i][j];
            }
        }
        if (sum<0)
            return -1;
        else return sum;
    }

    public static char findKthBit (int n, int k) {
        return findstr(n).charAt(k-1);
    }

    public static String findstr(int i){
        if (i==1) return "a";
        char Li = (char) (96+i);
        String Si = findstr(i-1);
        String res = Si+Li+reverse(invert(Si));
        return res;
    }

    public static String reverse(String s){
        StringBuffer str = new StringBuffer(s);
        str.reverse();
        return str.toString();
    }

    public static String invert(String x){
        char temp[];
        StringBuffer s = new StringBuffer();
        for (int i=0;i<x.length();i++)
        {
            char c = (char) ('z'-x.charAt(i)+'a');
            s.append(c);
        }
        return s.toString();
    }

}
