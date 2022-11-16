package com.example.watervan.Collections;

import com.example.watervan.Sale.MainSalesResponse;
import com.example.watervan.Sale.SalesRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CustomerInterfaceAPI {
    @POST("SalesManagement/CustomerLookup")
    Call<CustomerResponseArray> requestcustomer(@Header("Authorization") String token, @Body CustomerRequest customerRequest);
}
