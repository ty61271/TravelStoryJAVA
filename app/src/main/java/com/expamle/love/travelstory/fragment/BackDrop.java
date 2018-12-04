package com.expamle.love.travelstory.fragment;


import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Interpolator;

public class BackDrop {
    private AppCompatActivity activity;
    private Toolbar toolbar;
    private View scrollView;
    private View linearLayout;
    private Drawable openDrawable;
    private Drawable closeDrawable;
    private Interpolator interpolator;

    public  BackDrop(AppCompatActivity activity, Toolbar toolbar, View scrollView, View linearLayout, Drawable openDrawable){
        this(activity,toolbar,scrollView,linearLayout,openDrawable,null,null);
    }

    public BackDrop(AppCompatActivity activity, Toolbar toolbar, View scrollView, View linearLayout, Drawable openDrawable, Drawable closeDrawable){
        this(activity,toolbar,scrollView,linearLayout,openDrawable,closeDrawable,null);

    }

    public BackDrop(AppCompatActivity activity, Toolbar toolbar, View scrollView, View linearLayout, Drawable openDrawable, Drawable closeDrawable, android.view.animation.Interpolator interpolator) {
        this.activity=activity;
        this.toolbar=toolbar;
        this.scrollView=scrollView;
        this.linearLayout=linearLayout;
        this.openDrawable=openDrawable;
        this.closeDrawable=closeDrawable;
        this.interpolator=interpolator;
        setToolbar();
        setNavigation();
    }
    private void setToolbar(){
        activity.setSupportActionBar(toolbar);
        toolbar.setTitle("");
    }

    private void setNavigation() {
        toolbar.setNavigationOnClickListener(new NavigationIconClickListener(
                activity,
                scrollView,
                linearLayout,
                openDrawable,
                closeDrawable,
                interpolator));
    }
}
