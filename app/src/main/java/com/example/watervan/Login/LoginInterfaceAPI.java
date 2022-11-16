package com.example.watervan.Login;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface LoginInterfaceAPI {

    @POST("User/Login")
Call<LoginResponse> requestlogin( @Body LoginRequest loginRequest);


}
