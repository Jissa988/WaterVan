package com.example.watervan.Sale;

public class ProductResponseResultSet {
    int ItemId;
    String ItemCode;
    String ItemName;
    int TaxId;
    double TaxRate;
    String Stock;

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String itemCode) {
        ItemCode = itemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public int getTaxId() {
        return TaxId;
    }

    public void setTaxId(int taxId) {
        TaxId = taxId;
    }

    public double getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(double taxRate) {
        TaxRate = taxRate;
    }

    public String getStock() {
        return Stock;
    }

    public void setStock(String stock) {
        Stock = stock;
    }


}
