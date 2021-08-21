package com.yue.graph;

import java.util.ArrayList;
import java.util.Arrays;

//图
public class Graph {

    //构造器，vertexNum顶点个数
    public Graph(int vertexNum){
        vertexList = new ArrayList<>(vertexNum);
        edges = new int[vertexNum][vertexNum];
        numOfEdges = 0;
    }

    public static void main(String[] args) {

        int n = 5; //结点的个数
        String Vertexs[] = {"A", "B", "C", "D", "E"};
        //String Vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};

        Graph graph = new Graph(n);

        for(String vertex: Vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边 A-B A-C B-C B-D B-E
        graph.insertEdge(0, 1, 1); // A-B
        graph.insertEdge(0, 2, 1); //
        graph.insertEdge(1, 2, 1); //
        graph.insertEdge(1, 3, 1); //
        graph.insertEdge(1, 4, 1); //

        graph.showGraph();

    }

    private ArrayList<String> vertexList;   //存储顶点集合
    private int[][] edges;  //存储图对应的邻接矩阵
    private int numOfEdges; //存放边的数目

    //定义给数组 boolean[], 记录某个结点是否被访问
    private boolean[] isVisited;

    //获取顶点个数
    public int getNumOfVertex(){
        return vertexList.size();
    }
    //显示图对应的邻接矩阵
    public void showGraph(){
        System.out.println(Arrays.deepToString(edges));
    }
    //获得边的个数
    public int getNumOfEdges() {
        return numOfEdges;
    }
    //返回结点 i(下标)对应的数据 0->"A" 1->"B" 2->"C"
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }
    //返回v1和v2的权值
    public int getWeight(int v1,int v2){
        return edges[v1][v2];
    }
    //添加结点
    public void insertVertex(String vertex){
        vertexList.add(vertex);
    }
    //添加边
    // @param v1 表示点的下标即使第几个顶点 "A"-"B" "A"->0 "B"->1
    //* @param v2 第二个顶点对应的下标
    //* @param weight 表示
    public void insertEdge(int v1,int v2,int weight){
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }


}
