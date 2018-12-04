package com.expamle.love.travelstory.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.hotCity.HotCity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendFragment extends Fragment {

    private View view;
    private AppCompatActivity activity;
    private Toolbar toolbar;
    private NestedScrollView scrollView;
    private LinearLayout linearLayout;
    private Drawable openDrawable;
    private Drawable closeDrawable;
    private AccelerateInterpolator interpolator;
    private BackDrop backDrop;
    private RecyclerView recyclerView;
    private List<HotCity> recommendList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_recommend,container,false);
//        setBackDrop
        setBackDrop();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        findView
        findView();
        recommendList = getCityList();
        RecommendAdater adater=new RecommendAdater();
        recyclerView.setAdapter(adater);
    }

    private void findView() {
        recyclerView = view.findViewById(R.id.recommend_recycler);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

    }

    private void setBackDrop() {
        setView();
        backDrop = new BackDrop(activity,toolbar,scrollView,linearLayout,openDrawable,closeDrawable,interpolator);
    }

    private void setView() {
        activity = (AppCompatActivity) getActivity();
        toolbar = view.findViewById(R.id.recommend_toolbar);
        scrollView = view.findViewById(R.id.recommend_backdrop_scroll);
        linearLayout = view.findViewById(R.id.recommend_backdrop_position);
        openDrawable = ContextCompat.getDrawable(activity,R.drawable.navigation_icon_close);
        closeDrawable = ContextCompat.getDrawable(activity,R.drawable.navigation_icon_close);
        interpolator = new AccelerateInterpolator();
    }
//    設定menu

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu,menu);
    }
    class RecommendAdater extends RecyclerView.Adapter<RecommendAdater.RecommendHolder> {
        private Random random= new Random();
        @NonNull
        @Override
        public RecommendHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=getLayoutInflater().inflate(R.layout.item_icon,viewGroup,false);
            return new RecommendHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final RecommendHolder recommendHolder, int i) {
            HotCity hotCity=recommendList.get(i);
            recommendHolder.cityName.setText(hotCity.getCityName());
            recommendHolder.cityIcon.setImageResource(hotCity.getCityPicture());
            recommendHolder.cityIcon.getLayoutParams().height=getRandomIntInRange(350,200);
            recommendHolder.cityIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

        }
        protected int getRandomIntInRange(int max, int min){

            return random.nextInt((max-min)+min)+min;
        }

        @Override
        public int getItemCount() {
            return recommendList.size();
        }

        class RecommendHolder extends RecyclerView.ViewHolder {
            ImageView recommendView;
            TextView recommendCity;
            TextView recommendAddress;
            ImageView cityIcon;
            TextView cityName;
            ConstraintLayout constraintLayout;
            public RecommendHolder(@NonNull View itemView) {
                super(itemView);
                cityIcon=itemView.findViewById(R.id.city_icon1);
                cityName=itemView.findViewById(R.id.city_name1);
                constraintLayout=itemView.findViewById(R.id.cons);
            }
        }
    }
    public List<HotCity> getCityList(){
        recommendList=new ArrayList<>();
        recommendList.add(new HotCity("台灣", R.drawable.taipei,"台北市信義區信義路五段7號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段8號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段9號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段10號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段11號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段12號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段13號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段14號"));
        recommendList.add(new HotCity("日本", R.drawable.okinawa,"台北市信義區信義路五段15號"));
        return recommendList;
    }
}
