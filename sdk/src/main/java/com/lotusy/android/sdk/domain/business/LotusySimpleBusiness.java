package com.lotusy.android.sdk.domain.business;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusySimpleBusiness {

    protected int id;
    private String zhName;
    private String twName;
    private String enName;
    private String price;
    private boolean cashOnly;
    private String imageUrl;
    private int commentCount;
    private LotusyRating rating;

    public LotusySimpleBusiness() {}

    public int getId() {
        return id;
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

    public LotusyRating getRating() { return rating; }

    public void setRating(LotusyRating rating) { this.rating = rating; }

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
