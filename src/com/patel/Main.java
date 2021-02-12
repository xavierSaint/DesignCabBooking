package com.patel;

import com.patel.model.Driver;
import com.patel.model.Rider;
import com.patel.service.RatingService;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<String, List<Double>> driverRatingList = new HashMap<>();
        Map<String, List<Double>> riderRatingList = new HashMap<>();
        Map<String, List<String>> blockedDrivers = new HashMap<>();
        Map<String, List<String>> blockedRiders = new HashMap<>();

        int inputLines = scanner.nextInt();
        String line = scanner.nextLine();

        while(inputLines-->0){
            line = scanner.nextLine();
            // RideNumber - (Driver, DriverRating, Rider, RiderRating)
            /*

            6
            Ride1 - (Driver1, 1, Rider1, 5)
            Ride2 - (Driver2, 1, Rider1, 5)
            Ride3 - (Driver1, 5, Rider2, 5)
            Ride4 - (Driver2, 1, Rider2, 5)
            Ride5 - (Driver1, 5, Rider3, 5)
            Ride6 - (Driver3, 3, Rider3, 5)

             */
            String ratingDetails = line.split("-")[1].trim();
            ratingDetails = ratingDetails.substring(1,ratingDetails.indexOf(')'));
            String driverName = ratingDetails.split(",")[0].trim();
            double driverRating = Double.parseDouble(ratingDetails.split(",")[1].trim());
            String riderName = ratingDetails.split(",")[2].trim();
            double riderRating = Double.parseDouble(ratingDetails.split(",")[3].trim());
            if(driverRating<=1){
                if(blockedDrivers.containsKey(riderName)){
                    blockedDrivers.get(riderName).add(driverName);
                }
                else{
                    blockedDrivers.put(riderName,new ArrayList<>());
                    blockedDrivers.get(riderName).add(driverName);
                }
            }
            if(riderRating<=1){
                if(blockedRiders.containsKey(driverName)){
                    blockedRiders.get(driverName).add(riderName);
                }
                else{
                    blockedRiders.put(driverName,new ArrayList<>());
                    blockedRiders.get(driverName).add(riderName);
                }
            }
            if(driverRatingList.containsKey(driverName)){
                driverRatingList.get(driverName).add(driverRating);
            }else{
                driverRatingList.put(driverName,new ArrayList<>());
                driverRatingList.get(driverName).add(driverRating);
            }
            if(riderRatingList.containsKey(riderName)){
                riderRatingList.get(riderName).add(riderRating);
            }else{
                riderRatingList.put(riderName,new ArrayList<>());
                riderRatingList.get(riderName).add(riderRating);
            }
        }
        RatingService ratingService = new RatingService();
        ratingService.assignDriverRatings(driverRatingList);
        ratingService.assignRiderRatings(riderRatingList);

        List<Driver> ratingServiceDriverList = ratingService.getDriverList();
        List<Rider> ratingServiceRiderList = ratingService.getRiderList();

        ratingServiceDriverList.forEach(val-> System.out.println(val.getName()+" "+val.getAverageRating()));
        ratingServiceRiderList.forEach(val-> System.out.println(val.getName()+" "+val.getAverageRating()));

        Rider rider = new Rider("Rider1",5.0);
        Driver optimalDriver = ratingService.getOptimalDriver(rider,blockedDrivers);
        System.out.println("Optimal Driver for Rider1: "+optimalDriver.getName()+" "+optimalDriver.getAverageRating());

        Rider rider2 = new Rider("Rider2",5.0);
        Driver optimalDriver2 = ratingService.getOptimalDriver(rider2,blockedDrivers);
        System.out.println("Optimal Driver for Rider2: "+optimalDriver2.getName()+" "+optimalDriver2.getAverageRating());

        Rider rider3 = new Rider("Rider3",5.0);
        Driver optimalDriver3 = ratingService.getOptimalDriver(rider3,blockedDrivers);
        System.out.println("Optimal Driver for Rider3: "+optimalDriver3.getName()+" "+optimalDriver3.getAverageRating());
    }
}
