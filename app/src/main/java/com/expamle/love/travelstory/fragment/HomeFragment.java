package com.expamle.love.travelstory.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.json.CityJson;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
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
    private List<CityJson> cityJsons;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.home_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
//      取得Gson
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
                Log.d(TAG, "onResponse: " + json);
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
        HomeAdapter adapter = new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }

    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
        @NonNull
        @Override
        public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.item_icon, viewGroup, false);
            return new HomeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {
            CityJson cityJson = cityJsons.get(i);
            homeHolder.edText.setText(cityJson.getSubject());
            Picasso.get().load(cityJson.getUpImageUrl()).into(homeHolder.edImage);
        }

        @Override
        public int getItemCount() {
            return 10;
        }

        public class HomeHolder extends RecyclerView.ViewHolder {
            ImageView edImage;
            TextView edText;

            public HomeHolder(@NonNull View itemView) {
                super(itemView);
                edImage = itemView.findViewById(R.id.home_icon);
                edText = itemView.findViewById(R.id.home_name);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "onStart: "+"Start");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: "+"onResume");
    }
}
