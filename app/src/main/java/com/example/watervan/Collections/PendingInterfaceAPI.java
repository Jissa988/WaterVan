package com.example.watervan.Collections;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface PendingInterfaceAPI {
    @POST("Transactions/GetReceiptVoucherReceiptUnreceivedBills")
    Call<PendingCollectionResponseArray> requestcollection(@Header("Authorization") String token, @Body PendingCollectionRequest pendingCollectionRequest);
}
