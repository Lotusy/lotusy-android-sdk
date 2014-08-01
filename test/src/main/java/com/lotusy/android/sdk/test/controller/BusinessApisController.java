package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;

import com.lotusy.android.sdk.BusinessSDK;
import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.business.LotusyBusiness;
import com.lotusy.android.sdk.domain.business.LotusyBusinessCallback;
import com.lotusy.android.sdk.domain.business.LotusyBusinessLocationListCallback;
import com.lotusy.android.sdk.domain.business.LotusyRating;
import com.lotusy.android.sdk.domain.business.LotusyRatingCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.ResultActivity;

import java.util.Map;

/**
 * Created by indochino on 2014-07-25.
 */
public class BusinessApisController {

    private static int businessId = 1;

    public static void createBusiness(final Activity activity) {
        LotusyBusiness business = new LotusyBusiness();
        business.setSocial("http://testbusinesssocial/"+Math.round(Math.random()*1000));
        business.setWebsite("http://testbusinesswebsite/" + Math.round(Math.random() * 1000));
        business.setPhone("1-123-123-1234");
        LotusyAddress address = new LotusyAddress();
        address.setCity("Vancouver");
        address.setZip("B0G8H9");
        address.setCountry("CA");
        address.setState("BC");
        address.setStreet(Math.round(Math.random() * 1000) + " Business St.");
        business.setAddress(address);
        business.setCashOnly(false);
        business.setEnName("Business Name " + Math.round(Math.random() * 1000));
        LotusyHours hours = new LotusyHours();
        hours.setHoliday("7:00am - 9:00pm");
        hours.setFriday("7:00am - 9:00pm");
        hours.setSunday("7:00am - 9:00pm");
        hours.setSaturday("7:00am - 9:00pm");
        hours.setThursday("7:00am - 9:00pm");
        hours.setMonday("7:00am - 9:00pm");
        hours.setTuesday("7:00am - 9:00pm");
        hours.setWednesday("7:00am - 9:00pm");
        business.setHours(hours);
        business.setImageUrl("http://testbusinessimage/" + Math.round(Math.random() * 100));
        LotusyLatLng latlng = new LotusyLatLng();
        latlng.setLat(47+Math.random()*4);
        latlng.setLng(-125+Math.random()*4);
        business.setLatlng(latlng);
        business.setPrice("$$$");
        business.setZhName("商家" + Math.round(Math.random() * 1000));
        business.setTwName("商家"+ Math.round(Math.random() * 1000));

        BusinessSDK.createBusiness(business, new LotusyBusinessCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyBusiness business) {
                String msg = "result: " + result.isSuccess() + "\n\n";
                if (result.isSuccess()) {
                    businessId = business.getId();
                    msg = msg+"business id: "+business.getId()+"\n\n";
                    msg = msg+"business en: "+business.getEnName()+"\n\n";
                    msg = msg+"business zh: "+business.getZhName()+"\n\n";
                    msg = msg+"business tw: "+business.getTwName()+"\n\n";
                    msg = msg+"street: "+business.getAddress().getStreet()+"\n\n";
                    msg = msg+"city: "+business.getAddress().getCity()+"\n\n";
                    msg = msg+"state: "+business.getAddress().getState()+"\n\n";
                    msg = msg+"country: "+business.getAddress().getCountry()+"\n\n";
                    msg = msg+"zip: "+business.getAddress().getZip()+"\n\n";
                    msg = msg+"lat: "+business.getLatlng().getLat()+"\n\n";
                    msg = msg+"lng: "+business.getLatlng().getLng();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void businessProfile(final Activity activity) {
        BusinessSDK.getBusinessProfile(businessId, new LotusyBusinessCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyBusiness business) {
                String msg = "result: " + result.isSuccess() + "\n\n";
                if (result.isSuccess()) {
                    businessId = business.getId();
                    msg = msg+"business id: "+business.getId()+"\n\n";
                    msg = msg+"business en: "+business.getEnName()+"\n\n";
                    msg = msg+"business zh: "+business.getZhName()+"\n\n";
                    msg = msg+"business tw: "+business.getTwName()+"\n\n";
                    msg = msg+"street: "+business.getAddress().getStreet()+"\n\n";
                    msg = msg+"city: "+business.getAddress().getCity()+"\n\n";
                    msg = msg+"state: "+business.getAddress().getState()+"\n\n";
                    msg = msg+"country: "+business.getAddress().getCountry()+"\n\n";
                    msg = msg+"zip: "+business.getAddress().getZip()+"\n\n";
                    msg = msg+"lat: "+business.getLatlng().getLat()+"\n\n";
                    msg = msg+"lng: "+business.getLatlng().getLng();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void locationBusiness(final Activity activity) {
        LotusyLatLng latLng = new LotusyLatLng();
        latLng.setLat(49);
        latLng.setLng(-123);

        BusinessSDK.getBusinessesNearLocation(latLng, 10000, true, 0, 10, new LotusyBusinessLocationListCallback() {
            @Override
            public void callback(LotusyTaskResult result, Map<Double, LotusyBusiness> businesses) {
                String msg = "result: " + result.isSuccess() + "\n";
                if (result.isSuccess()) {
                    for (Double distance : businesses.keySet()) {
                        LotusyBusiness business = businesses.get(distance);
                        msg = msg + "\n===================== " + distance + "\n";
                        msg = msg + "business id: " + business.getId() + "\n";
                        msg = msg + "business en: " + business.getEnName() + "\n";
                        msg = msg + "business zh: " + business.getZhName() + "\n";
                        msg = msg + "business tw: " + business.getTwName() + "\n";
                        msg = msg + "lat: " + business.getLatlng().getLat() + "\n";
                        msg = msg + "lng: " + business.getLatlng().getLng() + "\n";
                    }
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void rateBusiness(final Activity activity) {
        LotusyRating rating = new LotusyRating();
        rating.setEnvironment(Math.ceil(Math.random() * 5));
        rating.setService(Math.ceil(Math.random() * 5));
        rating.setFood(Math.ceil(Math.random() * 5));
        rating.setOverall(Math.ceil(Math.random() * 5));

        BusinessSDK.rateBusiness(businessId, rating, new LotusySimpleCallback() {
            @Override
            public void callback(LotusyTaskResult result) {
                String msg = "result: " + result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void userBusinessRate(final Activity activity) {
        int userId = LotusyToken.current().getUserId();
        BusinessSDK.getUserRating(businessId, userId, new LotusyRatingCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyRating rating) {
                String msg = "result: " + result.isSuccess() + "\n\n";
                if (result.isSuccess()) {
                    msg = msg+"overall: "+rating.getOverall()+"\n\n";
                    msg = msg+"environment: "+rating.getEnvironment()+"\n\n";
                    msg = msg+"food: "+rating.getFood()+"\n\n";
                    msg = msg+"service: "+rating.getService()+"\n\n";
                    msg = msg+"create time: "+rating.getCreateTime()+"\n\n";
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static int current() {
        return businessId;
    }
}