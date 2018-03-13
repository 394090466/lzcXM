package com.lzc.liu.lzcproject.constant;

import com.blankj.utilcode.util.StringUtils;
import com.lzc.liu.lzcproject.entity.SubscrebedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liu on 2018/2/26.
 */

public class  NewData {

    private static ArrayList<String> NewTabList = new ArrayList<>();

    private static ArrayList<SubscrebedBean.DataBeanX.DataBean> DataBeanList = new ArrayList<>();

    private static String essay_joke = "essay_joke"; //段子

    private static String image_ppmm = "image_ppmm"; //街拍

    private static String image_funny = "image_funny"; //趣图

    private static String jinritemai = "jinritemai"; //特卖

    public static ArrayList<String> getNewTabList() {
        return NewTabList;

    }

    public static void setNewTabList(List<SubscrebedBean.DataBeanX.DataBean> newlist){
        NewTabList.clear();
        DataBeanList.clear();
        DataBeanList.addAll(newlist);
        for (int i = 0; i < newlist.size(); i++) {
            if (newlist.get(i).getCategory().equals("news_local")){
                continue;
            }
            if (StringUtils.equals(newlist.get(i).getCategory(),essay_joke)){
                NewTabList.add(3,newlist.get(i).getName());
                continue;
            }
            if (StringUtils.equals(newlist.get(i).getCategory(),image_funny)){
                NewTabList.add(4,newlist.get(i).getName());
                continue;
            }
            if (StringUtils.equals(newlist.get(i).getCategory(),jinritemai)){
                NewTabList.add(5,newlist.get(i).getName());
                continue;
            }

            if (StringUtils.equals(newlist.get(i).getCategory(),image_ppmm)){
                NewTabList.add(6,newlist.get(i).getName());
                continue;
            }
            NewTabList.add(newlist.get(i).getName());
        }
    }

    public static ArrayList<SubscrebedBean.DataBeanX.DataBean> getDataBeanList() {
        return DataBeanList;
    }

    public static String getEssay_joke() {
        return essay_joke;
    }

    public static String getImage_ppmm() {
        return image_ppmm;
    }

    public static String getImage_funny() {
        return image_funny;
    }

    public static String getJinritemai() {
        return jinritemai;
    }
}
