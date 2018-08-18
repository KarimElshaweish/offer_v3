package com.example.karim.offer_v3.Model;

/**
 * Created by Karim on 8/17/2018.
 */

public class Category {
    String coverImage,logoImage,categoryName;

    public String GetCoverImage() {
        return coverImage;
    }

    public void SetCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String GetLogoImage() {
        return logoImage;
    }

    public void SetLogoImage(String logoImage) {
        this.logoImage = logoImage;
    }

    public String GetCategoryName() {
        return categoryName;
    }

    public void SetCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
