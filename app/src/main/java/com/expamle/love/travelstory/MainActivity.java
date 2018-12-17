package com.expamle.love.travelstory;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int HOME_POSITION = 0;
    private static final int NEARBY_POSITION = 1;
    private static final int RECOMMEND_POSITION = 2;
    private static final int LIKE_POSITION = 3;
    private static final int USER_POSITION = 4;
    private FragmentManager fragmentManager;
    private Fragment temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        findViews();
        
    }

    private void findViews() {

//        元件
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

//        綁定
        FragmentList fragmentList = new FragmentList();
        final List<Fragment> myFragments = fragmentList.frgmentList();

        firstFragment(myFragments);


//        navigation 監聽器
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.navigation_home:
                        switchFragment(myFragments.get(HOME_POSITION));
                        return true;
                    case R.id.navigation_nearby:
                        switchFragment(myFragments.get(NEARBY_POSITION));
                        return true;
                    case R.id.navigation_recommend:
                        switchFragment(myFragments.get(RECOMMEND_POSITION));
                        return true;
                    case R.id.navigation_like:
                        switchFragment(myFragments.get(LIKE_POSITION));
                        return true;
                    case R.id.navigation_user:
                        switchFragment(myFragments.get(USER_POSITION));
                        return true;
                }
                return false;
            }
        });
//        viewPager.setPageTransformer(true, new ZoomOutTransformation());


//        改變ViewPager navigation跟著改變
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                    navigation.getMenu().getItem(position).setChecked(true);
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });

    }


    private void firstFragment(List<Fragment> myFragments) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment, myFragments.get(HOME_POSITION)).commit();
        temp = myFragments.get(HOME_POSITION);
    }

    private void switchFragment(Fragment fragment) {
        if (temp != fragment) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            if (!fragment.isAdded()) {
                fragmentTransaction
                        .hide(temp)
                        .add(R.id.fragment, fragment)
                        .commit();

            } else {
                fragmentTransaction
                        .hide(temp)
                        .show(fragment)
                        .commit();
            }
            temp = fragment;
        }

    }

    //    FragmentAdater
//    class FragmentAdater extends FragmentStatePagerAdapter {
//        private List<Fragment> myFragments;
//
//        public FragmentAdater(FragmentManager fm, List<Fragment> myFragments) {
//            super(fm);
//            this.myFragments = myFragments;
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            return myFragments.get(position);
//        }
//
//        @Override
//        public int getCount() {
//            return myFragments.size();
//        }
//    }

}
