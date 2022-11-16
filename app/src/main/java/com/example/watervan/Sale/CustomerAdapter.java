package com.example.watervan.Sale;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watervan.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {
    private Context context;
    private List<SalesResponse> cust_list;





    public CustomerAdapter(Context context, List<SalesResponse> list) {
        this.context = context;
        this.cust_list=list;

    }


    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "SalesActivity-----CustomerViewHolder-------------" );
        View v= LayoutInflater.from(context).inflate(R.layout.customer_recycler_list,parent,false);
        return new CustomerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerViewHolder holder, int position) {
        Log.v("water", "SalesActivity-----onBindViewHolder-------------" );



        holder.cname.setText((String) cust_list.get(position).getCustomerName());
        holder.cphn.setText((String) cust_list.get(position).getCompanyPhone());
        holder.croute.setText((String) cust_list.get(position).getOfficeAddress());
        holder.camount.setText((String) cust_list.get(position).getOutstandingAmount());
        //holder.custs_id.setText((String) cust_ids.get(position));

String name=cust_list.get(position).getCustomerName();
String phone=cust_list.get(position).getCompanyPhone();
String route=cust_list.get(position).getOfficeAddress();
int customer_id=cust_list.get(position).getCustomerId();
        holder.sale.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            ProductAdapter.arraylist.clear();

                Intent intent = new Intent(context, PaymentActivity.class);
//                intent.putExtra("custname",name);
//                intent.putExtra("custphone",phone);
//                intent.putExtra("custroute",route);
                intent.putExtra("cust_id",customer_id);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
       return cust_list.size();
    }



    public class CustomerViewHolder extends RecyclerView.ViewHolder {
        public TextView cname,cphn,croute,camount,custs_id ;
        public Button sale;


        public CustomerViewHolder(@NonNull View v) {
            super(v);
            this.cname=(TextView) v.findViewById(R.id.cust_name);
            this.cphn=(TextView) v.findViewById(R.id.cust_mobile);
            this.croute=(TextView) v.findViewById(R.id.cust_adress);
            this.camount=(TextView) v.findViewById(R.id.credit_amout);
            this.custs_id=(TextView) v.findViewById(R.id.customersid);
            this.sale=(Button) v.findViewById(R.id.new_sale);


        }
    }
}
