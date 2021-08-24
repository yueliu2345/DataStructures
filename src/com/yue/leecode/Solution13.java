package com.yue.leecode;

import jdk.nashorn.internal.ir.IfNode;

import java.util.HashMap;
import java.util.HashSet;

public class Solution13 {
    public static void main(String[] args) {
//        int iv = romanToInt("MCMXCIV");
//        System.out.println(iv);

        String[] strs = new String[]{"ab","a"};
        Solution14 solution14 = new Solution14();
        String s = solution14.longestCommonPrefix(strs);
        System.out.println(s);

    }

    //I             1
    //V             5
    //X             10
    //L             50
    //C             100
    //D             500
    //M             1000
    public static int romanToInt(String s) {
        int len = s.length();
        int sum = 0;
        for (int i =0;i<len;i++){
            char ch = s.charAt(i);
            if (i==len-1||getValue(ch)>=getValue(s.charAt(i+1)))
                sum+=getValue(ch) ;
            else
                sum-=getValue(ch);
        }
        return sum;
    }

    private static  int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }
}

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 ""。
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 */
class Solution14 {
    //纵向比较，取出第一个字符串的第i位，依次和后续的每个字符串的第i位进行比较
    public String longestCommonPrefix(String[] strs) {
        StringBuffer res = new StringBuffer();
        int len = strs.length;
        int strLen = strs[0].length();

        for (int i=0;i<strLen;i++){
            char temp = strs[0].charAt(i);
            for (int j=1;j<len;j++){
                //如果i==length说明第j个str达到了最大长度，不可能有更长的公共前缀了
                if (i==strs[j].length()||temp!=strs[j].charAt(i)) return String.valueOf(res);
            }
            res.append(strs[0].charAt(i));
        }
        return String.valueOf(res);
    }

    //横向比较，假设公共前缀是第一个字符串，拿第一个字符串依次和后面的字符串求公共前缀，直到遍历完所有的字符串，或者公共前缀为null
    public String longestCommonPrefix01(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        int count = strs.length;
        for (int i = 1; i < count; i++) {
            prefix = longestCommonPrefix(prefix, strs[i]);
            if (prefix.length() == 0) {
                break;
            }
        }
        return prefix;
    }

    public String longestCommonPrefix(String str1, String str2) {
        int length = Math.min(str1.length(), str2.length());
        int index = 0;
        while (index < length && str1.charAt(index) == str2.charAt(index)) {
            index++;
        }
        return str1.substring(0, index);
    }

    //分治法
    /*public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        } else {
            return longestCommonPrefix(strs, 0, strs.length - 1);
        }
    }

    public String longestCommonPrefix(String[] strs, int start, int end) {
        if (start == end) {
            return strs[start];
        } else {
            int mid = (end - start) / 2 + start;
            String lcpLeft = longestCommonPrefix(strs, start, mid);
            String lcpRight = longestCommonPrefix(strs, mid + 1, end);
            return commonPrefix(lcpLeft, lcpRight);
        }
    }

    public String commonPrefix(String lcpLeft, String lcpRight) {
        int minLength = Math.min(lcpLeft.length(), lcpRight.length());
        for (int i = 0; i < minLength; i++) {
            if (lcpLeft.charAt(i) != lcpRight.charAt(i)) {
                return lcpLeft.substring(0, i);
            }
        }
        return lcpLeft.substring(0, minLength);
    }*/

}

