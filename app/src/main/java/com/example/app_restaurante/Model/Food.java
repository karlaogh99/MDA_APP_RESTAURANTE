package com.example.app_restaurante.Model;

import java.io.Serializable;

public class Food {
    private String descripcion;
    private String name;
    private String id;
    private String image;
    private String price;

    public Food(String descripcion, String name, String id, String image, String price) {
        this.descripcion = descripcion;
        this.name = name;
        this.id = id;
        this.image = image;
        this.price = price;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
