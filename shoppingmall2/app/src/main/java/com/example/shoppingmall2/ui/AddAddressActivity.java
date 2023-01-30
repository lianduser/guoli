package com.example.shoppingmall2.ui;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;

public class AddAddressActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_address);
        super.onCreate(savedInstanceState);
    }

    public void backClick(View view) {
        finish();
    }

    public void saveAddressClick(View view) {
    }

    public void saveAndDefaultAddressClick(View view) {
    }
}
