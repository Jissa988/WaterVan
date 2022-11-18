package com.example.watervan.Sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.TokenSharedPreference;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    TextView name_text,phn_text,rout_text;
    Button back,proceed;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    int finacial_yearid;
    int strxid;
    List<SalesResponse> custview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();
        payment();
        name_text=findViewById(R.id.cust_name);
        phn_text=findViewById(R.id.cust_mobile);
        rout_text=findViewById(R.id.cust_adress);
        RadioGroup   radioGroup = (RadioGroup) findViewById(R.id.paymode);

//        String customer_name=getIntent().getExtras().getString("custname");
//        String custmer_contact=getIntent().getExtras().getString("custphone");
//        String customer_route=getIntent().getExtras().getString("custroute");
        int cust_id= getIntent().getExtras().getInt("cust_id");
        custview=SalesActivity.customerlist;
        for (SalesResponse salesResponse : custview) {
            if (salesResponse.getCustomerId() == cust_id) {
                String customer_name = salesResponse.getCustomerName();
                name_text.setText(customer_name);
                String customer_route = salesResponse.getOfficeAddress();
                rout_text.setText(customer_route);
                String custmer_contact = salesResponse.getCompanyPhone();
                phn_text.setText(custmer_contact);
            }

        }




        back=(Button) findViewById(R.id.pay_back);
        proceed=(Button) findViewById(R.id.pay_proceed);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                finish();
            }
        });

        proceed.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                int pay_id = 0;
                // find the radiobutton by returned id
                RadioButton  radioButtonid = (RadioButton) findViewById(selectedId);
                Log.v("water", "PaymentActivity---------radioButton------"+radioButtonid );
                String radioButton = (String) radioButtonid.getText();
                Log.v("water", "PaymentActivity---------radioButton------"+radioButton );
                if(radioButton.equals("Cash Sale")){
                    pay_id=1;
                }
                else if(radioButton.equals("Credit Sale")){
                    pay_id=2;
                }

                Log.v("water", "PaymentActivity---------finacial_yearid-intent------"+finacial_yearid );
                Intent intent = new Intent(PaymentActivity.this, ProductSaleActivity.class);
                intent.putExtra("custid",cust_id);
                intent.putExtra("fincialid",finacial_yearid);
                intent.putExtra("strx_id",strxid);
                intent.putExtra("payid",pay_id);
                startActivity(intent);
            }
        });

back.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(PaymentActivity.this, SalesActivity.class);

        startActivity(intent);
    }
});
    }
    public void payment(){

        Log.v("water","PaymentActivity---------payment------");
        Call<PaymentResponseResultSetArray> call = RetrofitClientInstance.gePaymentInterfaceAPI().requestpayment("Bearer"+" "+token);
call.enqueue(new Callback<PaymentResponseResultSetArray>() {
    @Override
    public void onResponse(Call<PaymentResponseResultSetArray> call, Response<PaymentResponseResultSetArray> response) {
        Log.v("water","PaymentActivity---------onResponse-------"+response);


        if(response.isSuccessful()) {
            Log.v("water", "PaymentActivity---------isSuccessful-------" );
            List<PaymentResponseResultSet> list = response.body().getResultSet();
            for(PaymentResponseResultSet paymentResponseResultSet:list){
                finacial_yearid=paymentResponseResultSet.getFinYearId();
                Log.v("water", "PaymentActivity---------finacial_yearid-------"+finacial_yearid );
            }

            List<PaymentResponseResultSet1> list1=response.body().getResultSet1();
            for(PaymentResponseResultSet1 paymentResponseResultSet1:list1){
                strxid=paymentResponseResultSet1.getStkTrxTypeId();
            }

            List<PaymentResponseResultSet2> list2=response.body().getResultSet2();
            for(PaymentResponseResultSet2 paymentResponseResultSet2:list2){
                int payment_id=paymentResponseResultSet2.getPaymentTermId();
                String payment_term=paymentResponseResultSet2.getPaymentTerm();

            }



        }else {
            Log.v("water", "PaymentActivity---------response failed--");
            Toast.makeText(getApplicationContext(), "Please Login session expired", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<PaymentResponseResultSetArray> call, Throwable t) {

    }
});


    }

    public void onBackPressed () {
       // super.onBackPressed();

    }
}