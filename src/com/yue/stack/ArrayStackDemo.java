package com.yue.stack;

import java.util.Scanner;

public class ArrayStackDemo {

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(4);
        String key = "";
        boolean loop = true; //控制是否退出菜单
        Scanner scanner = new Scanner(System.in);
        while(loop) {
            System.out.println("show: 表示显示栈");
            System.out.println("exit: 退出程序");
            System.out.println("push: 表示添加数据到栈(入栈)");
            System.out.println("pop: 表示从栈取出数据(出栈)");
            System.out.println("请输入你的选择");
            key = scanner.next();
            switch (key) {
                case "show":
                    stack.list();
                    break;
                case "push":
                    System.out.println("请输入一个数");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    try {
                        int res = stack.pop();
                        System.out.printf("出栈的数据是 %d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~~");
    }
}



class ArrayStack{
    private int maxSize;
    private int[] stack;
    private int top;

    public int getMaxSize() {
        return maxSize;
    }

    public ArrayStack(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[maxSize];
        top = -1;
    }

    public boolean isFull(){
        return top==maxSize-1;
    }
    public boolean isEmpty(){
        return top ==-1;
    }

    //入栈
    public void push(int value){
        if (isFull())
            throw new RuntimeException("栈满！！！");
        top++;
        stack[top] = value;
    }
    //出栈
    public int pop(){
        if (isEmpty())
            throw new RuntimeException("栈空！！！");
        top--;
        return stack[top+1];
    }

    //从栈顶开始输出栈内的数据
    public void list(){
        for (int i=top;i>=0;i--)
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
    }


}