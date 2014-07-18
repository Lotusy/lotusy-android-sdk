package com.lotusy.android.sdk.callback.business;

import com.lotusy.android.sdk.object.LotusyBusiness;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyBusinessListCallback extends LotusyCallback {

    @Override
    protected void doCallback(Object args) {

    }

    abstract public void callback(LotusyTaskResult result, LotusyBusiness[] business);
}
