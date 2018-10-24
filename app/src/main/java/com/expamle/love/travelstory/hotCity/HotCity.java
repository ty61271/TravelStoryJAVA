package com.expamle.love.travelstory.hotCity;

public class HotCity {
    private String cityName;
    private int cityPicture;

    public HotCity(String cityName, int cityPicture) {
        this.cityName = cityName;
        this.cityPicture = cityPicture;
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
}
