package com.example.watervan.Sale;

public class SaleBillRequest {
    int SaleInvoiceHeadId;
   int OutboundDeliveryHeadId;
   int FinYearId;
   int StkTrxTypeId;
   String InvoiceDate;
   int CustomerId;
   String Narration;
   double RoundOff;
   String RoundOffType;
   int WarehouseId;
   double GrossAmount ;
   double TotalDiscountPercent;
   double TotalDiscountAmount;
   double TotalTaxAmount;
   double NetAmount;
   double ReceiptType;
   String Finalized;
   String ActiveStatus;
    int PaymentTermId;

    public int getSaleInvoiceHeadId() {
        return SaleInvoiceHeadId;
    }

    public void setSaleInvoiceHeadId(int saleInvoiceHeadId) {
        SaleInvoiceHeadId = saleInvoiceHeadId;
    }

    public int getOutboundDeliveryHeadId() {
        return OutboundDeliveryHeadId;
    }

    public void setOutboundDeliveryHeadId(int outboundDeliveryHeadId) {
        OutboundDeliveryHeadId = outboundDeliveryHeadId;
    }

    public int getFinYearId() {
        return FinYearId;
    }

    public void setFinYearId(int finYearId) {
        FinYearId = finYearId;
    }

    public int getStkTrxTypeId() {
        return StkTrxTypeId;
    }

    public void setStkTrxTypeId(int stkTrxTypeId) {
        StkTrxTypeId = stkTrxTypeId;
    }

    public String getInvoiceDate() {
        return InvoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        InvoiceDate = invoiceDate;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public String getNarration() {
        return Narration;
    }

    public void setNarration(String narration) {
        Narration = narration;
    }

    public double getRoundOff() {
        return RoundOff;
    }

    public void setRoundOff(double roundOff) {
        RoundOff = roundOff;
    }

    public String getRoundOffType() {
        return RoundOffType;
    }

    public void setRoundOffType(String roundOffType) {
        RoundOffType = roundOffType;
    }

    public int getWarehouseId() {
        return WarehouseId;
    }

    public void setWarehouseId(int warehouseId) {
        WarehouseId = warehouseId;
    }

    public double getGrossAmount() {
        return GrossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        GrossAmount = grossAmount;
    }

    public double getTotalDiscountPercent() {
        return TotalDiscountPercent;
    }

    public void setTotalDiscountPercent(double totalDiscountPercent) {
        TotalDiscountPercent = totalDiscountPercent;
    }

    public double getTotalDiscountAmount() {
        return TotalDiscountAmount;
    }

    public void setTotalDiscountAmount(double totalDiscountAmount) {
        TotalDiscountAmount = totalDiscountAmount;
    }

    public double getTotalTaxAmount() {
        return TotalTaxAmount;
    }

    public void setTotalTaxAmount(double totalTaxAmount) {
        TotalTaxAmount = totalTaxAmount;
    }

    public double getNetAmount() {
        return NetAmount;
    }

    public void setNetAmount(double netAmount) {
        NetAmount = netAmount;
    }

    public double getReceiptType() {
        return ReceiptType;
    }

    public void setReceiptType(double receiptType) {
        ReceiptType = receiptType;
    }

    public String getFinalized() {
        return Finalized;
    }

    public void setFinalized(String finalized) {
        Finalized = finalized;
    }

    public String getActiveStatus() {
        return ActiveStatus;
    }

    public void setActiveStatus(String activeStatus) {
        ActiveStatus = activeStatus;
    }

    public int getPaymentTermId() {
        return PaymentTermId;
    }

    public void setPaymentTermId(int paymentTermId) {
        PaymentTermId = paymentTermId;
    }









}
