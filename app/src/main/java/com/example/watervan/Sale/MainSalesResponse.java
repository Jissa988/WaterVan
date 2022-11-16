package com.example.watervan.Sale;

import java.util.List;

public class MainSalesResponse {

    private List<SalesResponse> ResultSet;

    public List<SalesResponse> getResultSet() {
        return ResultSet;
    }

    public void setResultSet(List<SalesResponse> resultSet) {
        ResultSet = resultSet;
    }


    public MainSalesResponse(List<SalesResponse> resultSet) {
        ResultSet = resultSet;
    }


}
