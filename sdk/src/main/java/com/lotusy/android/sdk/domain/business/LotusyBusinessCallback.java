package com.lotusy.android.sdk.domain.business;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyJsonObjectCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-17.
 */
abstract public class LotusyBusinessCallback extends LotusyJsonObjectCallback {

    @Override
    protected String getObjectResponseKey() { return null; }

    abstract public void callback(LotusyTaskResult result, JsonObject user);
}