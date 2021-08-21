package com.yue.test;

import java.util.HashSet;

public class Test {
    public static void main(String[] args) {
        HashSet<String> hashSet1 = new HashSet<>();
        HashSet<String> hashSet2 = new HashSet<>();
        hashSet1.add("1");
        hashSet1.add("2");
        hashSet1.add("100");
        hashSet2.add("1");
        hashSet2.add("2");
        hashSet2.add("300");
        hashSet1.retainAll(hashSet2);
        System.out.println(hashSet1);
    }
}
