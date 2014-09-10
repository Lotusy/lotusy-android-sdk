package com.lotusy.android.sdk;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusySimpleUserListCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenAuthCallback;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.domain.account.LotusyUser;
import com.lotusy.android.sdk.domain.account.LotusyUserCallback;
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
        param.setUri(getHost()+"/auth/" + externalType + "/" + externalRef);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void authenticate( String externalType,
                                     String accessToken,
                                     LotusyTokenCallback callback ) {
        JsonObject body = new JsonObject();
        body.addProperty("access_token", accessToken);

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/token/auth/" + externalType);
        param.setMethod("POST");
        param.setBody(body.toString());

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }

    public static void tokenLogin( String accessToken,
                                   LotusyTokenAuthCallback callback ) {
        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/tokeninfo?access_token=" + accessToken);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getProfile(LotusyUserCallback callback) {
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


    public static void getUserProfile(int userId, LotusyUserCallback callback) {
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
                                      LotusyUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        if (userName==null && nickName==null && picture==null && description==null) {
            LotusyTaskResult result = new LotusyTaskResult();
            result.setSuccess(true);
            callback.callback(result, LotusyUser.current());
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


    public static void getUserFollowers( int userId,
                                         int start,
                                         int size,
                                         LotusySimpleUserListCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/"+userId+"/followers?start="+start+"&size="+size);
        param.setMethod("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


// ==========================================================================================================


    private static String getHost() {

        String host = "";

        switch (env()) {
            case DEV:
                host = "http://local.account.lotusy.com/rest";
                break;
            case TEST:
                host = "http://test.account.lotusy.com/rest";
                break;
            case INT:
                host = "http://int.account.lotusy.com/rest";
                break;
            case STAG:
                host = "http://staging.account.lotusy.com/rest";
                break;
            case PROD:
                host = "http://account.lotusy.com/rest";
                break;
        }

        return host;
    }
}
