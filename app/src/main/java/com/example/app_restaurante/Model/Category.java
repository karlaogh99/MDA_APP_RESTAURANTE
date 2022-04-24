package com.example.app_restaurante.Model;

import java.io.Serializable;

public class Category implements Serializable {

    private String image;
    private String name;

    public Category(String image, String name) {
        this.image = image;
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
