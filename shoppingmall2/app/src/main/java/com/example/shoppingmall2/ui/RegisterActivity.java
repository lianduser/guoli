package com.example.shoppingmall2.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo._User;
import com.example.shoppingmall2.utils.AppUtils;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends BaseActivity implements View.OnClickListener{

    private Button button3_get_validateNumber, button2_register;
    private EditText editText_phone, editText_password, editText_validate_number;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        button3_get_validateNumber = findViewById(R.id.button3_get_validateNumber);
        button3_get_validateNumber.setOnClickListener(this);
        button2_register = findViewById(R.id.button2_register);
        button2_register.setOnClickListener(this);
        editText_phone = findViewById(R.id.editText_phone);
        editText_password = findViewById(R.id.editText_password);
        editText_validate_number = findViewById(R.id.editText_validate_number);

    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button3_get_validateNumber:
                getValidateNumber();
                break;
            case R.id.button2_register:
                shubmitValidateNumber();
                break;
        }
        AppUtils.hindeInputMethod(view);

    }

    //提交验证码并验证
    private void shubmitValidateNumber() {
        String sms_code = editText_validate_number.getText().toString();
        if(TextUtils.isEmpty(sms_code)){
            showToast("请输入验证码");
        }
        final String phone =  editText_phone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            showToast("请输入电话号码");
        }
        final String password =  editText_password.getText().toString();
        if(TextUtils.isEmpty(password)){
            showToast("请输入密码");
        }
        BmobSMS.verifySmsCode(phone, sms_code, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    _User user = new _User();
                    user.setUsername(phone);
                    user.setMobilePhoneNumber(phone);
                    user.setPassword(password);
                    user.setMobilePhoneNumberVerified(true);
                    user.signUp(new SaveListener<_User>() {
                        @Override
                        public void done(_User o, BmobException e) {
                            if(e == null){
                                showToast("注册成功");
                                finish();
                            }else{
                                showToast("注册失败");
                            }
                        }
                });
                }else{
                    showToast("验证码验证失败");
                }
            }
        });
    }

    //获取验证码
    private void getValidateNumber() {
        String phone = editText_phone.getText().toString();
        if(TextUtils.isEmpty(phone)){
            showToast("请输入电话号码");
            return;
        }
        BmobSMS.requestSMSCode(phone, "商城验证", new QueryListener<Integer>() {
            @Override
            public void done(Integer integer, BmobException e) {
                if(e == null){
                    showToast("发送验证码成功");
                }else {
                    showToast("发送验证码失败");
                }
            }
        });
    }
}