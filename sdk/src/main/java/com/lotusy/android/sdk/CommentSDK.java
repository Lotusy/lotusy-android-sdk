package com.lotusy.android.sdk;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyUser;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentLocationListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyListCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-14.
 */
public class CommentSDK extends LotusySDK {


    public static void createComment( LotusyLatLng latlng,
                                      int business_id,
                                      String message,
                                      LotusyCommentCreateCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JsonObject body = new JsonObject();
        if (latlng!=null) {
            body.addProperty("lat", latlng.getLat());
            body.addProperty("lng", latlng.getLng());
        }

        body.addProperty("business_id", business_id);
        body.addProperty("message", message);


        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment");
        param.setBody(body.toString());
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getComment(int commentId, LotusyCommentCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void deleteComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId);
        param.setMethod("DELETE");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void likeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId+"/like");
        param.setMethod("PUT");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void dislikeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId+"/dislike");
        param.setMethod("PUT");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getCommentsNearLocation( LotusyLatLng latlng,
                                                int radius,
                                                boolean is_miles,
                                                int start,
                                                int size,
                                                LotusyCommentLocationListCallback callback ) {
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
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserComments( int userId,
                                        int start,
                                        int size,
                                        LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/user/"+userId+"/comments?&start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getBusinessComments( int businessId,
                                            int start,
                                            int size,
                                            LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId+"/comments?start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void createReply(int commentId, String message, LotusyReplyCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        JsonObject body = new JsonObject();
        body.addProperty("comment_id", commentId);
        body.addProperty("message", message);
        body.addProperty("nickname", LotusyUser.current().getNickName());

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+commentId+"/reply");
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getCommentReplies( int commentId,
                                          int start,
                                          int size,
                                          LotusyReplyListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+commentId+"/replies?start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


// ==========================================================================================================


    private static String getHost() {

        String host = "";

        switch (env()) {
            case DEV:
                host = "http://local.comment.lotusy.com/rest";
                break;
            case TEST:
                host = "http://test.comment.lotusy.com/rest";
                break;
            case INT:
                host = "http://int.comment.lotusy.com/rest";
                break;
            case STAG:
                host = "http://staging.comment.lotusy.com/rest";
                break;
            case PROD:
                host = "http://comment.lotusy.com/rest";
                break;
        }

        return host;
    }
}
