package com.example.shoppingmall2.ui;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.BookListItemAdapter;
import com.example.shoppingmall2.vo.BookInfo;
import com.example.shoppingmall2.utils.AppUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class SearchActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private EditText et_search_content;
    private ListView listView_search;
    private ImageButton ib_search_btn;
    private View loading_data;
    private BookListItemAdapter searchAdapter;
    private ArrayList<BookInfo> bookInfos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        et_search_content = (EditText) findViewById(R.id.et_search_content);
        listView_search = (ListView) findViewById(R.id.listView_search);
        listView_search.setOnItemClickListener(this);
        ib_search_btn = (ImageButton) findViewById(R.id.ib_search_btn);
        ib_search_btn.setOnClickListener(this);
        loading_data = findViewById(R.id.loading_data);
        loading_data.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        String search_content = et_search_content.getText().toString();;
        if(TextUtils.isEmpty(search_content)){
            showToast("请输入搜索内容");
            return;
        }

        AppUtils.hindeInputMethod(v);
        searchBook(search_content);
    }

    //搜索图书
    private void searchBook(String search_content) {

        loading_data.setVisibility(View.VISIBLE);
        listView_search.setVisibility(View.GONE);
        BmobQuery<BookInfo> query = new BmobQuery<>();
        query.addWhereEqualTo("bookName","Java核心技术");
            query.findObjects(new FindListener<BookInfo>() {
                @Override
                public void done(List<BookInfo> list, BmobException e) {
                    if(e == null){
                        bookInfos = (ArrayList<BookInfo>) list;
                        searchAdapter  = new BookListItemAdapter(SearchActivity.this,bookInfos);
                        listView_search.setAdapter(searchAdapter );
                        listView_search.setVisibility(View.VISIBLE);
                        loading_data.setVisibility(View.GONE);
                        System.out.println("搜索成功");
                    }else{
                        System.out.println("搜索失败");
                    }
                }
            });
    }

    public void backClick(View view) {
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BookInfo bookInfo = bookInfos.get(position);
        Intent intent = new Intent(this, BookDetailActivity.class);
        intent.putExtra("bookInfo",bookInfo);
        startActivity(intent);
    }
}