package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.OrdersAdapter;
import com.example.shoppingmall2.vo.Orders;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class OrdersActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView imageView_shopping_cart;
    private ListView listView2_orders;
    private int orderStatus;
    private ArrayList<Orders> ordersList = new ArrayList<>();
    private OrdersAdapter ordersAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_orders);
        imageView_shopping_cart = findViewById(R.id.imageView_shopping_cart);
        imageView_shopping_cart.setOnClickListener(this);
        listView2_orders = findViewById(R.id.listView2_orders);
        listView2_orders.setOnItemClickListener(this);
        ordersAdapter = new OrdersAdapter(this,ordersList);
        orderStatus = getIntent().getIntExtra("status", -1);
        if(orderStatus == -1){
            finish();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        loatOrdersDate();
        super.onResume();
    }

    private void loatOrdersDate() {
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Orders> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.order("-buyDate");
        switch (orderStatus){
            case 0:
                query.addWhereGreaterThan("status",0);
                break;
            case 1:
                query.addWhereEqualTo("status",1);
                break;
            case 3:
                query.addWhereEqualTo("status",3);
                break;
            case 4:
                query.addWhereEqualTo("status",4);
                break;
        }
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> list, BmobException e) {
                if(e== null) {
                    ordersList = (ArrayList<Orders>) list;
                    ordersAdapter.setOrderList(ordersList);
                    listView2_orders.setAdapter(ordersAdapter);
                    ordersAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_shopping_cart:
                Intent intent = new Intent(this,ShorppingCartActivity.class);
                startActivity(intent);
                break;

        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if(orderStatus == 4){
            Orders orders = ordersList.get(position);
            Intent intent = new Intent(this,EvaluationActivity.class);
            intent.putExtra("orders",orders);
            startActivity(intent);
        }
    }
}
