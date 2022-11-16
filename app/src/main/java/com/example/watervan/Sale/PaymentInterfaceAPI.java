package com.example.watervan.Sale;

import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PaymentInterfaceAPI {
    @POST("SalesMangement/GetVanSalePaymentTerm")
    Call<PaymentResponseResultSetArray> requestpayment(@Header("Authorization") String token);
}
