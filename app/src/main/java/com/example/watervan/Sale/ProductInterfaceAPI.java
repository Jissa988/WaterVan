package com.example.watervan.Sale;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ProductInterfaceAPI {
    @POST("SalesMangement/VanSalesItemLookup")
    Call<ProductResponseResultSetArray> requestproduct(@Header("Authorization") String token, @Body ProductRequest productRequest);
}
