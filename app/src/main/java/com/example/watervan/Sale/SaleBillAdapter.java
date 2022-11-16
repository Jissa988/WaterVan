package com.example.watervan.Sale;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watervan.R;

import java.util.List;

public class SaleBillAdapter  extends RecyclerView.Adapter<SaleBillAdapter.SaleBillViewHolder> {

    private Context context;
    List<ProductList> bill_list;
    double total_amout;
    SaleBillAdapter saleBillAdapter;



    public SaleBillAdapter(Context context, List<ProductList> list) {
        this.context = context;
        this.bill_list=list;
        total_amout=0;

    }



    @NonNull
    @Override
    public SaleBillAdapter.SaleBillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "SaleBillActivity-----CustomerViewHolder-------------" );
        View v= LayoutInflater.from(context).inflate(R.layout.sale_bill_recycler_list,parent,false);
        return new SaleBillAdapter.SaleBillViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleBillAdapter.SaleBillViewHolder holder, int position) {
        Log.v("water", "SaleBillActivity-----onBindViewHolder-------------" );


//        holder.setIsRecyclable(true);

        holder.productname.setText((String) bill_list.get(position).getItem_code());
        holder.quantity.setText((String) String.valueOf(bill_list.get(position).getQuantity()));
        holder.rates.setText((String) String.valueOf(bill_list.get(position).getRate()));
        holder.tax.setText((String) String.valueOf(bill_list.get(position).getTaxrate()));
        holder.delete.setImageResource(R.drawable.ic_baseline_delete_16);
       // holder.delete.setColorFilter(Color.argb(000, 144, 12, 63));
        int quant=bill_list.get(position).getQuantity();
        double amt=bill_list.get(position).getRate();
        double tax=bill_list.get(position).getTaxrate();
        int pos=bill_list.get(position).getArray_pos();
        Log.v("water", "SaleBillActivity-----onBindViewHolder------tax-------"+tax );
        double amount=quant*amt;
        double net_amount=amount+(amount*tax/100);
        holder.amounts.setText((String) String.valueOf(net_amount));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ProductAdapter.arraylist.remove(pos);

                Log.v("water", "SaleBillActivity-----onBindViewHolder------  ProductAdapter.arraylist.size();-------"+  ProductAdapter.arraylist.size() );

                holder.row.setVisibility(View.GONE);

//                bill_list.remove(position);
//                notifyItemRemoved(position);
//                bill_list.remove(holder.getAdapterPosition());
//                notifyItemRemoved(holder.getAdapterPosition());







            }
        });

        total_amout=total_amout+net_amount;

        Log.v("water", "SaleBillActivity-----onBindViewHolder------total_amout-------"+total_amout);
    }

    @Override
    public int getItemCount() {
        return bill_list.size();
    }

    public class SaleBillViewHolder extends RecyclerView.ViewHolder {
        public TextView productname,quantity,rates,amounts,tax ;
        ImageView delete;
        LinearLayout row;
        public SaleBillViewHolder(@NonNull View v) {
            super(v);
            this.productname=(TextView) v.findViewById(R.id.name);
            this.quantity=(TextView) v.findViewById(R.id.qty);
            this.rates=(TextView) v.findViewById(R.id.rate);
            this.amounts=(TextView) v.findViewById(R.id.amount);
            this.tax=(TextView) v.findViewById(R.id.tax_text);
            this.delete=(ImageView) v.findViewById(R.id.delete_icon);
            this.row=(LinearLayout) v.findViewById(R.id.linear_row);

        }
    }
}
