## 3.数据结构概述

### 3.1线性结构

​	<u>线性结构作为最常用的数据结构，其特点是数据元素之间存在一对一的线性关系</u> 

​	线性结构有两种不同的存储结构，即顺序存储结构(**数组**)和链式存储结构(**链表**)。顺序存储的线性表称为顺序表，顺序表中的存储元素是连续的；链式存储的线性表称为链表，链表中的存储元素不一定是连续的，元素节点中存放数据元素以及相邻元素的地址信息。

​	线性结构常见的有：**数组、队列、链表和栈**

### 3.2非线性结构

非线性结构包括：**二维数组，多维数组，广义表，树结构，图结**

## 4.稀疏数组和队列

### 4.1稀缺数组（SparseArray）

当一个数组中大部分元素为０，或者为同一个值的数组时，可以使用稀疏数组来保存该数组。 

稀疏数组的处理方法是: 

1) 记录数组一共有几行几列，有多少个不同的值 
2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模

![屏幕截图 2021-08-16 195958](算法\稀缺数组.png)

### 4.2队列

队列是一个有序列表，可以用数组或是链表来实现。 

遵循先入先出的原则。即：先存入队列的数据，要先取出。后存入的要后取出

### 4.2.1数组模拟队列

队列本身是有序列表，若使用数组的结构来存储队列的数据，则队列数组的声明如下图, 其中 maxSize 是该队列的最大容量。 

 因为队列的输出、输入是分别从前后端来处理，因此需要两个变量 front 及 rear 分别记录队列前后端的下标， front 会随着数据输出而改变，而 rear 则是随着数据输入而改变。

![数组实现队列](算法\数组实现队列.png)

```java
class ArrayQueue{
    private int maxSize;    //队列的最大长度
    private int front;      //队列头下标，指向队列头的前一个位置，初始为-1
    private int rear;       //队列的尾下标，指向队列尾的数据，初始为-1
    private int arr[];      //存放数据，模拟队列
}
```

**问题分析**：目前数组使用一次就不能用， 没有达到复用。

**优化**：将这个数组使用算法，改进成一个环形的队列。

### 4.2.2数组模拟环形队列

![数组实现环形队列](算法\数组实现环形队列.png)

```java
class CircleArrayQueue{
    private int maxSize;    //队列的最大长度，队列的有效长度为maxSize-1，空出一个空间
    private int front;      //队列头下标，指向队列的第一个数据，初始值为0
    private int rear;       //队列的尾下标，指向队列最后一个数据的后面一位，初始值为0
    private int arr[];      //存放数据，模拟队列
}
```
