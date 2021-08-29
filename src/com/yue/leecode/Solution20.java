package com.yue.leecode;

import java.util.Stack;

public class Solution20 {
    public static void main(String[] args) {
        int a = '[';
        int b = ']';
        String s = "({{{{}}}))";
        System.out.println(isValid(s));
    }
    //
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i=0;i<s.length();i++){
            char temp = s.charAt(i);
            if (temp=='('||temp=='['||temp=='{')
                stack.push(temp);
            else if ((!stack.isEmpty())&&(Math.abs(temp-stack.peek())<=2))
                stack.pop();
            else
                return false;
        }
        return stack.isEmpty();
    }
}
//合并两个有序链表
class Solution21{

    //思路来自于归并排序，合并两个有序子序列
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode temp = head;
        while(l2!=null&&l1!=null){
            if (l1.val<l2.val){
                temp.next = l1;
                l1 = l1.next;
            }else {
                temp.next = l2;
                l2 = l2.next;
            }
            temp = temp.next;
        }
        temp.next = (l1==null)?l2:l1;
        return head.next;
    }

    //递归方法，很妙
    public ListNode mergeTwoLists02(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        } else if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


 }

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}