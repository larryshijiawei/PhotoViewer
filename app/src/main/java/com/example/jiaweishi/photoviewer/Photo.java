package com.example.jiaweishi.photoviewer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jiaweishi on 2/5/16.
 */
public class Photo {
    private String photoUrl;
    private String caption;
    private String userName;

    public Photo(JSONObject jsonObject){
        try {
            photoUrl = jsonObject.getJSONObject("images").getJSONObject("thumbnail").getString("url");
            if(jsonObject.has("caption"))
                caption = jsonObject.getJSONObject("caption").getString("text");
            userName = jsonObject.getJSONObject("user").getString("username");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


}
