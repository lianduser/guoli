package com.example.shoppingmall2.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.adapter.BookListItemAdapter;
import com.example.shoppingmall2.ui.BookDetailActivity;
import com.example.shoppingmall2.ui.MainActivity;
import com.example.shoppingmall2.vo.BookInfo;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class IOSCategoryFragment extends Fragment implements AdapterView.OnItemClickListener{

    private MainActivity mainActivity;
    private BookListItemAdapter bookListItemAdapter;
    private LinearLayout load_layout;
    private ListView listView2_category_IOS;
    private ArrayList<BookInfo> bookInfos = new ArrayList<>();

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, null);
        listView2_category_IOS = view.findViewById(R.id.listView2_category);
        listView2_category_IOS.setOnItemClickListener(this);
        load_layout = view.findViewById(R.id.load_layout);
        bookListItemAdapter = new BookListItemAdapter(mainActivity, bookInfos);
        loadDate();
        listView2_category_IOS.setAdapter(bookListItemAdapter);
        return view;
    }

    private void loadDate() {
        if (bookInfos.size() == 0) {
            load_layout.setVisibility(View.VISIBLE);
            listView2_category_IOS.setVisibility(View.GONE);
            BmobQuery<BookInfo> query = new BmobQuery<>();
            query.addWhereEqualTo("categoryId", "AsZkEEEN");
            query.findObjects(new FindListener<BookInfo>() {
                @Override
                public void done(List<BookInfo> list, BmobException e) {
                    if (e == null) {
                        bookInfos = (ArrayList<BookInfo>) list;
                        bookListItemAdapter.setBookInfos(bookInfos);
                        bookListItemAdapter.notifyDataSetChanged();
                        load_layout.setVisibility(View.GONE);
                        listView2_category_IOS.setVisibility(View.VISIBLE);
                    } else {
                        System.out.println("查询失败");
                    }
                }
            });
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        BookInfo bookInfo = bookInfos.get(position);
        Intent intent = new Intent(mainActivity, BookDetailActivity.class);
        intent.putExtra("bookInfo",bookInfo);
        startActivity(intent);
    }
}
