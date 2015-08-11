package com.gabrielcw.domain;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SaleTest {
    private static Sale sale;

    @BeforeClass
    public static void setUp() {
        sale = new Sale();
        sale.setSaleId(1998l);
        sale.setSalesmanName("Roomba");
        sale.setSaleItemsRaw("[1-34-10,2-33-1.50,3-40-0.10,1-34-10]");

    }

    @Test
    public void shouldBuildItemList() {
        List<SaleItem> saleItems = sale.getSaleItems();
        Assert.assertEquals(saleItems.get(0).getItemId(), saleItems.get(3).getItemId());
        Assert.assertEquals(saleItems.get(0).getItemPrice(), saleItems.get(3).getItemPrice(), 0);
        Assert.assertEquals(saleItems.get(0).getItemQuantity(), saleItems.get(3).getItemQuantity());
    }

    @Test
    public void shouldCalculateSaleTotalPrice() {
        assertEquals(733.50f, sale.getSaleTotalPrice(), 0);
    }

    @Test
    public void shouldRemoveBracketsFromRawSales() {
        Sale localSale = new Sale();
        localSale.setSaleItemsRaw("[1-3-10,2-1-1.50,3-40-0.10,1-34-10]");
        assertEquals("1-3-10,2-1-1.50,3-40-0.10,1-34-10", localSale.getSaleItemsRaw());
    }

}