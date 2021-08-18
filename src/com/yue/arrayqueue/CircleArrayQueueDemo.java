package com.yue.arrayqueue;

import java.util.Scanner;

//数组模拟环形队列
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列的案例~~~");
        // 创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4); //说明设置 4, 其队列的有效数据最大是 3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }

}

//数组模拟环形队列
class CircleArrayQueue{
    private int maxSize;    //队列的最大长度
    private int front;      //队列头下标
    private int rear;       //队列的尾下标
    private int arr[];      //存放数据，模拟队列

    //初始化队列
    public CircleArrayQueue(int arrMaxSize){
        this.maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0; // 指向队列头部
        rear = 0; // 指向队列尾的后一个位置
    }

    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }
    public boolean isEmpty(){
        return front==rear;
    }
    //出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，无法出队列");
        }else {
            int value = arr[front];
            //因为是环形队列，当取出尾部(maxsize-1下标)的数字时，取模回到0下标
            front = (front+1)%maxSize;
            return value;
        }
    }
    //进队列
    public void addQueue(int num){
        if (isFull()){
            System.out.println("队列满，无法进队列");
        }else {
            arr[rear]=num;
            rear = (rear+1)%maxSize;
        }
    }
    public void showQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空，无数据");
        }else {
            for (int i = front; i < front+((rear-front+maxSize)%maxSize); i++) {
                System.out.printf("arr[%d]=%d\n", i%maxSize, arr[i%maxSize]);
            }
        }
    }
    // 显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列空的，没有数据~~");
        }
        return arr[front];
    }
}