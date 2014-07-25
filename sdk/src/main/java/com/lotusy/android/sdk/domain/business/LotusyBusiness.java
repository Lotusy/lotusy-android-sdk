package com.lotusy.android.sdk.domain.business;

import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyHours;
import com.lotusy.android.sdk.domain.LotusyLatLng;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyBusiness {

    protected int id;
    protected int creatorId;
    private String zhName;
    private String twName;
    private String enName;
    private String price;
    private boolean cashOnly;
    private boolean verified;
    private String phone;
    private String website;
    private String social;
    private String imageUrl;
    private int commentCount;
    private int ratingCount;
    private LotusyLatLng latlng;
    private LotusyHours hours;
    private LotusyAddress address;
    private LotusyRating rating;

    public LotusyBusiness() {}

    public int getId() {
        return id;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String name) {
        this.zhName = name;
    }

    public String getTwName() {
        return twName;
    }

    public void setTwName(String name) {
        this.twName = name;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String name) {
        this.enName = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isCashOnly() {
        return cashOnly;
    }

    public void setCashOnly(boolean cashOnly) {
        this.cashOnly = cashOnly;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public LotusyHours getHours() {
        return hours;
    }

    public void setHours(LotusyHours hours) {
        this.hours = hours;
    }

    public LotusyRating getRating() { return rating; }

    public void setRating(LotusyRating rating) { this.rating = rating; }

    public LotusyAddress getAddress() { return address; }

    public void setAddress(LotusyAddress address) { this.address = address; }

    public LotusyLatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LotusyLatLng latlng) {
        this.latlng = latlng;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }
}
