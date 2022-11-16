package com.example.watervan.Collections;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.watervan.Home.HomepageActivity;
import com.example.watervan.Login.LoginActivity;
import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.Sale.MainSalesResponse;
import com.example.watervan.Sale.SalesActivity;
import com.example.watervan.Sale.SalesRequest;
import com.example.watervan.Sale.SalesResponse;
import com.example.watervan.TokenSharedPreference;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CustomersOutstandingListActivity extends AppCompatActivity {
    CustomerOutstandingAdapter customer_outstanding_adapter;
    RecyclerView cust_outstanding_recyclerView;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    EditText search;
   public static List<CustomersResponse> outcustomerlist;
    List<CustomersResponse> filterlist=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customers_outstanding_list);
        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();

        search = findViewById(R.id.out_edit_search);
        bottom_navigation();
        customer_list("a");
        filter_search();

    }
    public void recyclerviews (List<CustomersResponse> list) {
        Log.v("water", "CustomersOutstandingListActivity-----recyclerview--" + "");
        cust_outstanding_recyclerView
                = (RecyclerView) findViewById(
                R.id.customer_outstanding_recycler);
        Log.v("water", "CustomersOutstandingListActivity-----recyclerview id--" + "");
        cust_outstanding_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.v("water", "CustomersOutstandingListActivity-----recyclerview setLayoutManager--" + "");

        customer_outstanding_adapter = new CustomerOutstandingAdapter(this,list);

        Log.v("water", "CustomersOutstandingListActivity-----recyclerview customer_adapter--" + "");
        cust_outstanding_recyclerView.setAdapter((RecyclerView.Adapter) customer_outstanding_adapter);
        Log.v("water", "CustomersOutstandingListActivity-----recyclerview setAdapter--" + "");

    }


    private void customer_list(String text) {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setCustomerName(text);


        Log.v("water","CustomersOutstandingListActivity---------customer_list------");
        Call<CustomerResponseArray> call = RetrofitClientInstance.getCustomerInterfaceAPI().requestcustomer("Bearer"+" "+token,customerRequest);

        call.enqueue(new Callback<CustomerResponseArray>() {
            @Override
            public void onResponse(Call<CustomerResponseArray> call, Response<CustomerResponseArray> response) {
                Log.v("water","CustomersOutstandingListActivity---------onResponse-------"+response);


                if(response.isSuccessful()) {
                    Log.v("water", "CustomersOutstandingListActivity---------isSuccessful-------" );
                    outcustomerlist= response.body().getResultSet();
                    Log.v("water", "SalesActivity---------list-------" + outcustomerlist);

                    recyclerviews(outcustomerlist);


                }
                else{
                    Log.v("water", "CustomersOutstandingListActivity---------response failed--");
                    Toast.makeText(getApplicationContext(), "Please Login session expired", Toast.LENGTH_SHORT).show();
                    tokenSharedPreference.Clear_token();
                    tokenSharedPreference.setFirstLaunch(true);
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }

            }



            @Override
            public void onFailure(Call<CustomerResponseArray> call, Throwable t) {

            }
        });

    }
    public void filter_search(){
        Log.v("water","CustomersOutstandingListActivity---------filter_search-----------");
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v("water","CustomersOutstandingListActivity---------beforeTextChanged-----------");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.v("water","CustomersOutstandingListActivity---------onTextChanged-----------");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.v("water","CustomersOutstandingListActivity---------search-----------");
                filterlist.clear();
                if (editable.toString().isEmpty()) {
//                Log.v("water","SalesActivity---------isEmpty-----------");
//                customer_adapter = new CustomerAdapter(getApplicationContext(), list);
                    customer_list("a");
                    // customer_adapter.notifyDataSetChanged();
                } else {

                    Log.v("water","CustomersOutstandingListActivity---------else-----------");
                    customer_list(editable.toString());


                }

            }


        });

    }
    public void onBackPressed () {
        super.onBackPressed();

    }

    public void bottom_navigation(){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // bottomNavigationView.setItemIconTintList(null);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.collection_nav);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.sales_nav:
                        startActivity(new Intent(getApplicationContext(), SalesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home_nav:

                        startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.collection_nav:

                        return true;

                }
                return false;
            }
        });

    }

}