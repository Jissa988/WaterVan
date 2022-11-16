package com.example.watervan.Sale;

import android.util.Log;

import java.sql.ResultSet;

public class SalesResponse {

    int CustomerId;
    int CustomerCode;
    String CustomerName;
    String OfficeAddress;
    String CompanyPhone;
    String CompanyMobile;
    String CompanyTRN;
    String OutstandingAmount;


    public SalesResponse(int customerId, int customerCode, String customerName, String officeAddress, String companyPhone, String companyMobile, String companyTRN, String outstandingAmount) {
        this.CustomerId = customerId;
        this.CustomerCode = customerCode;
        this.CustomerName = customerName;
        this.OfficeAddress = officeAddress;
        this.CompanyPhone = companyPhone;
        this.CompanyMobile = companyMobile;
        this.CompanyTRN = companyTRN;
        this.OutstandingAmount = outstandingAmount;
    }





    public int getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(int customerId) {
        CustomerId = customerId;
    }

    public int getCustomerCode() {
        return CustomerCode;
    }

    public void setCustomerCode(int customerCode) {
        CustomerCode = customerCode;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public String getOfficeAddress() {
        return OfficeAddress;
    }

    public void setOfficeAddress(String officeAddress) {
        OfficeAddress = officeAddress;
    }

    public String getCompanyPhone() {
        return CompanyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        CompanyPhone = companyPhone;
    }

    public String getCompanyMobile() {
        return CompanyMobile;
    }

    public void setCompanyMobile(String companyMobile) {
        CompanyMobile = companyMobile;
    }

    public String getCompanyTRN() {
        return CompanyTRN;
    }

    public void setCompanyTRN(String companyTRN) {
        CompanyTRN = companyTRN;
    }

    public String getOutstandingAmount() {
        return OutstandingAmount;
    }

    public void setOutstandingAmount(String outstandingAmount) {
        OutstandingAmount = outstandingAmount;
    }






}
