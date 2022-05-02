package com.example.app_restaurante.Model;

import java.io.Serializable;

public class Food implements Serializable{
    private String description;
    private String name;
    private String id;
    public String idcategory;

    private String price;

    public Food(String description, String name, String id, String image, String price) {
        this.description = description;
        this.name = name;
        this.id = id;

        this.price = price;
    }

    public String getDescripcion() {
        return description;
    }

    public void setDescripcion(String descripcion) {
        this.description = descripcion;
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


    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
