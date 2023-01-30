package com.example.shoppingmall2.utils;


import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.shoppingmall2.App;

public class AppUtils {

    //隐藏输入键盘
    public static void hindeInputMethod(View view){
        InputMethodManager imm = (InputMethodManager) App.context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()){
            imm.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
