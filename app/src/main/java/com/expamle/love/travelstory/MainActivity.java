package com.expamle.love.travelstory;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toolbar;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_POSISION = 0;
    private static final int RECOMMEND_POSISION = 1;
    private static final int SEARCH_POSISION = 2;
    private static final int LIKE_POSISION = 3;
    private static final int USER_POSISION = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        設置status bar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){ // 4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) { // 5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); // 確認取消半透明設置。
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 全螢幕顯示，status bar 不隱藏，activity 上方 layout 會被 status bar 覆蓋。
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE); // 配合其他 flag 使用，防止 system bar 改變後 layout 的變動。
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS); // 跟系統表示要渲染 system bar 背景。
            window.setStatusBarColor(Color.TRANSPARENT);
        }

        setContentView(R.layout.activity_main);
        findViews();

    }

    private void findViews() {

//        元件
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        final ViewPager viewPager = findViewById(R.id.viewPager);

//        綁定
        FragmentList fragmentList = new FragmentList();
        List<Fragment> myFragment = fragmentList.frgmentList();
        FragmentAdater adater = new FragmentAdater(getSupportFragmentManager(), myFragment);
        viewPager.setAdapter(adater);

//        navigation 監聽器
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.navigation_home:
                        viewPager.setCurrentItem(HOME_POSISION);
                        return true;
                    case R.id.navigation_recommend:
                        viewPager.setCurrentItem(RECOMMEND_POSISION);
                        return true;
                    case R.id.navigation_search:
                        viewPager.setCurrentItem(SEARCH_POSISION);
                        return true;
                    case R.id.navigation_like:
                        viewPager.setCurrentItem(LIKE_POSISION);
                        return true;
                    case R.id.navigation_user:
                        viewPager.setCurrentItem(USER_POSISION);
                        return true;
                }
                return false;
            }
        });
//        改變ViewPager navigation跟著改變
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                    navigation.getMenu().getItem(position).setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    //    FragmentAdater
    class FragmentAdater extends FragmentStatePagerAdapter {
        private List<Fragment> myFragments;

        public FragmentAdater(FragmentManager fm, List<Fragment> myFragments) {
            super(fm);
            this.myFragments = myFragments;
        }

        @Override
        public Fragment getItem(int position) {
            return myFragments.get(position);
        }

        @Override
        public int getCount() {
            return myFragments.size();
        }
    }

}
