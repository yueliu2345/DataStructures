package com.yue.sort;

import java.util.Arrays;

public class HeapSort {

    public static void main(String[] args) {
        //要求将数组进行升序排序
        int arr[] = {4, 6, 8, 5, 9,1,22,343,-1,7};
        heapSort(arr);
        System.out.println("排序后=" + Arrays.toString(arr));
    }

    public static void heapSort(int arr[]) {
        //将无序的数组组成一个大顶堆
        for (int i=arr.length/2-1;i>=0;i--) //length/2-1表示堆的最后（底层）一个非叶子节点
            adjustHeap(arr, i,arr.length);

        /*
        *   2).将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
            3).重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换
            步骤，直到整个序列有序。
        */
        for(int j = arr.length-1;j>0;j--){
            int temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            //执行完构建堆的for循之后就不需要每次都从底向上构建了，只需要从根节点开始扫描最大值
            adjustHeap(arr, 0, j);
        }
    }

    //将一个数组(二叉树), 调整成一个大顶堆
    /**
     * 功能： 完成 将 以 i 对应的非叶子结点的树调整成大顶堆
     * 举例 int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用 adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5, 4}
     * @param arr 待调整的数组
     * @param i 表示非叶子结点在数组中索引
     * @param lenght 表示对多少个元素继续调整， length 是在逐渐的减少
     */
    public static void adjustHeap(int arr[], int i, int lenght) {
        int temp = arr[i];
        for (int k = 2*i+1;k<lenght;k=2*k+1){
            if (k<lenght-1&&arr[k]<arr[k+1]) k++;   //比较左右俩个子树的大小，哪个大k取哪个
            if (arr[k]>temp){   //当左右子树大于了父节点时，将子节点赋值给子树
                arr[i]=arr[k];
                i=k;    //将i指向k，因为假如改变子树和其父节点的位置时，可能影响到子节点的子节点的位置，所以要继续循环
            }else {
                break;  //位置合适退出循环即可
            }
        }
        arr[i] = temp;
    }
}
