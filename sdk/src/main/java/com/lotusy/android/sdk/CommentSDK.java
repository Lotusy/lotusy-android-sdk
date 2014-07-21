package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyUser;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyListCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyProperties;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pshen on 2014-07-14.
 */
public class CommentSDK {


    public void createComment( LotusyLatLng latlng,
                               int business_id,
                               String message,
                               LotusyCommentCreateCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JSONObject body = new JSONObject();
        try {
            if (latlng!=null) {
                body.put("lat", latlng.getLat());
                body.put("lng", latlng.getLng());
            }

            body.put("business_id", business_id);
            body.put("message", message);
        } catch (JSONException e) {}

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment");
        param.setBody(body.toString());
        param.setMethod("POST");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getComment(int commentId, LotusyCommentCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId);
        param.setBody("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void deleteComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId);
        param.setBody("DELETE");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void likeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId+"/like");
        param.setBody("PUT");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void dislikeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId+"/dislike");
        param.setBody("PUT");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getCommentsNearLocation( LotusyLatLng latlng,
                                         int radius,
                                         boolean is_miles,
                                         int start,
                                         int size,
                                         LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        if (latlng==null) {
            LotusyTaskResult result = new LotusyTaskResult();
            result.setSuccess(false);
            result.setStatusCode(400);
            result.addError("missing_location");
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
        param.setBody("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getUserComments( int userId,
                                 int start,
                                 int size,
                                 LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/user/"+userId+"/comments?&start="+start+"&size="+size);
        param.setBody("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getBusinessComments( int businessId,
                                     int start,
                                     int size,
                                     LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId+"/comments?start="+start+"&size="+size);
        param.setBody("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void createReply(int commentId, String message, LotusyReplyCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JSONObject body = new JSONObject();
        try {
            body.put("comment_id", commentId);
            body.put("message", message);
            body.put("nickname", LotusyUser.current().getNickName());
        } catch (JSONException e) {}

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+commentId+"/replay");
        param.setBody("POST");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getCommentReplies( int commentId,
                                   int start,
                                   int size,
                                   LotusyReplyListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+commentId+"/replies?start="+start+"&size="+size);
        param.setBody("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


// ==========================================================================================================


    private static CommentSDK defaultSDK=null;

    public static CommentSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new CommentSDK();
        }
        return defaultSDK;
    }

    private static String getHost() {
        return LotusyProperties.getHost("comment");
    }

    private CommentSDK() {}
}
