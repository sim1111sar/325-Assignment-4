package com.example.foodies;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class PagerFragment2 extends Fragment {
    private ImageView imageView;
    private TextView address, title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_viewpager_item, container, false);
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
        restaurants.removeIf(restaurant -> !restaurant.isFeatured());

        imageView = view.findViewById(R.id.view_pager_image);
        address = view.findViewById(R.id.pager_address);
        title = view.findViewById(R.id.pager_title);

        imageView.setImageResource(restaurants.get(1).getBannerImage());
        address.setText(restaurants.get(1).getAddress1());
        title.setText(restaurants.get(1).getName());

        return (view);
    }
}
