package com.example.houses;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HouseAdapter extends RecyclerView.Adapter<HouseAdapter.MyHolder> {
    Context context;
    ArrayList<HouseModel> houseList;
    LayoutInflater layoutInflater;

    public HouseAdapter(Context context, ArrayList<HouseModel> houseList) {
        this.context = context;
        this.houseList = houseList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public HouseAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.house_item, parent, false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HouseAdapter.MyHolder holder, int position) {
//        holder.image.setImageURI(Uri.parse(houseList.get(position).getImage()));
//        holder.image.setImageURI(Uri.parse("https://cdn.pixabay.com/photo/2019/10/13/20/07/house-4547140_640.jpg"));
        holder.city.setText(houseList.get(position).getCity());
        holder.price.setText(String.valueOf(houseList.get(position).getPrice()));
        holder.bedrooms.setText(String.valueOf(houseList.get(position).getBedrooms()));
        holder.bathrooms.setText(String.valueOf(houseList.get(position).getBathrooms()));
        holder.size.setText(String.valueOf(houseList.get(position).getSize()));
        holder.zip.setText(houseList.get(position).getZip());
    }

    @Override
    public int getItemCount() {
        return houseList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        int id;
        ImageView image;
        TextView price, bedrooms, bathrooms, size, zip, city, latitude, longitude, createdDate;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.imageHouse);
            price = itemView.findViewById(R.id.textViewPrice);
            bedrooms = itemView.findViewById(R.id.textViewBedroomsNumber);
            bathrooms = itemView.findViewById(R.id.textViewBathroomsNumber);
            size = itemView.findViewById(R.id.textViewHouseSize);
            zip = itemView.findViewById(R.id.zipCode);
            city = itemView.findViewById(R.id.city);
//            latitude = itemView.findViewById(R.id.la);
//            longitude = itemView.findViewById(R.id.imageHouse);
//            createdDate = itemView.findViewById(R.id.imageHouse);
        }
    }
}
