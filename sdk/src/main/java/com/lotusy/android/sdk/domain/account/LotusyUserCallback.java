package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.util.Calendar;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyUserCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        LotusyUser user = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            user = new LotusyUser();

            if (response.get("id")!=null) {
                int userId = response.get("id").getAsInt();
                user.id = userId;
            }
            if (response.get("external_ref")!=null) {
                String externalRef = response.get("external_ref").getAsString();
                user.externalRef = externalRef;
            }
            if (response.get("external_type")!=null) {
                String externalType = response.get("external_type").getAsString();
                user.externalType = externalType;
            }
            if (response.get("username")!=null) {
                String userName = response.get("username").getAsString();
                user.userName = userName;
            }
            if (response.get("nickname")!=null) {
                String nickName = response.get("nickname").getAsString();
                user.nickName = nickName;
            }
            if (response.get("profile_pic")!=null) {
                String profilePic = response.get("profile_pic").getAsString();
                user.picture = profilePic;
            }
            if (response.get("description")!=null) {
                String description = response.get("description").getAsString();
                user.description = description;
            }
            if (response.get("superuser")!=null) {
                boolean superuser = response.get("superuser").getAsString().equals("Y");
                user.superUser = superuser;
            }
            if (response.get("blocked")!=null) {
                boolean blocked = response.get("blocked").getAsString().equals("Y");
                user.blocked = blocked;
            }
            if (response.get("last_login")!=null) {
                int lastLogin = (-1) * response.get("last_login").getAsInt();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, lastLogin);
                user.lastLogin = calendar.getTime();
            }

            if (LotusyToken.current!=null && user.id==LotusyToken.current.userId) {
                LotusyUser.current = user;
            }

            result = new LotusyTaskResult();
            result.setSuccess(true);
            result.setStatusCode(0);
        }
        else if (status == LotusyCallbackStatus.ERROR) {
            result = new LotusyTaskResult();
            result.setSuccess(false);
            String description = response.get("description").getAsString();
            result.addError(description);
            result.setStatusCode(1);
        }
        else if (status == LotusyCallbackStatus.FAILURE) {
            result = new LotusyTaskResult();
            result.setSuccess(false);
            String description = response.get("description").getAsString();
            result.addError(description);
            result.setStatusCode(2);
        }

        this.callback(result, user);
    }

    abstract public void callback(LotusyTaskResult result, LotusyUser user);
}

