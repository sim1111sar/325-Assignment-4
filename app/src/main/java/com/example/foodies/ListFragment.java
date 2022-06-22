package com.example.foodies;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.util.ArrayList;

public class ListFragment extends Fragment {
    private ArrayList<Restaurant> restaurants;
    private FoodiesAdapter adapter;
    private ListView listView;
    private Context context;
    private static ListFragment listFragment = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        context =  inflater.getContext();
        listView = view.findViewById(R.id.listview);
        restaurants = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
        adapter = new FoodiesAdapter(inflater.getContext(), R.layout.fragment_list_customize, restaurants);
        listView.setAdapter(adapter);

        return view;
    }
    public static ListFragment getInstance(){
        if (listFragment == null){
            listFragment = new ListFragment();
        }
        return listFragment;
    }
    public void updateListData (ArrayList<Restaurant> data){
       FoodiesAdapter adapter = new FoodiesAdapter(context, R.layout.fragment_list_customize, data);
       listView.setAdapter(adapter);
    }
}