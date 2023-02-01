package com.example.shoppingmall2.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo.Address;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class AddAddressActivity extends BaseActivity{

    private EditText editText_username,editText_phoneNumber,editText_address;
    private Address address;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_address);
        editText_username = findViewById(R.id.editText_username);
        editText_phoneNumber = findViewById(R.id.editText_phoneNumber);
        editText_address = findViewById(R.id.editText_address);
        super.onCreate(savedInstanceState);
    }

    public void backClick(View view) {
        finish();
    }

    public void saveAddressClick(View view) {
        creatAddress(false);
    }

    private void creatAddress(boolean isDefalt){
        _User user = BmobUser.getCurrentUser(_User.class);
        String username = editText_username.getText().toString();
        if(TextUtils.isEmpty(username)){
            showToast("请输入用户名");
            return;
        }
        String phoneNumber = editText_phoneNumber.getText().toString();
        if(TextUtils.isEmpty(phoneNumber)){
            showToast("请输入手机号");
            return;
        }
        String addressinfo = editText_address.getText().toString();
        if(TextUtils.isEmpty(addressinfo)){
            showToast("请输入地址");
            return;
        }
        address = new Address();
        address.setUsername(username);
        address.setPhoneNumber(phoneNumber);
        address.setAddress(addressinfo);
        address.setUserId(user.getObjectId());
        address.setIsDefault(isDefalt);
        address.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    showToast("添加成功");
                    finish();
                }else{
                    showToast("添加失败");
                }
            }
        });
    }

    private Address temp;
    public void saveAndDefaultAddressClick(View view) {
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Address> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.addWhereEqualTo("isDefault",true);
        query.findObjects(new FindListener<Address>() {
            @Override
            public void done(List<Address> list, BmobException e) {
                for (int i=0;i<list.size();i++){
                    temp = list.get(i);
                    temp.setIsDefault(false);
                    temp.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                        }
                    });
                }
            }
        });
        creatAddress(true);
        finish();
    }
}
