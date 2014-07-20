package com.lotusy.android.sdk.domain.business;

import com.lotusy.android.sdk.domain.LotusyAddress;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySchedule;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyBusiness {

    private int id;
    private int creatorId;
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
    private LotusyLatLng latlng;
    private LotusySchedule schedule;
    private LotusyAddress address;
    private LotusyRating rating;

    public LotusyBusiness() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public LotusySchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(LotusySchedule schedule) {
        this.schedule = schedule;
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
}
