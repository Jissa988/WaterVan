package com.example.watervan;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;



public class TokenSharedPreference {
    Context context;

    private static final String App_name="IS";
    private SharedPreferences sharedPreferences;
    private static final String User_Token="token";
    private static final String FIRST_LAUNCH = "_.FIRST_LAUNCH";


    public void setFirstLaunch(boolean flag) {
        sharedPreferences.edit().putBoolean(FIRST_LAUNCH, flag).apply();
    }

    public boolean isFirstLaunch() {
        return sharedPreferences.getBoolean(FIRST_LAUNCH, true);
    }



    public TokenSharedPreference(Context context) {

            this.context=context;
            sharedPreferences=this.context.getSharedPreferences(App_name,Context.MODE_PRIVATE);
        }






    public void Settoken(String token){
        Log.v("water","Settoken----"+token);

        Log.v("water","sharedPreferences--- Settoken-"+sharedPreferences);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString(User_Token,token);
        editor.apply();


    }

    public String Gettoken(){

        Log.v("water","sharedPreferences--- Gettoken-"+sharedPreferences);
     return sharedPreferences.getString(User_Token,"");

    }
    public void Clear_token(){
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.remove(User_Token).commit();


    }

}
