package com.expamle.love.travelstory;

import android.support.v4.app.Fragment;

import com.expamle.love.travelstory.fragment.HomeFragment;
import com.expamle.love.travelstory.fragment.LikeFragment;
import com.expamle.love.travelstory.fragment.RecommendFragment;
import com.expamle.love.travelstory.fragment.SearchFragment;
import com.expamle.love.travelstory.fragment.UserFragment;

import java.util.ArrayList;
import java.util.List;

public class FragmentList {
    public List<Fragment> frgmentList(){
        List<Fragment> myFragments=new ArrayList<>();
        myFragments.add(new HomeFragment());
        myFragments.add(new LikeFragment());
        myFragments.add(new RecommendFragment());
        myFragments.add(new SearchFragment());
        myFragments.add(new UserFragment());
        return myFragments;
    }
}
