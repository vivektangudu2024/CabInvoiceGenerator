package org.bridgelabz;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
public class CabInvoiceTest {
    InvoiceGenerator invoiceGenerator = null;
    @BeforeEach
    void setInvoiceGenerator(){
        invoiceGenerator = new InvoiceGenerator();
    }

    @Test
    void checkCalculateFare(){
        double fareCalculated = invoiceGenerator.calculateFare(2.0, 4);
        assertEquals(24, fareCalculated);
    }

    @Test
    void checkCalculateFareMinimum(){
        double fareCalculated = invoiceGenerator.calculateFare(0.1, 3);
        assertEquals(5, fareCalculated);
    }

    @Test
    void checkCalculateFareForMultipleRides(){
        Ride[] rides = {new Ride(0.1, 3),
                        new Ride(2, 4)
        };
        InvoiceSummary invoiceCalculated = invoiceGenerator.calculateFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 29);
        assertEquals(expectedInvoiceSummary, invoiceCalculated);
    }

}
