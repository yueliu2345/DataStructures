package com.yue.nowcoder;

/**
 * 假定你知道某只股票每一天价格的变动。
 * 你最多可以同时持有一只股票。但你可以无限次的交易（买进和卖出均无手续费）。
 * 请设计一个函数，计算你所能获得的最大收益。
 */
public class Nc134 {

    public static void main(String[] args) {
        int arr[] = {1,2,3,5,2,1,4};
        int i = maxProfit(arr);
        System.out.println(i);
    }

    public static int maxProfit (int[] prices) {

//        int[] mprofit = new int[];
//        mprofit[0]=0;
//        int len = prices.length;
//        if(len<=1) return 0;
//return 0;
///*        for (int i=1;i<len;i++){
//            if ()
//            //mprofit[i] = Math.max(mprofit[i])
//
//        }*/
        return 0;
    }


/*    public static int maxProfit1 (int[] prices,int i) {
        if (i==0) return 0;
        //价格的最大值等于 或者maxProfit1(prices, i-1)+prices[i]-上一次买股票的钱
        int val = ();
        System.out.println(val);
        return val;
    }*/
}
