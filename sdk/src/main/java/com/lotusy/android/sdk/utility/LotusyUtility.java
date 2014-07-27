package com.lotusy.android.sdk.utility;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.business.LotusyBusiness;
import com.lotusy.android.sdk.domain.business.LotusyRating;
import com.lotusy.android.sdk.domain.comment.LotusyComment;
import com.lotusy.android.sdk.domain.comment.LotusyReply;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

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

        if (json.get("hours")!=null && !json.get("hours").isJsonNull()) {
            LotusyHours hours = parseHoursJson(json.get("hours").getAsJsonObject());
            business.setHours(hours);
        }
        if (json.get("rating")!=null && !json.get("rating").isJsonNull()) {
            LotusyRating rating = parseRatingJson(json.get("rating").getAsJsonObject());
            business.setRating(rating);
        }
        if (json.get("comment_count")!=null && !json.get("comment_count").isJsonNull()) {
            int commentCount = json.get("comment_count").getAsInt();
            business.setCommentCount(commentCount);
        }
        if (json.get("rating_count")!=null && !json.get("rating_count").isJsonNull()) {
            int ratingCount = json.get("rating_count").getAsInt();
            business.setRatingCount(ratingCount);
        }
        if (json.get("name_zh")!=null && !json.get("name_zh").isJsonNull()) {
            String nameZh = json.get("name_zh").getAsString();
            business.setZhName(nameZh);
        }
        if (json.get("name_tw")!=null && !json.get("name_tw").isJsonNull()) {
            String nameTw = json.get("name_tw").getAsString();
            business.setTwName(nameTw);
        }
        if (json.get("name_en")!=null && !json.get("name_en").isJsonNull()) {
            String nameEn = json.get("name_en").getAsString();
            business.setEnName(nameEn);
        }
        if (json.get("price")!=null && !json.get("price").isJsonNull()) {
            String price = json.get("price").getAsString();
            business.setPrice(price);
        }
        if (json.get("cash_only")!=null && !json.get("cash_only").isJsonNull()) {
            boolean cachOnly = json.get("cash_only").getAsString().equals("Y");
            business.setCashOnly(cachOnly);
        }
        if (json.get("verified")!=null && !json.get("verified").isJsonNull()) {
            boolean verified = json.get("verified").getAsString().equals("Y");
            business.setVerified(verified);
        }
        if (json.get("tel")!=null && !json.get("tel").isJsonNull()) {
            String phone = json.get("tel").getAsString();
            business.setPhone(phone);
        }
        if (json.get("website")!=null && !json.get("website").isJsonNull()) {
            String website = json.get("website").getAsString();
            business.setWebsite(website);
        }
        if (json.get("social")!=null && !json.get("social").isJsonNull()) {
            String social = json.get("social").getAsString();
            business.setSocial(social);
        }


        return business;
    }

    public static LotusyRating parseRatingJson(JsonObject json) {
        LotusyRating rating = new LotusyRating();

        if (json.get("overall")!=null && !json.get("overall").isJsonNull()) {
            double overall = json.get("overall").getAsDouble();
            rating.setOverall(overall);
        }
        if (json.get("food")!=null && !json.get("food").isJsonNull()) {
            double food = json.get("food").getAsDouble();
            rating.setFood(food);
        }
        if (json.get("serv")!=null && !json.get("serv").isJsonNull()) {
            double service = json.get("serv").getAsDouble();
            rating.setService(service);
        }
        if (json.get("env")!=null && !json.get("env").isJsonNull()) {
            double environment = json.get("env").getAsDouble();
            rating.setEnvironment(environment);
        }
        if (json.get("create_time")!=null && !json.get("create_time").isJsonNull()) {
            int createTime = (-1) * json.get("create_time").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, createTime);
            rating.setCreateTime(calendar.getTime());
        }

        return rating;
    }

    public static LotusyComment parseCommentJson(JsonObject json) {
        LotusyComment comment = new LotusyComment();

        LotusyLatLng latlng = parseLatlngJson(json);
        comment.setLatlng(latlng);

        if (json.get("image_links")!=null && !json.get("image_links").isJsonNull()) {
            JsonArray imageLinkArr = json.get("image_links").getAsJsonArray();
            List<String> imageLinks = parseImageLinks(imageLinkArr);
            comment.setImageUris(imageLinks);
        }
        if (json.get("message")!=null && !json.get("message").isJsonNull()) {
            String message = json.get("message").getAsString();
            comment.setMessage(message);
        }
        if (json.get("like_count")!=null && !json.get("like_count").isJsonNull()) {
            int like = json.get("like_count").getAsInt();
            comment.setLikeCount(like);
        }
        if (json.get("dislike_count")!=null && !json.get("dislike_count").isJsonNull()) {
            int dislike = json.get("dislike_count").getAsInt();
            comment.setDislikeCount(dislike);
        }
        if (json.get("reply_count")!=null && !json.get("reply_count").isJsonNull()) {
            int replies = json.get("reply_count").getAsInt();
            comment.setReplyCount(replies);
        }
        if (json.get("create_time")!=null && !json.get("create_time").isJsonNull()) {
            int createTime = (-1) * json.get("create_time").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, createTime);
            comment.setCreateTime(calendar.getTime());
        }

        return comment;
    }

    public static LotusyReply parseReplyJson(JsonObject json) {
        LotusyReply reply = new LotusyReply();

        if (json.get("message")!=null && !json.get("message").isJsonNull()) {
            String message = json.get("message").getAsString();
            reply.setMessage(message);
        }
        if (json.get("nickname")!=null && !json.get("nickname").isJsonNull()) {
            String nickName = json.get("nickname").getAsString();
            reply.setNickName(nickName);
        }
        if (json.get("create_time")!=null && !json.get("create_time").isJsonNull()) {
            int createTime = (-1) * json.get("create_time").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, createTime);
            reply.setCreateTime(calendar.getTime());
        }

        return reply;
    }

    public static LotusyLatLng parseLatlngJson(JsonObject json) {
        LotusyLatLng latlng = new LotusyLatLng();

        if (json.get("lat")!=null && !json.get("lat").isJsonNull()) {
            double lat = json.get("lat").getAsDouble();
            latlng.setLat(lat);
        }
        if (json.get("lng")!=null && !json.get("lng").isJsonNull()) {
            double lng = json.get("lng").getAsDouble();
            latlng.setLng(lng);
        }

        return latlng;
    }

    public static LotusyAddress parseAddressJson(JsonObject json) {
        LotusyAddress address = new LotusyAddress();

        if (json.get("street")!=null && !json.get("street").isJsonNull()) {
            String street = json.get("street").getAsString();
            address.setStreet(street);
        }
        if (json.get("city")!=null && !json.get("city").isJsonNull()) {
            String city = json.get("city").getAsString();
            address.setCity(city);
        }
        if (json.get("state")!=null && !json.get("state").isJsonNull()) {
            String state = json.get("state").getAsString();
            address.setState(state);
        }
        if (json.get("country")!=null && !json.get("country").isJsonNull()) {
            String country = json.get("country").getAsString();
            address.setCountry(country);
        }
        if (json.get("zip")!=null && !json.get("zip").isJsonNull()) {
            String zip = json.get("zip").getAsString();
            address.setZip(zip);
        }

        return address;
    }

    public static LotusyHours parseHoursJson(JsonObject json) {
        LotusyHours hours = new LotusyHours();

        if (json.get("mon")!=null && !json.get("mon").isJsonNull()) {
            String mon = json.get("mon").getAsString();
            hours.setMonday(mon);
        }
        if (json.get("tue")!=null && !json.get("tue").isJsonNull()) {
            String tue = json.get("tue").getAsString();
            hours.setTuesday(tue);
        }
        if (json.get("wed")!=null && !json.get("wed").isJsonNull()) {
            String wed = json.get("wed").getAsString();
            hours.setWednesday(wed);
        }
        if (json.get("thu")!=null && !json.get("thu").isJsonNull()) {
            String thu = json.get("thu").getAsString();
            hours.setThursday(thu);
        }
        if (json.get("fri")!=null && !json.get("fri").isJsonNull()) {
            String fri = json.get("fri").getAsString();
            hours.setFriday(fri);
        }
        if (json.get("sat")!=null && !json.get("sat").isJsonNull()) {
            String sat = json.get("sat").getAsString();
            hours.setSaturday(sat);
        }
        if (json.get("sun")!=null && !json.get("sun").isJsonNull()) {
            String sun = json.get("sun").getAsString();
            hours.setSunday(sun);
        }
        if (json.get("holiday")!=null && !json.get("holiday").isJsonNull()) {
            String holiday = json.get("holiday").getAsString();
            hours.setHoliday(holiday);
        }

        return hours;
    }

    public static List<String> parseImageLinks(JsonArray array) {
        ArrayList<String> images = new ArrayList<String>();

        Iterator<JsonElement> itr = array.iterator();
        while(itr.hasNext()) {
            String link = itr.next().getAsString();
            images.add(link);
        }

        return images;
    }
}
