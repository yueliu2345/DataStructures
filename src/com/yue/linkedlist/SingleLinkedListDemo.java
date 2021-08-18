package com.yue.linkedlist;

import com.sun.org.apache.xpath.internal.WhitespaceStrippingElementMatcher;
import com.sun.xml.internal.ws.policy.privateutil.RuntimePolicyUtilsException;

import java.util.Stack;

//单向链表
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

//         singleLinkedList.addNode(hero1);
//         singleLinkedList.addNode(hero4);
//         singleLinkedList.addNode(hero2);
//         singleLinkedList.addNode(hero3);
        //加入按照编号的顺序
        singleLinkedList.addNodeByOrder(hero1);
        singleLinkedList.addNodeByOrder(hero4);
        singleLinkedList.addNodeByOrder(hero2);
        singleLinkedList.addNodeByOrder(hero3);

        singleLinkedList.list();


        singleLinkedList.updateNode(new HeroNode(3, "lusir", "兰陵王"));
        singleLinkedList.deleteNode(2);
        singleLinkedList.list();
        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));

        //打印链表倒数第二个节点
        //System.out.println(singleLinkedList.findLastIndexNode(singleLinkedList.getHead(), 2));

        //将链表逆转
        //singleLinkedList.reversetList(singleLinkedList.getHead());

        //将链表逆序打印
        //singleLinkedList.reversePrint1(singleLinkedList.getHead());
        //singleLinkedList.reversePrint2(singleLinkedList.getHead());

        //singleLinkedList.list();
    }
}


//定义单链表
class SingleLinkedList{
    //初始化头节点
    private HeroNode head  = new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    public void addNode(HeroNode node){
        //因为头节点不能动，所以使用中介节点
        HeroNode temp = head;
        while (true){
            if (temp.next==null)
                break;
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
        temp.next = node;
    }

    //根据no值添加节点到单向链表
    public void addNodeByOrder(HeroNode node){
        //因为头节点不能动，所以使用中介节点
        HeroNode temp = head;
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
        temp.next = node;
    }

    public void updateNode(HeroNode node){
        HeroNode temp = head;
        //将中介节点移动到合适的位置
        while (true){
            if (temp.next==null){
                System.out.println("没有该编号节点存在");
                return;
            }
            else if (temp.next.no==node.no){
                temp.next.name = node.name;
                temp.next.nickName = node.nickName;
                return;
            }
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
    }

    //根据编号删除节点
    public void deleteNode(int no){
        //因为头节点不能动，所以使用中介节点
        HeroNode temp = head;
        //将中介节点移动到合适的位置
        while (true){
            if (temp.next==null){
                System.out.println("没有该编号节点存在");
                return;
            }
            else if (temp.next.no==no){
                temp.next = temp.next.next;
                return;
            }
            else
                //将指针移动到链表的最后一位
                temp = temp.next;
        }
    }

    //获得链表的长度
    public int getLength(HeroNode head){
        int length =0;
        while (head.next!=null){
            length++;
            head = head.next;
        }
        return length;
    }

    //***使用快慢指针实现一次遍历查找链表的倒数第n个节点***
    public HeroNode findLastIndexNode(HeroNode head,int n){
        if (head.next==null)
            throw new RuntimeException("链表为空!!!");
         HeroNode fast = head.next;
         HeroNode slow = head.next;
         while (fast.next!=null){
             //快指针先移动
             fast = fast.next;
             if (n!=1)
                 n--;
             else
                 //当快指针移动了n次时，代表快慢指针相隔n，慢指针可以开始移动，此时当快指针移动到链表的尾部时，慢指针指向的是链表倒数第n个节点
                 slow = slow.next;
             //当快指针已经到达链表尾部时，快慢指针相隔任然小于n，说明n小于了链表的长度
             if (fast.next==null&&n>1)
                 throw new RuntimeException("n大于了链表的长度!!!");
         }
         return slow;
    }

    //头插法实现链表的逆转
    public void reversetList(HeroNode head){
        if (head.next==null||head.next.next==null)
            throw new RuntimeException("链表为空!!!");
        //将原来链表上面的节点依次插到该链表后
        HeroNode revHead = new HeroNode(0, "", "");
        HeroNode temp;
        while (head.next!=null){
            temp = head.next.next;          //temp用来保存与那链表的第二个节点，以防止数据丢失
            head.next.next=revHead.next;    //将原链表的第一个节点的next指向反转链表的第一个节点
            revHead.next = head.next;       //将反转链表的头结点的next指向原链表的第一个节点
            head.next = temp;               //原链表的第一个节点此时已经转移为了反转链表的第一个节点，将原链表的第二个节点作为原链表的第一个节点
        }
        //此处不能head = revHead 因为函数结束之后，revHead作为局部变量会失效
        head.next = revHead.next;
    }

    //递归实现逆序打印链表内容
    public void reversePrint1(HeroNode head){
        if(head.next == null) {
            return;//空链表，不能打印
        }
        //当head.next指向链表尾节点时递归结束
        if (head.next.next==null)
            System.out.println(head.next);
        else{
            reversePrint1(head.next);
            System.out.println(head.next);
        }
    }

    //使用栈Stack逆序打印链表内容
    public void reversePrint2(HeroNode head){
        if(head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode cur = head.next;
        //将链表的所有节点压入栈
        while(cur != null) {
            stack.push(cur);
            cur = cur.next; //cur 后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop()); //stack 的特点是先进后出
        }
    }

    //遍历链表
    public void list(){
        if (head.next==null)
            System.out.println("链表为空无法遍历");
        //不遍历头节点
        HeroNode temp = head.next;
        while(temp!=null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

}

//定义英雄节点
class HeroNode{
    public int no;          //编号
    public String name;     //名称
    public String nickName; //外号
    public HeroNode next;   //指向下一个节点的指针

    public HeroNode(int no, String name, String nickName) {
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