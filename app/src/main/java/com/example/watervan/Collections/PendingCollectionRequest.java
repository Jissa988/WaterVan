package com.example.watervan.Collections;

public class PendingCollectionRequest {
    int AccountId;
    int FinYearId;
    String DocNo;
    String FromDate;
    String ToDate;

    public int getAccountId() {
        return AccountId;
    }

    public void setAccountId(int accountId) {
        AccountId = accountId;
    }

    public int getFinYearId() {
        return FinYearId;
    }

    public void setFinYearId(int finYearId) {
        FinYearId = finYearId;
    }

    public String getDocNo() {
        return DocNo;
    }

    public void setDocNo(String docNo) {
        DocNo = docNo;
    }

    public String getFromDate() {
        return FromDate;
    }

    public void setFromDate(String fromDate) {
        FromDate = fromDate;
    }

    public String getToDate() {
        return ToDate;
    }

    public void setToDate(String toDate) {
        ToDate = toDate;
    }



}
