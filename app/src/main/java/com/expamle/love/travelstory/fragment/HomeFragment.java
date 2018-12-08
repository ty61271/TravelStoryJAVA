package com.expamle.love.travelstory.fragment;

import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.expamle.love.travelstory.CityActivity;
import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.hotCity.HotCity;
import com.expamle.love.travelstory.json.CityJson;
import com.expamle.love.travelstory.sql.HomeReadFireBase;
import com.expamle.love.travelstory.sql.MyFireBaseData;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private View view;
    private AppCompatActivity activity;
    private BackDrop backDrop;
    private NestedScrollView scrollView;
    private LinearLayout linearLayout;
    private Drawable openDrawable;
    private Drawable closeDrawable;
    private AccelerateDecelerateInterpolator interpolator;
    private HomeReadFireBase homeReadFireBase;


    //設定menu
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
//        設定 BackDrop
        setBackDrop();


        return view;

    }

    private void setBackDrop() {
        setView();
        backDrop = new BackDrop(activity, toolbar, scrollView, linearLayout, openDrawable, closeDrawable, interpolator);
    }

    private void setView() {
        activity = (AppCompatActivity) getActivity();
        toolbar = view.findViewById(R.id.home_toolbar);
        scrollView = view.findViewById(R.id.home_backdrop_scroll);
        linearLayout = view.findViewById(R.id.home_backdrop_position);
        openDrawable = ContextCompat.getDrawable(activity, R.drawable.navigation_icon_open);
        closeDrawable = ContextCompat.getDrawable(activity, R.drawable.navigation_icon_close);
        interpolator = new AccelerateDecelerateInterpolator();
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findViews();


        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(8, 8, 8, 0);
            }
        });


    }


    //      綁定
    private void findViews() {
        recyclerView = view.findViewById(R.id.home_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
//        readFireBaseData
        homeReadFireBase = new HomeReadFireBase(activity);
        homeReadFireBase.readFireBaseData();

        recyclerView.setAdapter(homeReadFireBase.getAdapter());


    }


    @Override
    public void onStart() {
        super.onStart();
        homeReadFireBase.getAdapter().startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        homeReadFireBase.getAdapter().stopListening();
    }




    //  設定menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }


}

