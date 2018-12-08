package com.expamle.love.travelstory;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class CityActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
        findViews();

    }

    private void findViews() {
        imageView = findViewById(R.id.app_bar_image);
        Glide.with(this)
                .load(getIntent().getStringExtra("IMAGE_PIC"))
                .into(imageView);
        textView = findViewById(R.id.place);
        textView.setText(getIntent().getStringExtra("PLACE"));
    }
}
