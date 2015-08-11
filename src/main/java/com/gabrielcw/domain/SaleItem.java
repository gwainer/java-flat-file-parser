package com.gabrielcw.domain;

import java.text.MessageFormat;

public class SaleItem {
    private String itemId;
    private Integer itemQuantity;
    private float itemPrice;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(Integer itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String toString() {
        return MessageFormat.format("itemId={0}, itemPrice={1}, itemQuantity={2}", itemId, itemPrice, itemQuantity);
    }
}

