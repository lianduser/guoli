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

public class ShoppingCartListItemEditAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Orders> orderList;

    public void setOrderList(ArrayList<Orders> orderList) {
        this.orderList = orderList;
    }

    public ShoppingCartListItemEditAdapter(Context context, ArrayList<Orders> orderList){
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
            convertView = LayoutInflater.from(context).inflate(R.layout.shopping_cart_list_item_edit,null);
        }
        final ImageView imageView_book = ViewHolder.getView(convertView,R.id.imageView_book,null);
        final TextView textView6_bookName = ViewHolder.getView(convertView,R.id.textView6_bookName,null);
        final TextView textView7_subtotal = ViewHolder.getView(convertView,R.id.textView7_subtotal,null);
        final ImageView button_delete = ViewHolder.getView(convertView, R.id.button_delete, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Orders o = orderList.get(position);
                o.delete(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        System.out.println("????????????");
                    }
                });
                orderList.remove(position);
                ShoppingCartListItemEditAdapter.this.notifyDataSetChanged();
                ((ShorppingCartActivity)context).setTotalAmountToUi();
                ((ShorppingCartActivity)context).setProductCount();
            }
        });
        final Orders orders = orderList.get(position);
        textView6_bookName.setText(orders.getBookName());
        textView7_subtotal.setText("???"+orders.getSubtotal());
        if(Objects.equals(orders.getBookName(), "Java ??????????????????8??????")) {
            imageView_book.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(orders.getBookName(), "Android????????????????????????")){
            imageView_book.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(orders.getBookName(), "iOS????????????")){
            imageView_book.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(orders.getBookName(), "Android??????????????????")) {
            imageView_book.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(orders.getBookName(), "C????????????????????????5??????")) {
            imageView_book.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(orders.getBookName(), "Java??????????????????4??????")) {
            imageView_book.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(orders.getBookName(), "Effective Java?????????(???2???)")) {
            imageView_book.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(orders.getBookName(), "Java????????????")) {
            imageView_book.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(orders.getBookName(), "??????iOS??????(???6???)")) {
            imageView_book.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer???????????????5??????")) {
            imageView_book.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(orders.getBookName(), "C++ Primer Plus(???6???)")) {
            imageView_book.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(orders.getBookName(), "??????C??????")) {
            imageView_book.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(orders.getBookName(), "Java??????????????????")) {
            imageView_book.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(orders.getBookName(), "Android??????????????????")) {
            imageView_book.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(orders.getBookName(), "Head First Java???????????????")) {
            imageView_book.setImageResource(R.mipmap.a_001);
        }else {
            imageView_book.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }

}
