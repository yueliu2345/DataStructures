package com.yue.stack;

public class Calculator {
    public static void main(String[] args) {
        String expression = "3-2*6-2";
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);
        int index = 0;  //扫描expression的下标
        int num1 = 0;
        int num2 = 0;
        int oper;      //存放操作符
        int res =0;     //存放暂时的运算结果
        char ch = ' ';  //将扫描结果暂存在ch中
        String keepNum = "";  //拼接字符串解决多位数的问题

        while (index<expression.length()){
            //依次获得表达式的每一个字符
            ch = expression.charAt(index);

            //判断字符是否是运算符
            if (operStack.isOper(ch)){
                if (operStack.isEmpty()){//如果是空栈，则直接将运算符入栈
                    operStack.push(ch);
                }else {
                    if (operStack.priority(operStack.peek())>=operStack.priority(ch)){//判断ch与栈顶运算符的优先级谁高
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        oper = operStack.pop();
                        res = operStack.cal(num1,num2,oper);
                        System.out.println(res);
                        numStack.push(res); //将与运算结果放入数栈

                        // 这里不可以直接将运算符进行入栈，因为-和+优先级一样，应该继续算下去
                        // 假如入栈之后，会在后面的while循环中，先算减号，再算前面的加号，单实际上先算后面的减号时，需要减号变加号
                        // 括号前面是减号，括号里面要变号
                        //operStack.push(ch);

                        continue;
                    }else {//如果ch的优先级更高，则将ch压入oper栈中
                        operStack.push(ch);
                    }
                }
            }else {
                //处理多位数的情况
                keepNum+=ch;
                if (index==expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //当下一位是运算符时将keepNum压入栈中，如果不是则继续for循环，前几位数已经保存在了keepNum中
                    if (operStack.isOper(expression.charAt(index+1))){
                        numStack.push(Integer.parseInt(keepNum));
                        keepNum="";
                    }
                }
            }
            index++;
        }

        //表达式扫描完毕之后，顺序从数栈和符号栈取出数和符号进行运算
        while (!operStack.isEmpty()){
            oper = operStack.pop();
            num1 = numStack.pop();
            num2 = numStack.pop();
            res = operStack.cal(num1,num2,oper);
            numStack.push(res); //将与运算结果放入数栈
            System.out.println(res);
        }
        res = numStack.pop();
        System.out.println(expression+"最后的结果为："+res);
    }
}

class ArrayStack2{
    private int maxSize;
    private int[] stack;
    private int top;

    public int getMaxSize() {
        return maxSize;
    }

    public ArrayStack2(int maxSize) {
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

    //返回栈顶的值，不出栈
    public int peek(){
        return stack[top];
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

    //返回运算符的优先级，数字越大优先级越高
    public int priority (int oper){
        if (oper=='*'||oper=='/')
            return 1;
        else if (oper=='+'||oper=='-')
            return 0;
        else
            return -1;
    }

    //判断是否是一个运算符
    public boolean isOper(int val){
        return val=='+'||val=='-'||val=='*'||val=='/';
    }

    //执行计算
    public int cal(int num1,int num2,int oper){
        int res = 0; // res 用于存放计算的结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;// 注意顺序
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
            default:
                break;
        }
        return res;
    }




}