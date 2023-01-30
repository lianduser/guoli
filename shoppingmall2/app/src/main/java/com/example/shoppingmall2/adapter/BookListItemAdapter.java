package com.example.shoppingmall2.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo.BookInfo;
import com.example.shoppingmall2.adapter.base.ViewHolder;


import java.util.ArrayList;
import java.util.Objects;

public class BookListItemAdapter extends BaseAdapter {


    private Context context;
    private ArrayList<BookInfo> bookInfos;
    public BookListItemAdapter(Context context,ArrayList<BookInfo> bookInfos){
        this.context = context;
        this.bookInfos = bookInfos;
    }

    @Override
    public int getCount() {
        return bookInfos.size();
    }

    @Override
    public Object getItem(int position) {
        return bookInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.book_list_item,null);
        }
        TextView tv_bookName = ViewHolder.getView(convertView,R.id.textView_bookName,null);
        TextView textView3_star  = ViewHolder.getView(convertView, R.id.textView3_star,null);
        TextView textView5_price  = ViewHolder.getView(convertView, R.id.textView5_price,null);
        TextView textView7_discountPrice  = ViewHolder.getView(convertView, R.id.textView7_discountPrice,null);
        TextView textView8_discount  = ViewHolder.getView(convertView, R.id.textView8_discount,null);
        ImageView imageView_bookImage = ViewHolder.getView(convertView, R.id.imageView_bookImage,null);

        BookInfo bookInfo = bookInfos.get(position);
        tv_bookName.setText(bookInfo.getBookName());
        textView3_star.setText(bookInfo.getStar()+"星");
        textView5_price.setText("原价：￥"+ bookInfo.getPrice());
        textView5_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);//给文本内容设置删除线
        textView7_discountPrice.setText("￥"+bookInfo.getDiscountPrice());
        textView8_discount.setText(bookInfo.getDiscount()+"折");
        if(Objects.equals(bookInfo.getBookName(), "Java 学习笔记（第8版）")) {
            imageView_bookImage.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(bookInfo.getBookName(), "Android安全攻防权威指南")){
            imageView_bookImage.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(bookInfo.getBookName(), "iOS编程实战")){
            imageView_bookImage.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(bookInfo.getBookName(), "Android应用性能优化")) {
            imageView_bookImage.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C语言入门经典（第5版）")) {
            imageView_bookImage.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(bookInfo.getBookName(), "Java编程思想（第4版）")) {
            imageView_bookImage.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Effective Java中文版(第2版)")) {
            imageView_bookImage.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Java核心技术")) {
            imageView_bookImage.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(bookInfo.getBookName(), "精通iOS开发(第6版)")) {
            imageView_bookImage.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C++ Primer中文版（第5版）")) {
            imageView_bookImage.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C++ Primer Plus(第6版)")) {
            imageView_bookImage.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(bookInfo.getBookName(), "嗨翻C语言")) {
            imageView_bookImage.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Java核心技术卷一")) {
            imageView_bookImage.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Android编程权威指南")) {
            imageView_bookImage.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(bookInfo.getBookName(), "Head First Java（中文版）")) {
            imageView_bookImage.setImageResource(R.mipmap.a_001);
        }else {
            imageView_bookImage.setImageResource(R.mipmap.ic_launcher);
        }
        return convertView;
    }

    public ArrayList<BookInfo> getBookInfos() {
        return bookInfos;
    }

    public void setBookInfos(ArrayList<BookInfo> bookInfos) {
        this.bookInfos = bookInfos;
    }
}
