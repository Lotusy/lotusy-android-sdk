package com.lotusy.android.sdk.domain.account;

import com.google.gson.JsonObject;
import com.lotusy.android.sdk.task.LotusyCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.util.Calendar;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyTokenCallback extends LotusyCallback {

    @Override
    protected void doCallback(LotusyCallbackStatus status, JsonObject response) {
        LotusyTaskResult result = null;

        if (status == LotusyCallbackStatus.SUCCESS) {
            String accessToken = response.get("access_token").getAsString();
            String refreshToken = response.get("access_token").getAsString();
            int userId = response.get("access_token").getAsInt();
            String tokenType = response.get("access_token").getAsString();
            int expiresAt = response.get("access_token").getAsInt();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.SECOND, expiresAt);

            LotusyToken.current = new LotusyToken();
            LotusyToken.current.setAccessToken(accessToken);
            LotusyToken.current.setRefreshToken(refreshToken);
            LotusyToken.current.setTokenType(tokenType);
            LotusyToken.current.setUserId(userId);
            LotusyToken.current.setExpiresAt(calendar.getTime());

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

        this.callback(result, LotusyToken.current);
    }

    abstract public void callback(LotusyTaskResult result, LotusyToken token);
}
