package com.yue.tree.threadedbinarytree;

public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        //测试一把中序线索二叉树的功能
        HeroNode root = new HeroNode(1, "tom");
        HeroNode node2 = new HeroNode(3, "jack");
        HeroNode node3 = new HeroNode(6, "smith");
        HeroNode node4 = new HeroNode(8, "mary");
        HeroNode node5 = new HeroNode(10, "king");
        HeroNode node6 = new HeroNode(14, "dim");
        //二叉树，后面我们要递归创建, 现在简单处理使用手动创建
        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);
        //测试中序线索化
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();
        //测试: 以 10 号节点测试
        HeroNode leftNode = node4.getLeft();
        HeroNode rightNode = node4.getRight();
        System.out.println("10 号结点的前驱结点是 =" + leftNode); //3
        System.out.println("10 号结点的后继结点是=" + rightNode); //1
        //当线索化二叉树后，能在使用原来的遍历方法
        //threadedBinaryTree.infixOrder();
        System.out.println("使用线索化的方式遍历 线索化二叉树");
        threadedBinaryTree.threadedList(); // 8, 3, 10, 1, 14,
    }

}
class ThreadedBinaryTree{
    //树的根节点
    private HeroNode root;
    //为了实现线索化，需要创建要给指向当前结点的前驱结点的指针
    //在递归进行线索化时，pre 总是保留前一个结点
    private HeroNode pre;

    public HeroNode getRoot() {
        return root;
    }
    //重载一把 threadedNodes 方法
    public void threadedNodes() {
        this.threadedNodes(this.root);
    }
    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //中序遍历线索二叉树
    public void threadedNodes(HeroNode node){
        if (node==null) return;
        if (node.getLeft()!=null) threadedNodes(node.getLeft());
        if (node.getLeft()==null){
            node.setLeft(pre);
            node.setLeftTag(1);
        }
        if (pre!=null&&pre.getRight()==null){
            pre.setRight(node);
            System.out.println(node);
            pre.setRightTag(1);
        }
        //当左子树遍历完之后，将左子树设置为前驱节点
        pre = node;
        if (node.getRight()!=null) threadedNodes(node.getRight());
    }

    //遍历线索化二叉树的方法
    public void threadedList(){
        HeroNode node = root;
        if (node==null) return;
        while (node!=null){
            //循环的找到 leftType == 1 的结点，第一个找到就是 8 结点
            //后面随着遍历而变化,因为当 leftType==1 时，说明该结点是按照线索化
            //处理后的有效结点
            while (node.getLeftTag()==0)
                node = node.getLeft();
            System.out.println(node);
            while (node.getRightTag()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

}


//树的节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;  //左右节点
    private HeroNode right;
    private int leftTag=0;    //标记左节点是否指向前驱节点，1指向前驱节点，指向左节点
    private int rightTag=0;   //标记右节点是否指向后继节点，1指向后继节点，指向右节点

    public int getLeftTag() {
        return leftTag;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public void setLeftTag(int leftTag) {
        this.leftTag = leftTag;
    }

    public int getRightTag() {
        return rightTag;
    }

    public void setRightTag(int rightTag) {
        this.rightTag = rightTag;
    }

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


