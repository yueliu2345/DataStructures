package com.yue.algorithm.dac;

public class Hanoitower {

    public static void main(String[] args) {
        Hanoitower(4, 'A', 'B', 'C');
    }

    /**
     * @param num       表示第几个盘子
     * @param source    表示从哪个塔出发
     * @param medium    表示移动盘子过程中借助的中介塔
     * @param target    表示要移动到哪个塔
     */
    public static void Hanoitower(int num,char source,char medium,char target){
        if (num==1){
            System.out.println("第 1 个盘从 " + source + "->" +target);
        }else {
            //假如盘子的数量大于一，就将盘子看作两部分，第n个盘子和上面n-1个盘子
            //先将上面n-1个盘子从A移动到B
            //在递归的过程中source和target是一直在变化的
            Hanoitower(num-1, source, target, medium);
            //再将第n个盘子从A移动到C
            System.out.println("第 "+num+" 个盘从 " + source + "->" +target);
            //最后将n-1个盘子从B移动到C
            Hanoitower(num-1, medium, source, target);
        }
    }
}
