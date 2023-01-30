package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo._User;
import com.example.shoppingmall2.utils.AppUtils;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class LoginActivity extends BaseActivity{

    private EditText editText_phone,editText_password;
    private ImageView imageView_show_password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText_phone = findViewById(R.id.editText_phone);
        editText_password = findViewById(R.id.editText_password);
        imageView_show_password = findViewById(R.id.imageView_show_password);
    }

    //返回按钮
    public void backClick(View view) {
        finish();
    }

    //注册按钮
    public void openRegisterClick(View view) {
        Intent intent = new Intent(this,RegisterActivity.class);
        startActivity(intent);
    }

    //忘记密码按钮
    public void forgetPasswordClick(View view) {
    }

    //登录按钮
    public void loginClick(View view) {
        AppUtils.hindeInputMethod(view);
        String phone = editText_phone.getText().toString();
        String password = editText_password.getText().toString();
        if(TextUtils.isEmpty(phone)){
            showToast("请输入手机号");
            return;
        }
        if(TextUtils.isEmpty(password)){
            showToast("请输入密码");
            return;
        }
        _User user = new _User();
        user.setUsername(phone);
        user.setPassword(password);
        user.login(new SaveListener<Object>() {
            @Override
            public void done(Object o, BmobException e) {
                if(e == null){
                    showToast("登录成功");
                    setResult(RESULT_OK);
                    finish();
                }else{
                    showToast("登录失败");
                }
            }
        });


    }

    private boolean showPassword = false;

    //隐藏密码按钮
    public void showHidePasswordClick(View view) {
        if(showPassword){
            imageView_show_password.setSelected(true);
            editText_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            showPassword = false;
        }else{
            imageView_show_password.setSelected(false);
            editText_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
            showPassword = true;
        }
    }
}
