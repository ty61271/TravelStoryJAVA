package com.expamle.love.travelstory.sql;

public class MyFireBaseData {
    String place;
    String picture;
    String address;
    int favoriteCount;
    boolean myFavorite;

    public boolean isMyFavorite() {
        return myFavorite;
    }

    public void setMyFavorite(boolean myFavorite) {
        this.myFavorite = myFavorite;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
