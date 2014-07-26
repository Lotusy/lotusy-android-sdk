package com.lotusy.android.sdk.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lotusy.android.sdk.test.controller.AccountApisController;

public class AccountApis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_apis);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_apis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onRegisterClick(View v){
        if(v.getId() == R.id.register){
            AccountApisController.registerUser(this);
        }
    }

    public void onLoginClick(View v){
        if(v.getId() == R.id.login){
            AccountApisController.loginUser(this);
        }
    }

    public void onCurrentClick(View v){
        if(v.getId() == R.id.current){
            AccountApisController.getCurrentProfile(this);
        }
    }

    public void onGetProfileClick(View v){
        if(v.getId() == R.id.profile){
            AccountApisController.getProfile(this);
        }
    }

    public void onUpdateClick(View v){
        if(v.getId() == R.id.update){
            AccountApisController.updateProfile(this);
        }
    }
}
