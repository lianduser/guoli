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


public class OrdersAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Orders> orderList;



    public void setOrderList(ArrayList<Orders> orderList) {
        this.orderList = orderList;
    }

    public OrdersAdapter(Context context, ArrayList<Orders> orderList){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.orders_item,null);
        }
        TextView textView5_subtotal = ViewHolder.getView(convertView,R.id.textView5_subtotal,null);
        TextView textView6_buyDate = ViewHolder.getView(convertView,R.id.textView6_buyDate,null);
        TextView textView7_total = ViewHolder.getView(convertView,R.id.textView7_total,null);
        TextView textView10_status = ViewHolder.getView(convertView,R.id.textView10_status,null);
        TextView textView11_bookname = ViewHolder.getView(convertView,R.id.textView11_bookname,null);
        ImageView imageView3_bookimage = ViewHolder.getView(convertView,R.id.imageView3_bookimage,null);

        Orders orders = orderList.get(position);
        textView5_subtotal.setText("总金额"+ orders.getSubtotal()+"元");
        textView6_buyDate.setText(orders.getBuyDate());
        textView7_total.setText("数量 x"+orders.getTotal());
        String status = "";
        switch (orders.getStatus()){
            case 1:
                status = "未付款";
                break;
            case 2:
                status = "已付款";
                break;
            case 3:
                status = "已发货";
                break;
            case 4:
                status = "已收货";
                break;
            case 5:
                status = "已评价";
                break;
            default:
                break;
        }
        textView10_status.setText(status);
        textView11_bookname.setText(orders.getBookName());
        if(Objects.equals(orders.getBookName(), "Java 学习笔记（第8版）")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(orders.getBookName(), "Android安全攻防权威指南")){
            imageView3_bookimage.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(orders.getBookName(), "iOS编程实战")){
            imageView3_bookimage.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(orders.getBookName(), "Android应用性能优化")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(orders.getBookName(), "C语言入门经典（第5版）")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(orders.getBookName(), "Java编程思想（第4版）")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(orders.getBookName(), "Effective Java中文版(第2版)")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(orders.getBookName(), "精通iOS开发(第6版)")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer中文版（第5版）")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer Plus(第6版)")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(orders.getBookName(), "嗨翻C语言")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(orders.getBookName(), "Java核心技术卷一")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(orders.getBookName(), "Android编程权威指南")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(orders.getBookName(), "Head First Java（中文版）")) {
            imageView3_bookimage.setImageResource(R.mipmap.a_001);
        }else {
            imageView3_bookimage.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }
}
