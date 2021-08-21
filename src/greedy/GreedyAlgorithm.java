package greedy;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        HashSet<String> hashSet1 = new HashSet<String>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");
        HashSet<String> hashSet2 = new HashSet<String>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");
        HashSet<String> hashSet3 = new HashSet<String>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");
        HashSet<String> hashSet4 = new HashSet<String>();
        hashSet4.add("上海");
        hashSet4.add("天津");
        HashSet<String> hashSet5 = new HashSet<String>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        //allAreas 存放所有的地区
        HashSet<String> allAreas = new HashSet<String>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("杭州");
        allAreas.add("大连");

        //创建 ArrayList, 存放选择的电台集合
        ArrayList<String> selects = new ArrayList<String>();
        //定义一个临时的集合， 在遍历的过程中，存放遍历过程中的电台覆盖的地区和当前还没有覆盖的地区的交集
        HashSet<String> tempSet = new HashSet<String>();
        //定义给 maxKey ， 保存在一次遍历过程中，能够覆盖最大未覆盖的地区对应的电台的 key
        //如果 maxKey 不为 null , 则会加入到 selects
        String maxKey = null;
        while (allAreas.size()>0){
            for(String key:broadcasts.keySet()){
                tempSet.clear();
                HashSet<String> area = broadcasts.get(key);
                tempSet.addAll(area);
                tempSet.retainAll(allAreas);
                if (maxKey!=null) broadcasts.get(maxKey).retainAll(allAreas);
                if (tempSet.size()>0&&(maxKey==null||tempSet.size()>broadcasts.get(maxKey).size())){
                    maxKey = key;
                }
            }
            if(maxKey != null) {
                selects.add(maxKey);
                //将 maxKey 指向的广播电台覆盖的地区，从 allAreas 去掉
                allAreas.removeAll(broadcasts.get(maxKey));
            }
        }
        System.out.println("得到的选择结果是" + selects);//[K1,K2,K3,K5]
    }
}
