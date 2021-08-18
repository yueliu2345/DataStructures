package com.yue.linkedlist;

//约瑟夫问题-环形链表
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);// 加入 5 个小孩节点
        circleSingleLinkedList.showBoy();
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

//环形链表
class CircleSingleLinkedList{
    //指向链表的第一个节点
    private Boy first = null;

    //添加num个小孩节点
    public void addBoy(int num){
        if (num<1)
            throw new RuntimeException("输入值错误！！！");
        Boy curBoy = null;
        for(int i=1;i<=num;i++){
            Boy boy = new Boy(i);
            if (i==1){
                first = boy;
                first.setNext(first);//形成环
                curBoy = first;
            }else{
                boy.setNext(first);
                curBoy.setNext(boy);
                curBoy = boy;
            }
        }
    }
// 根据用户的输入，计算出小孩出圈的顺序
    /**
     *
     * @param startNo
     * 表示从第几个小孩开始数数
     * @param countNum
     * 表示数几下
     * @param nums
     * 表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums) {

        if (nums<1||countNum<1||first==null||startNo<1||startNo>nums)
            throw new RuntimeException("参数有误，从新输入！！");
        //创建辅助指针，指向环形链表的最后一个节点
        Boy helper = first;
        while (helper.getNext()!=first){
            helper = helper.getNext();
        }
        //移动helper和first，使从第startNo个小孩开始数
        for (int i=0;i<(startNo-1);i++){
            helper = helper.getNext();
            first = first.getNext();
        }
        //开始数数，出圈
        while (helper!=first){
            //报数
            for (int i=0;i<(countNum-1);i++){
                helper = helper.getNext();
                first = first.getNext();
            }
            System.out.println("小孩"+ first.getNo() +"出队列：" );
            first = first.getNext();
            helper.setNext(first);

        }
        System.out.println("最后的小孩："+ first.getNo());
    }

    //遍历环形链表
    public void showBoy(){
        if (first==null)
            throw new RuntimeException("链表为空！！！");
        System.out.println("小孩的编号： "+first.getNo());
        //first无法移动，创建中介节点遍历链表
        Boy curBoy = first.getNext();
        //当中介节点再一次回到first时，表示链表遍历完成
        while (curBoy!=first){
            System.out.println("小孩的编号： "+curBoy.getNo());
            curBoy = curBoy.getNext();
        }
    }


}

//创建boy类表示一个节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

