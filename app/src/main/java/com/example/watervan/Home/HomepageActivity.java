package com.example.watervan.Home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.watervan.Collections.CustomersOutstandingListActivity;

import com.example.watervan.Sale.SalesActivity;
import com.example.watervan.TokenSharedPreference;
import com.example.watervan.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;

public class HomepageActivity extends AppCompatActivity {

    private ImageView sale_img,coll_img;
    Context context;
    private ActionBar actionBar;
    private Toolbar toolbar;
    private NavigationView nav_view;
    private static RecyclerView homepage_recyclerView;
    private HomepageAdapter homepageAdapter;
    private TokenSharedPreference tokenSharedPreference;
    String token;
    TextView date,time,latitude,logitude,location;
    EditText meter_reading;
    Button submit;
    TextView status,header_cross;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;

    BottomNavigationView bottomNavigationView;
    //private DrawableClickListener clickListener;
    ArrayList Img = new ArrayList<>(Arrays.asList(R.drawable.cash_icon,R.drawable.credit_icon,
            R.drawable.coupon, R.drawable.collection
    ));
    ArrayList Title = new ArrayList<>(Arrays.asList("Jissa Mathew", "Aleka Mathew", "Aleena Mathew", "Mathew Antony"
    ));
    ArrayList valuess = new ArrayList<>(Arrays.asList("0898.00 AED", "543.90 AED", "544.00 AED", "2.00 AED"
    ));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        tokenSharedPreference = new TokenSharedPreference(this);
        token = tokenSharedPreference.Gettoken();
        Log.v("water","Homepage-----token-----"+token);

        // launch instruction when first launch
        if (tokenSharedPreference.isFirstLaunch()) {
            tokenSharedPreference.setFirstLaunch(false);
        }


        bottomNavigationView=findViewById(R.id.bottom_navigation);

        //bottomNavigationView.setMaxhieght
        initToolbar();
        initDrawerMenu();
        bottom_navigation();
      //  details_of_user();
        recyclerviews();



    }

    private void initToolbar() {
        Log.v("water", "Homepage-----initToolbar--" + "");
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
//        actionBar.setTitle(Html.fromHtml("<font color='#0000'>DASHBOARD</font>"));


    }



    private void initDrawerMenu() {
        Log.v("water","Homepage-----initDrawerMenu--"+"");
        nav_view = (NavigationView) findViewById(R.id.nav_view);
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            public void onDrawerOpened(View drawerView) {
             //  showInterstitial();
                super.onDrawerOpened(drawerView);
            }
        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        nav_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(final MenuItem item) {
                onItemSelected(item.getItemId());
               // drawer.closeDrawers();
                return true;
            }
        });
      //  nav_view.setItemIconTintList(getResources().getColorStateList(R.color.nav_state_list));
    }

    public boolean onItemSelected(int id) {
        Intent i;
        switch (id) {
            //sub menu

            case R.id.open_shift:
                //  i = new Intent(this, ViewPendingOrders.class);
                // startActivity(i);
                break;

            case R.id.closing_shift:
                // i = new Intent(this, OrderHistory.class);
                // startActivity(i);
                break;

            case R.id.cust:
                //   i = new Intent(this, StockView.class);
                //startActivity(i);
                break;

            case R.id.sale:
                // i = new Intent(this, SalesView.class);
                //  startActivity(i);
                break;

            case R.id.collect:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.empty_can:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.depo:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.depo_ret:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.unload:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.sale_re:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.coup:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;
            case R.id.rep:
                ///  i = new Intent(this, Profile_User.class);
                // startActivity(i);
                break;

            case R.id.nav_logout:
                // i = new Intent(this, Logout.class);
                //startActivity(i);
                break;

            default:
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawers();
        return true;
    }




    public void recyclerviews(){
        Log.v("water","Homepage-----recyclerview--"+"");
        homepage_recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        Log.v("water","Homepage-----recyclerview id--"+"");
        homepage_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL, false));
        Log.v("water","Homepage-----recyclerview setLayoutManager--"+"");

        homepageAdapter = new HomepageAdapter(this,Img,Title,valuess);

        Log.v("water","Homepage-----recyclerview mCardAdapter--"+"");
        homepage_recyclerView.setAdapter((RecyclerView.Adapter) homepageAdapter);
        Log.v("water","Homepage-----recyclerview setAdapter--"+"");

    }
    public void bottom_navigation(){
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_navigation);
       // bottomNavigationView.setItemIconTintList(null);
        // Set Home selected
        bottomNavigationView.setSelectedItemId(R.id.home_nav);

        // Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch(item.getItemId())
                {
                    case R.id.sales_nav:
                        startActivity(new Intent(getApplicationContext(),SalesActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home_nav:
                        return true;
                    case R.id.collection_nav:
                        startActivity(new Intent(getApplicationContext(),CustomersOutstandingListActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });

    }


//    public void details_of_user(){
//
////         int FinYearId= 1;
////         String SONo= null;
////        String  SalesOrderFromDate= null;
////       String   SalesOrderToDate= null;
////       int  length=10;
////       int PageIndex= 1 ;
//
//
////        HomeRequest homeRequest = new HomeRequest();
////        homeRequest.setFinYearId(FinYearId);
////        homeRequest.setSONo(SONo);
////        homeRequest.setSalesOrderFromDate(SalesOrderFromDate);
////        homeRequest.setSalesOrderToDate(SalesOrderToDate);
////        homeRequest.setLength(length);
////        homeRequest.setPageIndex(PageIndex);
//
//        Call<HomeResponse> call = RetrofitClientInstance.getHomeInterfaceAPI().requesthome(homeRequest);
//        call.enqueue(new Callback<HomeResponse>() {
//            @Override
//            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
//                HomeResponse homeResponse = response.body();
//                Log.v("water","checkLoginDetails---------loginResponse-------"+homeResponse);
////                String  msg = loginResponse.getStatusMessage();
////                String responseToken =loginResponse.getResponseToken() ;
////                int status =loginResponse.getStatus() ;
////
////                Log.v("water","checkLoginDetails---------StatusMessage--"+msg);
////                Log.v("water","checkLoginDetails---------responseToken--"+responseToken);
////                Log.v("water","checkLoginDetails---------status--"+status);
//            }
//
//            @Override
//            public void onFailure(Call<HomeResponse> call, Throwable t) {
// Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
//            }
//        });
//    }
}


