package com.example.foodies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;


public class FoodiesAdapter extends ArrayAdapter <Restaurant>{

    private  Context mContext;
    private int mResource;
    private ArrayList<Restaurant> restaurants;

    public FoodiesAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Restaurant> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
        this.restaurants = objects;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent, false);

        Restaurant restaurant = restaurants.get(position);
        ImageView list_image = convertView.findViewById(R.id.list_image);
        TextView address_title_list = convertView.findViewById(R.id.address_title_list);
        TextView rating_list = convertView.findViewById(R.id.rating_list);
        ImageView vegan_list = convertView.findViewById(R.id.vegan_list);
        TextView price_list = convertView.findViewById(R.id.price_list);
        TextView delivery_time_list = convertView.findViewById(R.id.delivery_time_list);
        list_image.setImageResource(restaurant.getBannerImage());
        address_title_list.setText(restaurant.getAddress1()  +", "+ restaurant.getCity() + ", " + restaurant.getPostalCode());
        rating_list.setText(restaurant.getRating() + "");
        if (restaurant.isVegetarian() == true){
           vegan_list.setImageResource(R.drawable.check);
        } else {
            vegan_list.setImageDrawable(null);
        }
        price_list.setText(restaurant.getMinPrice() + "");
        delivery_time_list.setText(restaurant.getDeliveryTime());

        return convertView;
    }
}
