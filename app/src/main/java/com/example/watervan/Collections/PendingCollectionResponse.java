package com.example.watervan.Collections;

public class PendingCollectionResponse {
     int InvoiceId;
     int StkTrxTypeId;
    String DocNo;
     String ReffDoc;
     String DocDt;
      String DueDt;
      double BillAmt;
     double Balance;
    double Settled;

    public double getBillAmt() {
        return BillAmt;
    }

    public void setBillAmt(double billAmt) {
        BillAmt = billAmt;
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        Balance = balance;
    }

    public double getSettled() {
        return Settled;
    }

    public void setSettled(double settled) {
        Settled = settled;
    }



    public int getInvoiceId() {
        return InvoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        InvoiceId = invoiceId;
    }

    public int getStkTrxTypeId() {
        return StkTrxTypeId;
    }

    public void setStkTrxTypeId(int stkTrxTypeId) {
        StkTrxTypeId = stkTrxTypeId;
    }

    public String getDocNo() {
        return DocNo;
    }

    public void setDocNo(String docNo) {
        DocNo = docNo;
    }

    public String getReffDoc() {
        return ReffDoc;
    }

    public void setReffDoc(String reffDoc) {
        ReffDoc = reffDoc;
    }

    public String getDocDt() {
        return DocDt;
    }

    public void setDocDt(String docDt) {
        DocDt = docDt;
    }

    public String getDueDt() {
        return DueDt;
    }

    public void setDueDt(String dueDt) {
        DueDt = dueDt;
    }




}
