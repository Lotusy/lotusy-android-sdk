package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.domain.LotusyJsonObjectCallback;

import java.util.Calendar;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyCurrentUserCallback extends LotusyJsonObjectCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
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

        this.callback(result, response);
    }

    abstract public void callback(LotusyTaskResult result, JsonObject user);
}

