package org.bridgelabz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RideRepository {
    private final Map<String, List<Ride>> userRides;

    public RideRepository() {
        this.userRides = new HashMap<>();
    }

    public void addRide(String userId, Ride ride) {
        userRides.computeIfAbsent(userId, k -> new ArrayList<>()).add(ride);
    }

    public Ride[] getRides(String userId) {
        List<Ride> rides = userRides.getOrDefault(userId, new ArrayList<>());
        return rides.toArray(new Ride[0]);
    }
}
