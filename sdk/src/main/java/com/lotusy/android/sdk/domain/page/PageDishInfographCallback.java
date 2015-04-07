package com.lotusy.android.sdk.domain.page;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-07.
 */
abstract public class PageDishInfographCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        JsonObject infograph = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            try {
                infograph = response.get("detail").getAsJsonObject();

                result = new LotusyTaskResult();
                result.setStatusCode(0);
                result.setSuccess(true);
            } catch (Exception e) {
                result = new LotusyTaskResult();
                result.setStatusCode(2);
                result.setSuccess(false);

                String description = e.getMessage();
                result.addError(description);
            }
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

        this.callback(result, infograph);
    }

    abstract public void callback(LotusyTaskResult result, JsonObject infograph);
}

