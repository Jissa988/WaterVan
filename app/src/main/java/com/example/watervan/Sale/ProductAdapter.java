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
import com.example.watervan.callbacks.ProductItemCallback;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;


    public static List<ProductList> arraylist = new ArrayList<ProductList>();
    List<ProductList> poduct_list;
    ProductItemCallback callback;
    private List<ProductResponseResultSet> pro_list;
    private List<ProductResponseResultSet1> unit_list;
    //ProductList productList = new ProductList();

    public ProductAdapter(Context context, List<ProductResponseResultSet> productlist, List<ProductResponseResultSet1> unitlist, ProductItemCallback callback) {

        Log.v("water", "ProductSaleActivity-----ProductAdapter----Context------" + "");


        this.context = context;
        this.pro_list = productlist;
        this.unit_list = unitlist;
        this.callback=callback;

        for (int i = 0; i<pro_list.size(); i++) {
            ProductList productList = new ProductList();

            Log.v("water", "ProductSaleActivity-----ProductViewHolder-----i--------"+i);
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
            Log.v("water", "ProductSaleActivity------i-" + i);
        }
    }

    @NonNull
    @Override
    public ProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.v("water", "ProductSaleActivity-----ProductViewHolder-------------");
        View v = LayoutInflater.from(context).inflate(R.layout.product_recycler_list, parent, false);
        return new ProductAdapter.ProductViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ProductViewHolder holder, int position) {
        int adapterPosition = holder.getAdapterPosition();
        Log.v("water", "ProductSaleActivity------adapterPosition--" + adapterPosition);
        Log.v("water", "ProductSaleActivity------position--" + position);


        holder.productname.setText((String) pro_list.get(position).getItemName());
        holder.productstock.setText("Stock-" + (String) pro_list.get(position).getStock());
        double taxarte = pro_list.get(position).getTaxRate();
        Log.v("water", "ProductSaleActivity-----if----taxarte--" + taxarte);
        holder.tax_value.setText((String.valueOf(taxarte)));
        int itemid = pro_list.get(position).getItemId();

        ArrayList unitname = new ArrayList();
        for (ProductResponseResultSet1 productResponseResultSet1s : unit_list) {
            int item_id = productResponseResultSet1s.getItemId();
            if (item_id == itemid) {
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
                String spinner_unit = holder.spinner.getSelectedItem().toString();
                Log.v("water", "ProductSaleActivity-----if----spinner_unit--" + spinner_unit);
                for (ProductResponseResultSet1 productResponseResultSet1s : unit_list) {
                    String unitname = productResponseResultSet1s.getUnitName();

                    if (spinner_unit.equals(unitname)) {
                        Double salePrice = productResponseResultSet1s.getSalePrice();
                        holder.productrate.setText("Rate-" + (String) String.valueOf(salePrice) + "AED");
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
                int current_quantity = Integer.parseInt(holder.saleedit.getText().toString());
                Log.v("water", "ProductSale-----onBindViewHolder-------current_quantity---" + current_quantity);

                if (current_quantity == 0) {
                    holder.saleedit.setText(String.valueOf(current_quantity));
                } else {
                    int quantity_decrement = current_quantity - 1;
                    Log.v("water", "ProductSale-----onBindViewHolder-------quantity_decrement---" + quantity_decrement);
                    holder.saleedit.setText(String.valueOf(quantity_decrement));


                }


            }

        });

        holder.saleplus.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int current_quantity = Integer.parseInt(holder.saleedit.getText().toString());
                Log.v("water", "ProductSale-----onBindViewHolder-------current_quantity---" + current_quantity);

                int increment_quantity = current_quantity + 1;
                Log.v("water", "ProductSale-----onBindViewHolder-------increment_quantity---" + increment_quantity);
                holder.saleedit.setText(String.valueOf(increment_quantity));


            }
        });

        holder.additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int sale_count = Integer.parseInt(holder.saleedit.getText().toString());


                if (sale_count > 0) {
                    Log.v("water", "SaleBillActivity-----onBindViewHolder-------arraylist.size()"+arraylist.size());
                   ProductList productList = new ProductList();
                    Log.v("water", "SaleBillActivity-----onBindViewHolder-------increment_quantity>0---");
                    Log.v("water", "ProductSaleActivity------productList.getArray_pos()---" + productList.getArray_pos());

                    if (arraylist.size() == 0) {

                        productList.setProduct_id(pro_list.get(adapterPosition).getItemId());
                        productList.setItem_code(pro_list.get(adapterPosition).getItemCode());
                        productList.setProduct_name(pro_list.get(adapterPosition).getItemName());
                        productList.setQuantity(sale_count);
                        productList.setUnit_id(unit_list.get(adapterPosition).getUnitId());
                        String price_text = String.valueOf(holder.productrate.getText());
                        String[] parts = price_text.split("Rate-");
                        String price_rate = parts[1];
                        Log.v("water", "SaleBillActivity-----price_rate----" + price_rate);
                        String[] parts1 = price_rate.split("AED");
                        //String price=parts1[0];
                        double price = Double.parseDouble(parts1[0]); // 004
                        Log.v("water", "SaleBillActivity-----price----" + price);

                        productList.setRate(price);

                        productList.setTaxid(pro_list.get(adapterPosition).getTaxId());
                        productList.setTaxrate(pro_list.get(adapterPosition).getTaxRate());
                        productList.setArray_pos(adapterPosition);


//                        for(ProductList productList1:arraylist){
//                            Log.v("water", "SaleBillActivity-----for ---productList1-----" );
//                         if(pro_list.get(adapterPosition).getItemId()==productList1.getProduct_id())  {
//                             Log.v("water", "SaleBillActivity-----productList1.getArray_pos()----" + productList1.getArray_pos());
//                         }
//                        }


                        arraylist.add(adapterPosition, productList);


                        Snackbar snackbar = Snackbar.make(view, "Added successfully", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    } else {
                        for (int i = 0; i <=arraylist.size(); i++) {
                            if (pro_list.get(adapterPosition).getItemId() == productList.getProduct_id()) {
                                System.out.println("prod if: "+pro_list.get(adapterPosition).getItemId()+", "+productList.getProduct_id());
                                productList.setQuantity(sale_count);
                                Log.v("water", "SaleBillActivity-----productList.getArray_pos()----" +productList.getArray_pos());

                                arraylist.set(productList.getArray_pos(),productList);

                                Snackbar snackbar = Snackbar.make(view, "updated successfully", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            } else {
                                System.out.println("prod else: "+pro_list.get(adapterPosition).getItemId()+", "+productList.getProduct_id());
                                productList.setProduct_id(pro_list.get(adapterPosition).getItemId());
                                productList.setItem_code(pro_list.get(adapterPosition).getItemCode());
                                productList.setProduct_name(pro_list.get(adapterPosition).getItemName());
                                productList.setQuantity(sale_count);
                                productList.setUnit_id(unit_list.get(adapterPosition).getUnitId());
                                String price_text = String.valueOf(holder.productrate.getText());
                                String[] parts = price_text.split("Rate-");
                                String price_rate = parts[1];
                                Log.v("water", "SaleBillActivity-----price_rate----" + price_rate);
                                String[] parts1 = price_rate.split("AED");
                                //String price=parts1[0];
                                double price = Double.parseDouble(parts1[0]); // 004
                                Log.v("water", "SaleBillActivity-----price----" + price);

                                productList.setRate(price);

                                productList.setTaxid(pro_list.get(adapterPosition).getTaxId());
                                productList.setTaxrate(pro_list.get(adapterPosition).getTaxRate());


                                arraylist.add(adapterPosition, productList);
                                Snackbar snackbar = Snackbar.make(view, "added successfully", Snackbar.LENGTH_SHORT);
                                snackbar.show();
                            }
                        }
                    }


                } else if (sale_count == 0) {
                    Toast.makeText(context, "Please add minimum one quantity before add item", Toast.LENGTH_LONG).show();

                }
                arraylist.size();
                callback.onAddItemClicked(arraylist.size());
                Log.v("water", "SaleBillActivity-----onBindViewHolder-------        arraylist.size();---" + arraylist.size());



            }

        });




    }

    @Override
    public int getItemCount() {
         return pro_list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        public TextView productname, productstock, productrate, tax_value;
        public ImageView saleminus, saleplus;
        public EditText saleedit;
        public Spinner spinner;
        public Button additem;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            this.productname = (TextView) itemView.findViewById(R.id.pro_name);
            this.productstock = (TextView) itemView.findViewById(R.id.stock);
            this.productrate = (TextView) itemView.findViewById(R.id.rate);
            this.saleminus = (ImageView) itemView.findViewById(R.id.sale_minus);
            this.saleplus = (ImageView) itemView.findViewById(R.id.sale_plus);
            this.tax_value = (TextView) itemView.findViewById(R.id.tax);
            this.spinner = (Spinner) itemView.findViewById(R.id.unit_spinner);
            this.saleedit = (EditText) itemView.findViewById(R.id.sale_edit);
            this.additem = (Button) itemView.findViewById(R.id.add);

        }
    }
}
