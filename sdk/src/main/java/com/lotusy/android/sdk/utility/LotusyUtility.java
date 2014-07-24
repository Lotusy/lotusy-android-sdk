package com.lotusy.android.sdk.utility;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.business.LotusyBusiness;
import com.lotusy.android.sdk.domain.business.LotusyRating;

import java.util.Calendar;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyUtility {

    public static LotusyBusiness parseBusinessJson(JsonObject json) {
        LotusyBusiness business = new LotusyBusiness();

        LotusyLatLng latlng = parseLatlngJson(json);
        business.setLatlng(latlng);

        LotusyAddress address = parseAddressJson(json);
        business.setAddress(address);

        LotusyHours hours = parseHoursJson(json.get("hours").getAsJsonObject());
        business.setHours(hours);

        String nameZh = json.get("name_zh").getAsString();
        String nameTw = json.get("name_tw").getAsString();
        String nameEn = json.get("name_en").getAsString();
        String price = json.get("price").getAsString();
        boolean cachOnly = json.get("cash_only").getAsString().equals("Y");
        boolean verified = json.get("verified").getAsString().equals("Y");
        String phone = json.get("tel").getAsString();
        String website = json.get("website").getAsString();
        String social = json.get("social").getAsString();

        business.setEnName(nameEn);
        business.setZhName(nameZh);
        business.setTwName(nameTw);
        business.setPrice(price);
        business.setCashOnly(cachOnly);
        business.setVerified(verified);
        business.setPhone(phone);
        business.setWebsite(website);
        business.setSocial(social);

        return business;
    }

    public static LotusyRating parseRatingJson(JsonObject json) {
        LotusyRating rating = new LotusyRating();

        int overall = json.get("overall").getAsInt();
        int food = json.get("food").getAsInt();
        int service = json.get("service").getAsInt();
        int environment = json.get("environment").getAsInt();
        int createTime = (-1)*json.get("create_time").getAsInt();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, createTime);

        rating.setOverall(overall);
        rating.setFood(food);
        rating.setService(service);
        rating.setEnvironment(environment);
        rating.setCreateTime(calendar.getTime());

        return rating;
    }

    public static LotusyLatLng parseLatlngJson(JsonObject json) {
        LotusyLatLng latlng = new LotusyLatLng();

        double lat = json.get("lat").getAsDouble();
        double lng = json.get("lng").getAsDouble();

        latlng.setLat(lat);
        latlng.setLng(lng);

        return latlng;
    }

    public static LotusyAddress parseAddressJson(JsonObject json) {
        LotusyAddress address = new LotusyAddress();

        String street = json.get("street").getAsString();
        String city = json.get("city").getAsString();
        String state = json.get("state").getAsString();
        String country = json.get("country").getAsString();
        String zip = json.get("zip").getAsString();

        address.setCity(city);
        address.setStreet(street);
        address.setState(state);
        address.setCountry(country);
        address.setZip(zip);

        return address;
    }

    public static LotusyHours parseHoursJson(JsonObject json) {
        LotusyHours hours = new LotusyHours();

        String mon = json.get("mon").getAsString();
        String tue = json.get("tue").getAsString();
        String wed = json.get("wed").getAsString();
        String thu = json.get("thu").getAsString();
        String fri = json.get("fri").getAsString();
        String sat = json.get("sat").getAsString();
        String sun = json.get("sun").getAsString();
        String holiday = json.get("holiday").getAsString();

        hours.setMonday(mon);
        hours.setTuesday(tue);
        hours.setWednesday(wed);
        hours.setThursday(thu);
        hours.setFriday(fri);
        hours.setSaturday(sat);
        hours.setSunday(sun);
        hours.setHoliday(holiday);

        return hours;
    }
}
