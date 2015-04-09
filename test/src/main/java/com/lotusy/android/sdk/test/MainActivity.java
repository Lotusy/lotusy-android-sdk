package com.lotusy.android.sdk.test;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lotusy.android.sdk.AccountSDK;
import com.lotusy.android.sdk.LotusySDK;
import com.lotusy.android.sdk.PageSDK;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.domain.page.PageBuddyActivitiesCallback;
import com.lotusy.android.sdk.domain.page.PageBuddyDishActivitiesCallback;
import com.lotusy.android.sdk.domain.page.PageBusinessDishesCallback;
import com.lotusy.android.sdk.domain.page.PageDishDetailsCallback;
import com.lotusy.android.sdk.domain.page.PageDishInfographCallback;
import com.lotusy.android.sdk.domain.page.PageDishPopularityCallback;
import com.lotusy.android.sdk.domain.page.PageDishPreferenceCallback;
import com.lotusy.android.sdk.domain.page.PageMyBuddiesCallback;
import com.lotusy.android.sdk.domain.page.PageMyProfileBuddiesCallback;
import com.lotusy.android.sdk.domain.page.PageMyProfileDetailCallback;
import com.lotusy.android.sdk.domain.page.PageMyProfileSettingAlertsCallback;
import com.lotusy.android.sdk.domain.page.PageMyProfileSettingsCallback;
import com.lotusy.android.sdk.domain.page.PageMySuggestBuddiesCallback;
import com.lotusy.android.sdk.domain.page.PageNearByFoodsCallback;
import com.lotusy.android.sdk.domain.page.PageUserProfileCallback;
import com.lotusy.android.sdk.domain.page.PageUserProfileRankingCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.controller.MainActivityController;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LotusySDK.setup("9D0E7CE8711F6F1CF87704557828A16E", LotusySDK.ENVIRONMENT.INT, "en");
        AccountSDK.login("facebook", "123123123", new LotusyTokenCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyToken token) {
/*
                PageSDK.UC001_getBusinessDishes(1, new PageBusinessDishesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonArray dishes) {
                        int a = 1;
                    }
                });
                PageSDK.UC001_getDishDetails(1, new PageDishDetailsCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject dish) {
                        int a = 1;
                    }
                });
                PageSDK.UC002_getBuddiesDishActivities(new PageBuddyDishActivitiesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject activities) {
                        int a = 1;
                    }
                });
                PageSDK.UC002_getDishPopularity(1, new PageDishPopularityCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject popularity) {
                        int a = 1;
                    }
                });
                PageSDK.UC002_getNearByFoods(49.1880584, -122.8455534, 100, false, new PageNearByFoodsCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonArray dishes) {
                        int a = 1;
                    }
                });
                PageSDK.UC003_getDishInfograph(1, new PageDishInfographCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject infograph) {
                        int a = 1;
                    }
                });
                PageSDK.UC003_getDishPreference(1, new PageDishPreferenceCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonArray preferences) {
                        int a = 1;
                    }
                });
                PageSDK.UC004_getBuddiesActivities(new PageBuddyActivitiesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject activities) {
                        int a = 1;
                    }
                });
                PageSDK.UC004_getMyBuddies(new PageMyBuddiesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject activities) {
                        int a = 1;
                    }
                });
                PageSDK.UC004_getMyProfile(new PageMyProfileDetailCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject profile) {
                        int a = 1;
                    }
                });
                PageSDK.UC004_getMyProfileBuddies(new PageMyProfileBuddiesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject buddies) {
                        int a = 1;
                    }
                });
                PageSDK.UC004_getSuggestBuddies(new PageMySuggestBuddiesCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject buddies) {
                        int a = 1;
                    }
                });
                PageSDK.UC005_getOtherProfile(1, new PageUserProfileCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject profile) {
                        int a = 1;
                    }
                });
                PageSDK.UC005_getUserProfileRanking(1, new PageUserProfileRankingCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject ranking) {
                        int a = 1;
                    }
                });
                PageSDK.UC006_getProfileSettings(new PageMyProfileSettingsCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonObject settings) {
                        int a = 1;
                    }
                });
*/
                PageSDK.UC006_getProfileSettingAlerts(new PageMyProfileSettingAlertsCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, JsonArray alerts) {
                        int a = 1;
                    }
                });
            }
        });

        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        MainActivityController.init(this);
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
