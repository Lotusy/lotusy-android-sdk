package com.lotusy.android.sdk.domain.comment;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyUtility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyCommentListCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;
        ArrayList<LotusyComment> comments = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            comments = new ArrayList<LotusyComment>();

            JsonArray businessArr = response.get("comments").getAsJsonArray();

            Iterator<JsonElement> itr = businessArr.iterator();
            while(itr.hasNext()) {
                JsonObject element = itr.next().getAsJsonObject();

                LotusyComment comment = LotusyUtility.parseCommentJson(element);
                int commentId = response.get("id").getAsInt();
                comment.id = commentId;
                int userId = response.get("user_id").getAsInt();
                comment.userId = userId;
                int businessId = response.get("business_id").getAsInt();
                comment.businessId = businessId;

                comments.add(comment);
            }

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

        this.callback(result, comments);
    }

    abstract public void callback(LotusyTaskResult result, List<LotusyComment> comment);
}
