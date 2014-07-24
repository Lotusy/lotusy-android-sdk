package com.lotusy.android.sdk.domain.business;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyUtility;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyBusinessCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        LotusyBusiness business = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            business = LotusyUtility.parseBusinessJson(response);
            int businessId = response.get("id").getAsInt();
            business.id = businessId;
            int creatorId = response.get("user_id").getAsInt();
            business.creatorId = creatorId;

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

        this.callback(result, business);
    }

    abstract public void callback(LotusyTaskResult result, LotusyBusiness business);
}
