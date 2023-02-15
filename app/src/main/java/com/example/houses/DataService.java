package com.example.houses;//@Override
//public View onCreateView(LayoutInflater inflater, ViewGroup container,
//        Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//        if(i != 0){
//        rootView = inflater.inflate(R.layout.fragment_magazine, container, false);
//        TextView magText = (TextView) rootView.findViewById(R.id.textViewMag);
//        new GetData("http://csddata.site11.com").execute();
//        }
//        }

import android.os.AsyncTask;
import android.util.Log;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

class DataService extends AsyncTask<String,Void,String> {
    protected String doInBackground(String... urls) {
        String text = "";
        try {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("https://reqres.in/api/users?page=2");
            HttpResponse response = null;
            try {
                response = httpclient.execute(httppost);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            HttpEntity httpEntity = response.getEntity();
            InputStream inputstream = null;
            try {
                inputstream = httpEntity.getContent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {

                BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream, "iso-8859-1"), 8);
                text = reader.readLine();

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            Log.d("STATE",text);
            return text;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}