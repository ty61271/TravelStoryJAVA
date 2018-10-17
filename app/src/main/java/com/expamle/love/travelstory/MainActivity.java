package com.expamle.love.travelstory;

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
                        viewPager.setCurrentItem(0);
                        return true;
                    case R.id.navigation_recommend:
                        viewPager.setCurrentItem(1);
                        return true;
                    case R.id.navigation_search:
                        viewPager.setCurrentItem(2);
                        return true;
                    case R.id.navigation_like:
                        viewPager.setCurrentItem(3);
                        return true;
                    case R.id.navigation_user:
                        viewPager.setCurrentItem(4);
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
