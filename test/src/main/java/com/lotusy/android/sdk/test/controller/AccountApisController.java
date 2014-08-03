package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;

import com.lotusy.android.sdk.AccountSDK;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.account.LotusyTokenCallback;
import com.lotusy.android.sdk.domain.account.LotusyUser;
import com.lotusy.android.sdk.domain.account.LotusyUserCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.ResultActivity;

/**
 * Created by indochino on 2014-07-25.
 */
public class AccountApisController {

    private static String reference = "";
    private static int userId = 1;

    public static void registerUser(final Activity activity) {
        String externalType = "facebook";
        final String externalRef = "6"+Math.round(Math.random()*100000000);
        String userName = "John Bob "+Math.round(Math.random() * 100);
        String nickName = "Funny Guy "+Math.round(Math.random()*100);
        String picture = "http://www.lotusy.com/"+Math.round(Math.random()*100);
        String description = "Test user "+userName;
        AccountSDK.register(
                externalType, externalRef, userName, nickName, picture, description, new LotusyTokenCallback() {
                    @Override
                    public void callback(LotusyTaskResult result, LotusyToken token) {
                        String msg = "result: "+result.isSuccess()+"\n\n";
                        if (result.isSuccess()) {
                            reference = externalRef;
                            userId = token.getUserId();
                            msg = msg+"user id: "+token.getUserId()+"\n\n";
                            msg = msg+"access token: "+token.getAccessToken()+"\n\n";
                            msg = msg+"refresh token: "+token.getRefreshToken()+"\n\n";
                            msg = msg+"token type: "+token.getTokenType()+"\n\n";
                            msg = msg+"expires in: "+token.getExpiresAt();
                        }

                        Intent intent = new Intent(activity, ResultActivity.class);
                        intent.putExtra("result", msg);
                        activity.startActivity(intent);
                    }
                }
        );
    }


    public static void loginUser(final Activity activity) {
        String externalType = "facebook";
        AccountSDK.login(externalType, reference, new LotusyTokenCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyToken token) {
                String msg = "result: "+result.isSuccess()+"\n\n";
                if (result.isSuccess()) {
                    msg = msg+"user id: "+token.getUserId()+"\n\n";
                    msg = msg+"access token: "+token.getAccessToken()+"\n\n";
                    msg = msg+"refresh token: "+token.getRefreshToken()+"\n\n";
                    msg = msg+"token type: "+token.getTokenType()+"\n\n";
                    msg = msg+"expires in: "+token.getExpiresAt();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void getCurrentProfile(final Activity activity) {
        AccountSDK.getProfile(new LotusyUserCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyUser user) {
                String msg = "result: "+result.isSuccess()+"\n\n";
                if (result.isSuccess()) {
                    msg = msg+"user id: "+user.getId()+"\n\n";
                    msg = msg+"description : "+user.getDescription()+"\n\n";
                    msg = msg+"external reference: "+user.getExternalRef()+"\n\n";
                    msg = msg+"external type: "+user.getExternalType()+"\n\n";
                    msg = msg+"nick name: "+user.getNickName()+"\n\n";
                    msg = msg+"picture: "+user.getPicture()+"\n\n";
                    msg = msg+"user name: "+user.getUserName()+"\n\n";
                    msg = msg+"last login: "+user.getLastLogin();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void getProfile(final Activity activity) {
        AccountSDK.getUserProfile(userId, new LotusyUserCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyUser user) {
                String msg = "result: "+result.isSuccess()+"\n\n";
                if (result.isSuccess()) {
                    msg = msg+"user id: "+user.getId()+"\n\n";
                    msg = msg+"description : "+user.getDescription()+"\n\n";
                    msg = msg+"external reference: "+user.getExternalRef()+"\n\n";
                    msg = msg+"external type: "+user.getExternalType()+"\n\n";
                    msg = msg+"nick name: "+user.getNickName()+"\n\n";
                    msg = msg+"picture: "+user.getPicture()+"\n\n";
                    msg = msg+"user name: "+user.getUserName()+"\n\n";
                    msg = msg+"last login: "+user.getLastLogin();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void updateProfile(final Activity activity) {
        AccountSDK.updateProfile("updated name", "updated nick", "http://profile", "updated profile", new LotusyUserCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyUser user) {
                String msg = "result: "+result.isSuccess();
                if (result.isSuccess()) {
                    msg = msg+"user id: "+user.getId()+"\n\n";
                    msg = msg+"description : "+user.getDescription()+"\n\n";
                    msg = msg+"external reference: "+user.getExternalRef()+"\n\n";
                    msg = msg+"external type: "+user.getExternalType()+"\n\n";
                    msg = msg+"nick name: "+user.getNickName()+"\n\n";
                    msg = msg+"picture: "+user.getPicture()+"\n\n";
                    msg = msg+"user name: "+user.getUserName()+"\n\n";
                    msg = msg+"last login: "+user.getLastLogin();
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static int current() {
        return userId;
    }
}
