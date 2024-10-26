package com.example.foodappwithfirebase.Domain;

public class Time {
    private int id;
    private String Value;

    public Time(int id) {
        this.id = id;
    }

    public Time(String value) {
        Value = value;
    }

    public Time(int id, String value) {
        this.id = id;
        Value = value;
    }

    @Override
    public String toString() {
        return Value ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
