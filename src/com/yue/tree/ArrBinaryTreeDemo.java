package com.yue.tree;

public class ArrBinaryTreeDemo {
    public static void main(String[] args) {
        int[] arr = { 1, 2, 3, 4, 5, 6, 7,8 };
        //创建一个 ArrBinaryTree
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder(); // 1,2,4,5,3,6,7
    }
}

class ArrBinaryTree{
    private int arr[];

    public ArrBinaryTree(int[] arr) {
        this.arr = arr;
    }
    public int[] getArr() {
        return arr;
    }

    //重载 preOrder
    public void preOrder() {
        this.preOrder(0);
    }
    //编写一个方法，完成顺序存储二叉树的前序遍历
    /**
     *
     * @param index 数组的下标
     */
    public void preOrder(int index){

        if (arr==null||arr.length==0) throw new RuntimeException("树为空！！！");
        System.out.println(arr[index]);
        if (2*index+1<arr.length) preOrder(2*index+1);
        if (2*index+2<arr.length) preOrder(2*index+2);
    }
    public void infixOrder(int index){
        if (arr==null||arr.length==0) throw new RuntimeException("树为空！！！");

        if (2*index+1<arr.length) preOrder(2*index+1);
        System.out.println(arr[index]);
        if (2*index+2<arr.length) preOrder(2*index+2);
    }
    public void postOrder(int index){
        if (arr==null||arr.length==0) throw new RuntimeException("树为空！！！");
        if (2*index+1<arr.length) preOrder(2*index+1);
        if (2*index+2<arr.length) preOrder(2*index+2);
        System.out.println(arr[index]);
    }
}