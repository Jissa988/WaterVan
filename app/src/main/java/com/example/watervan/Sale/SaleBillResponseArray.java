package com.example.watervan.Sale;

import java.util.List;

public class SaleBillResponseArray {
    public List<SaleBillResponse> getResultSet() {
        return ResultSet;
    }

    public void setResultSet(List<SaleBillResponse> resultSet) {
        ResultSet = resultSet;
    }

    private List<SaleBillResponse> ResultSet;
}
