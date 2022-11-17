package com.example.watervan.Sale;

import java.util.ArrayList;

public class SaleBillProductRequest {
//    public ArrayList<com.example.watervan.Sale.TypeSaleLine> getTypeSaleLine() {
//        return TypeSaleLine;
//    }
//
//    public void setTypeSaleLine(ArrayList<com.example.watervan.Sale.TypeSaleLine> typeSaleLine) {
//        TypeSaleLine = typeSaleLine;
//    }
//
//    ArrayList<com.example.watervan.Sale.TypeSaleLine> TypeSaleLine;

int SaleInvoiceLineId;
int RowNum;
int ItemId;
int UnitId;
int Qty;
double UnitPrice;
double Amount;
double DiscountPercent;
double DiscountAmount;
double TaxableAmount;
double TaxPercent;
double TaxAmount;
double TotalAmount;
double StockIssueId;

    public Boolean getActiveStatus() {
        return ActiveStatus;
    }

    public void setActiveStatus(Boolean activeStatus) {
        ActiveStatus = activeStatus;
    }

    Boolean ActiveStatus;

    public int getSaleInvoiceLineId() {
        return SaleInvoiceLineId;
    }

    public void setSaleInvoiceLineId(int saleInvoiceLineId) {
        SaleInvoiceLineId = saleInvoiceLineId;
    }

    public int getRowNum() {
        return RowNum;
    }

    public void setRowNum(int rowNum) {
        RowNum = rowNum;
    }

    public int getItemId() {
        return ItemId;
    }

    public void setItemId(int itemId) {
        ItemId = itemId;
    }

    public int getUnitId() {
        return UnitId;
    }

    public void setUnitId(int unitId) {
        UnitId = unitId;
    }

    public int getQty() {
        return Qty;
    }

    public void setQty(int qty) {
        Qty = qty;
    }

    public double getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        UnitPrice = unitPrice;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double amount) {
        Amount = amount;
    }

    public double getDiscountPercent() {
        return DiscountPercent;
    }

    public void setDiscountPercent(double discountPercent) {
        DiscountPercent = discountPercent;
    }

    public double getDiscountAmount() {
        return DiscountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        DiscountAmount = discountAmount;
    }

    public double getTaxableAmount() {
        return TaxableAmount;
    }

    public void setTaxableAmount(double taxableAmount) {
        TaxableAmount = taxableAmount;
    }

    public double getTaxPercent() {
        return TaxPercent;
    }

    public void setTaxPercent(double taxPercent) {
        TaxPercent = taxPercent;
    }

    public double getTaxAmount() {
        return TaxAmount;
    }

    public void setTaxAmount(double taxAmount) {
        TaxAmount = taxAmount;
    }

    public double getTotalAmount() {
        return TotalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        TotalAmount = totalAmount;
    }

    public double getStockIssueId() {
        return StockIssueId;
    }

    public void setStockIssueId(double stockIssueId) {
        StockIssueId = stockIssueId;
    }





}
