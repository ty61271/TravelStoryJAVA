package com.expamle.love.travelstory.json;


import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;

public class CityJson {
    @SerializedName("Subject")
    String subject;

    public String getUpImageUrl() {
        return upImageUrl;
    }

    public void setUpImageUrl(String upImageUrl) {
        this.upImageUrl = upImageUrl;
    }

    @SerializedName("UpImageUrl")
    String upImageUrl;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
       this.subject = subject;
    }
}
