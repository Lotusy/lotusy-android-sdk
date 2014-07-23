package com.lotusy.android.sdk.test;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.lotusy.android.sdk.AccountSDK;
import com.lotusy.android.sdk.LotusySDK;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LotusySDK.setup("9D0E7CE8711F6F1CF87704557828A16E", LotusySDK.ENVIRONMENT.PROD);

        AccountSDK.register(
            "facebook", "12345"+(Math.floor(1000000*Math.random())), "pshen", "ppp", "https://www.leanstartupmachine.com/images/default_profile_photo.png", "Great guy", new LotusyTokenCallback() {
                @Override
                public void callback(LotusyTaskResult result, LotusyToken token) {
                    if (result.isSuccess()) {
                        LotusyToken.current();
                    }
                }
            }
        );

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }
}
