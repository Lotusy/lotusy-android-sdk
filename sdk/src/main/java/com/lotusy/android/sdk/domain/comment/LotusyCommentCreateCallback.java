package com.lotusy.android.sdk.domain.comment;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyUtility;

/**
 * Created by pshen on 2014-07-18.
 */
abstract public class LotusyCommentCreateCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        LotusyComment comment = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            comment = LotusyUtility.parseCommentJson(response);
            int commentId = response.get("id").getAsInt();
            comment.id = commentId;
            int userId = response.get("user_id").getAsInt();
            comment.userId = userId;
            int businessId = response.get("business_id").getAsInt();
            comment.businessId = businessId;

            result = new LotusyTaskResult();
            result.setStatusCode(0);
            result.setSuccess(true);
        }
        else if (status == LotusyCallbackStatus.ERROR) {
            result = new LotusyTaskResult();
            result.setStatusCode(1);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }
        else if (status == LotusyCallbackStatus.FAILURE) {
            result = new LotusyTaskResult();
            result.setStatusCode(2);
            result.setSuccess(false);

            String description = response.get("description").getAsString();
            result.addError(description);
        }

        this.callback(result, comment);
    }

    abstract public void callback(LotusyTaskResult result, LotusyComment comment);
}
