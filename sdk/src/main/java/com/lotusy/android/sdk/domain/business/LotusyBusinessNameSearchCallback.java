package com.lotusy.android.sdk.domain.business;

import com.google.gson.JsonArray;
import com.lotusy.android.sdk.domain.LotusyJsonArrayCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-17.
 */
abstract public class LotusyBusinessNameSearchCallback extends LotusyJsonArrayCallback {

    @Override
    protected String getArrayResponseKey() {
        return "businesses";
    }

    abstract public void callback(LotusyTaskResult result, JsonArray businesses);
}
