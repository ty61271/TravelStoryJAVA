package com.expamle.love.travelstory.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.expamle.love.travelstory.R;
import com.expamle.love.travelstory.sql.SQLlist;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private List<SQLlist> sqlLists;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home,container,false);
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        sqlLists = new ArrayList<>();
        sqlLists.add(new SQLlist("1",R.drawable.func_balance));
        sqlLists.add(new SQLlist("2",R.drawable.func_contacts));
        sqlLists.add(new SQLlist("3",R.drawable.func_exit));
        sqlLists.add(new SQLlist("4",R.drawable.func_finance));
        sqlLists.add(new SQLlist("5",R.drawable.func_transaction));
        sqlLists.add(new SQLlist("6",R.drawable.func_balance));
        sqlLists.add(new SQLlist("7",R.drawable.func_balance));
        sqlLists.add(new SQLlist("8",R.drawable.func_balance));
        sqlLists.add(new SQLlist("9",R.drawable.func_balance));
        findSuggestionView();


    }

    private void findSuggestionView() {
        RecyclerView recyclerView=getActivity().findViewById(R.id.home_recycler);
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HomeAdapter adapter=new HomeAdapter();
        recyclerView.setAdapter(adapter);
    }
    public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeHolder> {
        @NonNull
        @Override
        public HomeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view=getLayoutInflater().inflate(R.layout.item_icon,viewGroup,false);
            return new HomeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull HomeHolder homeHolder, int i) {
            SQLlist sqLlist=sqlLists.get(i);
            homeHolder.imageView.setImageResource(sqLlist.getId());
            homeHolder.textView.setText(sqLlist.getName());
        }

        @Override
        public int getItemCount() {
            return sqlLists.size();
        }

        public class HomeHolder extends RecyclerView.ViewHolder{
            ImageView imageView;
            TextView textView;
            public HomeHolder(@NonNull View itemView) {
                super(itemView);
                imageView=itemView.findViewById(R.id.home_icon);
                textView=itemView.findViewById(R.id.home_name);
            }
        }
    }
}
