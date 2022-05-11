package com.example.app_restaurante.Model;

import android.text.Editable;

public class Rating {
    private String Rating;
    private String Star;
    private String Id;


    public Rating(String Rating, String Star, String Id) {
        this.Rating = Rating;
        this.Star = Star;
        this.Id = Id;

    }

    public String getRating() {
        return Rating;
    }

    public void setRating(String Rating) {
        this.Rating = Rating;
    }

    public String getStar() {
        return Star;
    }

    public void setStar(String Star) {
        this.Star = Star;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id = Id;
    }

}
