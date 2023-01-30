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
import cn.bmob.v3.listener.UpdateListener;

public class SelectAddressActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    private ListView listView2_address;
    private AddressListItemAdapter addressListItemAdapter;
    private ArrayList<Address> adderssLists = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.select_address);
        listView2_address = findViewById(R.id.listView2_address);
        listView2_address.setOnItemClickListener(this);

        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        loadAddressDate();
        super.onResume();
    }

    private void loadAddressDate() {
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Address> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.order("-isDefault");
        query.findObjects(new FindListener<Address>() {
            @Override
            public void done(List<Address> list, BmobException e) {
                adderssLists = (ArrayList<Address>) list;
                addressListItemAdapter = new AddressListItemAdapter(SelectAddressActivity.this,adderssLists,R.layout.address_list_item);
                addressListItemAdapter.setAddresses(adderssLists);
                addressListItemAdapter.notifyDataSetChanged();
                listView2_address.setAdapter(addressListItemAdapter);
            }
        });
    }

    public void backClick(View view) {
        backAddress(adderssLists.get(0));
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        view.setSelected(true);
        Address address = adderssLists.get(0);
        address.setIsDefault(false);
        address.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
            }
        });
        address = adderssLists.get(position);
        address.setIsDefault(true);
        address.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
            }
        });
        backAddress(address);
    }

    private void backAddress(Address address) {
        Intent intent = getIntent();
        intent.putExtra("address",address);
        setResult(RESULT_OK, intent);
        finish();
    }

    public void addressManagerClick(View view) {
        Intent intent = new Intent(this,AddressManagerActivity.class);
        intent.putExtra("adderssLists",adderssLists);
        startActivity(intent);
    }
}
