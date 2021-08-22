package com.yue.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PolandNotation {
    public static void main(String[] args) {
        //先定义给逆波兰表达式
        //(30+4)×5-6 => 30 4 + 5 × 6 - => 164
        // 4 * 5 - 8 + 60 + 8 / 2 => 4 5 * 8 - 60 + 8 2 / +
        //测试
        //说明为了方便，逆波兰表达式 的数字和符号使用空格隔开
        //String suffixExpression = "30 4 + 5 * 6 -";
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / +"; // 76
        //思路
        //1. 先将 "3 4 + 5 × 6 - " => 放到 ArrayList 中
        //2. 将 ArrayList 传递给一个方法，遍历 ArrayList 配合栈 完成计算
        String expression = "1+((2+3)*4)-5";//注意表达式
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的 List=" + infixExpressionList); // ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的 List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.printf("expression=%d", calculate(suffixExpreesionList)); //
    }

    //将逆波兰表达式转换成数字和符号放入到list中
    public static List<String> getListString(String suffixExpression){
        List<String> list = new ArrayList<>();
        String[] split = suffixExpression.split(" ");
        for (String els:split){
            list.add(els);
        }
        return list;
    }

    //方法：将 中缀表达式转成对应的 List
    // s="1+((2+3)×4)-5";
    public static List<String> toInfixExpressionList(String s) {
        List<String> ls = new ArrayList<>();    //存放中缀表达式
        int len = s.length();
        String str = "";    //用于多位数的拼接
        for (int i=0;i<len;i++){
            char c = s.charAt(i);
            if (c<'0'||c>'9')   ls.add(""+c);
            else {
                str = "";
                while (i<len&&(c=s.charAt(i))>='0'&&c<='9') {   //如果相邻的几位都是数字，，说明是多位数
                    str+=c;
                    i++;
                }
                ls.add(str);
                i--;    //while循环和for重复了i++的操作
            }
        }
        return ls;
    }

    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
    //方法：将得到的中缀表达式对应的 List => 后缀表达式对应的 List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的 Lists2
        //遍历 ls
        for(String item: ls) {
            //如果是一个数，加入 s2
            if(item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出 s1 栈顶的运算符，并压入 s2，直到遇到左括号为止，此时将这一对括号丢弃
                while(!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将 ( 弹出 s1 栈， 消除小括号
            } else {
                //当 item 的优先级小于等于 s1 栈顶运算符, 将 s1 栈顶的运算符弹出并加入到 s2 中，再次转到(4.1)与 s1 中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while(s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item) ) {
                    s2.add(s1.pop());
                }
                //还需要将 item 压入栈
                s1.push(item);
            }
        }
        //将 s1 中剩余的运算符依次弹出并加入 s2
        while(s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2; //注意因为是存放到 List, 因此按顺序输出就是对应的后缀表达式对应的 List
    }

    //完成对逆波兰表达式的运算
    /*
    * 1)从左至右扫描，将 3 和 4 压入堆栈；
    2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
    3)将 5 入栈；
    4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
    5)将 6 入栈；
    6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
    */
    public static int calculate(List<String> ls){
        Stack<String> stack = new Stack();
        for (String item:ls){
            if (item.matches("\\d+")){//使用正则表达式来匹配字符串是否是多位数
                stack.push(item);
            }else {
                //假如不是的话就pop出两个数进行运算，将结果再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if (item.equals("+")) {
                    res = num1 + num2;
                } else if (item.equals("-")) {
                    res = num1 - num2;
                } else if (item.equals("*")) {
                    res = num1 * num2;
                } else if (item.equals("/")) {
                    res = num1 / num2;
                } else {
                    throw new RuntimeException("运算符有误");
                }
                //把 res 入栈
                stack.push("" + res);
            }
        }
        return Integer.parseInt(stack.pop());
    }



}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;
    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}