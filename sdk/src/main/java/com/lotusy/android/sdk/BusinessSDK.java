package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySchedule;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.business.LotusyBusiness;
import com.lotusy.android.sdk.domain.business.LotusyBusinessCallback;
import com.lotusy.android.sdk.domain.business.LotusyBusinessListCallback;
import com.lotusy.android.sdk.domain.business.LotusyRating;
import com.lotusy.android.sdk.domain.business.LotusyRatingCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyProperties;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK extends LotusySDK {


    public void createBusiness(LotusyBusiness business, LotusyBusinessCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JSONObject body = this.populateBusiness(business);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void getBusinessProfile(int businessId, LotusyBusinessCallback callback) {
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


    public void getBusinessesNearLocation( LotusyLatLng latlng,
                                           int radius,
                                           boolean is_miles,
                                           int start,
                                           int size,
                                           LotusyBusinessListCallback callback ) {
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


    public void rateBusiness( int businessId,
                              LotusyRating rating,
                              LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        JSONObject body = this.populateRating(rating);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+businessId+"/business");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void getUserRating(int businessId, LotusyRatingCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        int userId = LotusyToken.current().getUserId();

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId+"/user/"+userId+"/rating");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();

    }


// ==========================================================================================================


    private JSONObject populateBusiness(LotusyBusiness business) {

        JSONObject body = new JSONObject();
        try {
            LotusyLatLng latlng = business.getLatlng();
            if (latlng!=null) {
                body.put("lat", latlng.getLat());
                body.put("lng", latlng.getLng());
            }

            if (business.getZhName()!=null) { body.put("zh_name", business.getZhName()); }
            if (business.getTwName()!=null) { body.put("tw_name", business.getTwName()); }
            if (business.getEnName()!=null) { body.put("en_name", business.getEnName()); }

            LotusyAddress address = business.getAddress();
            if (address!=null) {
                body.put("street", address.getStreet());
                body.put("city", address.getCity());
                body.put("state", address.getState());
                body.put("country", address.getCountry());
                if (address.getZip()!=null) { body.put("zip", address.getZip()); }
            }

            LotusySchedule schedule = business.getSchedule();
            if (schedule!=null) {
                JSONObject hours = new JSONObject();
                if (schedule.getMonday()!=null) { hours.put("mon", schedule.getMonday()); }
                if (schedule.getTuesday()!=null) { hours.put("tue", schedule.getTuesday()); }
                if (schedule.getWednesday()!=null) { hours.put("wed", schedule.getWednesday()); }
                if (schedule.getThursday()!=null) { hours.put("thu", schedule.getThursday()); }
                if (schedule.getFriday()!=null) { hours.put("fri", schedule.getFriday()); }
                if (schedule.getSaturday()!=null) { hours.put("sat", schedule.getSaturday()); }
                if (schedule.getSunday()!=null) { hours.put("sun", schedule.getSunday()); }
            }

            body.put("cach_only", business.isCashOnly() ? "Y" : "N");
            if (business.getPhone()!=null) { body.put("tel", business.getPhone()); }
            if (business.getWebsite()!=null) { body.put("website", business.getWebsite()); }
            if (business.getSocial()!=null) { body.put("social", business.getSocial()); }
        } catch (JSONException e) {}

        return body;
    }


    private JSONObject populateRating(LotusyRating rating) {

        JSONObject body = new JSONObject();
        try {
            body.put("overall", rating.getOverall());
            body.put("env", rating.getEnvironment());
            body.put("food", rating.getFood());
            body.put("serv", rating.getService());
        } catch (JSONException e) {}

        return body;
    }


// ==========================================================================================================


    private static BusinessSDK defaultSDK=null;

    public static BusinessSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new BusinessSDK();
        }
        return defaultSDK;
    }

    private static String getHost() {
        return LotusyProperties.getHost("business");
    }

    private BusinessSDK() {}
}
