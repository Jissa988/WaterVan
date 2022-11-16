package com.example.watervan.Sale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.watervan.Collections.CustomersOutstandingListActivity;
import com.example.watervan.Home.HomepageActivity;
import com.example.watervan.Login.LoginActivity;
import com.example.watervan.RetrofitClientInstance;

import com.example.watervan.TokenSharedPreference;
import com.example.watervan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {
    CustomerAdapter customer_adapter;
    RecyclerView cust_recyclerView;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    EditText search;
    public static List<SalesResponse> customerlist;
    List<SalesResponse> filterlist=new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales);
        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();

        search = findViewById(R.id.edit_search);
        bottom_navigation();
        customer_list("a");
        filter_search();

    }

public void filter_search(){
    Log.v("water","SalesActivity---------filter_search-----------");
    search.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.v("water","SalesActivity---------beforeTextChanged-----------");
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            Log.v("water","SalesActivity---------onTextChanged-----------");
        }

        @Override
        public void afterTextChanged(Editable editable) {
            Log.v("water","SalesActivity---------search-----------");
            filterlist.clear();
            if (editable.toString().isEmpty()) {
//                Log.v("water","SalesActivity---------isEmpty-----------");
//                customer_adapter = new CustomerAdapter(getApplicationContext(), list);
                customer_list("a");
              // customer_adapter.notifyDataSetChanged();
            } else {

                Log.v("water","SalesActivity---------else-----------");
                customer_list(editable.toString());

              //  filter(editable.toString());
            }

        }


    });

}


    private void customer_list(String text) {

            SalesRequest salesRequest = new SalesRequest();
            salesRequest.setCustomerName(text);


            Log.v("water","SalesActivity---------customer_list------");
            Call<MainSalesResponse> call = RetrofitClientInstance.getSalesInterfaceAPI().requestsales("Bearer"+" "+token,salesRequest);

            call.enqueue(new Callback<MainSalesResponse>() {
                @Override
                public void onResponse(Call<MainSalesResponse> call, Response<MainSalesResponse> response) {
                    Log.v("water","SalesActivity---------onResponse-------"+response);


                    if(response.isSuccessful()) {
                        Log.v("water", "SalesActivity---------isSuccessful-------" );
                        customerlist = response.body().getResultSet();
                        Log.v("water", "SalesActivity---------list-------" + customerlist);

                        recyclerviews(customerlist);


                    }
                    else{
                        Log.v("water", "SalesActivity---------response failed--");
                        Toast.makeText(getApplicationContext(), "Please Login session expired", Toast.LENGTH_SHORT).show();
                        tokenSharedPreference.Clear_token();
                        tokenSharedPreference.setFirstLaunch(true);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }

                }



                @Override
                public void onFailure(Call<MainSalesResponse> call, Throwable t) {

                }
            });

    }

    private void recyclerviews(List<SalesResponse> list) {
        Log.v("water", "SalesActivity-----recyclerview--" + "");
        cust_recyclerView
                = (RecyclerView) findViewById(
                R.id.customer_recycler);
        Log.v("water", "SalesActivity-----recyclerview id--" + "");
        cust_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.v("water", "SalesActivity-----recyclerview setLayoutManager--" + "");

        customer_adapter = new CustomerAdapter(getApplicationContext(), list);

        Log.v("water", "SalesActivity-----recyclerview customer_adapter--" + "");
        cust_recyclerView.setAdapter((RecyclerView.Adapter) customer_adapter);
        Log.v("water", "SalesActivity-----recyclerview setAdapter--" + "");


    }
    public void bottom_navigation(){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
        // bottomNavigationView.setItemIconTintList(null);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.sales_nav);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.sales_nav:
                        return true;
                    case R.id.home_nav:

                    startActivity(new Intent(getApplicationContext(), HomepageActivity.class));
                    overridePendingTransition(0,0);
                    return true;
                    case R.id.collection_nav:
                        startActivity(new Intent(getApplicationContext(), CustomersOutstandingListActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }



}