package com.example.shoppingmall2.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo.Address;
import com.example.shoppingmall2.vo._User;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class EditAddressActivity extends BaseActivity implements View.OnClickListener{
    private Address address;
    private EditText editText_username,editText_phoneNumber,editText_address;
    private Button button_updateAddress,button_delete_address,button_set_default_address;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_address);
        editText_username = findViewById(R.id.editText_username);
        editText_phoneNumber = findViewById(R.id.editText_phoneNumber);
        editText_address = findViewById(R.id.editText_address);
        button_updateAddress = findViewById(R.id.button_updateAddress);
        button_delete_address = findViewById(R.id.button_delete_address);
        button_set_default_address = findViewById(R.id.button_set_default_address);
        button_updateAddress.setOnClickListener(this);
        button_delete_address.setOnClickListener(this);
        button_set_default_address.setOnClickListener(this);
        setAddressText();
        super.onCreate(savedInstanceState);
    }
    private void setAddressText() {
        address = (Address) getIntent().getSerializableExtra("addresslist");
        editText_username.setText(address.getUsername());
        editText_phoneNumber.setText(address.getPhoneNumber());
        editText_address.setText(address.getAddress());
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_updateAddress:
                updateAddress();
                break;
            case R.id.button_delete_address:
                deleteAddress();
                break;
            case R.id.button_set_default_address:
                setDefalutAddress();
                break;
        }
    }

    private void setDefalutAddress() {
        if(address.getIsDefault() == true){
            showToast("此地址为默认地址");
        }
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Address> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.addWhereEqualTo("isDefault",true);
        query.findObjects(new FindListener<Address>() {
            @Override
            public void done(List<Address> list, BmobException e) {
                Address temp;
                for(int i=0;i < list.size();i++){
                    temp = list.get(i);
                    temp.setIsDefault(false);
                    temp.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                        }
                    });
                    address.setIsDefault(true);
                    address.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            showToast("设置默认地址成功");
                            finish();
                        }
                    });

                }
            }
        });

    }

    private void deleteAddress() {

        address.delete(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e == null){
                    showToast("删除成功");
                    finish();
                }else{
                    showToast("删除失败");
                }
            }
        });

    }

    private void updateAddress() {
        String username = editText_username.getText().toString();
        if (TextUtils.isEmpty(username)){
            showToast("请输入收货人姓名");
            return;
        }
        String phoneNumber = editText_phoneNumber.getText().toString();
        if (TextUtils.isEmpty(phoneNumber)){
            showToast("请输入收货人手机号码");
            return;
        }
        String addressInfo = editText_address.getText().toString();
        if (TextUtils.isEmpty(addressInfo)){
            showToast("请输入收货人详细地址");
            return;
        }

        address.setUsername(username);
        address.setPhoneNumber(phoneNumber);
        address.setAddress(addressInfo);
        address.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    showToast("修改成功");
                }else{
                    showToast("修改失败");
                }
            }
        });
        finish();
    }


}
