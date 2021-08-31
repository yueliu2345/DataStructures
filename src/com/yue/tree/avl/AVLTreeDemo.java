package com.yue.tree.avl;

public class AVLTreeDemo {
    public static void main(String[] args) {
        //int[] arr = {4,3,6,5,7,8};
        //int[] arr = { 10, 12, 8, 9, 7, 6 };
        int[] arr = { 10, 11, 7, 6, 8, 9 };
        //创建一个 AVLTree 对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for(int i=0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }
        //遍历
        System.out.println("中序遍历");
        avlTree.infixOrder();
        System.out.println("在平衡处理~~");
        System.out.println("树的高度=" + avlTree.getRoot().height()); //3
        System.out.println("树的左子树高度=" + avlTree.getRoot().leftHeight()); // 2
        System.out.println("树的右子树高度=" + avlTree.getRoot().rightHeight()); // 2
        System.out.println("当前的根结点=" + avlTree.getRoot());//8
    }

}

class AVLTree{
    private Node root;
    public Node getRoot() {
        return root;
    }

    //添加结点的方法
    public void add(Node node) {
        if(root == null) {
            root = node;//如果 root 为空则直接让 root 指向 node
        } else {
            root.addNode(node);
        }
    }

    //中序遍历
    public void infixOrder() {
        if(root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}


class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "Node [value=" + value + "]";
    }
    //左子树的高度
    public int rightHeight(){
        if (right==null) return 0;
        return right.height();
    }
    //右子树的高度
    public int leftHeight(){
        if(left==null) return 0;
        return left.height();
    }
    //当前结点的高度
    public int height() {
        return Math.max(left==null?0:left.height(), right==null?0:right.height())+1;
    }

    //根据值查找结点
    public Node search(int value){
        if (value == this.value) return this;
        else if (value<this.value){
            if (this.left==null) return null;
            else return this.left.search(value);    //向左递归查找
        }else {
            if (this.right==null) return null;
            else return this.right.search(value);   //向右递归查找
        }
    }
    //根据值查找父节点，方便删除
    public Node searchParent(int value){
        if ((this.left!=null&&this.left.value==value)||(this.right!=null&&this.right.value==value)) return this;
        else {
            if (value<this.value&&this.left!=null) return this.left.searchParent(value);
            else if (value>=this.value&&this.right!=null) return this.right.searchParent(value);
            else return null;
        }
    }

    public void addNode(Node node){
        if (node == null) return;
        //判断要添加节点的值和当前节点的值的大小关系
        if (node.value<this.value){
            if (this.left!=null) this.left.addNode(node);   //如果小于当前值，则向左子树递归
            else this.left = node; //如果已经递归到树底部，则添加
        }else {
            if (this.right!=null) this.right.addNode(node); //如果大于当前值，则向右子树递归
            else this.right = node;
        }

        //当添加完一个节点后，如果:右子树的高度-左子树的高度>1，左旋转
        if (this.rightHeight()-this.leftHeight()>1){
            //如果子树为RL型，则需要先右旋后左旋
            if (right!=null&&right.leftHeight()>right.rightHeight())    //先对右子节点进行右旋转
                right.rightRotate();
            leftRotate();   //如果为RR型直接左旋即可
            return;
        }
        if (this.leftHeight()-rightHeight()>1){
            //如果子树为LR型，则需要先左旋有右旋
            if (left!=null&&left.rightHeight()>left.leftHeight())
                left.leftRotate();
            rightRotate();  //如果为LL型则直接右旋即可
            return;
        }
    }

    //中序遍历
    public void infixOrder(){
        if (this.left!=null) this.left.infixOrder();
        System.out.println(this.value);
        if (this.right!=null) this.right.infixOrder();
    }

    //当前树的左旋转
    public void leftRotate(){
        //创建新的节点，使用当前结点的value
        Node newNode = new Node(this.value);
        //将新节点左子树设置为本节点的左子树
        newNode.left = this.left;
        //将新节点的右子树设置为当前节点的右子树的左子树
        newNode.right = this.right.left;
        //把当前节点的值转换为右子节点的值
        this.value = this.right.value;
        //把当前节点的右子树设置为右子树的右子树
        this.right = this.right.right;
        //把当前节点的左子树指向新节点
        this.left = newNode;
    }
    //当前树的右旋转(将左旋转的代码left与right置换即可)
    public void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = this.right;
        newNode.left = this.left.right;
        this.value = left.value;
        this.left = this.left.left;
        right = newNode;
    }



}