package com.lotusy.android.sdk;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.business.LotusyBusinessCallback;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.business.LotusyBusinessNameSearchCallback;
import com.lotusy.android.sdk.domain.business.LotusyLocationBusinessCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK extends LotusySDK {


    public static void createBusiness( LotusyLatLng location,
                                       String zhName,
                                       String twName,
                                       String enName,
                                       String price,
                                       String category,
                                       boolean cashOnly,
                                       boolean verified,
                                       String phone,
                                       String website,
                                       String social,
                                       LotusyHours schedule,
                                       LotusyAddress address,
                                       LotusyBusinessCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JsonObject body = new JsonObject();

        if (location!=null) {
            body.addProperty("lat", location.getLat());
            body.addProperty("lng", location.getLng());
        }

        if (zhName!=null) { body.addProperty("name_zh", zhName); }
        if (twName!=null) { body.addProperty("name_tw", twName); }
        if (enName!=null) { body.addProperty("name_en", enName); }

        if (address!=null) {
            body.addProperty("street", address.getStreet());
            body.addProperty("city", address.getCity());
            body.addProperty("state", address.getState());
            body.addProperty("country", address.getCountry());
            if (address.getZip()!=null) { body.addProperty("zip", address.getZip()); }
        }

        if (schedule!=null) {
            JsonObject hours = new JsonObject();
            if (schedule.getMonday()!=null) { hours.addProperty("mon", schedule.getMonday()); }
            if (schedule.getTuesday()!=null) { hours.addProperty("tue", schedule.getTuesday()); }
            if (schedule.getWednesday()!=null) { hours.addProperty("wed", schedule.getWednesday()); }
            if (schedule.getThursday()!=null) { hours.addProperty("thu", schedule.getThursday()); }
            if (schedule.getFriday()!=null) { hours.addProperty("fri", schedule.getFriday()); }
            if (schedule.getSaturday()!=null) { hours.addProperty("sat", schedule.getSaturday()); }
            if (schedule.getSunday()!=null) { hours.addProperty("sun", schedule.getSunday()); }
            if (schedule.getHoliday()!=null) { hours.addProperty("holiday", schedule.getSunday()); }
            body.add("hours", hours);
        }

        body.addProperty("cach_only", cashOnly ? "Y" : "N");
        body.addProperty("verified", verified ? "Y" : "N");
        if (phone!=null) { body.addProperty("tel", phone); }
        if (website!=null) { body.addProperty("website", website); }
        if (social!=null) { body.addProperty("social", social); }
        if (price!=null) { body.addProperty("price", price); }
        if (category!=null) { body.addProperty("category", category); }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void createSimpleBusiness( LotusyLatLng location,
                                             String zhName,
                                             String twName,
                                             String enName,
                                             String category,
                                             LotusyBusinessCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JsonObject body = new JsonObject();

        if (location!=null) {
            body.addProperty("lat", location.getLat());
            body.addProperty("lng", location.getLng());
        }

        if (zhName!=null) { body.addProperty("name_zh", zhName); }
        if (twName!=null) { body.addProperty("name_tw", twName); }
        if (enName!=null) { body.addProperty("name_en", enName); }
        if (category!=null) { body.addProperty("category", category); }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/quick");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();

    }


    public static void getBusinessProfile(int businessId, LotusyBusinessCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/business/" + businessId + "/profile");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getBusinessesNearLocation( LotusyLatLng latlng,
                                                  int radius,
                                                  boolean is_miles,
                                                  int start,
                                                  int size,
                                                  LotusyLocationBusinessCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/location?lat="+latlng.getLat()+
                                                 "&lng="+latlng.getLng()+
                                                 "&radius="+radius+
                                                 "&is_miles="+(is_miles ? "true" : "false")+
                                                 "&start="+start+
                                                 "&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void searchBusinessesByName( String name,
                                               LotusyBusinessNameSearchCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/search/name?name="+name);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }
}
