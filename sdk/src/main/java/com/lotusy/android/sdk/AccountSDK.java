package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.domain.account.LotusyUserCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyProperties;

import org.json.JSONException;
import org.json.JSONObject;

public class AccountSDK extends LotusySDK {


    public static void register( String externalType,
                                 String externalRef,
                                 String userName,
                                 String nickName,
                                 String picture,
                                 String description,
                                 LotusyTokenCallback callback) {

        JSONObject body = new JSONObject();
        try {
            body.put("id", externalType);
            body.put("username", userName);
            body.put("nickname", nickName);
            body.put("profile_pic", picture);
            body.put("description", description);
        } catch (JSONException e) {}

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/register/" + externalType);
        param.setMethod("POST");
        param.setBody(body.toString());

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public static void login( String externalType,
                              String externalRef,
                              LotusyTokenCallback callback) {

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/auth/" + externalType + "/" + externalRef);
        param.setMethod("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


// ==========================================================================================================


    public void getProfile(LotusyUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/profile/");
        param.setMethod("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void getUserProfile(int userId, LotusyUserCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/" + userId + "/profile/");
        param.setMethod("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public void updateProfile( String userName,
                               String nickName,
                               String picture,
                               String description,
                               LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

        if (userName==null && nickName==null && picture==null && description==null) {
            LotusyTaskResult result = new LotusyTaskResult();
            result.setSuccess(true);
            callback.callback(result);
        }

        JSONObject body = new JSONObject();
        try {
            if (userName!=null) { body.put("username", userName); }
            if (nickName!=null) { body.put("nickname", nickName); }
            if (picture!=null) { body.put("profile_pic", picture); }
            if (description!=null) { body.put("description", description); }
        } catch (JSONException e) {}

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/profile");
        param.setMethod("POST");
        param.setBody(body.toString());

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


// ==========================================================================================================


    private static AccountSDK defaultSDK=null;

    public static AccountSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new AccountSDK();
        }
        return defaultSDK;
    }

    private static String getHost() {
        return LotusyProperties.getHost("user");
    }

    private AccountSDK() {}
}
