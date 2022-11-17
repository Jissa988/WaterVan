package com.example.watervan.Sale;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.watervan.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private Context context;



 public static List<ProductList> arraylist=new ArrayList<ProductList>();
 List <ProductList> poduct_list;




    private List<ProductResponseResultSet> pro_list;
    private List<ProductResponseResultSet1> unit_list;




    public ProductAdapter(Context context, List<ProductResponseResultSet> list, List<ProductResponseResultSet1> list1) {
        Log.v("water", "ProductSaleActivity-----ProductAdapter----Context------" + "");


        this.context=context;
        this.pro_list=list;
        this.unit_list=list1;
       // arraylist.clear();
        //<pro_list.size()
        for(int i=0;i==5;i++){
            ProductList productList=new ProductList();

            productList.setProduct_id(pro_list.get(i).getItemId());
            productList.setProduct_name(pro_list.get(i).getItemName());
            productList.setQuantity(0);
            productList.setUnit_id(unit_list.get(i).getUnitId());
            productList.setRate(unit_list.get(i).getSalePrice());
            productList.setTaxid(pro_list.get(i).getTaxId());
            productList.setTaxrate(pro_list.get(i).getTaxRate());
            productList.setArray_pos(i);
            Log.v("water", "SaleBillActivity-----productList- for -----getProduct_id----" + pro_list.get(i).getItemId());
            Log.v("water", "SaleBillActivity-----productList--- for ---getProduct_id----" + pro_list.get(i).getItemName());
        }



    }



    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "ProductSaleActivity-----ProductViewHolder-------------" );
        View v= LayoutInflater.from(context).inflate(R.layout.product_recycler_list,parent,false);
        return new ProductAdapter.ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int pos) {
        Log.v("water", "ProductSale-----onBindViewHolder-------------" );

        int position= holder.getAdapterPosition();
        if(arraylist.size()==0){
            Log.v("water", "ProductSale-----onBindViewHolder--------if arraylist null-----" );

        }
else{
       //     holder.saleedit.setText();
        }


        holder.productname.setText((String) pro_list.get(pos).getItemName());
        holder.productstock.setText("Stock-"+(String) pro_list.get(pos).getStock());
        double taxarte= pro_list.get(pos).getTaxRate();
        Log.v("water", "ProductSaleActivity-----if----taxarte--" + taxarte);
        holder.tax_value.setText((String.valueOf(taxarte)) );
       int itemid= pro_list.get(pos).getItemId();

        ArrayList unitname=new ArrayList();
       for(ProductResponseResultSet1 productResponseResultSet1s:unit_list){
           int item_id=productResponseResultSet1s.getItemId();
           if(item_id==itemid){
               Log.v("water", "ProductSaleActivity-----if----item_idunit--" + item_id);
               Log.v("water", "ProductSaleActivity-----if----itemidproduct--" + itemid);

               unitname.add(productResponseResultSet1s.getUnitName());
               Log.v("water", "ProductSaleActivity-----if----unit--" + unitname);
           }

       }
        ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_item, unitname);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        holder.spinner.setAdapter(adapter);

        holder.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
                String spinner_unit=holder.spinner.getSelectedItem().toString();
                Log.v("water", "ProductSaleActivity-----if----spinner_unit--" + spinner_unit);
                for(ProductResponseResultSet1 productResponseResultSet1s:unit_list){
                    String unitname=productResponseResultSet1s.getUnitName();

                    if(spinner_unit.equals(unitname)){
                        Double salePrice=productResponseResultSet1s.getSalePrice();
                        holder.productrate.setText("Rate-"+(String) String.valueOf (salePrice)+"AED");
                        Log.v("water", "ProductSaleActivity-----if----salePrice--" + salePrice);
                    }


                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        holder.saleminus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
        int current_quantity=Integer.parseInt(holder.saleedit.getText().toString());
                Log.v("water", "ProductSale-----onBindViewHolder-------current_quantity---" + current_quantity);

                if(current_quantity==0){
                    holder.saleedit.setText(String.valueOf(current_quantity));
                }
                else {
                    int quantity_decrement = current_quantity - 1;
                    Log.v("water", "ProductSale-----onBindViewHolder-------quantity_decrement---" + quantity_decrement);
                    holder.saleedit.setText(String.valueOf(quantity_decrement));


                }


            }

        });
        holder.saleplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int current_quantity=Integer.parseInt(holder.saleedit.getText().toString());
                Log.v("water", "ProductSale-----onBindViewHolder-------current_quantity---" + current_quantity);

//                String string=String.valueOf(pro_stock.get(position));
//                Log.v("water", "ProductSale-----onBindViewHolder-------string---" + string);
//                String[] arrayString = string.split("Stock-");
//                String stock=arrayString[1];
//                int product_stock=Integer.parseInt(stock);
//                Log.v("water", "ProductSale-----onBindViewHolder-------product_stock---" + product_stock);

//               if(current_quantity>product_stock){
//                   Toast.makeText(context, "Sale quantity is greater than stock", Toast.LENGTH_SHORT).show();
//                   int increment_quantity=current_quantity-1;
//                   holder.saleedit.setText(String.valueOf(increment_quantity));
//               }else {

                   int increment_quantity = current_quantity + 1;
                   Log.v("water", "ProductSale-----onBindViewHolder-------increment_quantity---" + increment_quantity);
                   holder.saleedit.setText(String.valueOf(increment_quantity));
//               }

//
//                }

            }
        });
        holder.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sale_count=Integer.parseInt(holder.saleedit.getText().toString());


                if (sale_count > 0) {
                    ProductList productList=new ProductList();
                    Log.v("water", "SaleBillActivity-----onBindViewHolder-------increment_quantity>0---");
                     //   if(arraylist.size()==0) {

                            productList.setProduct_id(pro_list.get(position).getItemId());
                            productList.setItem_code(pro_list.get(position).getItemCode());
                            productList.setProduct_name(pro_list.get(position).getItemName());
                            productList.setQuantity(sale_count);
                            productList.setUnit_id(unit_list.get(position).getUnitId());
                            String price_text = String.valueOf(holder.productrate.getText());
                            String[] parts = price_text.split("Rate-");
                            String price_rate = parts[1];
                            Log.v("water", "SaleBillActivity-----price_rate----" + price_rate);
                            String[] parts1 = price_rate.split("AED");
                            //String price=parts1[0];
                            double price = Double.parseDouble(parts1[0]); // 004
                            Log.v("water", "SaleBillActivity-----price----" + price);

                            productList.setRate(price);

                            productList.setTaxid(pro_list.get(position).getTaxId());
                            productList.setTaxrate(pro_list.get(position).getTaxRate());


                            arraylist.add(productList.getArray_pos(), productList);

                            Snackbar snackbar = Snackbar.make(view, "Added successfully", Snackbar.LENGTH_SHORT);
                            snackbar.show();
                      //  }
                      //  else{
                       //     for(int i=0;i<arraylist.size();i++){
                       //         if(pro_list.get(position).getItemId()==productList.getProduct_id()){
//                                    productList.setQuantity(sale_count);
//                                    arraylist.add(productList.getArray_pos(), productList);
//                                    Snackbar snackbar = Snackbar.make(view, "Added successfully", Snackbar.LENGTH_SHORT);
//                                    snackbar.show();
//                                }
//                                else{
//                                    productList.setProduct_id(pro_list.get(position).getItemId());
//                                    productList.setItem_code(pro_list.get(position).getItemCode());
//                                    productList.setProduct_name(pro_list.get(position).getItemName());
//                                    productList.setQuantity(sale_count);
//                                    productList.setUnit_id(unit_list.get(position).getUnitId());
//                                    String price_text = String.valueOf(holder.productrate.getText());
//                                    String[] parts = price_text.split("Rate-");
//                                    String price_rate = parts[1];
//                                    Log.v("water", "SaleBillActivity-----price_rate----" + price_rate);
//                                    String[] parts1 = price_rate.split("AED");
//                                    //String price=parts1[0];
//                                    double price = Double.parseDouble(parts1[0]); // 004
//                                    Log.v("water", "SaleBillActivity-----price----" + price);
//
//                                    productList.setRate(price);
//
//                                    productList.setTaxid(pro_list.get(position).getTaxId());
//                                    productList.setTaxrate(pro_list.get(position).getTaxRate());
//
//
//                                    arraylist.add(productList.getArray_pos(), productList);
//
//                                }
//                            }
//                        }

                }
                else if(sale_count==0){
                    Toast.makeText(context, "Please add minimum one quantity before add item", Toast.LENGTH_LONG).show();

                }
                arraylist.size();
                Log.v("water", "SaleBillActivity-----onBindViewHolder-------        arraylist.size();---" +arraylist.size());


//
            }

        });


    }



    @Override
    public int getItemCount() {
        return pro_list.size();
    }
    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productname,productstock,productrate,tax_value;
        public ImageView saleminus,saleplus;
        public EditText saleedit;
        public Spinner spinner;
        public Button additem;
        public ProductViewHolder(@NonNull View v) {
            super(v);
            this.productname=(TextView) v.findViewById(R.id.pro_name);
            this.productstock=(TextView) v.findViewById(R.id.stock);
            this.productrate=(TextView) v.findViewById(R.id.rate);
            this.saleminus=(ImageView) v.findViewById(R.id.sale_minus);
            this.saleplus=(ImageView) v.findViewById(R.id.sale_plus);
            this.tax_value=(TextView) v.findViewById(R.id.tax);
            this.spinner=(Spinner) v.findViewById(R.id.unit_spinner);
            this.saleedit=(EditText) v.findViewById(R.id.sale_edit);
            this.additem=(Button) v.findViewById(R.id.add);


        }

    }



}
