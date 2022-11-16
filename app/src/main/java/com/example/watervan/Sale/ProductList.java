package com.example.watervan.Sale;

import java.util.ArrayList;
import java.util.List;

public class ProductList {


    String product_name;
    int product_id;
    int unit_id;
    int quantity;
    double rate;
    int taxid;
    double taxrate;
    int array_pos;
    String item_code;
    public String getItem_code() {
        return item_code;
    }

    public void setItem_code(String item_code) {
        this.item_code = item_code;
    }



    public int getTaxid() {
        return taxid;
    }

    public void setTaxid(int taxid) {
        this.taxid = taxid;
    }

    public double getTaxrate() {
        return taxrate;
    }

    public void setTaxrate(double taxrate) {
        this.taxrate = taxrate;
    }

    public int getArray_pos() {
        return array_pos;
    }

    public void setArray_pos(int array_pos) {
        this.array_pos = array_pos;
    }








    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }




    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getUnit_id() {
        return unit_id;
    }

    public void setUnit_id(int unit_id) {
        this.unit_id = unit_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }



}
