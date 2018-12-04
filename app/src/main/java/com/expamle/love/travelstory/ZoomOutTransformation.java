package com.expamle.love.travelstory;

import android.support.v4.view.ViewPager;
import android.view.View;

public class ZoomOutTransformation implements ViewPager.PageTransformer {

    private static final float MIN_SCALE = 0.65f;
    private static final float MIN_ALPHA = 0.3f;

//    @Override
//    public void transformPage(View page, float position) {
//
//        if (position <-1){  // [-Infinity,-1)
//            // This page is way off-screen to the left.
//            page.setAlpha(0);
//
//        }
//        else if (position <=1){ // [-1,1]
//
//            page.setScaleX(Math.max(MIN_SCALE,1-Math.abs(position)));
//            page.setScaleY(Math.max(MIN_SCALE,1-Math.abs(position)));
//            page.setAlpha(Math.max(MIN_ALPHA,1-Math.abs(position)));
//
//        }
//        else {  // (1,+Infinity]
//            // This page is way off-screen to the right.
//            page.setAlpha(0);
//
//        }

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();
        if (position < -1) {
            // 不用理会
            view.setAlpha(0);
        } else if (position <= 0) { // [-1,0]
            // position<=0，表示View从左边划入或者划出到左边。
            view.setAlpha(1);
            view.setTranslationX(0);
            view.setScaleX(1);
            view.setScaleY(1);
        } else if (position <= 1) {
            // position <= 1，表示View从右边进入或者划出到右边。
            view.setAlpha(1 - position);    //右边进入或退出的透明度，进入时透明度越来越大，退出时透明度越来越小。
            view.setTranslationX(pageWidth * -position);
            // Scale the page down (between MIN_SCALE and 1)
            float scaleFactor = MIN_SCALE
                    + (1 - MIN_SCALE) * (1 - Math.abs(position));
            view.setScaleX(scaleFactor);
            view.setScaleY(scaleFactor);
        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }
    }
}


