package com.gabrielcw.domain;


import com.github.ffpojo.metadata.DefaultFieldDecorator;
import com.github.ffpojo.metadata.delimited.annotation.DelimitedField;
import com.github.ffpojo.metadata.delimited.annotation.DelimitedRecord;

import java.util.ArrayList;
import java.util.List;

@DelimitedRecord(delimiter = "ç")
public class Sale {
    private Long saleId;
    private String saleItemsRaw;
    private List<SaleItem> saleItems;
    private String salesmanName;
    private float saleTotalPrice;

    @DelimitedField(positionIndex = 2, decorator = LongDecorator.class)
    public Long getSaleId() {
        return saleId;
    }

    @DelimitedField(positionIndex = 3)
    public String getSaleItemsRaw() {
        return saleItemsRaw;
    }

    @DelimitedField(positionIndex = 4)
    public String getSalesmanName() {
        return salesmanName;
    }

    public List<SaleItem> getSaleItems() {
        if (saleItems == null) {
            saleItems = buildItemListFromString(saleItemsRaw);
        }
        return saleItems;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public void setSaleItemsRaw(String saleItemsRaw) {
        this.saleItemsRaw = saleItemsRaw.replace("[","").replace("]", "");
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    private List<SaleItem> buildItemListFromString(String saleItems) {
        List<SaleItem> itemsList = new ArrayList<>();
        String[] sales = saleItems.split("\\s*,\\s*");

        for (String sale : sales) {
            SaleItem item = buildSaleItem(sale);
            itemsList.add(item);
        }
        return itemsList;
    }

    public static class LongDecorator extends DefaultFieldDecorator {
        @Override
        public Object fromString(String str) {
            return Long.valueOf(str);
        }
    }


    private SaleItem buildSaleItem(String sale) {
        String[] saleAttributes = sale.split("-");
        SaleItem item = new SaleItem();

        item.setItemId(saleAttributes[0]);
        item.setItemQuantity(Integer.valueOf(saleAttributes[1]));
        item.setItemPrice(Float.parseFloat(saleAttributes[2]));

        return item;
    }


    public float getSaleTotalPrice() {
        if (saleTotalPrice == 0) {
            saleTotalPrice = calculateSaleTotalPrice();
        }
        return saleTotalPrice;
    }

    private float calculateSaleTotalPrice() {
        float total = 0;
        for (SaleItem item : getSaleItems()) {
            total += item.getItemQuantity() * item.getItemPrice();
        }
        return total;
    }
}
