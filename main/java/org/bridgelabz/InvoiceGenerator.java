package org.bridgelabz;

public class InvoiceGenerator {

    private static final int NORMAL_COST_PER_KM = 10;
    private static final int NORMAL_COST_PER_MIN = 1;
    private static final int NORMAL_MIN_FARE = 5;

    private static final int PREMIUM_COST_PER_KM = 15;
    private static final int PREMIUM_COST_PER_MIN = 2;
    private static final int PREMIUM_MIN_FARE = 20;

    private final RideRepository rideRepository;

    public InvoiceGenerator(RideRepository rideRepository) {
        this.rideRepository = rideRepository;
    }

    public double calculateFare(double distance, int time, RideType rideType) {
        if (rideType == RideType.NORMAL) {
            double totalFare = distance * NORMAL_COST_PER_KM + time * NORMAL_COST_PER_MIN;
            return Math.max(totalFare, NORMAL_MIN_FARE);
        } else if (rideType == RideType.PREMIUM) {
            double totalFare = distance * PREMIUM_COST_PER_KM + time * PREMIUM_COST_PER_MIN;
            return Math.max(totalFare, PREMIUM_MIN_FARE);
        }else {
            throw new IllegalArgumentException("Invalid ride type");
        }
    }

    public InvoiceSummary calculateFare(String userId, RideType rideType) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }

        Ride[] rides = rideRepository.getRides(userId);

        if (rides == null) {
            throw new IllegalArgumentException("User not found");
        }

        double totalFare = 0;
        for (Ride ride : rides) {
            if (ride.getRideType() == rideType) {
                totalFare += this.calculateFare(ride.getDistance(), ride.getTime(), rideType);
            }
        }

        return new InvoiceSummary(rides.length, totalFare);
    }
}
