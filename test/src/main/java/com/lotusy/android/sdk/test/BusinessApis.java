package com.lotusy.android.sdk.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lotusy.android.sdk.test.controller.BusinessApisController;

public class BusinessApis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_apis);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.business_apis, menu);
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

    public void onCreateBusinessClick(View view) {
        BusinessApisController.createBusiness(this);
    }

    public void onBusinessProfileClick(View view) {
        BusinessApisController.businessProfile(this);
    }

    public void onBusinessLocationClick(View view) {
        BusinessApisController.locationBusiness(this);
    }

    public void onBusinessRateClick(View view) {
        BusinessApisController.rateBusiness(this);
    }

    public void onBusinessUserRateClick(View view) {
        BusinessApisController.userBusinessRate(this);
    }
}
