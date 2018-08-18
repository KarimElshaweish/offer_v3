package com.example.karim.offer_v3.EntreyData;

import java.util.List;

/**
 * Created by Karim on 8/18/2018.
 */

public class CategoryData {
    public List<String> nameList,coverList,logoList;

    public CategoryData(List<String> nameList, List<String> coverList, List<String> logoList) {
        this.nameList = nameList;
        this.coverList = coverList;
        this.logoList = logoList;
    }
}
