package com.yue.linkedlist;

import java.lang.management.MemoryNotificationInfo;
import java.util.Stack;

//双向链表
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

//        doubleLinkedList.addNode(hero1);
//        doubleLinkedList.addNode(hero4);
//        doubleLinkedList.addNode(hero2);
//        doubleLinkedList.addNode(hero3);
        //加入按照编号的顺序
        doubleLinkedList.addNodeByOrder(hero1);
        doubleLinkedList.addNodeByOrder(hero4);
        doubleLinkedList.addNodeByOrder(hero2);
        doubleLinkedList.addNodeByOrder(hero3);

        doubleLinkedList.list();


        doubleLinkedList.updateNode(new HeroNode2(4, "lusir", "兰陵王"));
        doubleLinkedList.deleteNode(4);
        System.out.println(doubleLinkedList.getLength(doubleLinkedList.getHead()));
        doubleLinkedList.list();
    }
}

//定义双向链表
class DoubleLinkedList{
    //初始化头节点
    private HeroNode2 head  = new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //添加节点到单向链表
    public void addNode(HeroNode2 node){
        //因为头节点不能动，所以使用中介节点
        HeroNode2 temp = head;
        while (true){
            if (temp.next==null)
                break;
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
        temp.next = node;
        node.pre = temp;
    }

    //根据no值添加节点到单向链表
    public void addNodeByOrder(HeroNode2 node){
        if (head.next==null)
            System.out.println("链表为空!!!");
        //因为头节点不能动，所以使用中介节点
        HeroNode2 temp = head;
        //将中介节点移动到合适的位置
        while (true){
            if (temp.next==null)
                break;
            else if (temp.next.no>node.no)
                break;
            else if (temp.next.no==node.no){
                System.out.println("编号已存在，无法加入");
                return;
            }
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
        //将节点插入
        node.next = temp.next;
        node.pre = temp;
        if (temp.next!=null)
            temp.next.pre = node;
        temp.next = node;

    }

    public void updateNode(HeroNode2 node){
        if (head.next==null)
            System.out.println("链表为空!!!");
        HeroNode2 temp = head.next;
        //将中介节点移动到合适的位置
        while (true){
            if (temp==null){
                System.out.println("没有该编号节点存在");
                return;
            }
            else if (temp.no==node.no){
                temp.name = node.name;
                temp.nickName = node.nickName;
                return;
            }
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
    }

    //根据编号删除节点
    public void deleteNode(int no){
        if (head.next==null)
            System.out.println("链表为空无法遍历");
        //因为头节点不能动，所以使用中介节点
        HeroNode2 temp = head.next;
        //将中介节点移动到合适的位置
        while (true){
            if (temp==null){
                System.out.println("没有该编号节点存在");
                return;
            }
            else if (temp.no==no){
                temp.pre.next = temp.next;
                if (temp.next!=null)    //加入删除最后一点节点的话，temp的next为null，不判断会报空指针异常
                    temp.next.pre = temp.pre;
                return;
            }
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
    }

    //获得链表的长度
    public int getLength(HeroNode2 head){
        int length =0;
        while (head.next!=null){
            length++;
            head = head.next;
        }
        return length;
    }

    //遍历链表
    public void list(){
        if (head.next==null)
            System.out.println("链表为空无法遍历");
        HeroNode2 last = null;
        //不遍历头节点
        HeroNode2 temp = head.next;
        while(temp!=null){
            System.out.println(temp);
            if (temp.next==null)
                last=temp;
            temp = temp.next;
        }
        //将链表从后向前打印，验证链表的pre节点是否有问题
        while(last.pre!=null){
            System.out.println(last);
            last = last.pre;
        }

    }

}

//定义双向链表的英雄节点
class HeroNode2{
    public int no;          //编号
    public String name;     //名称
    public String nickName; //外号
    public HeroNode2 next;  //指向下一个节点的指针
    public HeroNode2 pre;   //指向前一个节点的指针

    public HeroNode2(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
        this.next = null;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}