package com.example.mytravelguide;

/**
 * Created by Samet ERDEM on 25.06.2020.
 */
public class TravelList {
    String title;
    String country;
    String city;
    String description;
    Boolean isVisited;

    public TravelList(String title, String country, String city, String description, Boolean isVisited) {
        this.title = title;
        this.country = country;
        this.city = city;
        this.description = description;
        this.isVisited = isVisited;
    }

    public String getTitle() {
        return title;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getVisited() {
        return isVisited;
    }

}
