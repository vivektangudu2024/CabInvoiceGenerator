package org.bridgelabz;

public class Ride {
    private final double distance;
    private final int time;
    private final String userId;
    private final RideType rideType;

    public Ride(String userId, double distance, int time, RideType rideType) {
        this.userId = userId;
        this.distance = distance;
        this.time = time;
        this.rideType = rideType;
    }

    // Getters for distance, time, userId, and rideType

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public String getUserId() {
        return userId;
    }

    public RideType getRideType() {
        return rideType;
    }
}
