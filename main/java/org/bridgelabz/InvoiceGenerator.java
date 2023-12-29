package org.bridgelabz;

public class InvoiceGenerator {

    private static final int COST_PER_KM = 10;
    private static final int COST_PER_MIN = 1;
    private static final int MIN_FARE = 5;

    private final RideRepository rideRepository;

    public InvoiceGenerator(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_FARE);
    }

    public InvoiceSummary calculateFare(String userId) {
        if (userId == null) {
            throw new NullPointerException("User ID cannot be null");
        }
        Ride[] rides = null;
        rides = rideRepository.getRides(userId);
        if (rides.length == 0) {
            throw new IllegalArgumentException("User not found");
        }

        double totalFare = 0;
        for (Ride ride : rides) {
            totalFare += this.calculateFare(ride.getDistance(), ride.getTime());
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
