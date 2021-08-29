package com.yue.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = { 13, 7, 8, 3, 29, 6, 1 };
        Node root = createHuffmanTree(arr);
        //测试一把
        preOrder(root);
    }

    //用数组创建哈夫曼树
    public static Node createHuffmanTree(int arr[]){
        List<Node> nodeList = new ArrayList<>();
        for (int val:arr)
            nodeList.add(new Node(val));

        //不断地挑选出数组中最小的两个组成一个新的树
        while (nodeList.size()>1){
            //排序之后0，1就是权值最小的两个结点
            Collections.sort(nodeList);
            Node leftNode = nodeList.get(0);
            Node rightNode = nodeList.get(1);

            //将0，1移除，将组成的新的树的结点放入数组
            Node newNode = new Node(leftNode.getVal() + rightNode.getVal());
            newNode.setLeft(leftNode);
            newNode.setRight(rightNode);
            nodeList.remove(leftNode);
            nodeList.remove(rightNode);
            nodeList.add(newNode);
        }
        //最后剩下的一个结点即为哈夫曼树的根节点
        return nodeList.get(0);
    }
    //前序遍历二叉树
    public static void preOrder(Node node){
        System.out.println(node);
        if (node!=null&&node.getLeft()!=null) preOrder(node.getLeft());
        if (node!=null&&node.getRight()!=null) preOrder(node.getRight());
    }

}

//实现comparable接口可以比较
class Node implements Comparable<Node>{
    private int val;
    private Node left;  //指向树的左节点
    private Node right; //指向树的右节点
    public Node(int val) {
        this.val = val;
    }


    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "val=" + val +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        return this.val - o.getVal();
    }
}