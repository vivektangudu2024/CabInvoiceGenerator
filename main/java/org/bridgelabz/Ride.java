package org.bridgelabz;

public class Ride {
    private final double distance;
    private final int time;
    private final String userId;

    public Ride(String userId, double distance, int time) {
        this.userId = userId;
        this.distance = distance;
        this.time = time;
    }

    // Getters for distance, time, and userId

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public String getUserId() {
        return userId;
    }
}
