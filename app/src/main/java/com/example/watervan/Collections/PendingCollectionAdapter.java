package com.example.watervan.Collections;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watervan.R;

import java.util.ArrayList;
import java.util.List;

public class PendingCollectionAdapter extends RecyclerView.Adapter<PendingCollectionAdapter.PendingCollectionViewHolder> {

private Context context;
    List<PendingCollectionResponse> pending_list;
    public static List<PendingList> pending_collection_list=new ArrayList<PendingList>();

    public PendingCollectionAdapter(PendingCollectionActivity context, List<PendingCollectionResponse> list) {
        this.context = context;
        this.pending_list=list;

        for(int i=0;i<pending_list.size();i++){
            PendingList pendingList=new PendingList();

            pendingList.setInvoiceId(pending_list.get(i).getInvoiceId());
            pendingList.setPaidamout(0);
            pendingList.setCollection_array(i);
        }
}

    @NonNull
    @Override
    public PendingCollectionAdapter.PendingCollectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "PendingCollectionActivity-----CustomerViewHolder-------------" );
        View v= LayoutInflater.from(context).inflate(R.layout.pending_collection_recycler,parent,false);
        return new PendingCollectionAdapter.PendingCollectionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingCollectionAdapter.PendingCollectionViewHolder holder, int position) {
        Log.v("water", "PendingCollectionActivity-----onBindViewHolder-------------" );


        holder.setIsRecyclable(true);

        holder.invoice_nos.setText((String) pending_list.get(position).getDocNo());
        holder.invo_date.setText((String) pending_list.get(position).getDueDt());
        holder.outsta_amount.setText(String.valueOf(pending_list.get(position).getBalance()));
        holder.paid_amout.setText((String) "");


        holder.paid_amout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double amount_value=Double. parseDouble(String.valueOf(s));
                PendingList pendingList=new PendingList();
                if(amount_value>0){
                    double get_amout=Double.parseDouble(String.valueOf(holder.paid_amout.getText()));
                    pendingList.setInvoiceId(pending_list.get(position).getInvoiceId());
                    pendingList.setPaidamout(get_amout);
                    pending_collection_list.add(pendingList.getCollection_array(),pendingList);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return pending_list.size();
    }

    public class PendingCollectionViewHolder extends RecyclerView.ViewHolder {
        public TextView invoice_nos,invo_date,outsta_amount ;
        EditText paid_amout;
        public PendingCollectionViewHolder(@NonNull View v) {
            super(v);
            this.invoice_nos=(TextView) v.findViewById(R.id.invoice_no);
            this.invo_date=(TextView) v.findViewById(R.id.invoice_date);
            this.outsta_amount=(TextView) v.findViewById(R.id.invoice_amount);
            this.paid_amout=(EditText) v.findViewById(R.id.paid_amount);
        }
    }
}
