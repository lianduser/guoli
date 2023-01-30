package com.example.shoppingmall2;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;


public class App extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, "6909ad801836737860b395305ed9dcca");
        context = getApplicationContext();
    }
}
