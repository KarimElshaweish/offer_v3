package com.example.karim.offer_v3.Firebase;

import com.example.karim.offer_v3.EntreyData.CategoryData;
import com.example.karim.offer_v3.Model.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Karim on 8/18/2018.
 */

public class GetData {

    public static List<String>GetAllCategory(Map<String,Object> values){
        List<String>categoryList=new ArrayList<>();
        for(Map.Entry<String,Object>entry:values.entrySet()){
            categoryList.add(entry.getKey().toString());
        }
        return  categoryList;
    }
    public static CategoryData SplitCategoryData(Map<String, Object> value) {
        List<String> coverList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        List<String> logoList = new ArrayList<>();
        for (Map.Entry<String, Object> enter1 : value.entrySet()) {
                // Urls.add(Url);
                nameList.add(enter1.getKey());
                logoList.add(((Map) enter1.getValue()).get("logoImage").toString());
                coverList.add((((Map) enter1.getValue()).get("coverImage")).toString());

        }
        return new CategoryData(nameList,coverList,logoList);

    }
}
