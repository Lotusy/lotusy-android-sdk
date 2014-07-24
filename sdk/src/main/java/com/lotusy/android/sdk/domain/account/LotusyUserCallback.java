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
            int userId = response.get("id").getAsInt();
            String externalRef = response.get("external_ref").getAsString();
            String externalType = response.get("external_type").getAsString();
            String userName = response.get("username").getAsString();
            String nickName = response.get("nickname").getAsString();
            String profilePic = response.get("profile_pic").getAsString();
            String description = response.get("description").getAsString();
            boolean superuser = response.get("superuser").getAsString().equals("Y");
            boolean blocked = response.get("blocked").getAsString().equals("Y");
            int lastLogin = (-1)*response.get("last_login").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, lastLogin);

            user = new LotusyUser();

            user.id = userId;
            user.description = description;
            user.externalRef = externalRef;
            user.externalType = externalType;
            user.userName = userName;
            user.nickName = nickName;
            user.picture = profilePic;
            user.lastLogin = calendar.getTime();
            user.superUser = superuser;
            user.blocked = blocked;

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

