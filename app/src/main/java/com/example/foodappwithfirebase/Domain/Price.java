package com.example.foodappwithfirebase.Domain;

public class Price {

    private int id;
    private String Value;

    public Price(int id, String value) {
        this.id = id;
        Value = value;
    }

    public Price(String value) {
        Value = value;
    }

    public Price() {
    }

    public Price(int id) {
        this.id = id;
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
