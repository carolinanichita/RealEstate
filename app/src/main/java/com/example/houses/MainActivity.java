package com.example.houses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.houses.fragments.HomeFragment;
import com.example.houses.fragments.InfoFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    HomeFragment Home_fragment = new HomeFragment();
    InfoFragment Info_fragment = new InfoFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setItemIconTintList(ContextCompat.getColorStateList(MainActivity.this, R.color.home_menu_color));
        bottomNavigationView.setSelectedItemId(R.id.homeMenu);

//        bottomNavigationView.selectedItemId = R.id.page_2

//        bottomNavigationView.setItemBackgroundResource(android.R.color.holo_red_light);

//        bottomNavigationView.setItemIconTintList(null);

//        public void setIconTint(ColorStateList iconTint);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, Home_fragment).commit();
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeMenu:
//                        bottomNavigationView.setSelectedItemId(R.id.homeMenu);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, Home_fragment).commit();
                        break;

                    case R.id.infoMenu:
//                        bottomNavigationView.setSelectedItemId(R.id.infoMenu);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, Info_fragment).commit();
                        break;

                    default:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, Home_fragment).commit();
                }
                return false;
            }

        });

//        bottomNavigationView.setOnItemSelectedListener() { item ->
//                when(item.itemId) {
//            R.id.item1 -> {
//                // Respond to navigation item 1 reselection
//            }
//            R.id.item2 -> {
//                // Respond to navigation item 2 reselection
//            }
//        }
//        }

//        bnv.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.action_tab1 -> {
//                    tv.text = getString(R.string.str_bnv_tab1) //"Tab 1"
//                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab1))
//                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv1)
//                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv1)
//                }
//                R.id.action_tab2 -> {
//                    tv.text = getString(R.string.str_bnv_tab2) //"Tab 2"
//                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab2))
//                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv2)
//                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv2)
//                }
//                R.id.action_tab3 -> {
//                    tv.text = getString(R.string.str_bnv_tab3) //"Tab 3"
//                    tv.setTextColor(ContextCompat.getColor(this, R.color.colorTab3))
//                    bnv.itemIconTintList = ContextCompat.getColorStateList(this, R.color.color_bnv3)
//                    bnv.itemTextColor = ContextCompat.getColorStateList(this, R.color.color_bnv3)
//                }
//            }
//            true
//        }
//    }

//    bottomNavigationView.OnItemSelectedListener { item ->
//            when(item.itemId) {
//        R.id.item1 -> {
//            // Respond to navigation item 1 click
//            true
//        }
//        R.id.item2 -> {
//            // Respond to navigation item 2 click
//            true
//        }
//        else -> false
//    }
    }
}