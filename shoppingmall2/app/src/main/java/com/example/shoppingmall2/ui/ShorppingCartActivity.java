package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.ShoppingCartListItemAdapter;
import com.example.shoppingmall2.adapter.ShoppingCartListItemEditAdapter;
import com.example.shoppingmall2.vo.Orders;
import com.example.shoppingmall2.vo._User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ShorppingCartActivity extends BaseActivity implements View.OnClickListener{

    private ListView listView2_shopping_cart;
    private ShoppingCartListItemAdapter shoppingCartListItemAdapter;
    private ShoppingCartListItemEditAdapter shoppingCartListItemEditAdapter;
    private ArrayList<Orders> orderList = new ArrayList<>();
    private TextView textView5_count,textView4_sum;
    private Button button_Shopping_Cart_edit,button_Shopping_Cart_finish,button_Settlement;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);
        listView2_shopping_cart = findViewById(R.id.listView2_shopping_cart);
        textView5_count = findViewById(R.id.textView5_count);
        textView4_sum = findViewById(R.id.textView4_sum);
        button_Shopping_Cart_edit = findViewById(R.id.button_Shopping_Cart_edit);
        button_Shopping_Cart_finish = findViewById(R.id.button_Shopping_Cart_finish);
        button_Settlement = findViewById(R.id.button_Settlement);
        button_Settlement.setOnClickListener(this);
        button_Shopping_Cart_edit.setOnClickListener(this);
        button_Shopping_Cart_finish.setOnClickListener(this);
        shoppingCartListItemAdapter = new ShoppingCartListItemAdapter(this,orderList);
        shoppingCartListItemEditAdapter = new ShoppingCartListItemEditAdapter(this,orderList);

    }

    @Override
    protected void onResume() {
        loadDate();
        super.onResume();
    }

    public void setProductCount() {
        textView5_count.setText("共有" + orderList.size() + "件商品");
    }

    private void loadDate() {
        _User user = BmobUser.getCurrentUser(_User.class);
        BmobQuery<Orders> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.addWhereEqualTo("status",1);
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> list, BmobException e) {
                if(e == null) {
                    orderList = (ArrayList<Orders>) list;
                    shoppingCartListItemAdapter.setOrderList(orderList);
                    listView2_shopping_cart.setAdapter(shoppingCartListItemAdapter);
                    shoppingCartListItemAdapter.notifyDataSetChanged();
                    setProductCount();
                    getTotalAmount();
                    setTotalAmountToUi();
                    System.out.println("查找购物车商品成功");
                }else {
                    System.out.println("查找购物车商品失败");
                }
            }
        });
    }

    public void setTotalAmountToUi(){
        double totalMount = getTotalAmount();
        textView4_sum.setText("共" + totalMount + "元");
        if(totalMount <= 0){
            button_Settlement.setEnabled(false);
        }else {
            button_Settlement.setEnabled(true);
        }
    }

    private Double getTotalAmount(){
        double totalAmount = 0;
        Orders orders;
        for(int i=0 ; i<orderList.size(); i++){
            orders = orderList.get(i);
            totalAmount += orders.getSubtotal();
        }
        return totalAmount;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_Shopping_Cart_edit:
                button_Shopping_Cart_edit.setVisibility(View.GONE);
                button_Shopping_Cart_finish.setVisibility(View.VISIBLE);
                shoppingCartListItemEditAdapter.setOrderList(orderList);
                listView2_shopping_cart.setAdapter(shoppingCartListItemEditAdapter);
                break;
            case R.id.button_Shopping_Cart_finish:
                button_Shopping_Cart_edit.setVisibility(View.VISIBLE);
                button_Shopping_Cart_finish.setVisibility(View.GONE);
                shoppingCartListItemAdapter.setOrderList(orderList);
                listView2_shopping_cart.setAdapter(shoppingCartListItemAdapter);
                break;
            case R.id.button_Settlement:
                Intent intent = new Intent(this,SettlementActivity.class);
                intent.putExtra("totalAmount",getTotalAmount());
                intent.putExtra("orderList",orderList);
                startActivity(intent);
                break;
        }
    }

    public void backClick(View view) {
        finish();
    }
}
