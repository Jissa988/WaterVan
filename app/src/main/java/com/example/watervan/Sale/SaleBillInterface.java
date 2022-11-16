package com.example.watervan.Sale;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SaleBillInterface {
    @POST("SalesManagement/InsertUpdateSalesInvoice")
    Call<SaleBillResponseArray> requestbill(@Header("Authorization") String token, @Body SaleBillRequest saleBillRequest,List<ProductList> list);

}
