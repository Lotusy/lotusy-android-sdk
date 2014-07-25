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

        if (json.get("hours")!=null) {
            LotusyHours hours = parseHoursJson(json.get("hours").getAsJsonObject());
            business.setHours(hours);
        }
        if (json.get("rating")!=null) {
            LotusyRating rating = parseRatingJson(json.get("rating").getAsJsonObject());
            business.setRating(rating);
        }
        if (json.get("comment_count")!=null) {
            int commentCount = json.get("comment_count").getAsInt();
            business.setCommentCount(commentCount);
        }
        if (json.get("rating_count")!=null) {
            int ratingCount = json.get("rating_count").getAsInt();
            business.setRatingCount(ratingCount);
        }
        if (json.get("name_zh")!=null) {
            String nameZh = json.get("name_zh").getAsString();
            business.setZhName(nameZh);
        }
        if (json.get("name_tw")!=null) {
            String nameTw = json.get("name_tw").getAsString();
            business.setTwName(nameTw);
        }
        if (json.get("name_en")!=null) {
            String nameEn = json.get("name_en").getAsString();
            business.setEnName(nameEn);
        }
        if (json.get("price")!=null) {
            String price = json.get("price").getAsString();
            business.setPrice(price);
        }
        if (json.get("cash_only")!=null) {
            boolean cachOnly = json.get("cash_only").getAsString().equals("Y");
            business.setCashOnly(cachOnly);
        }
        if (json.get("verified")!=null) {
            boolean verified = json.get("verified").getAsString().equals("Y");
            business.setVerified(verified);
        }
        if (json.get("tel")!=null) {
            String phone = json.get("tel").getAsString();
            business.setPhone(phone);
        }
        if (json.get("website")!=null) {
            String website = json.get("website").getAsString();
            business.setWebsite(website);
        }
        if (json.get("social")!=null) {
            String social = json.get("social").getAsString();
            business.setSocial(social);
        }


        return business;
    }

    public static LotusyRating parseRatingJson(JsonObject json) {
        LotusyRating rating = new LotusyRating();

        if (json.get("overall")!=null) {
            int overall = json.get("overall").getAsInt();
            rating.setOverall(overall);
        }
        if (json.get("food")!=null) {
            int food = json.get("food").getAsInt();
            rating.setFood(food);
        }
        if (json.get("service")!=null) {
            int service = json.get("service").getAsInt();
            rating.setService(service);
        }
        if (json.get("environment")!=null) {
            int environment = json.get("environment").getAsInt();
            rating.setEnvironment(environment);
        }
        if (json.get("create_time")!=null) {
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

        if (json.get("image_links")!=null) {
            JsonArray imageLinkArr = json.get("image_links").getAsJsonArray();
            List<String> imageLinks = parseImageLinks(imageLinkArr);
            comment.setImageUris(imageLinks);
        }
        if (json.get("message")!=null) {
            String message = json.get("message").getAsString();
            comment.setMessage(message);
        }
        if (json.get("like_count")!=null) {
            int like = json.get("like_count").getAsInt();
            comment.setLikeCount(like);
        }
        if (json.get("dislike_count")!=null) {
            int dislike = json.get("dislike_count").getAsInt();
            comment.setDislikeCount(dislike);
        }
        if (json.get("reply_count")!=null) {
            int replies = json.get("reply_count").getAsInt();
            comment.setReplyCount(replies);
        }
        if (json.get("create_time")!=null) {
            int createTime = (-1) * json.get("create_time").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, createTime);
            comment.setCreateTime(calendar.getTime());
        }

        return comment;
    }

    public static LotusyReply parseReplyJson(JsonObject json) {
        LotusyReply reply = new LotusyReply();

        if (json.get("message")!=null) {
            String message = json.get("message").getAsString();
            reply.setMessage(message);
        }
        if (json.get("nickname")!=null) {
            String nickName = json.get("nickname").getAsString();
            reply.setNickName(nickName);
        }
        if (json.get("create_time")!=null) {
            int createTime = (-1) * json.get("create_time").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, createTime);
            reply.setCreateTime(calendar.getTime());
        }

        return reply;
    }

    public static LotusyLatLng parseLatlngJson(JsonObject json) {
        LotusyLatLng latlng = new LotusyLatLng();

        if (json.get("lat")!=null) {
            double lat = json.get("lat").getAsDouble();
            latlng.setLat(lat);
        }
        if (json.get("lng")!=null) {
            double lng = json.get("lng").getAsDouble();
            latlng.setLng(lng);
        }

        return latlng;
    }

    public static LotusyAddress parseAddressJson(JsonObject json) {
        LotusyAddress address = new LotusyAddress();

        if (json.get("street")!=null) {
            String street = json.get("street").getAsString();
            address.setStreet(street);
        }
        if (json.get("city")!=null) {
            String city = json.get("city").getAsString();
            address.setCity(city);
        }
        if (json.get("state")!=null) {
            String state = json.get("state").getAsString();
            address.setState(state);
        }
        if (json.get("country")!=null) {
            String country = json.get("country").getAsString();
            address.setCountry(country);
        }
        if (json.get("zip")!=null) {
            String zip = json.get("zip").getAsString();
            address.setZip(zip);
        }

        return address;
    }

    public static LotusyHours parseHoursJson(JsonObject json) {
        LotusyHours hours = new LotusyHours();

        if (json.get("mon")!=null) {
            String mon = json.get("mon").getAsString();
            hours.setMonday(mon);
        }
        if (json.get("tue")!=null) {
            String tue = json.get("tue").getAsString();
            hours.setTuesday(tue);
        }
        if (json.get("wed")!=null) {
            String wed = json.get("wed").getAsString();
            hours.setWednesday(wed);
        }
        if (json.get("thu")!=null) {
            String thu = json.get("thu").getAsString();
            hours.setThursday(thu);
        }
        if (json.get("fri")!=null) {
            String fri = json.get("fri").getAsString();
            hours.setFriday(fri);
        }
        if (json.get("sat")!=null) {
            String sat = json.get("sat").getAsString();
            hours.setSaturday(sat);
        }
        if (json.get("sun")!=null) {
            String sun = json.get("sun").getAsString();
            hours.setSunday(sun);
        }
        if (json.get("holiday")!=null) {
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
