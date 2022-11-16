package com.example.watervan.Sale;

import java.util.List;

public class ProductResponseResultSetArray {

    private List<ProductResponseResultSet> ResultSet;
    private List<ProductResponseResultSet1> ResultSet1;


    public List<ProductResponseResultSet1> getResultSet1() {
        return ResultSet1;
    }

    public void setResultSet1(List<ProductResponseResultSet1> resultSet1) {
        ResultSet1 = resultSet1;
    }



    public ProductResponseResultSetArray(List<ProductResponseResultSet> resultSet) {
        ResultSet = resultSet;
    }

    public List<ProductResponseResultSet> getResultSet() {
        return ResultSet;
    }

    public void setResultSet(List<ProductResponseResultSet> resultSet) {
        ResultSet = resultSet;
    }


}
