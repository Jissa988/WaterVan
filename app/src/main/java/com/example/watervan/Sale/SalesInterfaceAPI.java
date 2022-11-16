package com.example.watervan.Sale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface SalesInterfaceAPI {
    @POST("SalesManagement/CustomerLookup")
    Call<MainSalesResponse> requestsales(@Header("Authorization") String token, @Body SalesRequest salesRequest);

}
