package com.example.cw;

public class Categories_Obj {

    private String categoryName;
    private String categoryDescription;
    private int categoryImage;

    public Categories_Obj(String categoryName, String categoryDescription, int categoryImage) {
        this.categoryName = categoryName;
        this.categoryDescription = categoryDescription;
        this.categoryImage = categoryImage;
    }

    public String getcategoryName() {
        return categoryName;
    }

    public String getcategoryDescription() {
        return categoryDescription;
    }

    public int getCategoryImage() {
        return categoryImage;
    }
}