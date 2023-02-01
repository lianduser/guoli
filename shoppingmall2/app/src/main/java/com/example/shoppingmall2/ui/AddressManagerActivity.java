package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.AddressListItemAdapter;
import com.example.shoppingmall2.vo.Address;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class AddressManagerActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView2_address;
    private AddressListItemAdapter addressListItemAdapter;
    private ArrayList<Address> adderssLists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_address_manager);
        super.onCreate(savedInstanceState);

        listView2_address = findViewById(R.id.listView2_address);
        listView2_address.setOnItemClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    private void initData() {
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Address> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.order("-isDefault");
        query.findObjects(new FindListener<Address>() {
            @Override
            public void done(List<Address> list, BmobException e) {
                adderssLists = (ArrayList<Address>) list;
                addressListItemAdapter = new AddressListItemAdapter(AddressManagerActivity.this,adderssLists,R.layout.address_list_item2);
                addressListItemAdapter.setAddresses(adderssLists);
                listView2_address.setAdapter(addressListItemAdapter);
                addressListItemAdapter.notifyDataSetChanged();
            }
        });
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this,EditAddressActivity.class);
        intent.putExtra("addresslist",adderssLists.get(position));
        startActivity(intent);
    }

    public void addAddressClick(View view) {
        Intent intent = new Intent(this,AddAddressActivity.class);
        startActivity(intent);
    }
}
