package com.example.houses.fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.houses.HouseModel;
import com.example.houses.R;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.ClientProtocolException;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpGet;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;


import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class HomeFragment extends Fragment {
//    TextView textView;
    SearchView searchView;
    RecyclerView recyclerView;
    ArrayList<HouseModel> housesList = new ArrayList<>();

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
//        textView = view.findViewById(R.id.textView7);
        searchView = view.findViewById(R.id.searchView);
        recyclerView = view.findViewById(R.id.recyclerView);
//        textView.setText("message dsfgfds gfds");

        this.getDataFromBE();
        return view;
    }

    public void getDataFromBE() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://intern.d-tt.nl/api/house")
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
                    JSONArray array;

                    try {
                        array = new JSONArray(stringResponse);
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }


//                    MediaType contentType = response.body().contentType();
//                    ResponseBody body = ResponseBody.create(contentType, ArrayList<HouseModel>);
//                    ArrayList<HouseModel> dataResponse = response.newBuilder().body(body).build();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("STATE",stringResponse);
//                            textView.setText(myResponse);
                            setDataIntoList(array);
                        }
                    });
                }
            }
        });
    }

    public void setDataIntoList(JSONArray data) {
        for (int i = 0; i < data.length(); i++) {
            HouseModel houseModel = new HouseModel();
            houseModel.setId(data[i]);
            houseModel.setImage(data[i].);
            houseModel.setPrice(data[i].);
            houseModel.setBedrooms(data[i].);
            houseModel.setBathrooms(data[i].);
            houseModel.setSize(data[i].);
            houseModel.setDescription(data[i].);
            houseModel.setZip(data[i].);
            houseModel.setCity(data[i].);
            houseModel.setLatitude(data[i].);
            houseModel.setLongitude(data[i].);
            houseModel.setCreatedDate(data[i].);
            housesList.add(data.getString(i));
        }
    }
}