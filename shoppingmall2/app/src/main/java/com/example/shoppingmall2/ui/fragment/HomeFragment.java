package com.example.shoppingmall2.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.allthelucky.common.view.AutoPlayManager;
import com.allthelucky.common.view.ImageIndicatorView;
import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.BookListItemAdapter;
import com.example.shoppingmall2.ui.BookDetailActivity;
import com.example.shoppingmall2.ui.MainActivity;
import com.example.shoppingmall2.ui.ShorppingCartActivity;
import com.example.shoppingmall2.vo.BookInfo;

import java.util.ArrayList;
import java.util.List;


import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener,View.OnClickListener{

    private ImageIndicatorView indicate_view;
    private MainActivity mainActivity;
    private ArrayList<BookInfo> bookInfos = new ArrayList<>();
    private ListView listView;
    private BookListItemAdapter bookListItemAdapter;
    private View loading_data;
    private ImageView imageView_shopping_cart;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home,null);
        initIndicateView(view);
        listView = view.findViewById(R.id.ListView);
        imageView_shopping_cart = view.findViewById(R.id.imageView_shopping_cart);
        imageView_shopping_cart.setOnClickListener(this);
        bookListItemAdapter = new BookListItemAdapter(mainActivity,bookInfos);
        listView.setAdapter(bookListItemAdapter);
        listView.setOnItemClickListener(this);
        loading_data = view.findViewById(R.id.loading_data);
        loading_data.setVisibility(View.GONE);

        loadData();
        return view;
    }

    //初始化主页列表数据
    private void loadData() {
        loading_data.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        if (bookInfos.size() == 0) {
            BmobQuery<BookInfo> query = new BmobQuery<>();
            query.addWhereEqualTo("star",5);
            query.findObjects(new FindListener<BookInfo>() {
                @Override
                public void done(List<BookInfo> list, BmobException e) {
                    if (e == null) {
                        bookInfos = (ArrayList<BookInfo>) list;
                        bookListItemAdapter.setBookInfos(bookInfos);
                        bookListItemAdapter.notifyDataSetChanged();
                        loading_data.setVisibility(View.GONE);
                        listView.setVisibility(View.VISIBLE);
                    } else {
                        System.out.println("查询失败");
                    }
                }
            });

        }

    }
    private void initIndicateView(View view) {
        indicate_view = (ImageIndicatorView) view.findViewById(R.id.indicate_view);
        final Integer[] resArray = new Integer[] { R.mipmap.qdzt, R.mipmap.qinghuabiancheng,R.mipmap.scala};
        indicate_view.setupLayoutByDrawable(resArray);
        indicate_view.setIndicateStyle(ImageIndicatorView.INDICATE_USERGUIDE_STYLE);
        indicate_view.show();
        AutoPlayManager autoBrocastManager =  new AutoPlayManager(indicate_view);
        autoBrocastManager.setBroadcastEnable(true);
        autoBrocastManager.setBroadCastTimes(5);//loop times
        autoBrocastManager.setBroadcastTimeIntevel(3 * 1000, 3 * 1000);//set first play time and interval
        autoBrocastManager.loop();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BookInfo bookInfo = bookInfos.get(position);
        Intent intent = new Intent(mainActivity, BookDetailActivity.class);
        intent.putExtra("bookInfo",bookInfo);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(mainActivity, ShorppingCartActivity.class);
        startActivity(intent);
    }
}
