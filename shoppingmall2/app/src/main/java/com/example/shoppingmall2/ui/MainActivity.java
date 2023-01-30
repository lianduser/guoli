package com.example.shoppingmall2.ui;

import android.os.Bundle;

import com.example.shoppingmall2.R;
import com.example.shoppingmall2.ui.fragment.DiscoverFragment;
import com.example.shoppingmall2.ui.fragment.HomeFragment;
import com.example.shoppingmall2.ui.fragment.PersonalFragment;


import android.view.View;


import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import cn.bmob.v3.Bmob;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener{

    private Button button_tab_home,button2_tab_discover,button3_tab_personal;
    private ViewPager viewPager;

    private MypagerAdapter mypagerAdapter;
    private HomeFragment homeFragment;
    private DiscoverFragment discoverFragment;
    private PersonalFragment personalFragment;
    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        button_tab_home = (Button) findViewById(R.id.button_tab_home);
        button2_tab_discover = (Button) findViewById(R.id.button2_tab_discover);
        button3_tab_personal = (Button) findViewById(R.id.button3_tab_personal);
        defaultButton();
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        homeFragment = new HomeFragment();
        discoverFragment = new DiscoverFragment();
        personalFragment = new PersonalFragment();
        fragments.add(homeFragment);
        fragments.add(discoverFragment);
        fragments.add(personalFragment);

        mypagerAdapter = new MypagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(mypagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(this);

    }
    public void tabSelectClick(View view){
        changeTab(view);
    }

    public void defaultButton(){
        button_tab_home.setSelected(true);
        button2_tab_discover.setSelected(false);
        button3_tab_personal.setSelected(false);
    }


    private void changeTab(View view) {
        switch (view.getId()){
            case R.id.button_tab_home:
                button_tab_home.setSelected(true);
                button2_tab_discover.setSelected(false);
                button3_tab_personal.setSelected(false);
                viewPager.setCurrentItem(0);
                break;
            case R.id.button2_tab_discover:
                button_tab_home.setSelected(false);
                button2_tab_discover.setSelected(true);
                button3_tab_personal.setSelected(false);
                viewPager.setCurrentItem(1);
                break;
            case R.id.button3_tab_personal:
                button_tab_home.setSelected(false);
                button2_tab_discover.setSelected(false);
                button3_tab_personal.setSelected(true);
                viewPager.setCurrentItem(2);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                changeTab(button_tab_home);
                break;
            case 1:
                changeTab(button2_tab_discover);
                break;
            case 2:
                changeTab(button3_tab_personal);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    class MypagerAdapter extends FragmentPagerAdapter{

        public MypagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            //如果注释这行，那么不管怎么切换，page都不会被销毁
            //super.destroyItem(container, position, object);
        }

    }

}