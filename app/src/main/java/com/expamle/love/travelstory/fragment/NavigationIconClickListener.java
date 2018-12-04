package com.expamle.love.travelstory.fragment;

import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import com.expamle.love.travelstory.R;

class NavigationIconClickListener implements View.OnClickListener {
    private AppCompatActivity activity;
    private View scollView;
    private View linerView;
    private Drawable openDrawable;
    private Drawable closeDrawable;
    private Interpolator interpolator;
    private int height;
    private boolean backdropShown = false;
    private int linerViewHeight;
    private Drawable aniDrable;


    public NavigationIconClickListener(AppCompatActivity activity, View scollView) {
        this(activity, scollView, null, null, null, null);
    }

    public NavigationIconClickListener(AppCompatActivity activity, View scollView, View linerView) {
        this(activity, scollView, linerView, null, null, null);
    }

    public NavigationIconClickListener(AppCompatActivity activity, View scollView, View linerView, Interpolator interpolator) {
        this(activity, scollView, linerView, null, null, interpolator);
    }

    public NavigationIconClickListener(AppCompatActivity activity, View scollView, View linerView, Drawable openDrawable, Interpolator interpolator) {
        this(activity, scollView, linerView, openDrawable, null, interpolator);
    }


    public NavigationIconClickListener(AppCompatActivity activity, View scollView, final View linerView, Drawable openDrawable, Drawable closeDrawable, Interpolator interpolator) {
        this.activity = activity;
        this.scollView = scollView;
        this.linerView = linerView;
        this.openDrawable = openDrawable;
        this.closeDrawable = closeDrawable;
        this.interpolator = interpolator;


//        取得顯示器大小
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;


//        取得linerlayout高度
        final ViewTreeObserver observer = linerView.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                linerViewHeight = linerView.getHeight();
            }
        });
    }


    @Override
    public void onClick(View v) {
//        確認開啟關閉的icon
        updateIcon(v);

//        backdrop動畫
        backdropShown = !backdropShown;
        int translationY = linerViewHeight - activity.getResources().getDimensionPixelSize(R.dimen.toolbar_height);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(
                scollView,
                "translationY",
                backdropShown ? translationY : 0
        );
        objectAnimator.setDuration(500);

//        加速器
        if (interpolator != null) {
            objectAnimator.setInterpolator(interpolator);
        }
        objectAnimator.start();

//        navigationicon 動畫
        setNavigationAni(aniDrable);


    }

    private void setNavigationAni(Drawable drawable) {
        //        toolbar navigationicon 動畫

        if (Build.VERSION.SDK_INT > 22) {
            AnimatedVectorDrawable aniDrawable = (AnimatedVectorDrawable) drawable;
            aniDrawable.start();
        } else {
            AnimatedVectorDrawableCompat aniDrawable = (AnimatedVectorDrawableCompat) drawable;
            aniDrawable.start();


        }
    }

    public Drawable updateIcon(View v) {
        if (openDrawable != null && closeDrawable != null) {
            if (backdropShown) {
                ((ImageView) v).setImageDrawable(closeDrawable);
            } else {
                ((ImageView) v).setImageDrawable(openDrawable);
            }
        }
        ImageView imageView = (ImageView) v;
        aniDrable = imageView.getDrawable();
        return aniDrable;
    }
}
