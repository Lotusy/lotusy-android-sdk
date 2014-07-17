package com.lotusy.android.sdk.object;

import java.util.Date;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyComment {

    private int id;
    private int businessId;
    private int userId;
    private LotusyLatLng latlng;
    private String message;
    private int likeCount;
    private int dislikeCount;
    private Date createTime;
    private int replyCount;
    private String[] imageUris;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LotusyLatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LotusyLatLng latlng) {
        this.latlng = latlng;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getDislikeCount() {
        return dislikeCount;
    }

    public void setDislikeCount(int dislikeCount) {
        this.dislikeCount = dislikeCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getReplyCount() {
        return replyCount;
    }

    public void setReplyCount(int replyCount) {
        this.replyCount = replyCount;
    }

    public String[] getImageUris() {
        return imageUris;
    }

    public void setImageUris(String[] imageUris) {
        this.imageUris = imageUris;
    }
}
