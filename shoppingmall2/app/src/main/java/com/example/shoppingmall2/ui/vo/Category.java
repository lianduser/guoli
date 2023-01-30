package com.example.shoppingmall2.ui.vo;

import cn.bmob.v3.BmobObject;


public class Category extends BmobObject{


    private String categoryName;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


}
