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
import com.example.shoppingmall2.ui.ShorppingCartActivity;
import com.example.shoppingmall2.vo.Orders;

import java.util.ArrayList;
import java.util.Objects;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

public class ShoppingCartListItemAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Orders> orderList;

    public void setOrderList(ArrayList<Orders> orderList) {
        this.orderList = orderList;
    }

    public ShoppingCartListItemAdapter(Context context, ArrayList<Orders> orderList){
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
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_cart_list_item,null);
        }
        final Orders orders = orderList.get(position);
        final ImageView imageView_book = ViewHolder.getView(convertView,R.id.imageView_book,null);
        final TextView textView6_bookName = ViewHolder.getView(convertView,R.id.textView6_bookName,null);
        final TextView textView_total = ViewHolder.getView(convertView,R.id.textView_total,null);
        final TextView textView7_subtotal = ViewHolder.getView(convertView,R.id.textView7_subtotal,null);
        final ImageView button3_reduce = ViewHolder.getView(convertView, R.id.button3_reduce, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = Integer.parseInt(textView_total.getText().toString());
                total--;
                if(total < 1){
                    total = 1;
                }
                orders.setTotal(total);
                orders.setSubtotal(total * orders.getDiscountPrice());
                orders.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                    }
                });
                textView_total.setText(String.valueOf(total));
                textView7_subtotal.setText("￥" + orders.getSubtotal());
                ((ShorppingCartActivity)context).setTotalAmountToUi();
            }
        });
        final ImageView button4_add = ViewHolder.getView(convertView, R.id.button4_add, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int total = Integer.parseInt(textView_total.getText().toString());
                total++;
                if(total > 100){
                    total = 100;
                }
                orders.setTotal(total);
                orders.setSubtotal(total * orders.getDiscountPrice());
                orders.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                    }
                });
                textView_total.setText(String.valueOf(total));
                textView7_subtotal.setText("￥" + orders.getSubtotal());
                ((ShorppingCartActivity)context).setTotalAmountToUi();
            }
        });
        textView6_bookName.setText(orders.getBookName());
        textView7_subtotal.setText("￥"+orders.getSubtotal());
        textView_total.setText(String.valueOf(orders.getTotal()));
        if(Objects.equals(orders.getBookName(), "Java 学习笔记（第8版）")) {
            imageView_book.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(orders.getBookName(), "Android安全攻防权威指南")){
            imageView_book.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(orders.getBookName(), "iOS编程实战")){
            imageView_book.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(orders.getBookName(), "Android应用性能优化")) {
            imageView_book.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(orders.getBookName(), "C语言入门经典（第5版）")) {
            imageView_book.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(orders.getBookName(), "Java编程思想（第4版）")) {
            imageView_book.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(orders.getBookName(), "Effective Java中文版(第2版)")) {
            imageView_book.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术")) {
            imageView_book.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(orders.getBookName(), "精通iOS开发(第6版)")) {
            imageView_book.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer中文版（第5版）")) {
            imageView_book.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer Plus(第6版)")) {
            imageView_book.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(orders.getBookName(), "嗨翻C语言")) {
            imageView_book.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术卷一")) {
            imageView_book.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(orders.getBookName(), "Android编程权威指南")) {
            imageView_book.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(orders.getBookName(), "Head First Java（中文版）")) {
            imageView_book.setImageResource(R.mipmap.a_001);
        }else {
            imageView_book.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }
}
