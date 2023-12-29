package org.bridgelabz;

public class InvoiceGenerator {

    private static final int COST_PER_KM = 10;
    private static final int COST_PER_MIN = 1;
    private static final int MIN_FARE = 5;
    public double calculateFare(double distance, int time) {
        double totalFare = distance * COST_PER_KM + time * COST_PER_MIN;
        return Math.max(totalFare, MIN_FARE);
    }

    public InvoiceSummary calculateFare(Ride[] rides){
        double totalFare = 0;
        for(Ride ride:rides){
            totalFare += this.calculateFare(ride.distance, ride.time);
        }
        return new InvoiceSummary(rides.length, totalFare);
    }
}
