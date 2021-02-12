package com.patel.service;

import com.patel.model.Driver;
import com.patel.model.Rider;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RatingService {

    List<Driver> driverList = new ArrayList<>();
    List<Rider> riderList = new ArrayList<>();

    public List<Driver> getDriverList() {
        return driverList;
    }

    public List<Rider> getRiderList() {
        return riderList;
    }

    public void assignDriverRatings(Map<String, List<Double>> driverRatingList) {
        driverRatingList.entrySet().forEach(val -> {
            String name = val.getKey();
            List<Double> ratings = val.getValue();
            double averageRating = 0.0;
            double sumOfRatings = 0.0;
            for(double rating: ratings){
                sumOfRatings += rating;
            }
            averageRating = sumOfRatings/ratings.size();
            Driver driver = new Driver(name,averageRating);
            driverList.add(driver);
        });
    }

    public void assignRiderRatings(Map<String, List<Double>> riderRatingList) {
        riderRatingList.entrySet().forEach(val -> {
            String name = val.getKey();
            List<Double> ratings = val.getValue();
            double averageRating = 0.0;
            double sumOfRatings = 0.0;
            for(double rating: ratings){
                sumOfRatings += rating;
            }
            averageRating = sumOfRatings/ratings.size();
            Rider rider = new Rider(name,averageRating);
            riderList.add(rider);
        });
    }

    public Driver getOptimalDriver(Rider rider, Map<String, List<String>> blockedDrivers) {

        //shouldn't have got 1 rating from rider
        List<Driver>acceptableDrivers = driverList.stream().filter(driver -> {
            return blockedDrivers.get(rider.getName())==null || !blockedDrivers.get(rider.getName()).contains(driver.getName());
        }).collect(Collectors.toList());

        for(Driver driver: acceptableDrivers){
            System.out.println("ACCEPTABLE: "+driver.getName());
        }

        //best driver
        Driver optimalDriver = getBestAmongAcceptableDrivers(acceptableDrivers);
        return optimalDriver;
    }

    private Driver getBestAmongAcceptableDrivers(List<Driver> acceptableDrivers) {
        Driver dummyDriver = new Driver("DUMMY", -1);
        for(Driver driver: acceptableDrivers){
            if(driver.getAverageRating()>dummyDriver.getAverageRating()){
                dummyDriver.setName(driver.getName());
                dummyDriver.setAverageRating(driver.getAverageRating());
            }
        }
        return dummyDriver;
    }
}
