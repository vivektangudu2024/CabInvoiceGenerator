package org.bridgelabz;

public class InvoiceSummary {
    private final int numOfRides;
    private final double totalFare;
    private final double averageFare;

    public InvoiceSummary(int numOfRides, double totalFare){
        this.numOfRides = numOfRides;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare / this.numOfRides;
    }

    @Override
    public boolean equals(Object a){
        if(this == a)return true;
        if(a == null || getClass() != a.getClass())return false;
        InvoiceSummary that = (InvoiceSummary) a;
        return numOfRides == that.numOfRides && totalFare == that.totalFare && averageFare == that.averageFare;
    }
}
