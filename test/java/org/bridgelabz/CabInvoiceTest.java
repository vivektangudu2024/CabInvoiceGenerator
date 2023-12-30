package org.bridgelabz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CabInvoiceTest {
    InvoiceGenerator invoiceGenerator = null;
    RideRepository rideRepository = null;

    @BeforeEach
    void setInvoiceGenerator() {
        rideRepository = new RideRepository();
        invoiceGenerator = new InvoiceGenerator(rideRepository);
    }

    @Test
    void checkCalculateFareForMultipleRides() {
        Ride[] rides = {
                new Ride("user1", 0.1, 3, RideType.NORMAL),
                new Ride("user1", 2, 4, RideType.PREMIUM)
        };
        for (Ride ride : rides) {
            rideRepository.addRide(ride.getUserId(), ride);
        }

        InvoiceSummary invoiceCalculated = invoiceGenerator.calculateFare("user1", RideType.NORMAL);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(1, 5.5);
        assertEquals(expectedInvoiceSummary, invoiceCalculated);

        invoiceCalculated = invoiceGenerator.calculateFare("user1", RideType.PREMIUM);
        expectedInvoiceSummary = new InvoiceSummary(1, 34);
        assertEquals(expectedInvoiceSummary, invoiceCalculated);
    }

    @Test
    void checkCalculateFareForUserNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            invoiceGenerator.calculateFare("nonexistentUser", RideType.NORMAL);
        });
    }

    @Test
    void checkCalculateFareForNullUser() {
        assertThrows(IllegalArgumentException.class, () -> {
            invoiceGenerator.calculateFare(null, RideType.PREMIUM);
        });
    }

    @Test
    void checkCalculateFareForInvalidRideType() {
        assertThrows(IllegalArgumentException.class, () -> {
            invoiceGenerator.calculateFare("user1", null);
        });
    }
}
