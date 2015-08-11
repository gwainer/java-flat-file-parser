package com.gabrielcw.domain.stats;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class SaleStatisticsTest {

    private static SaleStatistics stats;

    @Before
    public void setUp() {
        stats = new SaleStatistics();

    }

    @Test
    public void shouldDisconsiderRepeatedCustomers() throws Exception {
        stats.addCustomerCnpj("1");
        stats.addCustomerCnpj("2");
        stats.addCustomerCnpj("1234567890");
        stats.addCustomerCnpj("4");
        stats.addCustomerCnpj("5");
        stats.addCustomerCnpj("1");
        stats.addCustomerCnpj("2");

        assertEquals(5, stats.getCustomersAmount());
    }

    @Test
    public void shouldDisconsiderRepeatedSalesmen() throws Exception {
        stats.addSalesmanCpf("1");
        stats.addSalesmanCpf("10");
        stats.addSalesmanCpf("100");
        stats.addSalesmanCpf("1000");
        stats.addSalesmanCpf("10000");
        stats.addSalesmanCpf("10");
        stats.addSalesmanCpf("1");

        assertEquals(5, stats.getSalesmenAmount());
    }

    @Test
    public void shouldGetWorstSalesmanName() throws Exception {
        stats.addToSalesmanTotals("Liberty Valance", 123.50f);
        stats.addToSalesmanTotals("Jack the Nipper", 10f);
        stats.addToSalesmanTotals("Monty", 42.99f);
        stats.addToSalesmanTotals("Liberty Valance", 986.12f);
        stats.addToSalesmanTotals("Monty", 323.50f);
        stats.addToSalesmanTotals("Liberty Valance", 150f);

        assertEquals("Jack the Nipper",stats.getWorstSalesmanName());
    }

    @Test
    public void shouldAddToSalesmanTotals() throws Exception {
        stats.addToSalesmanTotals("Liberty Valance", 123.50f);
        assertEquals(stats.getSalesmanTotals().get("Liberty Valance").floatValue(), 123.50f, 0);
        stats.addToSalesmanTotals("Liberty Valance", 123.50f);
        assertEquals(stats.getSalesmanTotals().get("Liberty Valance").floatValue(), 247f, 0);
    }
}