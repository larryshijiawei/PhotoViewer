package com.example.jiaweishi.photoviewer;

import android.text.format.DateUtils;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jiaweishi on 2/5/16.
 */
public class Photo {
    private String photoUrl;
    private String caption;
    private String userName;
    private String userProfileImageUrl;
    private int likeCount;
    private long timeStamp;


    public Photo(JSONObject jsonObject){
       initFromJSON(jsonObject);
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public String getUserName() {
        return userName;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public String getRelativeTimeStamp(){
        return DateUtils.getRelativeTimeSpanString(this.timeStamp*1000).toString();
    }

    public String getUserProfileImageUrl() {
        return userProfileImageUrl;
    }

    private void initFromJSON(JSONObject jsonObject){
        try {
            photoUrl = jsonObject.getJSONObject("images").getJSONObject("standard_resolution").getString("url");

            if(jsonObject.optJSONObject("caption") != null) {
                caption = jsonObject.getJSONObject("caption").getString("text");
                timeStamp = jsonObject.getJSONObject("caption").getLong("created_time");
            }
            else
                caption = "";

            userName = jsonObject.getJSONObject("user").getString("username");
            userProfileImageUrl = jsonObject.getJSONObject("user").getString("profile_picture");


            likeCount = jsonObject.getJSONObject("likes").getInt("count");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
