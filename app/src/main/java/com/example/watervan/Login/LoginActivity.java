package com.example.watervan.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.watervan.Home.HomepageActivity;
import com.example.watervan.R;
import com.example.watervan.RetrofitClientInstance;
import com.example.watervan.TokenSharedPreference;

import org.json.JSONException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private Context context;
    EditText edit_username,edit_password;
    Button signin_button;
    String username,password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin_button=(Button) findViewById(R.id.submit);
        edit_username=(EditText) findViewById(R.id.user_name);
        edit_password=(EditText) findViewById(R.id.pass_word);


        signin_button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub

                Log.v("water","sign in------");
                try {
                    buttonclick();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });



    }

 public void buttonclick() throws JSONException {
     checkLoginDetails ();

    }




    private void checkLoginDetails (){

        username = edit_username.getText().toString();
        Log.v("water","username----"+username);
        password = edit_password.getText().toString();
        Log.v("water","password---"+password);
        if(username.isEmpty()){
            Toast.makeText(this, "Please enter username", Toast.LENGTH_LONG).show();
        }
        else if(password.isEmpty()){
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
        }
        else {
            Log.v("water","pass username and password---"+username+","+password);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername(username);
            loginRequest.setPassword(password);
            loginRequest.setDeviceType("M");

            Call<LoginResponse> call = RetrofitClientInstance.getInterfaceAPI().requestlogin(loginRequest);

            call.enqueue(new Callback<LoginResponse>() {
                             @Override
                             public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                                 Log.v("water","checkLoginDetails---------onResponse-------"+response);

                                 LoginResponse loginResponse = response.body();
                                 Log.v("water","checkLoginDetails---------loginResponse-------"+loginResponse);
                                 String  msg = loginResponse.getStatusMessage();
                                 String responseToken =loginResponse.getResponseToken() ;
                                 int status =loginResponse.getStatus() ;

                      Log.v("water","checkLoginDetails---------StatusMessage--"+msg);
                       Log.v("water","checkLoginDetails---------responseToken--"+responseToken);
                       Log.v("water","checkLoginDetails---------status--"+status);
                       if(status==1){

                           new TokenSharedPreference(getApplicationContext()).Settoken(responseToken);

                           Toast.makeText(getApplicationContext(), "sucessfully login", Toast.LENGTH_LONG).show();
                  Intent intentlogin = new Intent(LoginActivity.this, HomepageActivity.class);
                   startActivity(intentlogin);
                       }else if(status==0){
                           Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_LONG).show();
                       }



                             }

                             @Override
                             public void onFailure(Call<LoginResponse> call, Throwable t) {
                                 Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                             }
                         });





    }



    }

}