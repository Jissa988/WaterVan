package com.example.watervan.Collections;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watervan.Login.LoginActivity;
import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.Sale.PaymentActivity;
import com.example.watervan.Sale.SalesActivity;
import com.example.watervan.Sale.SalesResponse;
import com.example.watervan.TokenSharedPreference;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingCollectionActivity extends AppCompatActivity {

    TextView customer_name,customer_phn,customer_address,customer_amount;
    LinearLayout L1,L2;
    Button save,back;
    PendingCollectionAdapter pendingCollectionAdapter;
    RecyclerView pending_collectio_recyclerview;
    int ldghead_id;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    List<PendingCollectionResponse> pendingcollection;
    int customer_id;
    List<CustomersResponse> customer_view;
    List<PendingList> coll_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_collection);

        customer_name=findViewById(R.id.out_cust_name);
        customer_phn=findViewById(R.id.out_cust_phn);
        customer_address=findViewById(R.id.out_cust_address);
        customer_amount=findViewById(R.id.out_cust_amount);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.outpaymode);
        L1=findViewById(R.id.linear_chequeno);
        L2=findViewById(R.id.linear_chequedate);
        save=findViewById(R.id.collection_save);
        back=findViewById(R.id.collection_back);

        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();
        Log.v("water", "PendingCollectionActivity-----PendingCollectionActivity-------------" );
        ldghead_id=getIntent().getExtras().getInt("leger_id");
        customer_id=getIntent().getExtras().getInt("cust_id");
        customer_view= CustomersOutstandingListActivity.outcustomerlist;

        for (CustomersResponse customersResponse : customer_view) {
            if (customersResponse.getCustomerId() == customer_id) {
                String custname = customersResponse.getCustomerName();
                customer_name.setText(custname);
                String addres = customersResponse.getOfficeAddress();
                customer_address.setText(addres);
                String phns = customersResponse.getCompanyPhone();
                customer_phn.setText(phns);
                String amount=customersResponse.getOutstandingAmount();
                customer_amount.setText(amount);
            }

        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                int selectedId = radioGroup.getCheckedRadioButtonId();

                RadioButton radioButtonid = (RadioButton) findViewById(selectedId);
                Log.v("water", "PendingCollectionActivity---------radioButton------"+radioButtonid );
                String radioButton = (String) radioButtonid.getText();
                Log.v("water", "PendingCollectionActivity---------radioButton------"+radioButton );
                if(radioButton.equals("Cash")){
                    L1.setVisibility(View.GONE);
                    L2.setVisibility(View.GONE);

                }
                else if(radioButton.equals("Cheque")){
                    L1.setVisibility(View.VISIBLE);
                    L2.setVisibility(View.VISIBLE);
                }

            }
        });

        coll_list=PendingCollectionAdapter.pending_collection_list;

save.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Log.v("water", "PendingCollectionActivity---------coll_list------"+coll_list );
        for(PendingList pendingList:coll_list) {
            Log.v("water", "PendingCollectionActivity---------getInvoiceId------" +pendingList.getInvoiceId());
            Log.v("water", "PendingCollectionActivity---------getPaidamout------" + pendingList.getPaidamout());
        }
    }
});


back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PendingCollectionActivity.this, CustomersOutstandingListActivity.class);

        startActivity(intent);
    }
});

        pending_collections();
    }
    public void recyclerviews (List<PendingCollectionResponse> list) {
        Log.v("water", "PendingCollectionActivity-----recyclerview--" + "");
        pending_collectio_recyclerview
                = (RecyclerView) findViewById(
                R.id.pending_bill_recycler);
        Log.v("water", "PendingCollectionActivity-----recyclerview id--" + "");
        pending_collectio_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.v("water", "PendingCollectionActivity-----recyclerview setLayoutManager--" + "");

        pendingCollectionAdapter = new PendingCollectionAdapter(this,list);

        Log.v("water", "PendingCollectionActivity-----recyclerview customer_adapter--" + "");
        pending_collectio_recyclerview.setAdapter((RecyclerView.Adapter) pendingCollectionAdapter);
        Log.v("water", "PendingCollectionActivity-----recyclerview setAdapter--" + "");

    }
    private void pending_collections(){

        PendingCollectionRequest pendingCollectionRequest = new PendingCollectionRequest();
        pendingCollectionRequest.setAccountId(ldghead_id);
        pendingCollectionRequest.setDocNo(null);
        pendingCollectionRequest.setFinYearId(1);
        pendingCollectionRequest.setFromDate(null);
        pendingCollectionRequest.setToDate(null);


        Log.v("water","CustomersOutstandingListActivity---------customer_list------");
        Call<PendingCollectionResponseArray> call = RetrofitClientInstance.getPendingInterfaceAPI().requestcollection("Bearer"+" "+token,pendingCollectionRequest);

        call.enqueue(new Callback<PendingCollectionResponseArray>() {
            @Override
            public void onResponse(Call<PendingCollectionResponseArray> call, Response<PendingCollectionResponseArray> response) {
                Log.v("water","CustomersOutstandingListActivity---------onResponse-------"+response);


                if(response.isSuccessful()) {
                    Log.v("water", "CustomersOutstandingListActivity---------isSuccessful-------" );
                    pendingcollection= response.body().getResultSet();
                    Log.v("water", "SalesActivity---------list-------" + pendingcollection);

                    recyclerviews(pendingcollection);


                }
                else{
                    Log.v("water", "CustomersOutstandingListActivity---------response failed--");

                }

            }



            @Override
            public void onFailure(Call<PendingCollectionResponseArray> call, Throwable t) {

            }
        });


    }
    public void onBackPressed () {
       // super.onBackPressed();

    }



}