package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.page.*;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;

/**
 * Created by pshen on 2015-04-06.
 */
public class PageSDK extends LotusySDK {
    public static void UC001_getBusinessDishes(int businessId, PageBusinessDishesCallback callback) {
        String uri = getHost()+"/business/"+businessId+"/dishes?start=0&size=15";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC001_getDishDetails(int dishId, PageDishDetailsCallback callback) {
        String uri = getHost()+"/flow/dish/"+dishId+"/detail";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();

    }

    public static void UC002_getBuddiesDishActivities(PageBuddyDishActivitiesCallback callback) {
        String uri = getHost()+"/flow/user/followings/dishes?start=0&size=15";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC002_getNearByFoods(double lat, double lng, int radius, boolean isMiles, PageNearByFoodsCallback callback) {
        String miles = isMiles ? "&is_miles=true" : "&is_miles=false";
        String uri = getHost()+"/dish/location?lat="+lat+"&lng="+lng+"&radius="+radius+"&start=0&size=15"+miles;

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC002_getDishPopularity(int dishId, PageDishPopularityCallback callback) {
        String uri = getHost()+"/flow/dish/"+dishId+"/popularity";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC003_getDishPreference(int dishId, PageDishPreferenceCallback callback) {
        String uri = getHost()+"/flow/dish/"+dishId+"/preference?start=0&size=10";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC003_getDishInfograph(int dishId, PageDishInfographCallback callback) {
        String uri = getHost()+"/flow/dish/"+dishId+"/infograph";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getBuddiesActivities(PageBuddyActivitiesCallback callback) {
        int userId = LotusyToken.current().getUserId();
        String uri = getHost()+"/flow/user/"+userId+"/activities";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getMyProfile(PageMyProfileDetailCallback callback) {
        String uri = getHost()+"/flow/me/profile";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getMyProfileBuddies(PageMyProfileBuddiesCallback callback) {
        String uri = getHost()+"/flow/me/buddies";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getMyBuddies(PageMyBuddiesCallback callback) {
        String uri = getHost()+"/flow/me/buddy/add";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getNetworkBuddies(PageMyNetworkBuddiesCallback callback) {
        String uri = getHost()+"/flow/me/buddy/add/network";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC004_getSuggestBuddies(PageMySuggestBuddiesCallback callback) {
        String uri = getHost()+"/flow/me/buddy/add/suggest";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC005_getUserProfileRanking(int userId, PageUserProfileRankingCallback callback) {
        String uri = getHost()+"/flow/user/"+userId+"/profile/ranking";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC005_getOtherProfile(int userId, PageUserProfileCallback callback) {
        String uri = getHost()+"/flow/user/"+userId+"/profile";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC006_getProfileSettings(PageMyProfileSettingsCallback callback) {
        String uri = getHost()+"/flow/me/setting";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void UC006_getProfileSettingAlerts(PageMyProfileSettingAlertsCallback callback) {
        String uri = getHost()+"/flow/me/setting/alerts";

        LotusyTaskParam param = new LotusyTaskParam(uri, "GET");
        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }
}
