package com.example.houses;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.houses.fragments.HomeFragment;

public class HouseDetailsActivity extends AppCompatActivity implements SelectListenerInterface {
    Button back;
    TextView priceView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_details);
        priceView = findViewById(R.id.textViewHomePrice);
        imageView = findViewById(R.id.imageView15);

        String price = getIntent().getStringExtra("PRICE");
        String image = getIntent().getStringExtra("IMAGE");

        priceView.setText(price);
        imageView.setImageResource(Integer.parseInt(image));

        back = findViewById(R.id.backButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}