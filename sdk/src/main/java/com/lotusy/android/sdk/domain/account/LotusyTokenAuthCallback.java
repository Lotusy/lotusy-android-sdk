package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.util.Calendar;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyTokenAuthCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            LotusyToken.current = new LotusyToken();
            if (response.get("access_token")!=null) {
                String accessToken = response.get("access_token").getAsString();
                LotusyToken.current.accessToken = accessToken;
            }
            if (response.get("refresh_token")!=null) {
                String refreshToken = response.get("refresh_token").getAsString();
                LotusyToken.current.refreshToken = refreshToken;
            }
            if (response.get("user_id")!=null) {
                int userId = response.get("user_id").getAsInt();
                LotusyToken.current.userId = userId;
            }
            if (response.get("token_type")!=null) {
                String tokenType = response.get("token_type").getAsString();
                LotusyToken.current.tokenType = tokenType;
            }
            if (response.get("expires_in")!=null) {
                int expiresAt = response.get("expires_in").getAsInt();
                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.SECOND, expiresAt);
                LotusyToken.current.expiresAt = calendar.getTime();
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

        this.callback(result);
    }

    abstract public void callback(LotusyTaskResult result);
}
