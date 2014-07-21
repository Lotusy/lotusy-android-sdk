package com.lotusy.android.sdk.domain;

import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import org.json.JSONObject;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusySimpleCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JSONObject response) {

    }

    abstract public void callback(LotusyTaskResult result);
}
