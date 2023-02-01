package com.example.shoppingmall2.ui.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.ui.AddressManagerActivity;
import com.example.shoppingmall2.ui.LoginActivity;
import com.example.shoppingmall2.ui.OrdersActivity;
import com.example.shoppingmall2.vo._User;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;

import cn.bmob.v3.BmobUser;


public class PersonalFragment extends Fragment implements View.OnClickListener{

    private static final int REQUEST_CODE_LOGIN = 0x100;
    private Button textView3_login,button_user_address;
    private TextView textView2_username;
    private LinearLayout exit_layout;
    private _User user = BmobUser.getCurrentUser(_User.class);
    private RoundedImageView imageView_userIcon;
    private LinearLayout all_orders_layout,wait_pay_layout,wait_goodsReceipt_layout,wait_Evaluation_layout;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personal,null);
        textView3_login = view.findViewById(R.id.textView3_login);
        textView3_login.setOnClickListener(this);
        exit_layout = view.findViewById(R.id.exit_layout);
        exit_layout.setOnClickListener(this);
        textView2_username = view.findViewById(R.id.textView2_username);
        button_user_address = view.findViewById(R.id.button_user_address);
        button_user_address.setOnClickListener(this);
        imageView_userIcon = view.findViewById(R.id.imageView_userIcon);
        imageView_userIcon.setOnClickListener(this);
        all_orders_layout = view.findViewById(R.id.all_orders_layout);
        wait_pay_layout = view.findViewById(R.id.wait_pay_layout);
        wait_goodsReceipt_layout = view.findViewById(R.id.wait_goodsReceipt_layout);
        wait_Evaluation_layout = view.findViewById(R.id.wait_Evaluation_layout);
        all_orders_layout.setOnClickListener(this);
        wait_pay_layout.setOnClickListener(this);
        wait_goodsReceipt_layout.setOnClickListener(this);
        wait_Evaluation_layout.setOnClickListener(this);


        return view;
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView3_login:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivityForResult(intent,REQUEST_CODE_LOGIN);
                break;
            case R.id.exit_layout:
                logout();
                break;
            case R.id.button_user_address:
                Intent intent1 = new Intent(getActivity(), AddressManagerActivity.class);
                startActivity(intent1);
                break;
            case R.id.imageView_userIcon:
                setIcon();
                break;
            case R.id.all_orders_layout:
                allOrder();
                break;
            case R.id.wait_pay_layout:
                waitPay();
                break;
            case R.id.wait_goodsReceipt_layout:
                waitGoodsReceipt();
                break;
            case R.id.wait_Evaluation_layout:
                waitEvaluation();
                break;
        }

    }

    private void waitEvaluation() {
        viewOrders(4);
    }

    private void waitGoodsReceipt() {
        viewOrders(3);
    }

    private void waitPay() {
        viewOrders(1);
    }

    private void allOrder() {
        viewOrders(0);
    }

    private void viewOrders(int status){
        if(user == null){
            Toast.makeText(getActivity(), "请登录后再查看订单", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(),LoginActivity.class);
            startActivity(intent);
        }
        Intent intent = new Intent(getActivity(),OrdersActivity.class);
        intent.putExtra("status",status);
        startActivity(intent);
    }

    private void setIcon() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, 2);

    }

    @Override
    public void onResume() {
        super.onResume();
        _User user = BmobUser.getCurrentUser(_User.class);
        updateLoginStatus();
    }

    //退出登录
    private void logout() {
        _User.logOut();
        updateLoginStatus();
        Toast.makeText(getActivity(), "退出成功", Toast.LENGTH_SHORT).show();
    }

    //转换页面派生出来的方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(Activity.RESULT_OK == resultCode && REQUEST_CODE_LOGIN == requestCode){
            updateLoginStatus();
        }
        if(Activity.RESULT_OK == resultCode && 2 == requestCode) {
            if (data != null) {
                Uri uri = data.getData();
                imageView_userIcon.setImageURI(uri);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    //更新状态
    private void updateLoginStatus() {
        _User user = BmobUser.getCurrentUser(_User.class);
        if(user != null){
            exit_layout.setVisibility(View.VISIBLE);
            textView2_username.setVisibility(View.VISIBLE);
            textView3_login.setVisibility(View.GONE);
            button_user_address.setVisibility(View.VISIBLE);
            textView2_username.setText(user.getUsername());
        }else{
            exit_layout.setVisibility(View.GONE);
            textView2_username.setVisibility(View.GONE);
            textView3_login.setVisibility(View.VISIBLE);
            button_user_address.setVisibility(View.GONE);
        }
    }
}
