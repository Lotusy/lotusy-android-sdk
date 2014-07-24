package com.lotusy.android.sdk.domain.image;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.util.List;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyImageLinksCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {

    }

    abstract public void callback(LotusyTaskResult result, List<String> links);
}

