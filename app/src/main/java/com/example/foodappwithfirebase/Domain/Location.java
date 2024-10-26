package com.example.foodappwithfirebase.Domain;

public class Location {

    private int Id;
    private String loc;

    public Location(int id) {
        Id = id;
    }

    public Location(String loc) {
        this.loc = loc;
    }

    public Location(int id, String loc) {
        Id = id;
        this.loc = loc;
    }

    @Override
    public String toString() {
        return  loc ;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }
}
