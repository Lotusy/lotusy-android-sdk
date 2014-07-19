package com.lotusy.android.sdk;

import com.lotusy.android.sdk.callback.LotusySimpleCallback;
import com.lotusy.android.sdk.callback.account.LotusyTokenCallback;
import com.lotusy.android.sdk.callback.account.LotusyUserCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;

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
        param.setPath("/register/"+externalType);
        param.setMethod("POST");
        param.setBody(body.toString());

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


    public static void login( String externalType,
                              String externalRef,
                              LotusyTokenCallback callback) {

        LotusyTaskParam param = new LotusyTaskParam();
        param.setPath("/auth/"+externalType+"/"+externalRef);
        param.setMethod("GET");

        LotusyRestTransactionTask task = new LotusyRestTransactionTask(param, callback);
        task.run();
    }


// ==========================================================================================================


    public void getProfile(LotusyUserCallback callback) {

    }


    public void getUserProfile(int userId, LotusyUserCallback callback) {

    }


    public void updateProfile( String userName,
                               String nickName,
                               String picture,
                               String description,
                               LotusySimpleCallback callback) {

    }


// ==========================================================================================================


    private static AccountSDK defaultSDK=null;


    public static AccountSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new AccountSDK();
        }
        return defaultSDK;
    }


    private AccountSDK() {}
}
