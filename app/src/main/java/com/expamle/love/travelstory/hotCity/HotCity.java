package com.expamle.love.travelstory.hotCity;

public class HotCity {
    private String cityName;
    private String address;
    private int cityPicture;


    public HotCity(String cityName, int cityPicture,String address) {
        this.cityName = cityName;
        this.cityPicture = cityPicture;
        this.address=address;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityPicture() {
        return cityPicture;
    }

    public void setCityPicture(int cityPicture) {
        this.cityPicture = cityPicture;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
