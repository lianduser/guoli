package com.example.shoppingmall2.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.CommentAdapter;
import com.example.shoppingmall2.vo.Comment;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class CommentActivity extends BaseActivity{

    private CommentAdapter commentAdapter;
    private String bookInfoId;
    private ArrayList<Comment> comments = new ArrayList<>();
    private ListView listView_comment;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        bookInfoId = getIntent().getStringExtra("bookInfoId");
        listView_comment = findViewById(R.id.listView_comment);
        initData();
    }

    private void initData() {
        commentAdapter = new CommentAdapter(this,comments);
        listView_comment.setAdapter(commentAdapter);
        loadComment();
    }

    private void loadComment() {
        BmobQuery<Comment> query = new BmobQuery<>();
        query.addWhereEqualTo("bookInfoId",bookInfoId);
        query.order("-createAt");
        query.findObjects(new FindListener<Comment>() {
            @Override
            public void done(List<Comment> list, BmobException e) {
                for (int i=0;i<list.size();i++){
                    comments.add(list.get(i));
                }
                commentAdapter.notifyDataSetChanged();
            }
        });
    }

    //返回事件
    public void backClick(View view) {
        finish();
    }
}
