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
                new Ride("user1", 0.1, 3),
                new Ride("user1", 2, 4)
        };
        for (Ride ride : rides) {
            rideRepository.addRide(ride.getUserId(), ride);
        }

        InvoiceSummary invoiceCalculated = invoiceGenerator.calculateFare("user1");
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 29);
        assertEquals(expectedInvoiceSummary, invoiceCalculated);
    }
    @Test
    void checkCalculateFareForUserNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            // Attempting to calculate fare for a user that does not exist in the repository
            invoiceGenerator.calculateFare("nonexistentUser");
        });
    }

    @Test
    void checkCalculateFareForNullUser() {
        assertThrows(NullPointerException.class, () -> {
            // Attempting to calculate fare with a null user ID
            invoiceGenerator.calculateFare(null);
        });
    }
}
