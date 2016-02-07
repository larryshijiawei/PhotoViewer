package com.example.jiaweishi.photoviewer;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    private static final String URL_TWEETS = "https://api.instagram.com/v1/media/popular?client_id=e05c462ebd86446ea48a5af73769b602";

    private ArrayList<Photo> photoList;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                fetchPhotos();
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);


        fetchPhotos();

    }

    //Execute a Http call to fetch the tweets
    private void fetchPhotos(){
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(URL_TWEETS, new RequestParams(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                displayMessage("Status Code is " + statusCode);

                parsePhotos(response);

                displayPhotos();

                swipeContainer.setRefreshing(false);
            }


            @Override
            public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                displayMessage("Status code is " + statusCode);
            }
        });
    }

    private void parsePhotos(JSONObject jsonObject){
        try {
            photoList = new ArrayList<>();
            JSONArray photos = jsonObject.getJSONArray("data");
            for(int i=0; i<photos.length(); i++){
                photoList.add(new Photo(photos.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void displayPhotos(){

        PhotoAdapter photoAdapter = new PhotoAdapter(this, photoList);
        ListView listView = (ListView) findViewById(R.id.lv_photos);
        listView.setAdapter(photoAdapter);
    }

    private void displayMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG);
    }
}


