package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.vo.Comment;
import com.example.shoppingmall2.vo.Orders;
import com.example.shoppingmall2.vo._User;

import java.util.Objects;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class EvaluationActivity extends BaseActivity implements View.OnClickListener {

    private ImageView imageView_shopping_cart;
    private Orders orders;
    private TextView textView7_bookName,textView10_discount_price,editText_evaluation;
    private ImageView imageView3_bookimage;
    private RatingBar ratingBar_star;
    private Button button_submit;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluation);
        imageView_shopping_cart = findViewById(R.id.imageView_shopping_cart);
        textView7_bookName = findViewById(R.id.textView7_bookName);
        textView10_discount_price = findViewById(R.id.textView10_discount_price);
        imageView3_bookimage = findViewById(R.id.imageView3_bookimage);
        editText_evaluation = findViewById(R.id.editText_evaluation);
        ratingBar_star = findViewById(R.id.ratingBar_star);
        button_submit = findViewById(R.id.button_submit);
        button_submit.setOnClickListener(this);
        imageView_shopping_cart.setOnClickListener(this);
        initData();
        super.onCreate(savedInstanceState);
    }

    private void initData() {
        orders = (Orders) getIntent().getSerializableExtra("orders");
        textView7_bookName.setText(orders.getBookName());
        textView10_discount_price.setText("价格:"+orders.getDiscountPrice());
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
            case R.id.button_submit:
                submitEvaluation();
                break;
        }
    }

    private void submitEvaluation() {
        String content = editText_evaluation.getText().toString();
        if(TextUtils.isEmpty(content)){
            showToast("请输入评论");
            return;
        }
        int star = (int) ratingBar_star.getRating();
        Comment comment = new Comment();
        _User user = BmobUser.getCurrentUser(_User.class);
        comment.setBookInfoId(orders.getBookInfoId());
        comment.setUser(user.getUsername());
        comment.setUserId(user.getObjectId());
        comment.setContent(content);
        comment.setStar(star);
        comment.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if(e == null){
                    orders.setStatus(5);
                    orders.update(new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                        }
                    });
                    showToast("提交成功");
                    finish();
                }else{
                    showToast("提交失败");
                }
            }
        });
    }
}
