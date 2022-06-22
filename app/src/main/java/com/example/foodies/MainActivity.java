package com.example.foodies;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {
    public static final int pages = 3;
    private ViewPager2 viewPager;
    private FragmentStateAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new ScreenSlideAdapter(this);
        viewPager.setAdapter(pagerAdapter);

        ListFragment listFragment = ListFragment.getInstance();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_container, listFragment).commit();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.Featured){
            ListFragment listFragment = ListFragment.getInstance();
            ArrayList<Restaurant> temp = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
            temp.removeIf(restaurant -> !restaurant.isFeatured());
            listFragment.updateListData(temp);
        }else if (id == R.id.rating){
            ListFragment listFragment = ListFragment.getInstance();
            ArrayList<Restaurant> temp = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
            temp.removeIf(restaurant -> restaurant.getRating() < 4);
            listFragment.updateListData(temp);
        }else if (id == R.id.notvegan) {

            ListFragment listFragment = ListFragment.getInstance();
            ArrayList<Restaurant> temp = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
            temp.removeIf(restaurant -> restaurant.isVegetarian());
            listFragment.updateListData(temp);
        }else if(id == R.id.vegan){
            ListFragment listFragment = ListFragment.getInstance();
            ArrayList<Restaurant> temp = (ArrayList<Restaurant>)DataSource.getRestaurantList().clone();
            temp.removeIf(restaurant -> !restaurant.isVegetarian());
            listFragment.updateListData(temp);
        }
        return super.onOptionsItemSelected(item);
    }
    private class ScreenSlideAdapter extends FragmentStateAdapter {
        public ScreenSlideAdapter(MainActivity mainActivity) {
            super(mainActivity);
        }
        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position){
                case 0:
                    return new PagerFragment1();
                case 1:
                    return new PagerFragment2();
                case 2:
                    return new PagerFragment3();
                default:
                    return null;
            }
        }
        @Override
        public int getItemCount() {
            return pages;
        }
    }
}