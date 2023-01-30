package com.example.shoppingmall2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.base.ViewHolder;
import com.example.shoppingmall2.vo.Address;

import java.util.ArrayList;


public class AddressListItemAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<Address> addresses;
    private int list_item_id;


    public void setAddresses(ArrayList<Address> addresses) {
        this.addresses = addresses;
    }

    public AddressListItemAdapter(Context context, ArrayList<Address> addresses,int list_item_id){
        this.context = context;
        this.addresses = addresses;
        this.list_item_id = list_item_id;
    }
    @Override
    public int getCount() {
        return addresses.size();
    }

    @Override
    public Object getItem(int position) {
        return addresses.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(list_item_id,null);
        }
        TextView textView4_username = ViewHolder.getView(convertView, R.id.textView4_username,null);
        TextView textView4_phone = ViewHolder.getView(convertView,R.id.textView4_phone,null);
        TextView textView4_address = ViewHolder.getView(convertView,R.id.textView4_address,null);
        TextView textView4_default = ViewHolder.getView(convertView,R.id.textView4_default,null);

        Address address = addresses.get(position);
        textView4_username.setText(address.getUsername());
        textView4_phone.setText(address.getPhoneNumber());
        textView4_address.setText(address.getAddress());

        if (address.getIsDefault()){
            textView4_default.setVisibility(View.VISIBLE);
        }else{
            textView4_default.setVisibility(View.GONE);
        }

        return convertView;
    }
}
