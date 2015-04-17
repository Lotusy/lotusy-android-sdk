package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.domain.LotusyJsonObjectCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-15.
 */
abstract public class LotusyUserCallback extends LotusyJsonObjectCallback {

    @Override
    protected String getObjectResponseKey() { return null; }

    abstract public void callback(LotusyTaskResult result, JsonObject user);
}