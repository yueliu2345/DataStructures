package com.yue.algorithm.kmp;

public class ViolenceMatch {

    public static void main(String[] args) {
        String str1 = "硅硅谷 尚硅谷你尚硅 尚硅谷你尚硅谷你尚硅你好";
        String str2 = "尚硅谷你尚硅你";
        int index = violenceMatch(str1, str2);
        System.out.println("index=" + index);
    }

    //暴力匹配
    public static int violenceMatch(String str1,String str2){
        int len1 = str1.length();
        int len2 = str2.length();
        int i=0,j=0;

        //i指向str1，j指向str2，当有一个检索完毕就跳出for循环
        while (i<len1&&j<len2){
            if (str1.charAt(i)==str2.charAt(j)){
                i++;
                j++;
            }else {
                //不相等就回溯到str2开始的下一位
                i = i - j + 1;
                j=0;
            }
        }
        if (j==str2.length())
            return i-j+1;
        else
            return -1;
    }


}
