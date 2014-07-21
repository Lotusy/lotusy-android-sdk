package com.lotusy.android.sdk.domain.comment;

import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import org.json.JSONObject;

/**
 * Created by pshen on 2014-07-18.
 */
abstract public class LotusyCommentCreateCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JSONObject response) {

    }

    abstract public void callback(LotusyTaskResult result, LotusyComment comment);
}
