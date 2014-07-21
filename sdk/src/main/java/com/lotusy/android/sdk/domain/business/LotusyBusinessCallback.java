package com.lotusy.android.sdk.domain.business;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyBusinessCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {

    }

    abstract public void callback(LotusyTaskResult result, LotusyBusiness business);
}
