package com.example.watervan.Home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.watervan.R;

import java.util.ArrayList;

public class HomepageAdapter extends RecyclerView.Adapter<HomepageAdapter.HomepageViewHolder> {
    private Context context;
    private ArrayList Imgs,accounts,amounts;
    public RecyclerView recyclerView;

    public HomepageAdapter(Context context, ArrayList image, ArrayList account, ArrayList amount) {


        Log.v("water", "HomepageActivity-----Customer_Adapter----------" + "");
        this.context = context;
        this.Imgs= image;
        this.accounts = account;
        this.amounts = amount;


    }

    @NonNull
    @Override
    public HomepageAdapter.HomepageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "HomepageActivity-----CustomerViewHolder-------------" );

        this.recyclerView=(RecyclerView) parent;

       View v= LayoutInflater.from(context).inflate(R.layout.homepage_recycler_list,parent,false);
//        int width = recyclerView.getWidth();
//        ViewGroup.LayoutParams params = v.getLayoutParams();
//        params.width = (int)(width * 0.7);
//        v.setLayoutParams(params);
 return new HomepageAdapter.HomepageViewHolder(v);



        }

    @Override
    public void onBindViewHolder(@NonNull HomepageAdapter.HomepageViewHolder holder, int position) {
        Log.v("water", "HomepageActivity-----onBindViewHolder-------------" );
        Log.v("water", "HomepageActivity-----onBindViewHolder-------Img---" + Imgs);
        Log.v("water", "HomepageActivity-----onBindViewHolder-------accounts---" + accounts);
        Log.v("water", "HomepageActivity-----onBindViewHolder-------amounts---" + amounts);


//       int res = (int)Imgs.get(position);
//       holder.img.setImageResource(res);
       holder.acc.setText((String) accounts.get(position));
       holder.amt.setText((String) amounts.get(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class HomepageViewHolder extends RecyclerView.ViewHolder {
      public ImageView img;
      public TextView acc,amt;
        public HomepageViewHolder(@NonNull View v) {
            super(v);
//            this.img=(ImageView) v.findViewById(R.id.imagview);
            this.acc=(TextView) v.findViewById(R.id.acc_text);
            this.amt=(TextView) v.findViewById(R.id.amou_text);

        }
    }
}
