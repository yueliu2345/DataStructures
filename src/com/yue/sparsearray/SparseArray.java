package com.yue.sparsearray;

//稀缺数组
//将存放棋盘的二位数组转化为稀缺数组
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11来存放棋盘，0无子，1黑子，2白子
        int chessArr[][] = new int[11][11];
        chessArr[1][2]=1;
        chessArr[2][3]=2;
        chessArr[4][5]=2;
        System.out.println("原始的二维数组：");
        for (int[] row:chessArr){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //存放当前棋盘有多少个棋子
        int sum=0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr[i][j]!=0)
                    sum++;
            }
        }


        System.out.println(sum);
        //存放当前是稀缺数组的第几行
        int count =1;
        int sparseArr[][] = new int[sum+1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr[i][j]!=0){
                    System.out.println(count);
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr[i][j];
                    count++;
                }
            }
        }

        System.out.println();
        System.out.println("得到的稀缺数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        //将稀缺数组转化为二维数组
        //从稀缺数组第一行取得二维数组的大小
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i=1;i<sparseArr.length;i++){
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        System.out.println();
        System.out.println("得到的的二维数组：");
        for (int[] row:chessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


    }
}
