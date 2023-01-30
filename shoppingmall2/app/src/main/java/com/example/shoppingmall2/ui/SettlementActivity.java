package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.SettlementOrderListItemAdapter;
import com.example.shoppingmall2.vo.Address;
import com.example.shoppingmall2.vo.Orders;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;

public class SettlementActivity extends BaseActivity implements View.OnClickListener{

    private TextView textView5_total_amount,textView4_username,textView4_phone,textView4_address;
    private Double totalAmount;
    private ArrayList<Orders> orderList = new ArrayList<>();
    private ListView listView2_orders;
    private SettlementOrderListItemAdapter settlementOrderListItemAdapter;
    private Button button_payment;
    private RelativeLayout relativeLayout2_address;
    private static final int GET_ADDRESS_REQUEST_CODE = 0x100;
    private Address userAddress;
    private ArrayList<Address> AddressList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_settlement);

        Intent intent = getIntent();
        totalAmount = intent.getDoubleExtra("totalAmount",0);
        orderList = (ArrayList<Orders>) intent.getSerializableExtra("orderList");

        textView5_total_amount = findViewById(R.id.textView5_total_amount);
        listView2_orders = findViewById(R.id.listView2_orders);
        button_payment = findViewById(R.id.button_payment);
        relativeLayout2_address = findViewById(R.id.relativeLayout2_address);
        textView4_username = findViewById(R.id.textView4_username);
        textView4_phone = findViewById(R.id.textView4_phone);
        textView4_address = findViewById(R.id.textView4_address);

        textView5_total_amount.setText("合计:" + totalAmount + "元");
        settlementOrderListItemAdapter = new SettlementOrderListItemAdapter(this,orderList);
        listView2_orders.setAdapter(settlementOrderListItemAdapter);
        button_payment.setOnClickListener(this);
        relativeLayout2_address.setOnClickListener(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        loadAddressDate();
        super.onResume();
    }

    private void loadAddressDate() {
        _User user = _User.getCurrentUser(_User.class);
        BmobQuery<Address> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.findObjects(new FindListener<Address>() {
            @Override
            public void done(List<Address> list, BmobException e) {
                AddressList = (ArrayList<Address>) list;
                userAddress = AddressList.get(0);
                updateAddressForUI();
            }
        });
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_payment:
                payment();
                break;
            case R.id.relativeLayout2_address:
                getAddress();
                break;
        }
    }

    private void getAddress() {
        startActivityForResult(new Intent(this,SelectAddressActivity.class),GET_ADDRESS_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(GET_ADDRESS_REQUEST_CODE == requestCode && RESULT_OK == resultCode){
            userAddress = (Address) data.getSerializableExtra("address");
            updateAddressForUI();
        }
    }

    private void updateAddressForUI() {
        textView4_username.setText(userAddress.getUsername());
        textView4_phone.setText(userAddress.getPhoneNumber());
        textView4_address.setText(userAddress.getAddress());
    }

    private void payment() {
        for(int i=0;i<orderList.size();i++){
            Orders temp = new Orders();
            temp = orderList.get(i);
            temp.setStatus(2);
            temp.update(new UpdateListener() {
                @Override
                public void done(BmobException e) {
                }
            });
        }
        showToast("购买成功");
        finish();
    }
}
