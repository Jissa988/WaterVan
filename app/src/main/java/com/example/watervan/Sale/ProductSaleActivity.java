package com.example.watervan.Sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.TokenSharedPreference;
import com.nex3z.notificationbadge.NotificationBadge;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductSaleActivity extends AppCompatActivity {
  ProductAdapter productAdapter;
    RecyclerView pro_recyclerView;
    Button back,proceed;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    int cust_id,pay_id;
    int fin_id,strx_id;
    ImageView cart;
    EditText search;
    NotificationBadge notificationBadge;

    List<SalesResponse> custemer;
    List<ProductList> product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_sale);
        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();
        search = findViewById(R.id.edit_searchs);
      //  cart=findViewById(R.id.cartcont);

        //  notificationBadge.setNumber(2);

       cust_id=(getIntent().getExtras().getInt("custid"));
       fin_id=(getIntent().getExtras().getInt("fincialid"));
        strx_id=(getIntent().getExtras().getInt("strx_id"));
        pay_id=(getIntent().getExtras().getInt("payid"));

//        cust_id=1;
//        fin_id=1;
//        strx_id=28;
//        pay_id=1;

        Log.v("water","ProductSaleActivity---------fin_id-----------"+fin_id);



        productview("a");
        filter_search();


        back = (Button) findViewById(R.id.pro_back);
        proceed = (Button) findViewById(R.id.pro_proceed);


        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

//                Intent intent = new Intent(context, PaymentActivity.class);
//                context.startActivity(intent);
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

    Intent intent = new Intent(ProductSaleActivity.this, SaleBillActivity.class);
    intent.putExtra("custid",cust_id);
    intent.putExtra("finid",fin_id);
    intent.putExtra("strxid",strx_id);
    intent.putExtra("payid",pay_id);

    startActivity(intent);


            }


        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductSaleActivity.this, PaymentActivity.class);
                intent.putExtra("cust_id",cust_id);
                startActivity(intent);


            }
        });

//
//        this.cart.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                Log.v("water","Homepage-----setOnTouchListener-----");
//                final int Drawable_Left=0;
//                final int Drawable_Top=1;
//                final int Drawable_Right=2;
//                final int Drawable_Bottom=3;
//                if(motionEvent.getAction()==motionEvent.ACTION_UP){
//                    if(motionEvent.getRawX()>=cart.getRight()-
//                            cart.getCompoundDrawables()[Drawable_Right].getBounds().width()){
//
//
//                        return true;
//                    }
//                }
//                return false;
//            }
//        });

    }

    public void productview(String text){
        ProductRequest productRequest = new ProductRequest();
        productRequest.setFinYearId(fin_id);
        productRequest.setCustomerId(cust_id);
        productRequest.setItemName(text);


        Log.v("water","ProductSaleActivity---------productview------");
        Call<ProductResponseResultSetArray> call = RetrofitClientInstance.getProductInterfaceAPI().requestproduct("Bearer"+" "+token,productRequest);

        call.enqueue(new Callback<ProductResponseResultSetArray>() {
            @Override
            public void onResponse(Call<ProductResponseResultSetArray> call, Response<ProductResponseResultSetArray> response) {
                Log.v("water","ProductSaleActivity---------onResponse-------"+response);


                if(response.isSuccessful()) {
                    Log.v("water", "ProductSaleActivity---------isSuccessful-------" );
                    List<ProductResponseResultSet> list = response.body().getResultSet();
                    List<ProductResponseResultSet1> list1 = response.body().getResultSet1();
                    Log.v("water", "ProductSaleActivity---------list-------" + list);
                    recyclerviews1(list,list1);


                }else {
                    Log.v("water", "ProductSaleActivity---------response failed--");
                    Toast.makeText(getApplicationContext(), "Please Login session expired", Toast.LENGTH_SHORT).show();
                }

            }

            private void recyclerviews1(List<ProductResponseResultSet> list, List<ProductResponseResultSet1> list1) {

                Log.v("water", "ProductSaleActivity-----recyclerview--" + "");
                pro_recyclerView
                        = (RecyclerView) findViewById(
                        R.id.product_recycler);
                Log.v("water", "ProductSaleActivity-----recyclerview id--" + "");
                pro_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
                Log.v("water", "ProductSaleActivity-----recyclerview setLayoutManager--" + "");

                productAdapter = new ProductAdapter(getApplicationContext(),list,list1);

                Log.v("water", "ProductSaleActivity-----recyclerview productAdapter--" + "");
                pro_recyclerView.setAdapter((RecyclerView.Adapter) productAdapter);
                Log.v("water", "ProductSaleActivity-----recyclerview setAdapter--" + "");
            }

            @Override
            public void onFailure(Call<ProductResponseResultSetArray> call, Throwable t) {

            }
        });
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
              //  filterlist.clear();
                if (editable.toString().isEmpty()) {
//                Log.v("water","SalesActivity---------isEmpty-----------");
//                customer_adapter = new CustomerAdapter(getApplicationContext(), list);
                    productview("a");
                    // customer_adapter.notifyDataSetChanged();
                } else {

                    Log.v("water","SalesActivity---------else-----------");
                    productview(editable.toString());

                    //  filter(editable.toString());
                }

            }


        });

    }

    public void onBackPressed () {
        super.onBackPressed();

    }


}