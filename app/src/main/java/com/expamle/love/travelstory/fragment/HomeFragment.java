package com.expamle.love.travelstory.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.expamle.love.travelstory.CityActivity;
import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.hotCity.HotCity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    private static final String TAG = HomeFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private List<HotCity> hotCities;
    private Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);

    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        findViews();
////      list資料
//        hotCities = getCityList();
//        HomeAdater adater = new HomeAdater();
//        recyclerView.setAdapter(adater);
//
//    }
//
//
//
//    //      綁定
//    private void findViews() {
//        recyclerView = getActivity().findViewById(R.id.recycler_hot_city);
//        recyclerView.setHasFixedSize(false);
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//    }
//
//    //      Adater監聽器
//    class HomeAdater extends RecyclerView.Adapter<HomeAdater.HomeHolder> {
//
//        @NonNull
//        @Override
//        public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//            View view = getLayoutInflater().inflate(R.layout.item_icon, viewGroup, false);
//            return new HomeHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {
//            HotCity hotCity = hotCities.get(i);
//            homeHolder.hotCityName.setText(hotCity.getCityName());
//            homeHolder.hotCityView.setImageResource(hotCity.getCityPicture());
//            homeHolder.hotCityView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    startActivity(new Intent(getActivity(), CityActivity.class));
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return hotCities.size();
//        }
//
//        class HomeHolder extends RecyclerView.ViewHolder {
//            TextView hotCityName;
//            ImageView hotCityView;
//
//            public HomeHolder(@NonNull View itemView) {
//                super(itemView);
//                hotCityName = itemView.findViewById(R.id.city_name);
//                hotCityView = itemView.findViewById(R.id.city_icon);
//            }
//        }
//
//    }
//    public List<HotCity> getCityList(){
//        hotCities=new ArrayList<>();
//        hotCities.add(new HotCity("台灣", R.drawable.taipei));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        hotCities.add(new HotCity("日本", R.drawable.okinawa));
//        return hotCities;
//    }

}
