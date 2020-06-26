package com.example.mytravelguide;

/**
 * Created by Samet ERDEM on 25.06.2020.
 */
public class TravelModel {
    private int ID;
    private String Title;
    private String Country;
    private String City;
    private String Description;
    private int Visited;

    public int getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getVisited() {
        return Visited;
    }

    public void setVisited(int visited) {
        Visited = visited;
    }
}
