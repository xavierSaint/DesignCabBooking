package com.patel.model;

public class Rider {
    private String name;
    private double averageRating;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public Rider(String name, double averageRating) {
        this.name = name;
        this.averageRating = averageRating;
    }
}
