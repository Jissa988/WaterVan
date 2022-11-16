package com.example.watervan.Sale;

public class ProductRequest {

    int FinYearId;
    int CustomerId;
    String ItemName;

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }



    public int getFinYearId() {
        return FinYearId;
    }

    public void setFinYearId(int finYearId) {
        FinYearId = finYearId;
    }

    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }


}
