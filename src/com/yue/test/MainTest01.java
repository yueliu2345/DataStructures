package com.yue.test;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 小美给小团n张数字卡片，问小团在可以任意排列的情况下，能排列出多少种不同的排列，并按字典序写出每种排列方式。
 * 举例：
 * 小美给小团 [1, 2, 3]，则共有6种排列方式，分别是
 * [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], [3, 2, 1]。
 * 小美给小团 [2, 2, 3]，则共有3种排列方式，分别是
 * [2, 2, 3], [2, 3, 2], [3, 2 2]。
 * 我们认为两个排列不同，当且仅当至少存在一个位置pos，两个排列在那个位置的数是不相同的。
 * 我们认为一个排列字典序比另一个字典序小，当且仅当存在一个位置pos，在pos之前两个排列所有的数字相同，而前者在那个位置的数比后者在那个位置的数小，那么前者字典序比后者小。
 */
//第一行是一个数字n，表示小美一共给了n张数字卡片。
//接下来一行n个空格隔开的正整数。
//对于60%的数据，数字卡片张数1<= n <= 3。
//对于100%的数据，数字卡片张数1<= n <= 6。
//数字卡片上的数字x满足1 <= x <= n。

//第一行输出一个数ans，表示所有不同的排列数。
//下面ans行，每行输出一种排列，且排列需要按字典序输出。
public class MainTest01 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        String input = sc.nextLine();
        String t[] = input.split(" ");
        int arr[] = new int[n];
        int maxPos = (int) (Math.pow(2, n-1)-1);

        int res[][] = new int[maxPos][n];

        for (int i=0;i<n;i++){
            arr[i] = Integer.parseInt(t[i]);
        }
        for (int i=0;i<maxPos;i++){
            for (int j=0;j<n;j++){
                res[i][j] = arr[j];
            }
        }
        System.out.println(Arrays.deepToString(res));
        for (int i=0;i<maxPos;i++){
            for (int j=0;j<n;j++){
                for (int x=j+1;x<n;x++){
                    int temp = res[i][j];
                    res[i][j] = res[i][x];
                    res[i][x] = temp;
                }
            }
        }
        int sum =0;
        System.out.println(Arrays.deepToString(res));
        for (int i=0;i<maxPos;i++){
            for (int j=i+1;j<maxPos;j++){
                for (int x=0;x<n;x++){
                    if (res[i][x]!=res[j][x]){
                        sum++;
                        break;
                    }
                }
            }
        }

        System.out.println(sum);



    }

}
