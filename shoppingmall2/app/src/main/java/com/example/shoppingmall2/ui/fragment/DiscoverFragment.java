package com.example.shoppingmall2.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.ui.SearchActivity;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;


public class DiscoverFragment extends Fragment implements View.OnClickListener{

    private SlidingTabLayout slidingTabLayout;
    private ViewPager pager;
    private LinearLayout ll_search_btn_container;
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private MyPagerAdapter myPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover,null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        ll_search_btn_container = view.findViewById(R.id.ll_search_btn_container);
        ll_search_btn_container.setOnClickListener(this);
        slidingTabLayout = view.findViewById(R.id.tabs);
        pager = view.findViewById(R.id.pager);
        fragments.add(new JavaCategoryFragment());
        fragments.add(new CCategoryFragment());
        fragments.add(new AndroidCategoryFragment());
        fragments.add(new IOSCategoryFragment());
        myPagerAdapter = new MyPagerAdapter(getFragmentManager());
        pager.setAdapter(myPagerAdapter);
        slidingTabLayout.setViewPager(pager);


        slidingTabLayout.setIndicatorColor(0xfff6f6f6);//指示器
        slidingTabLayout.setTextUnselectColor(0xFFF4F6F6);//文本
        slidingTabLayout.setBackgroundColor(0xff489cfa);//背景
        slidingTabLayout.setTabSpaceEqual(true);
        slidingTabLayout.setTabPadding(20);
        slidingTabLayout.setTextsize(20);
        pager.setCurrentItem(0);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), SearchActivity.class);
        startActivity(intent);

    }

    class MyPagerAdapter extends FragmentPagerAdapter{


        private String[] titles = {"Java","C/OC/C++","Android","IOS"};
        public MyPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }
    }
}
