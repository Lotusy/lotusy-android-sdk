package com.lotusy.android.sdk;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.business.LotusyBusiness;
import com.lotusy.android.sdk.domain.business.LotusyBusinessCallback;
import com.lotusy.android.sdk.domain.business.LotusyBusinessLocationListCallback;
import com.lotusy.android.sdk.domain.business.LotusyDishListCallback;
import com.lotusy.android.sdk.domain.business.LotusyRating;
import com.lotusy.android.sdk.domain.business.LotusyRatingCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK extends LotusySDK {


    public static void createBusiness(LotusyBusiness business, LotusyBusinessCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JsonObject body = populateBusiness(business);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business");
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
        param.setUri(getHost()+"/"+businessId+"/profile");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getBusinessesNearLocation( LotusyLatLng latlng,
                                                  int radius,
                                                  boolean is_miles,
                                                  int start,
                                                  int size,
                                                  LotusyBusinessLocationListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/location?lat="+latlng.getLat()+
                                        "&lng="+latlng.getLng()+
                                        "&radius="+radius+
                                        "&is_miles="+(is_miles ? "true" : "false")+
                                        "&start="+start+
                                        "&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void rateBusiness( int businessId,
                                     LotusyRating rating,
                                     LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        JsonObject body = populateRating(rating);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+businessId+"/rate");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserRating(int businessId, int userId, LotusyRatingCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId+"/user/"+userId+"/rating");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void createDish( int businessId,
                                   String enName,
                                   String zhName,
                                   String twName,
                                   LotusySimpleCallback callback) {

        if (LotusyToken.current()==null) {
            callback.callback(null);
            return;
        }

        JsonObject body = new JsonObject();
        if (enName!=null) { body.addProperty("name_en", enName); }
        if (zhName!=null) { body.addProperty("name_zh", zhName); }
        if (twName!=null) { body.addProperty("name_tw", twName); }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+businessId+"/dish");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getBusinessDishes( int businessId,
                                          int start,
                                          int size,
                                          LotusyDishListCallback callback) {

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+businessId+"/dishes?start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


// ==========================================================================================================


    private static JsonObject populateBusiness(LotusyBusiness business) {

        JsonObject body = new JsonObject();

        LotusyLatLng latlng = business.getLatlng();
        if (latlng!=null) {
            body.addProperty("lat", latlng.getLat());
            body.addProperty("lng", latlng.getLng());
        }

        if (business.getZhName()!=null) { body.addProperty("name_zh", business.getZhName()); }
        if (business.getTwName()!=null) { body.addProperty("name_tw", business.getTwName()); }
        if (business.getEnName()!=null) { body.addProperty("name_en", business.getEnName()); }

        LotusyAddress address = business.getAddress();
        if (address!=null) {
            body.addProperty("street", address.getStreet());
            body.addProperty("city", address.getCity());
            body.addProperty("state", address.getState());
            body.addProperty("country", address.getCountry());
            if (address.getZip()!=null) { body.addProperty("zip", address.getZip()); }
        }

        LotusyHours schedule = business.getHours();
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

        body.addProperty("cach_only", business.isCashOnly() ? "Y" : "N");
        if (business.getPhone()!=null) { body.addProperty("tel", business.getPhone()); }
        if (business.getWebsite()!=null) { body.addProperty("website", business.getWebsite()); }
        if (business.getSocial()!=null) { body.addProperty("social", business.getSocial()); }


        return body;
    }


    private static JsonObject populateRating(LotusyRating rating) {

        JsonObject body = new JsonObject();
        body.addProperty("overall", rating.getOverall());
        body.addProperty("env", rating.getEnvironment());
        body.addProperty("food", rating.getFood());
        body.addProperty("serv", rating.getService());


        return body;
    }
}
