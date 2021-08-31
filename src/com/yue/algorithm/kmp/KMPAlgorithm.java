package com.yue.algorithm.kmp;

import java.util.Arrays;

public class KMPAlgorithm {

    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDAB";
        int[] next = getNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println(kmpSearch(str1, str2, next));
    }

    //KMP字符串匹配算法
    public static int kmpSearch(String str1,String str2,int[] next){
        int len1 = str1.length();
        int len2 = str2.length();
        int i=0,j=0;
        while (i<len1&&j<len2){
            if (str1.charAt(i)==str2.charAt(j)){
                i++;
                j++;
            }else {
                //假如不相等的时候不能立马进行下一位比较，要回退到公共最长前缀的后一位进行比较
                // 一直调整位置，直到相等或者j=0如果再次不相等则开始下一位
                if (j>0) j = next[j-1];
                else i++;
            }
        }
        if (j==str2.length())
            return i-j+1;
        else
            return -1;
    }

    //求目标字符串的next数组
    public static int[] getNext(String str2){
        int[] next = new int[str2.length()];
        int len=0,i=1;  //第一位最长前缀一定为0不需要判断，所以从1开始
        while (i<str2.length()){
            if (str2.charAt(i)==str2.charAt(len)){
                len++;
                next[i] = len;
                i++;
            }else {
                if (len>0) len = next[len-1];   //当不相等时，len取左下方的数值，也就是next[len-1]
                else next[i++] = len;  //仍然不相等就将next[i]置零，判断下一位
            }
        }
        return next;
    }

}
