package com.example.watervan.Collections;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.watervan.R;
import com.example.watervan.Sale.SalesResponse;

import java.util.ArrayList;
import java.util.List;

public class CustomerOutstandingAdapter extends RecyclerView.Adapter<CustomerOutstandingAdapter.CustomerOutstandingViewHolder> {

    private Context context;
    List<CustomersResponse> outstandcustomer_list;





    public CustomerOutstandingAdapter(CustomersOutstandingListActivity context, List<CustomersResponse> list) {
        this.context = context;
        this.outstandcustomer_list=list;

    }


    @NonNull
    @Override
    public CustomerOutstandingAdapter.CustomerOutstandingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "SalesActivity-----CustomerViewHolder-------------" );
        View v= LayoutInflater.from(context).inflate(R.layout.customer_outstanding_recycler_list,parent,false);
        return new CustomerOutstandingAdapter.CustomerOutstandingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerOutstandingAdapter.CustomerOutstandingViewHolder holder, int position) {
        Log.v("water", "CustomerOutstandingActivity-----onBindViewHolder-------------" );



        holder.cname.setText((String) outstandcustomer_list.get(position).getCustomerName());
        holder.cphn.setText((String) outstandcustomer_list.get(position).getCompanyPhone());
        holder.croute.setText((String) outstandcustomer_list.get(position).getOfficeAddress());
        holder.camount.setText((String) outstandcustomer_list.get(position).getOutstandingAmount());

        holder.paid.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.v("water", "CustomerOutstandingActivity-----paid.setOnClickListener-------------" );
                Intent intent = new Intent(context, PendingCollectionActivity.class);
                intent.putExtra("leger_id",outstandcustomer_list.get(position).getLdgHeadId());
                intent.putExtra("cust_id",outstandcustomer_list.get(position).getCustomerId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return outstandcustomer_list.size();
    }

    public class CustomerOutstandingViewHolder extends RecyclerView.ViewHolder {
        public TextView cname,cphn,croute,camount ;
        public Button paid;

        public CustomerOutstandingViewHolder(View v) {
            super(v);
            this.cname=(TextView) v.findViewById(R.id.outcust_name);
            this.cphn=(TextView) v.findViewById(R.id.outcust_mobile);
            this.croute=(TextView) v.findViewById(R.id.outcust_adress);
            this.camount=(TextView) v.findViewById(R.id.outcredit_amout);
            this.paid=(Button) v.findViewById(R.id.outbtn_paid);

        }
    }
}
