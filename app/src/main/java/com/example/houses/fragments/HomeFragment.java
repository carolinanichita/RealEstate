package com.example.houses.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.houses.HouseAdapter;
import com.example.houses.HouseModel;
import com.example.houses.R;
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HomeFragment extends Fragment {
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<HouseModel> housesList = new ArrayList<>();
    ArrayList<HouseModel> searchList;
//    public HomeFragment() {
//        // Required empty public constructor
//    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.recyclerView);
        searchView.setIconified(false);
        searchView.clearFocus();

        this.getDataFromBE();

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
//        recyclerView.setLayoutManager(layoutManager);
//
//        HouseAdapter houseAdapter = new HouseAdapter(getContext(), housesList);
//        recyclerView.setAdapter(houseAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchList = new ArrayList<>();

                if (query.length() > 0) {
                    for (int i = 0; i < housesList.size(); i++) {
                        if (housesList.get(i).getCity().toUpperCase().contains(query.toUpperCase()) || housesList.get(i).getZip().toUpperCase().contains(query.toUpperCase())) {
                            HouseModel houseModel = new HouseModel();
                            houseModel.setId(housesList.get(i).getId());
                            houseModel.setImage(housesList.get(i).getImage());
                            houseModel.setPrice(housesList.get(i).getPrice());
                            houseModel.setBedrooms(housesList.get(i).getBedrooms());
                            houseModel.setBathrooms(housesList.get(i).getBathrooms());
                            houseModel.setSize(housesList.get(i).getSize());
                            houseModel.setDescription(housesList.get(i).getDescription());
                            houseModel.setZip(housesList.get(i).getZip());
                            houseModel.setCity(housesList.get(i).getCity());
                            houseModel.setLatitude(housesList.get(i).getLatitude());
                            houseModel.setLongitude(housesList.get(i).getLongitude());
                            houseModel.setCreatedDate(housesList.get(i).getCreatedDate());
                            searchList.add(houseModel);
                        }
                    }

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    HouseAdapter houseAdapter = new HouseAdapter(getContext(), searchList);
                    recyclerView.setAdapter(houseAdapter);
                } else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    HouseAdapter houseAdapter = new HouseAdapter(getContext(), housesList);
                    recyclerView.setAdapter(houseAdapter);
                }

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList = new ArrayList<>();

                if (newText.length() > 0) {
                    for (int i = 0; i < housesList.size(); i++) {
                        if (housesList.get(i).getCity().toUpperCase().contains(newText.toUpperCase()) || housesList.get(i).getZip().toUpperCase().contains(newText.toUpperCase())) {
                            HouseModel houseModel = new HouseModel();
                            houseModel.setId(housesList.get(i).getId());
                            houseModel.setImage(housesList.get(i).getImage());
                            houseModel.setPrice(housesList.get(i).getPrice());
                            houseModel.setBedrooms(housesList.get(i).getBedrooms());
                            houseModel.setBathrooms(housesList.get(i).getBathrooms());
                            houseModel.setSize(housesList.get(i).getSize());
                            houseModel.setDescription(housesList.get(i).getDescription());
                            houseModel.setZip(housesList.get(i).getZip());
                            houseModel.setCity(housesList.get(i).getCity());
                            houseModel.setLatitude(housesList.get(i).getLatitude());
                            houseModel.setLongitude(housesList.get(i).getLongitude());
                            houseModel.setCreatedDate(housesList.get(i).getCreatedDate());
                            searchList.add(houseModel);

                        }
                    }

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    HouseAdapter houseAdapter = new HouseAdapter(view.getContext(), searchList);
                    recyclerView.setAdapter(houseAdapter);
                } else {
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(view.getContext());
                    recyclerView.setLayoutManager(layoutManager);

                    HouseAdapter houseAdapter = new HouseAdapter(view.getContext(), housesList);
                    recyclerView.setAdapter(houseAdapter);
                }

                return false;
            }
        });

        return view;
    }

    public void getDataFromBE() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://intern.d-tt.nl/api/house")
                .method("GET", null)
                .header("Access-Key", "98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    final String stringResponse = response.body().string();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("STATE", stringResponse);
//                            textView.setText(myResponse);
//                            setDataIntoList(myList);
                            parseJSON(stringResponse);
                        }
                    });
                }
            }
        });
    }

    public void parseJSON(String responseString) {
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<HouseModel>>() {
        }.getType();
        ArrayList<HouseModel> housesArr = gson.fromJson(responseString, type);

        Log.i("TAG", "parseJSON: ");

//        for (HouseModel house : housesArr) {
//            HouseModel houseModel = new HouseModel();
//            houseModel.setId(house.getId());
//            houseModel.setImage(house.getImage());
//            houseModel.setPrice(house.getPrice());
//            houseModel.setBedrooms(house.getBedrooms());
//            houseModel.setBathrooms(house.getBathrooms());
//            houseModel.setSize(house.getSize());
//            houseModel.setDescription(house.getDescription());
//            houseModel.setZip(house.getZip());
//            houseModel.setCity(house.getCity());
//            houseModel.setLatitude(house.getLatitude());
//            houseModel.setLongitude(house.getLongitude());
//            houseModel.setCreatedDate(house.getCreatedDate());
//            housesList.add(houseModel);
//        }

        for (int i = 0; i < housesArr.size(); i++) {
            HouseModel houseModel = new HouseModel();
            houseModel.setId(housesArr.get(i).getId());
            houseModel.setImage(housesArr.get(i).getImage());
            houseModel.setPrice(housesArr.get(i).getPrice());
            houseModel.setBedrooms(housesArr.get(i).getBedrooms());
            houseModel.setBathrooms(housesArr.get(i).getBathrooms());
            houseModel.setSize(housesArr.get(i).getSize());
            houseModel.setDescription(housesArr.get(i).getDescription());
            houseModel.setZip(housesArr.get(i).getZip());
            houseModel.setCity(housesArr.get(i).getCity());
            houseModel.setLatitude(housesArr.get(i).getLatitude());
            houseModel.setLongitude(housesArr.get(i).getLongitude());
            houseModel.setCreatedDate(housesArr.get(i).getCreatedDate());
            housesList.add(houseModel);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        HouseAdapter houseAdapter = new HouseAdapter(getContext(), housesList);
        recyclerView.setAdapter(houseAdapter);
    }

//    public void setDataIntoList(ArrayList<HouseModel> data) {
//        for (int i = 0; i < data.toArray().length; i++) {
//            HouseModel houseModel = new HouseModel();
//            houseModel.setId(data.toArray()[i].);
//            houseModel.setImage(data[i].);
//            houseModel.setPrice(data[i].);
//            houseModel.setBedrooms(data[i].);
//            houseModel.setBathrooms(data[i].);
//            houseModel.setSize(data[i].);
//            houseModel.setDescription(data[i].);
//            houseModel.setZip(data[i].);
//            houseModel.setCity(data[i].);
//            houseModel.setLatitude(data[i].);
//            houseModel.setLongitude(data[i].);
//            houseModel.setCreatedDate(data[i].);
//            housesList.add(data.getString(i));
//        }
//    }
}