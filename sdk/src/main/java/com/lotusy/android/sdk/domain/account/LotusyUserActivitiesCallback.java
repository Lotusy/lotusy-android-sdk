package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonArray;
import com.lotusy.android.sdk.domain.LotusyJsonArrayCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by Indochino on 2015-04-15.
 */
abstract public class LotusyUserActivitiesCallback extends LotusyJsonArrayCallback {

    @Override
    protected String getArrayResponseKey() {
        return "activities";
    }

    abstract public void callback(LotusyTaskResult result, JsonArray activities);
}
