package com.yue.tree.huffmancode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();   //将每一位取ASCII码放入byte数组
        System.out.println(Arrays.toString(contentBytes));
        System.out.println(contentBytes.length); //40
        byte[] huffmanCodesBytes= huffmanZip(contentBytes);
        System.out.println(" 压 缩 后 的 结 果 是 :" + Arrays.toString(huffmanCodesBytes) + " 长 度 = " +
                huffmanCodesBytes.length);
    }

    //使用一个方法，将前面的方法封装起来，便于我们的调用.
         /**
        *
        * @param bytes 原始的字符串对应的字节数组
        * @return 是经过 赫夫曼编码处理后的字节数组(压缩后的数组)
        */
    private static byte[] huffmanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes); //将byte数组根据每个元素出现的次数创建node集合
        Node huffmanTreeRoot = createHuffmanTree(nodes);    //根据每个node的权重(出现的次数)创建哈夫曼树
        getCodes(huffmanTreeRoot, "");  //根据哈夫曼树生成对应每个结点的哈夫曼编码
        byte[] huffmanCodesBytes= zip(bytes,huffmanCodes);  //根据哈夫曼编码将初始字符串对应的byte数组压缩
        return huffmanCodesBytes;
    }

    //编写一个方法，将字符串对应的 byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码 压缩后的byte[]
    /**
     *
     * @param bytes 这时原始的字符串对应的 byte[]
     * @param huffmanCodes 生成的赫夫曼编码 map
     * @return 返回赫夫曼编码处理后的 byte[]
     * 举例： String content = "i like like like java do you like a java"; =》 byte[] contentBytes = content.getBytes();
     * 返 回 的 是 字 符 串"10101000101111111100100..."
     * => 对应的 byte[] huffmanCodeBytes ，即 8 位对应一个 byte,放入到 huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) => byte [推导 10101000=> 10101000 - 1 => 10100111(反码)=> 11011000= -88 ]
     * huffmanCodeBytes[1] = -88
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        StringBuilder stringBuilder = new StringBuilder();
        for (byte b:bytes)
            stringBuilder.append(huffmanCodes.get(b));
        int len = (stringBuilder.length()+7)/8; //要创建的byte数组的大小
        byte huffmanCodeBytes[] = new byte[len];
        int index = 0;
        for (int i=0;i<stringBuilder.length();i+=8){//8位对应一个byte
            String s;
            if (i+8>stringBuilder.length()) //末尾不够8为就直接取i到结尾
                s = stringBuilder.substring(i);
            else
                s = stringBuilder.substring(i,i+8);
            huffmanCodeBytes[index] = (byte) Integer.parseInt(s,2);
            index++;
        }
        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //思路:
    //1. 将赫夫曼编码表存放在 Map<Byte,String> 形式
    // 生 成 的 赫 夫 曼 编 码 表 {32=01, 97=100, 100=11000, 117=11001, 101=1110, 118=11011, 105=101, 121=11010, 106=0010, 107=1111, 108=000, 111=0011}
    static Map<Byte, String> huffmanCodes = new HashMap<Byte,String>();
    //2. 在生成赫夫曼编码表示，需要去拼接路径, 定义一个 StringBuilder 存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 功能：将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     */
    private static void getCodes(Node node, String code){
        stringBuilder.append(code);
        if (node!=null&&node.data==null){//如果是非叶子节点则乡向下递归
            if (node.left!=null) getCodes(node.left, "0");
            if (node.right!=null) getCodes(node.right, "1");
        }else {//如果是叶子节点的话就将哈夫曼编码加到map中
            huffmanCodes.put(node.data, String.valueOf(stringBuilder));
        }
        if (stringBuilder.length()>0) //从叶子节点中回溯到上一层时要将stringBuiler中表示当前结点的code去掉
            stringBuilder.deleteCharAt(stringBuilder.length()-1);
    }

    //判断给顶的byte数组中的各个字母的出现次数，将其创建成node数组，以方便后续生成哈夫曼树
    private static List<Node> getNodes(byte[] bytes){
        List<Node> nodeList = new ArrayList<>();

        Map<Byte,Integer> map = new HashMap<>();
        for (byte b:bytes){
            Integer count = map.get(b);
            if (count==null)
                map.put(b, 1);
            else
                map.put(b, count+1);
        }

        for (Map.Entry<Byte,Integer> entry:map.entrySet()){
            nodeList.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodeList;
    }
    //通过给定的list生成哈夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size()>1){
            Collections.sort(nodes);

            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);

            //将0，1移除，将组成的新的树的结点放入数组
            com.yue.tree.huffmancode.Node newNode = new Node(null,leftNode.weight + rightNode.weight);
            newNode.left = leftNode;
            newNode.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(newNode);
        }
        return nodes.get(0);
    }

    //前序遍历二叉树
    public static void preOrder(Node node){
        System.out.println(node);
        if (node!=null&&node.left!=null) preOrder(node.left);
        if (node!=null&&node.right!=null) preOrder(node.right);
    }

}

class Node implements Comparable<Node>{
    Byte data;
    int weight;
    Node left;
    Node right;
    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
    @Override
    public int compareTo(Node o) {
    // 从小到大排序
        return this.weight - o.weight;
    }
    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }

}
