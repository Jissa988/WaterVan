package com.example.watervan;

import com.example.watervan.Collections.CustomerInterfaceAPI;
import com.example.watervan.Collections.PendingInterfaceAPI;
import com.example.watervan.Home.HomeInterfaceAPI;
import com.example.watervan.Login.LoginInterfaceAPI;
import com.example.watervan.Sale.PaymentInterfaceAPI;
import com.example.watervan.Sale.ProductInterfaceAPI;
import com.example.watervan.Sale.SaleBillInterface;
import com.example.watervan.Sale.SalesInterfaceAPI;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance {
    private static String API_BASE_URL="http://103.220.223.37:8082/";
    private static String API_BASE_URL1="http://103.220.223.37:8083/";
    private static String API_BASE_URL2="http://103.220.223.37:8084/";
    private static Retrofit retrofit,retrofit1,retrofit2;

    public static Retrofit getretrofitInstance(){


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

    retrofit=new Retrofit.Builder().baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit;



    }
    public static Retrofit getretrofitInstance1(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();


        retrofit1= new Retrofit.Builder().baseUrl(API_BASE_URL1)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit1;

    }

    public static Retrofit getretrofitInstance2(){


        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        retrofit2=new Retrofit.Builder().baseUrl(API_BASE_URL2)
                .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        return retrofit2;



    }

    public static LoginInterfaceAPI getInterfaceAPI(){
        LoginInterfaceAPI interfaceAPI=getretrofitInstance().create(LoginInterfaceAPI.class);
        return interfaceAPI;
    }
    public static HomeInterfaceAPI getHomeInterfaceAPI(){
        HomeInterfaceAPI homeInterfaceAPI=getretrofitInstance().create(HomeInterfaceAPI.class);
        return homeInterfaceAPI;
    }
    public static SalesInterfaceAPI getSalesInterfaceAPI(){
        SalesInterfaceAPI salesInterfaceAPI=getretrofitInstance().create(SalesInterfaceAPI.class);
        return salesInterfaceAPI;
    }
    public static PaymentInterfaceAPI gePaymentInterfaceAPI(){
        PaymentInterfaceAPI paymentInterfaceAPI=getretrofitInstance1().create(PaymentInterfaceAPI.class);
        return paymentInterfaceAPI;
    }
    public static ProductInterfaceAPI getProductInterfaceAPI(){
        ProductInterfaceAPI productInterfaceAPI=getretrofitInstance1().create(ProductInterfaceAPI.class);
        return productInterfaceAPI;
    }
    public static SaleBillInterface getSaleBillInterface(){
        SaleBillInterface saleBillInterface=getretrofitInstance1().create(SaleBillInterface.class);
        return saleBillInterface;
    }

    public static CustomerInterfaceAPI getCustomerInterfaceAPI(){
        CustomerInterfaceAPI customerInterfaceAPI=getretrofitInstance().create(CustomerInterfaceAPI.class);
        return customerInterfaceAPI;
    }
    public static PendingInterfaceAPI getPendingInterfaceAPI(){
        PendingInterfaceAPI pendingInterfaceAPI=getretrofitInstance2().create(PendingInterfaceAPI.class);
        return pendingInterfaceAPI;
    }
}
