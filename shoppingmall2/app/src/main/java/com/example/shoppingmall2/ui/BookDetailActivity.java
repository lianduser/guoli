package com.example.shoppingmall2.ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.ui.fragment.BookAuthorDescriptionFragment;
import com.example.shoppingmall2.ui.fragment.BookCatalogFragment;
import com.example.shoppingmall2.ui.fragment.BookContentDescriptionFragment;
import com.example.shoppingmall2.ui.fragment.BookPublishingFragment;
import com.example.shoppingmall2.vo.BookInfo;
import com.example.shoppingmall2.vo.Comment;
import com.example.shoppingmall2.vo.Orders;
import com.example.shoppingmall2.vo._User;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.CountListener;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

//书的详情页面
public class BookDetailActivity extends BaseActivity implements View.OnClickListener{

    //书的信息
    private ImageView imageView_book_detail;
    private TextView textView4_discountPrice,textView5_price,textView6_discount,textView7_bookName,textView8_author;
    private BookInfo bookInfo;

    //Fragment
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private SlidingTabLayout tabs;
    private ViewPager pager;

    //评论
    private TextView textView11_username,textView12_createdAt,textView13_comment_content,textView10_comment_count;
    private RatingBar ratingBar_star;
    private ArrayList<Comment> comment;
    private int commentNumber;

    //加入购物车
    private Button button_Shopping_Cart;

    //进入购物车界面
    private ImageView imageView_shopping_cart;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        bookInfo = (BookInfo) getIntent().getSerializableExtra("bookInfo");
        initView();

    }

    private void initView() {
        //书本
        imageView_book_detail = findViewById(R.id.imageView_book_detail);
        textView4_discountPrice = findViewById(R.id.textView4_discountPrice);
        textView5_price = findViewById(R.id.textView5_price);
        textView6_discount = findViewById(R.id.textView6_discount);
        textView7_bookName = findViewById(R.id.textView7_bookName);
        textView8_author = findViewById(R.id.textView8_author);
        initBook();

        //评论
        textView11_username = findViewById(R.id.textView11_username);
        textView12_createdAt = findViewById(R.id.textView12_createdAt);
        textView13_comment_content = findViewById(R.id.textView13_comment_content);
        ratingBar_star = findViewById(R.id.ratingBar_star);
        textView10_comment_count = findViewById(R.id.textView10_comment_count);
        initComment();

        //Fragment
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        initPager();

        //加入购物车
        button_Shopping_Cart = findViewById(R.id.button_Shopping_Cart);
        button_Shopping_Cart.setOnClickListener(this);

        //进入购物车
        imageView_shopping_cart = findViewById(R.id.imageView_shopping_cart);
        imageView_shopping_cart.setOnClickListener(this);


    }

    //Fragment
    private void initPager() {
        fragments.add(BookContentDescriptionFragment.newInstance(bookInfo.getContentDescription()));
        fragments.add(BookAuthorDescriptionFragment.newInstance(bookInfo.getAuthorDescription()));
        fragments.add(new BookCatalogFragment());
        fragments.add(BookPublishingFragment.newInstance(bookInfo));

        pager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager()));
        pager.setOnPageChangeListener(null);
        tabs.setViewPager(pager);
        tabs.setIndicatorColor(0xfff6f6f6);//指示器
        tabs.setTextUnselectColor(0xFFF4F6F6);//文本
        tabs.setBackgroundColor(0xff489cfa);//背景
        tabs.setTabSpaceEqual(true);
        tabs.setTabPadding(28);
        tabs.setTextsize(20);
        pager.setCurrentItem(0);

    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            //加入购物车
            case R.id.button_Shopping_Cart:
                addToShoppingCar();
                break;
            //进入购物车
            case R.id.imageView_shopping_cart:
                Intent intent = new Intent(this,ShorppingCartActivity.class);
                startActivity(intent);
                break;
        }
    }

    //加入购物车
    private void addToShoppingCar() {
        _User user = BmobUser.getCurrentUser(_User.class);
        if(user == null){
            showToast("请登录账号");
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
            return;
        }

        BmobQuery<Orders> query = new BmobQuery<>();
        query.addWhereEqualTo("userId",user.getObjectId());
        query.addWhereEqualTo("bookInfoId",bookInfo.getObjectId());
        query.addWhereEqualTo("status",1);
        query.findObjects(new FindListener<Orders>() {
            @Override
            public void done(List<Orders> list, BmobException e) {
                if (e == null){
                    List<Orders> ordersList = list;
                    if(ordersList.size() > 0){
                        Orders orders = ordersList.get(0);
                        orders.setTotal(orders.getTotal()+1);
                        orders.setSubtotal(orders.getTotal()*orders.getDiscountPrice());
                        orders.update(new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null){
                                    showToast("添加成功");
                                }else {
                                    showToast("添加失败");
                                }
                            }
                        });
                    }else {
                        Orders orders = new Orders();
                        orders.setStatus(1);
                        orders.setBookInfoId(bookInfo.getObjectId());
                        orders.setBookName(bookInfo.getBookName());
                        orders.setDiscountPrice(bookInfo.getDiscountPrice());
                        orders.setTotal(1);
                        orders.setUserId(user.getObjectId());
                        orders.setSubtotal(bookInfo.getDiscountPrice());
                        orders.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null){
                                    showToast("添加购物车成功");
                                }else {
                                    showToast("添加购物车失败");
                                }
                            }
                        });
                    }
                }else{
                    System.out.println("查找失败");
                }
            }
        });
    }

    //返回事件
    public void backClick(View view) {
        finish();
    }

    //Fragment的适配器
    class MyFragmentAdapter extends FragmentPagerAdapter{

        private String[] titles = {"内容","作者","目录","出版"};

        public MyFragmentAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return titles.length;
        }
    }


    //评论
    private void initComment() {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("bookInfoId",bookInfo.getObjectId());
        query.order("creatdAt");
        query.count(Comment.class, new CountListener() {
            @Override
            public void done(Integer integer, BmobException e) {
                Integer commentCount = integer;
                if(commentCount > 0){
                    query.findObjects(new FindListener<Comment>() {
                        @Override
                        public void done(List<Comment> list, BmobException e) {
                            if(e == null){
                                comment =  (ArrayList<Comment>) list;
                                commentNumber = comment.size();
                                setCommentDate();
                                System.out.println("查找成功");
                            }else {
                                System.out.println("查找失败");
                            }
                        }
                    });
                }
            }
        });

    }

    //设置评论数据
    private void setCommentDate() {
        textView10_comment_count.setText("(共有" + commentNumber + "条评论)");
        if(comment != null) {
            textView11_username.setText("用户名:" + comment.get(0).getUser());
            textView12_createdAt.setText(comment.get(0).getCreatedAt());
            textView13_comment_content.setText(comment.get(0).getContent());
            ratingBar_star.setRating(comment.get(0).getStar());
        }
    }


    //书的信息
    private void initBook() {

        textView4_discountPrice.setText("￥"+bookInfo.getDiscountPrice());
        textView5_price.setText("原价:"+bookInfo.getPrice());
        textView5_price.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        textView6_discount.setText(bookInfo.getDiscount()+"折");
        textView7_bookName.setText(bookInfo.getBookName());
        textView8_author.setText(bookInfo.getAuthor());
        if(Objects.equals(bookInfo.getBookName(), "Java 学习笔记（第8版）")) {
            imageView_book_detail.setImageResource(R.mipmap.a_015);
        }else if(Objects.equals(bookInfo.getBookName(), "Android安全攻防权威指南")){
            imageView_book_detail.setImageResource(R.mipmap.a_014);
        }else if(Objects.equals(bookInfo.getBookName(), "iOS编程实战")){
            imageView_book_detail.setImageResource(R.mipmap.a_012);
        }else if(Objects.equals(bookInfo.getBookName(), "Android应用性能优化")) {
            imageView_book_detail.setImageResource(R.mipmap.a_005);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C语言入门经典（第5版）")) {
            imageView_book_detail.setImageResource(R.mipmap.a_009);
        }else if(Objects.equals(bookInfo.getBookName(), "Java编程思想（第4版）")) {
            imageView_book_detail.setImageResource(R.mipmap.a_002);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Effective Java中文版(第2版)")) {
            imageView_book_detail.setImageResource(R.mipmap.a_003);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Java核心技术")) {
            imageView_book_detail.setImageResource(R.mipmap.a_010);
        }
        else if(Objects.equals(bookInfo.getBookName(), "精通iOS开发(第6版)")) {
            imageView_book_detail.setImageResource(R.mipmap.a_013);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C++ Primer中文版（第5版）")) {
            imageView_book_detail.setImageResource(R.mipmap.a_006);
        }
        else if(Objects.equals(bookInfo.getBookName(), "C++ Primer Plus(第6版)")) {
            imageView_book_detail.setImageResource(R.mipmap.a_004);
        }
        else if(Objects.equals(bookInfo.getBookName(), "嗨翻C语言")) {
            imageView_book_detail.setImageResource(R.mipmap.a_007);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Java核心技术卷一")) {
            imageView_book_detail.setImageResource(R.mipmap.a_008);
        }
        else if(Objects.equals(bookInfo.getBookName(), "Android编程权威指南")) {
            imageView_book_detail.setImageResource(R.mipmap.a_011);
        }else if(Objects.equals(bookInfo.getBookName(), "Head First Java（中文版）")) {
            imageView_book_detail.setImageResource(R.mipmap.a_001);
        }else {
            imageView_book_detail.setImageResource(R.mipmap.ic_launcher);
        }

    }
}
