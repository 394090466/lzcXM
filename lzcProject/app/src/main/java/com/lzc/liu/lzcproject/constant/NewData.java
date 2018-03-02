package com.lzc.liu.lzcproject.constant;

import com.lzc.liu.lzcproject.bean.SubscrebedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/2/26.
 */

public class  NewData {

    private static ArrayList<String> NewTabList = new ArrayList<>();

    private static ArrayList<SubscrebedBean.DataBeanX.DataBean> DataBeanList = new ArrayList<>();

    public static ArrayList<String> getNewTabList() {
        return NewTabList;

    }

    public static void setNewTabList(List<SubscrebedBean.DataBeanX.DataBean> newlist){
        NewTabList.clear();
        DataBeanList.clear();
        DataBeanList.addAll(newlist);
        for (int i = 0; i < newlist.size(); i++) {
            if (!newlist.get(i).getCategory().equals("news_local")){
                NewTabList.add(newlist.get(i).getName());
            }
        }
    }

    public static ArrayList<SubscrebedBean.DataBeanX.DataBean> getDataBeanList() {
        return DataBeanList;
    }
}
