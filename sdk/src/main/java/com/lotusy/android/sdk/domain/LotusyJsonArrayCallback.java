package com.lotusy.android.sdk.domain;

import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-15.
 */
abstract public class LotusyJsonArrayCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        JsonArray array = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            String key = this.getArrayResponseKey();
            if (key!=null && key!="") {
                array = response.getAsJsonArray(key);
            }
            result = new LotusyTaskResult();
            result.setStatusCode(0);
            result.setSuccess(true);
        }
        else if (status == LotusyCallbackStatus.ERROR) {
            result = new LotusyTaskResult();
            result.setStatusCode(1);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }
        else if (status == LotusyCallbackStatus.FAILURE) {
            result = new LotusyTaskResult();
            result.setStatusCode(2);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }

        this.callback(result, array);
    }

    abstract public void callback(LotusyTaskResult result, JsonArray array);

    abstract protected String getArrayResponseKey();
}