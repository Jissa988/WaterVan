package com.example.watervan.Sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.TokenSharedPreference;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleBillActivity extends AppCompatActivity  {
    ProductAdapter productAdapter;
    SaleBillAdapter saleBillAdapter;
    Button back_button;
    RecyclerView sale_bill_recyclerview;
    int cust_id,fin_id,strx_id,pay_id;
    String payment_type;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    List<SalesResponse> custview;
    List<ProductList> list;
    TextView payment_mode;
  ProductListArray product_view;
    TextView name,phn,address,saleTotal,round_view,total_amount;
    ImageView plus_button,minus_button;
    Button save;
    int i=0;
    double toal_sale;

//    List<SalesResponse> custemer;
//    List<ProductList> product;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_bill);
        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();
        cust_id=(getIntent().getExtras().getInt("custid"));
        fin_id=(getIntent().getExtras().getInt("finid"));
        strx_id=(getIntent().getExtras().getInt("strxid"));
        pay_id=(getIntent().getExtras().getInt("payid"));




        Log.v("water", "------------SaleBillActivity-------------cust_id---------"+cust_id );
        Log.v("water", "------------SaleBillActivity-------------fin_id---------"+fin_id );
        Log.v("water", "------------SaleBillActivity-------------strx_id---------"+strx_id );
        Log.v("water", "------------SaleBillActivity-------------pay_id---------"+pay_id );
        if(pay_id==1){
            payment_type="Cash";
        }
        else if(pay_id==2){
            payment_type="Credit";
        }

        save=findViewById(R.id.save_button);
        name=findViewById(R.id.billcust_name);
        phn=findViewById(R.id.billcust_mobile);
        address=findViewById(R.id.billcust_adress);
        saleTotal=findViewById(R.id.total);
//        plus_button=findViewById(R.id.round_plus);
//        minus_button=findViewById(R.id.round_minus);
        total_amount=findViewById(R.id.total_amount_text);
        back_button=findViewById(R.id.back_button);

        round_view=findViewById(R.id.round_amount);
        payment_mode=findViewById(R.id.pay_term);
        payment_mode.setText(payment_type);


         list=ProductAdapter.arraylist;
        custview=SalesActivity.customerlist;
        if(custview.isEmpty()){
            Log.v("water", "-------------------------SaleBillActivity-----custview is empty----" );
        }else{
            int count1=custview.size();
            Log.v("water", "-------------------------SaleBillActivity-----count1----"+count1 );
        }

        int count=list.size();

        Log.v("water", "-------------------------SaleBillActivity-----count----"+count );


            for (SalesResponse salesResponse : custview) {
                if (salesResponse.getCustomerId() == cust_id) {
                    String custname = salesResponse.getCustomerName();
                    name.setText(custname);
                    String addres = salesResponse.getOfficeAddress();
                    address.setText(addres);
                    String phns = salesResponse.getCompanyPhone();
                    phn.setText(phns);
                }

            }



        Log.v("water", "SaleBillActivity-----product_view.size();-------------" +list);
        recyclerviews();
        salescounts();

        Log.v("water", "-----------SaleBillActivity--------------toal_sale---------"+toal_sale);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billinsert();
            }
        });


//        plus_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                double nettotal=toal_sale;
//              double round_value=
//              total_amount.setText(String.valueOf(total_amounts));
//
//
//            }
//        });

//        minus_button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                double minus_roundoff=toal_sale;
//                String[] arr=String.valueOf(minus_roundoff).split("\\.");
//                int[] intArr=new int[2];
//                intArr[0]=Integer.parseInt(arr[0]); // 1
//                intArr[1]=Integer.parseInt(arr[1]); // 9
//                double  total_amounts=intArr[0];
//                total_amount.setText(String.valueOf(total_amounts));
//            }
//        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("water", "SaleBillActivity----onBackPressed-------------");

                Intent intent = new Intent(SaleBillActivity.this, ProductSaleActivity.class);
        intent.putExtra("custid",cust_id);
        intent.putExtra("fincialid",fin_id);
        intent.putExtra("strx_id",strx_id);
        intent.putExtra("payid",pay_id);


                startActivity(intent);
            }
        });
    }



    public void onBackPressed () {
      //  super.onBackPressed();

    }




    public void recyclerviews() {


                Log.v("water", "SaleBillActivity-----recyclerview--" + "");
        sale_bill_recyclerview
                = (RecyclerView) findViewById(
                R.id.sale_bill_recycler);
        Log.v("water", "SaleBillActivity-----recyclerview id--" + "");
        sale_bill_recyclerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        Log.v("water", "SaleBillActivity-----recyclerview setLayoutManager--" + "");

        saleBillAdapter = new SaleBillAdapter(getApplicationContext(),list);

        Log.v("water", "SaleBillActivity-----recyclerview customer_adapter--" + "");
        sale_bill_recyclerview.setAdapter((RecyclerView.Adapter) saleBillAdapter);
        Log.v("water", "SaleBillActivity-----recyclerview setAdapter--" + "");





    }
    public void salescounts(){
        Log.v("water", "-----------SaleBillActivity--------------salescounts---------");


        toal_sale=0;
 for (int i=0; i<list.size(); i++){
    double quant=list.get(i).getQuantity();
     double amt=list.get(i).getRate();
     double tax=list.get(i).getTaxrate();
     double total=(quant*amt*tax/100)+(quant*amt);

     toal_sale=toal_sale+total;
        }

        Log.v("water", "-----------SaleBillActivity--------------toal_sale---------"+toal_sale);

        saleTotal.setText("Net Total :"+String.valueOf(toal_sale));
        round_view.setText("0");
        total_amount.setText(String.valueOf(toal_sale));
    }



    public void billinsert(){
        SaleBillRequest saleBillRequest=new SaleBillRequest();
        saleBillRequest.setCustomerId(9);
        saleBillRequest.setSaleInvoiceHeadId(0);
        saleBillRequest.setOutboundDeliveryHeadId(0);
        saleBillRequest.setFinYearId(1);
        saleBillRequest.setStkTrxTypeId(10);
        saleBillRequest.setInvoiceDate("01-Aug-2022");
        saleBillRequest.setNarration("");
        saleBillRequest.setRoundOff(9.0);
        saleBillRequest.setWarehouseId(1);
        saleBillRequest.setGrossAmount(20.0);
        saleBillRequest.setTotalDiscountPercent(1.0);
        saleBillRequest.setTotalDiscountAmount(1);
        saleBillRequest.setTotalTaxAmount(12);
        saleBillRequest.setNetAmount(34);
        saleBillRequest.setReceiptType(1);
        saleBillRequest.setFinalized("true");
        saleBillRequest.setActiveStatus("true");
        saleBillRequest.setPaymentTermId(1);

        Call<SaleBillResponseArray> call = RetrofitClientInstance.getSaleBillInterface().requestbill("Bearer"+" "+token,saleBillRequest,list);

call.enqueue(new Callback<SaleBillResponseArray>() {
    @Override
    public void onResponse(Call<SaleBillResponseArray> call, Response<SaleBillResponseArray> response) {
        Log.v("water","ProductSaleActivity---------onResponse-------"+response);


        if(response.isSuccessful()) {
            Log.v("water", "ProductSaleActivity---------isSuccessful-------");
            List<SaleBillResponse> list = response.body().getResultSet();
        }
        else {
            Log.v("water", "ProductSaleActivity---------response failed--");
            Toast.makeText(getApplicationContext(), "Please Login session expired", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<SaleBillResponseArray> call, Throwable t) {

    }
});



    }



}