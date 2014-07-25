package com.lotusy.android.sdk.domain.image;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyUtility;

import java.util.List;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyImageLinksCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        List<String> links = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            JsonArray linkArr = response.get("links").getAsJsonArray();

            links = LotusyUtility.parseImageLinks(linkArr);

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

        this.callback(result, links);
    }

    abstract public void callback(LotusyTaskResult result, List<String> links);
}

