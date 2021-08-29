package com.yue.tree.binarysorttree;

//二叉排序树
public class BinarySortTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5,  9, 1};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环的添加结点到二叉排序树
        for(int i = 0; i< arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }
        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树~");
        binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12
        //测试一下删除叶子结点
        binarySortTree.delNode(7);
        System.out.println("root=" + binarySortTree.getRoot());
        System.out.println("删除结点后");
        binarySortTree.infixOrder();
    }
}

class BinarySortTree{
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

    public Node searchNode(int value){
        if (root==null) return null;
        else return root.search(value);
    }
    public Node searchParent(int value){
        if (root==null) return null;
        else return root.searchParent(value);
    }

    public void delNode(int value){
        if (root==null) return;
        else{
            //获取到要删除的结点
            System.out.println("=====================");
            Node targetNode = searchNode(value);
            if (targetNode==null) return;

            //获取到要删除的结点的父节点方便后续删除
            Node parentNode = searchParent(value);
            //如果要删除的结点是叶子节点
            if (targetNode.left==null&&targetNode.right==null){
                System.out.println("=====================");
                //判断要删除的结点是其父节点的左子节点还是右子节点
                if (parentNode.left!=null&&parentNode.left.value==value) parentNode.left=null;
                else if (parentNode.right!=null&&parentNode.right.value==value) parentNode.right=null;
            }else if(targetNode.left!=null&&targetNode.right!=null){//如果要删除的结点有两棵子树
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;
            }else {//如果要删除的结点只有一颗子树
                //如果要删除的结点有左子结点
                if(targetNode.left != null) {
                    if(parentNode != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parentNode.left.value == value) {
                            parentNode.left = targetNode.left;
                        } else { // targetNode 是 parent 的右子结点
                            parentNode.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { //如果要删除的结点有右子结点
                    if(parentNode != null) {
                        //如果 targetNode 是 parent 的左子结点
                        if(parentNode.left.value == value) {
                            parentNode.left = targetNode.right;
                        } else { //如果 targetNode 是 parent 的右子结点
                            parentNode.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }

        }


    }

    private int delRightTreeMin(Node node) {
        Node target = node;
        //循环的查找左子节点，就会找到最小值
        while (target.left!=null) target=target.left;
        delNode(target.value);
        return target.value;
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
    }

    //中序遍历
    public void infixOrder(){
        if (this.left!=null) this.left.infixOrder();
        System.out.println(this.value);
        if (this.right!=null) this.right.infixOrder();
    }

}