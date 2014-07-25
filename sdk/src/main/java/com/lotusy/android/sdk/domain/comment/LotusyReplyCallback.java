package com.lotusy.android.sdk.domain.comment;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyUtility;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyReplyCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        LotusyReply reply = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            reply = LotusyUtility.parseReplyJson(response);
            int replyId = response.get("id").getAsInt();
            reply.id = replyId;
            int commentId = response.get("comment_id").getAsInt();
            reply.commentId = commentId;
            int userId = response.get("user_id").getAsInt();
            reply.userId = userId;

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

        this.callback(result, reply);
    }

    abstract public void callback(LotusyTaskResult result, LotusyReply reply);
}
