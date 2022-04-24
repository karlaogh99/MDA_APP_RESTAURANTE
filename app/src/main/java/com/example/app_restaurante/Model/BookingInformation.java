package com.example.app_restaurante.Model;

import java.sql.Timestamp;

public class BookingInformation {
    private String customerName;
    private Timestamp date;

    public BookingInformation(String customerName, Timestamp date) {
        this.customerName = customerName;
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
