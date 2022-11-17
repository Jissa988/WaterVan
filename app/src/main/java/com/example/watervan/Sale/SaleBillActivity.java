package com.example.watervan.Sale;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.TokenSharedPreference;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SaleBillActivity extends AppCompatActivity  {
    ProductAdapter productAdapter;
    SaleBillAdapter saleBillAdapter;
    Button back_button;
    Spinner spinner;
    RecyclerView sale_bill_recyclerview;
    int cust_id,fin_id,strx_id,pay_id;
    String payment_type;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    List<SalesResponse> custview;
    List<ProductList> list;
    TextView payment_mode;
  ProductListArray product_view;
    TextView name,phn,address,saleTotal,total_amount;
    EditText round_value;
    ImageView plus_button,minus_button;
    Button save;
    int i=0;
    double toal_sale;
    String date;
    String round_offtype;
    double gross_amount=0,total_tax=0,net_amount=0,round_amount=0;
     List<SaleBillProductRequest> product_request=new ArrayList<>();
     SaleBillProductRequestArray saleBillProductRequestArray;
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
        spinner=findViewById(R.id.round_spinner);

         date = String.valueOf(android.text.format.DateFormat.format("dd-MMM-yyyy", new java.util.Date()));
        Log.v("water", "------------SaleBillActivity-------------date---------"+date );


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

        total_amount=findViewById(R.id.total_amount_text);
        back_button=findViewById(R.id.back_button);

        round_value=findViewById(R.id.round_amount);
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


        List<String> list = new ArrayList<String>();
        list.add("+");
        list.add("-");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        round_offtype=  spinner.getSelectedItem().toString();


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



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                round_offtype= spinner.getSelectedItem().toString();
                round_amount=Double.parseDouble(String.valueOf(round_value.getText()));
                String nettotal_string= String.valueOf(saleTotal.getText());
                String[] parts = nettotal_string.split("Net Total :");
                double net_rate = Double.parseDouble(parts[1]);
                if(round_offtype.equals("+")) {
                    net_amount = round_amount + net_rate;
                }
                else if(round_offtype.equals("-")){
                    net_amount = net_rate-round_amount;
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        round_value.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {


                round_offtype= spinner.getSelectedItem().toString();
                round_amount=Double. parseDouble(String.valueOf(s));
                String nettotal_string= String.valueOf(saleTotal.getText());
                String[] parts = nettotal_string.split("Net Total :");
                double net_rate = Double.parseDouble(parts[1]);
                if(round_offtype.equals("+")) {
                    net_amount = round_amount + net_rate;
                }
                else if(round_offtype.equals("-")){
                    net_amount = net_rate-round_amount;
                }

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
        round_value.setText("0");
        total_amount.setText(String.valueOf(toal_sale));
    }



    public void billinsert(){


        SaleBillProductRequest saleBillProductRequest=new SaleBillProductRequest();
        int i=0;
        for(ProductList productList:list){

            saleBillProductRequest.setSaleInvoiceLineId(0);
            saleBillProductRequest.setRowNum(i);
            saleBillProductRequest.setItemId(productList.getProduct_id());
            saleBillProductRequest.setUnitId(productList.getUnit_id());
            saleBillProductRequest.setQty(productList.getQuantity());
            saleBillProductRequest.setUnitPrice(productList.getRate());
            double amount=productList.getQuantity()*productList.getRate();
            saleBillProductRequest.setAmount(amount);
            saleBillProductRequest.setDiscountPercent(0);
            saleBillProductRequest.setDiscountAmount(0);
            saleBillProductRequest.setTaxPercent(productList.getTaxrate());
            double tax_amount=productList.getTaxrate()*amount/100;
            saleBillProductRequest.setTaxableAmount(tax_amount);
            double total=tax_amount+amount;
            saleBillProductRequest.setTotalAmount(total);
            saleBillProductRequest.setStockIssueId(1);
            saleBillProductRequest.setActiveStatus(true);

         //   saleBillProductRequestArray.setTypeSaleLine((List<SaleBillProductRequest>) saleBillProductRequest);

            product_request.add(saleBillProductRequest);
        }
      i++;

        for(SaleBillProductRequest saleBillRequest:product_request){
            gross_amount = gross_amount + saleBillRequest.getAmount();
            total_tax=total_tax+saleBillRequest.getTaxableAmount();
            net_amount=net_amount+saleBillRequest.getTotalAmount();

        }
        Log.v("water","ProductSaleActivity---------onResponse-------"+gross_amount);
        Log.v("water","ProductSaleActivity---------onResponse-------"+total_tax);
        Log.v("water","ProductSaleActivity---------onResponse-------"+net_amount);


        SaleBillRequest saleBillRequest=new SaleBillRequest();
        saleBillRequest.setSaleInvoiceHeadId(0);
        saleBillRequest.setOutboundDeliveryHeadId(1);
        saleBillRequest.setFinYearId(fin_id);
        saleBillRequest.setStkTrxTypeId(strx_id);
        saleBillRequest.setInvoiceDate(date);
        saleBillRequest.setCustomerId(cust_id);
        saleBillRequest.setNarration("");
        saleBillRequest.setRoundOff(round_amount);
        saleBillRequest.setRoundOffType(round_offtype);
        saleBillRequest.setGrossAmount(gross_amount);
        saleBillRequest.setTotalDiscountPercent(0);
        saleBillRequest.setTotalDiscountAmount(0);
        saleBillRequest.setTotalTaxAmount(total_tax);
        saleBillRequest.setNetAmount(net_amount);
        saleBillRequest.setReceiptType(1);
        saleBillRequest.setFinalized(true);
        saleBillRequest.setActiveStatus(true);
        saleBillRequest.setPaymentTermId(pay_id);

Call<SaleBillResponseArray> call = RetrofitClientInstance.getSaleBillInterface()
                .requestbill("Bearer"+" "+token,saleBillRequest,product_request);

call.enqueue(new Callback<SaleBillResponseArray>() {
    @Override
    public void onResponse(Call<SaleBillResponseArray> call, Response<SaleBillResponseArray> response) {
        Log.v("water","ProductSaleActivity---------onResponse-------"+response);


        if(response.isSuccessful()) {
            Log.v("water", "ProductSaleActivity---------isSuccessful-------");
            List<SaleBillResponse> list = response.body().getResultSet();
            for(SaleBillResponse saleBillResponse:list){
                String docno=saleBillResponse.getDocNo();
                String message=saleBillResponse.getResultText();
                if(message.equals("Success")){
                    Snackbar snackbar = Snackbar.make(
                            (((SaleBillActivity) getApplicationContext()).findViewById(android.R.id.content)),
                            message + "Sale successfully completed with documentno "+docno, Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }
            Log.v("water", "ProductSaleActivity--------SaleBillResponse------"+list);
        }
        else {
            Log.v("water", "ProductSaleActivity---------response failed--");

        }

    }

    @Override
    public void onFailure(Call<SaleBillResponseArray> call, Throwable t) {

    }
});



    }



}