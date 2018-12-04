package com.expamle.love.travelstory.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Interpolator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.expamle.love.travelstory.CityActivity;
import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.hotCity.HotCity;
import com.expamle.love.travelstory.json.CityJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<HotCity> hotCities;
    private Toolbar toolbar;
    private View view;
    private AppCompatActivity activity;
    private BackDrop backDrop;
    private NestedScrollView scrollView;
    private LinearLayout linearLayout;
    private Drawable openDrawable;
    private Drawable closeDrawable;
    private AccelerateDecelerateInterpolator interpolator;
    private List<CityJson> cityJsons;
    private ImageView cityView;
    private CityJson cityJson;


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

//      list資料
//        hotCities = getCityList();

        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.set(8, 8, 8, 0);
            }
        });


    }

    private void getJson() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://www.fun2tw.com/TravelSchdule/ListByThemeJson")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String json = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        parseGson(json);
                    }
                });
            }
        });
    }

    private void parseGson(String json) {
//        Gson轉換
        Gson gson = new Gson();
        cityJsons = gson.fromJson(json,
                new TypeToken<ArrayList<CityJson>>() {
                }.getType());
        HomeAdater adater = new HomeAdater();
        recyclerView.setAdapter(adater);
    }


   //      綁定
    private void findViews() {
        recyclerView = view.findViewById(R.id.home_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(activity, 1));
        getJson();
    }

    //      Adater監聽器
    class HomeAdater extends RecyclerView.Adapter<HomeAdater.HomeHolder> {

        @NonNull
        @Override
        public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.cardview, viewGroup, false);
            return new HomeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {
//              自創資料
//            myList(homeHolder, i);

            cityJson = cityJsons.get(i);
            homeHolder.hotCityAddressName.setText(cityJson.getSubject());
            Glide.with(activity)
                    .load(cityJson.getUpImageUrl())
                    .into(homeHolder.hotCityView);

        }

        //        自創資料
        private void myList(@NonNull HomeHolder homeHolder, int i) {
            HotCity hotCity = hotCities.get(i);
            homeHolder.hotCityName.setText(hotCity.getCityName());
            homeHolder.hotCityAddress.setText(hotCity.getAddress());
            homeHolder.hotCityView.setImageResource(hotCity.getCityPicture());
        }

        @Override
        public int getItemCount() {
            return cityJsons.size();
        }

        class HomeHolder extends RecyclerView.ViewHolder {

            ImageView hotCityView;
            TextView hotCityName;
            TextView hotCityAddressName;
            TextView hotCityAddress;

            public HomeHolder(@NonNull View itemView) {
                super(itemView);
                hotCityView = itemView.findViewById(R.id.city_icon);
                hotCityName = itemView.findViewById(R.id.city_name);
                hotCityAddressName=itemView.findViewById(R.id.city_address_name);
                hotCityAddress = itemView.findViewById(R.id.city_address);
            }
        }

    }

    //  設定menu
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.home_menu, menu);
    }


    public List<HotCity> getCityList() {
        hotCities = new ArrayList<>();
        hotCities.add(new HotCity("台灣", R.drawable.taipei, "台北市信義區信義路五段7號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段8號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段9號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段10號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段11號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段12號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段13號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段14號"));
        hotCities.add(new HotCity("日本", R.drawable.okinawa, "台北市信義區信義路五段15號"));
        return hotCities;
    }
}

