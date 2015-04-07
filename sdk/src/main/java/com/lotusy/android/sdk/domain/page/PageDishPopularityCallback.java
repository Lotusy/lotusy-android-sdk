package com.lotusy.android.sdk.domain.page;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2015-04-06.
 */
abstract public class PageDishPopularityCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallback.LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;

        if (status == LotusyCallback.LotusyCallbackStatus.SUCCESS) {
            result = new LotusyTaskResult();
            result.setStatusCode(0);
            result.setSuccess(true);
        }
        else if (status == LotusyCallback.LotusyCallbackStatus.ERROR) {
            result = new LotusyTaskResult();
            result.setStatusCode(1);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }
        else if (status == LotusyCallback.LotusyCallbackStatus.FAILURE) {
            result = new LotusyTaskResult();
            result.setStatusCode(2);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }

        this.callback(result, response);
    }

    abstract public void callback(LotusyTaskResult result, JsonObject popularity);
}