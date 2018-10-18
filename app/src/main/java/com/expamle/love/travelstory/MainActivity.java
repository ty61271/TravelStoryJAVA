package com.expamle.love.travelstory;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

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
