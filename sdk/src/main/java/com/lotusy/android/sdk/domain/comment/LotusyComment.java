package com.lotusy.android.sdk.domain.comment;

import com.lotusy.android.sdk.domain.LotusyLatLng;

import java.util.Date;
import java.util.List;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyComment {

    protected int id;
    protected int businessId;
    protected int userId;
    private LotusyLatLng latlng;
    private String message;
    private int likeCount;
    private int dislikeCount;
    private Date createTime;
    private int replyCount;
    private List<String> imageUris;

    public int getId() {
        return id;
    }

    public int getBusinessId() {
        return businessId;
    }

    public int getUserId() {
        return userId;
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

    public List<String> getImageUris() {
        return imageUris;
    }

    public void setImageUris(List<String> imageUris) {
        this.imageUris = imageUris;
    }
}
