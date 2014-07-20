package com.lotusy.android.sdk.domain.account;

import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyTokenCallback extends LotusyCallback {

    @Override
    protected void doCallback(Object args) {

        LotusyToken.current = new LotusyToken();
    }

    abstract public void callback(LotusyTaskResult result, LotusyToken token);
}
