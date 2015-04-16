package com.lotusy.android.sdk;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyCurrentUserCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.domain.account.LotusyUserFollowersCallback;
import com.lotusy.android.sdk.domain.account.LotusyUserFollowingsCallback;
import com.lotusy.android.sdk.domain.business.LotusyDishListCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;


public class AccountSDK extends LotusySDK {


    public static void register( String externalType,
                                 String externalRef,
                                 String userName,
                                 String nickName,
                                 String picture,
                                 String description,
                                 LotusyTokenCallback callback) {

        JsonObject body = new JsonObject();
        body.addProperty("id", externalRef);
        body.addProperty("username", userName);
        body.addProperty("nickname", nickName);
        body.addProperty("profile_pic", picture);
        body.addProperty("description", description);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/register/" + externalType);
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void login( String externalType,
                              String externalRef,
                              LotusyTokenCallback callback) {

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/auth/" + externalType + "/" + externalRef);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void logout(LotusySimpleCallback callback) {

    }


    public static void getCurrentUserProfile(LotusyCurrentUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/profile");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserProfile(int userId, LotusyCurrentUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/" + userId + "/profile");
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void updateProfile( String userName,
                                      String nickName,
                                      String picture,
                                      String description,
                                      LotusyCurrentUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        if (userName==null && nickName==null && picture==null && description==null) {
            LotusyTaskResult result = new LotusyTaskResult();
            result.setSuccess(false);
            result.setStatusCode(1);
            result.addError("all input are null");
            callback.callback(result, null);
        }

        JsonObject body = new JsonObject();
        if (userName!=null) { body.addProperty("username", userName); }
        if (nickName!=null) { body.addProperty("nickname", nickName); }
        if (picture!=null) { body.addProperty("profile_pic", picture); }
        if (description!=null) { body.addProperty("description", description); }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/profile");
        param.setMethod("PUT");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserFollowings( int userId,
                                          int start,
                                          int size,
                                          LotusyUserFollowingsCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/user/" + userId + "/followers?start=" + start + "&size=" + size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserFollowers( int userId,
                                         int start,
                                         int size,
                                         LotusyUserFollowersCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/user/" + userId + "/followers?start=" + start + "&size=" + size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void followUser(int userId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/follow/" + userId);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void collectDish(int dishId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost() + "/dish/" + dishId + "/collect");
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getUserDishCollection( int userId,
                                              int start,
                                              int size,
                                              LotusyDishListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+userId+"/dishes?start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }
}
