package com.example.shoppingmall2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.base.ViewHolder;
import com.example.shoppingmall2.vo.Orders;

import java.util.ArrayList;
import java.util.Objects;

public class SettlementOrderListItemAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Orders> orderList;

    public SettlementOrderListItemAdapter(Context context,ArrayList<Orders> orderList){
        this.context = context;
        this.orderList = orderList;
    }
    @Override
    public int getCount() {
        return orderList.size();
    }

    @Override
    public Object getItem(int position) {
        return orderList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.settlement_order_list_item,null);
        }
        ImageView imageView2_bookImage = ViewHolder.getView(convertView, R.id.imageView2_bookImage,null);
        TextView textView5_bookname = ViewHolder.getView(convertView,R.id.textView5_bookname,null);
        TextView textView5_count_total = ViewHolder.getView(convertView,R.id.textView5_count_total,null);
        TextView textView6_discount_price = ViewHolder.getView(convertView,R.id.textView6_discount_price,null);

        Orders orders = orderList.get(position);
        textView5_bookname.setText(orders.getBookName());
        textView5_count_total.setText("x"+orders.getTotal());
        textView6_discount_price.setText(orders.getDiscountPrice()+"元");

        if(Objects.equals(orders.getBookName(), "Java 学习笔记（第8版）")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(orders.getBookName(), "Android安全攻防权威指南")){
            imageView2_bookImage.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(orders.getBookName(), "iOS编程实战")){
            imageView2_bookImage.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(orders.getBookName(), "Android应用性能优化")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(orders.getBookName(), "C语言入门经典（第5版）")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(orders.getBookName(), "Java编程思想（第4版）")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(orders.getBookName(), "Effective Java中文版(第2版)")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(orders.getBookName(), "精通iOS开发(第6版)")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer中文版（第5版）")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer Plus(第6版)")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(orders.getBookName(), "嗨翻C语言")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术卷一")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(orders.getBookName(), "Android编程权威指南")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(orders.getBookName(), "Head First Java（中文版）")) {
            imageView2_bookImage.setImageResource(R.mipmap.a_001);
        }else {
            imageView2_bookImage.setImageResource(R.mipmap.ic_launcher);
        }


        return convertView;
    }
}
