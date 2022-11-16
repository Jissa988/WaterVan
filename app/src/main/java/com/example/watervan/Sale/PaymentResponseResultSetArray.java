package com.example.watervan.Sale;

import java.util.List;

public class PaymentResponseResultSetArray {
    private List<PaymentResponseResultSet> ResultSet;
    private List<PaymentResponseResultSet1> ResultSet1;

    public List<PaymentResponseResultSet1> getResultSet1() {
        return ResultSet1;
    }

    public void setResultSet1(List<PaymentResponseResultSet1> resultSet1) {
        ResultSet1 = resultSet1;
    }

    public List<PaymentResponseResultSet2> getResultSet2() {
        return ResultSet2;
    }

    public void setResultSet2(List<PaymentResponseResultSet2> resultSet2) {
        ResultSet2 = resultSet2;
    }

    private List<PaymentResponseResultSet2> ResultSet2;



    public PaymentResponseResultSetArray(List<PaymentResponseResultSet> resultSet) {
        ResultSet = resultSet;
    }

    public List<PaymentResponseResultSet> getResultSet() {
        return ResultSet;
    }

    public void setResultSet(List<PaymentResponseResultSet> resultSet) {
        ResultSet = resultSet;
    }


}
