package com.expamle.love.travelstory.sql;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.expamle.love.travelstory.CityActivity;
import com.expamle.love.travelstory.R;
import com.firebase.ui.common.ChangeEventType;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class HomeReadFireBase {

    private static final String TAG = HomeReadFireBase.class.getSimpleName();
    private FirebaseRecyclerAdapter<MyFireBaseData, HomeHolder> adapter;
    private AppCompatActivity context;

    public HomeReadFireBase(AppCompatActivity context) {
        this.context = context;
    }

    public FirebaseRecyclerAdapter<MyFireBaseData, HomeHolder> getAdapter() {
        return adapter;
    }

    public void readFireBaseData() {
        Query query = FirebaseDatabase.getInstance().getReference("travel")
                .child("taiwan");
        FirebaseRecyclerOptions<MyFireBaseData> options = new FirebaseRecyclerOptions.Builder<MyFireBaseData>()
                .setQuery(query, MyFireBaseData.class)
                .build();
        adapter = new FirebaseRecyclerAdapter<MyFireBaseData, HomeHolder>(options) {

            @NonNull
            @Override
            public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                LayoutInflater view = LayoutInflater.from(context);
                View myView = view.inflate(R.layout.cardview, viewGroup, false);
                return new HomeHolder(myView);
            }

            @Override
            public void onChildChanged(@NonNull ChangeEventType type, @NonNull DataSnapshot snapshot, int newIndex, int oldIndex) {
                super.onChildChanged(type, snapshot, newIndex, oldIndex);

            }

            @Override
            protected void onBindViewHolder(@NonNull final HomeHolder holder, final int position, @NonNull final MyFireBaseData model) {
                if (model.getFavoriteCount() != 0) {
                    holder.favoriteCount.setText(String.valueOf(model.getFavoriteCount()));
                } else {
                    holder.favoriteCount.setText("");
                }
                holder.favoriteView.setImageResource(model.isMyFavorite() ? R.drawable.ic_favorite_black_24dp : R.drawable.ic_favorite_border_black_24dp);
                holder.hotCityAddressName.setText(model.getPlace());
                holder.hotCityAddress.setText(model.getAddress());
                Glide.with(context)
                        .load(model.getPicture())
                        .into(holder.hotCityView);


                holder.hotCityView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, CityActivity.class);
                        intent.putExtra("IMAGE_PIC", model.getPicture());
                        intent.putExtra("PLACE", model.getPlace());
                        context.startActivity(intent);
                    }
                });


                holder.favoriteView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (model.getFavoriteCount() == 0) {
                            FirebaseDatabase.getInstance().getReference("travel")
                                    .child("taiwan")
                                    .child(String.valueOf(position))
                                    .child("favoriteCount")
                                    .setValue(1);
                            FirebaseDatabase.getInstance().getReference("travel")
                                    .child("taiwan")
                                    .child(String.valueOf(position))
                                    .child("myFavorite")
                                    .setValue(true);
                        } else {
                            if (!model.isMyFavorite()) {
                                FirebaseDatabase.getInstance().getReference("travel")
                                        .child("taiwan")
                                        .child(String.valueOf(position))
                                        .child("favoriteCount")
                                        .setValue(Integer.parseInt(holder.favoriteCount.getText().toString()) + 1);
                                FirebaseDatabase.getInstance().getReference("travel")
                                        .child("taiwan")
                                        .child(String.valueOf(position))
                                        .child("myFavorite")
                                        .setValue(true);
                            } else {
                                FirebaseDatabase.getInstance().getReference("travel")
                                        .child("taiwan")
                                        .child(String.valueOf(position))
                                        .child("favoriteCount")
                                        .setValue(Integer.parseInt(holder.favoriteCount.getText().toString()) - 1);
                                FirebaseDatabase.getInstance().getReference("travel")
                                        .child("taiwan")
                                        .child(String.valueOf(position))
                                        .child("myFavorite")
                                        .setValue(false);
                            }
                        }
                    }
                });
            }
        };
    }

    class HomeHolder extends RecyclerView.ViewHolder {

        ImageView hotCityView;
        TextView hotCityName;
        TextView hotCityAddressName;
        TextView hotCityAddress;
        CardView cardView;
        ImageView favoriteView;
        TextView favoriteCount;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardview);
            hotCityView = itemView.findViewById(R.id.city_icon);
            hotCityName = itemView.findViewById(R.id.city_name);
            hotCityAddressName = itemView.findViewById(R.id.city_address_name);
            hotCityAddress = itemView.findViewById(R.id.city_address);
            favoriteView = itemView.findViewById(R.id.card_favorite);
            favoriteCount = itemView.findViewById(R.id.card_favorite_count);
        }

    }
}
