package com.zhonghua.shidawei.nhaoyang.activity.home.dummy;

import com.zhonghua.shidawei.nhaoyang.data.model.ArtModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleContent {

    public static final List<ArtModel> ITEMS = new ArrayList<ArtModel>();


    public static final Map<Integer, ArtModel> ITEM_MAP = new HashMap<Integer, ArtModel>();


    private static void addItem(ArtModel item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static void addAll(List<ArtModel> artModels){
        for(ArtModel artModel:artModels){
            addItem(artModel);
        }
    }


}
