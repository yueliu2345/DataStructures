package com.yue.tree;

import javax.management.BadBinaryOpValueExpException;

//二叉树
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree = new BinaryTree();
        //创建需要的结点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");
        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(root);

//        System.out.println("前序遍历"); // 1,2,3,5,4
//        binaryTree.preOrder(binaryTree.getRoot());
//        System.out.println("中序遍历");
//        binaryTree.infixOrder(binaryTree.getRoot()); // 2,1,5,3,4
//        System.out.println("后序遍历");
//        binaryTree.postOrder(binaryTree.getRoot()); //2,5,4,3,1

//        System.out.println(binaryTree.preOrderSearch(binaryTree.getRoot(), 4).getName());
//        System.out.println(binaryTree.infixOrderSearch(binaryTree.getRoot(), 4).getName());
//        System.out.println(binaryTree.postOrderSearch(binaryTree.getRoot(), 4).getName());

        System.out.println("删除前,前序遍历");
        binaryTree.preOrder(binaryTree.getRoot()); // 1,2,3,5,4
        binaryTree.delNode(3);
        System.out.println("删除后，前序遍历");
        binaryTree.preOrder(binaryTree.getRoot()); // 1,2,3,4

    }
}

//树
class BinaryTree{
    //树的根节点
    private HeroNode root;

    public HeroNode getRoot() {
        return root;
    }

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(HeroNode node){
        if (node == null) throw new RuntimeException("树为空！！！");
        System.out.println(node.getNo());
        if (node.getLeft()!=null) preOrder(node.getLeft());
        if (node.getRight()!=null) preOrder(node.getRight());
    }
    //中序遍历
    public void infixOrder(HeroNode node){
        if (node == null) throw new RuntimeException("树为空！！！");
        if (node.getLeft()!=null) infixOrder(node.getLeft());
        System.out.println(node.getNo());
        if (node.getRight()!=null) infixOrder(node.getRight());
    }
    //后序遍历
    public void postOrder(HeroNode node){
        if (node == null) throw new RuntimeException("树为空！！！");
        if (node.getLeft()!=null) postOrder(node.getLeft());
        if (node.getRight()!=null) postOrder(node.getRight());
        System.out.println(node.getNo());
    }

    //前序遍历查找
    public HeroNode preOrderSearch(HeroNode node,int no){
        if (node == null) throw new RuntimeException("树为空！！！");
        HeroNode resNode = null;    //假如查找到节点就放到resNode中，以方便判断递归的停止
        if (node.getNo()==no) return node;
        if (node.getLeft()!=null) resNode = preOrderSearch(node.getLeft(),no);
        if (resNode!=null) return resNode;  //在左分支上面查找到结果的话就结束递归，不在进入右分支
        if (node.getRight()!=null)resNode = preOrderSearch(node.getRight(),no);
        if (resNode!=null) return resNode;
        return null;
    }
    //中序遍历查找
    public HeroNode infixOrderSearch(HeroNode node,int no){
        if (node == null) throw new RuntimeException("树为空！！！");
        HeroNode resNode = null;
        if (node.getLeft()!=null) resNode = infixOrderSearch(node.getLeft(),no);
        if (resNode!=null) return resNode;
        if (node.getNo()==no) return node;
        if (node.getRight()!=null)resNode = infixOrderSearch(node.getRight(),no);
        if (resNode!=null) return resNode;
        return null;
    }
    //后序遍历查找
    public HeroNode postOrderSearch(HeroNode node,int no){
        if (node == null) throw new RuntimeException("树为空！！！");
        HeroNode resNode = null;
        if (node.getLeft()!=null) resNode = postOrderSearch(node.getLeft(),no);
        if (resNode!=null) return resNode;
        if (node.getRight()!=null)resNode = postOrderSearch(node.getRight(),no);
        if (resNode!=null) return resNode;
        if (node.getNo()==no) return node;
        return null;
    }

    public void delNode(int no){
        if (root == null) throw new RuntimeException("树为空！！！");
        if (root.getNo() == no) root=null;
        else root.delNode(no);
    }


}

//树的节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  //左右节点
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
        this.left=null;
        this.right=null;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //删除结点
    public void delNode(int no){
        if (this.left!=null){
            if (this.left.getNo()==no){
                this.left=null;
            }else
                this.left.delNode(no);
        }
        if (this.right!=null){
            if (this.right.getNo()==no){
                this.right=null;
            }else
                this.right.delNode(no);
        }

    }

}