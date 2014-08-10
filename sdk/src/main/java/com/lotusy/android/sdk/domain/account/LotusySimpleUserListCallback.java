package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusySimpleUserListCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        ArrayList<LotusySimpleUser> users = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            users = new ArrayList<LotusySimpleUser>();

            JsonArray userArr = response.get("users").getAsJsonArray();

            Iterator<JsonElement> itr = userArr.iterator();
            while(itr.hasNext()) {
                JsonObject element = itr.next().getAsJsonObject();

                LotusySimpleUser user = new LotusySimpleUser();
                if (element.get("id")!=null) {
                    int userId = element.get("id").getAsInt();
                    user.id = userId;
                }
                if (element.get("nickname")!=null) {
                    String nickName = element.get("nickname").getAsString();
                    user.nickName = nickName;
                }
                if (element.get("profile_pic")!=null) {
                    String profilePic = element.get("profile_pic").getAsString();
                    user.picture = profilePic;
                }

                users.add(user);
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

        this.callback(result, users);
    }

    abstract public void callback(LotusyTaskResult result, List<LotusySimpleUser> users);
}

