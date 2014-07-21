package com.lotusy.android.sdk.task;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyCallback {

    protected enum LotusyCallbackStatus { SUCCESS, ERROR, FAILURE }

    protected void parseResponse(String response) {
        JSONObject jsonResponse = null;

        try {
            jsonResponse = new JSONObject(response);
        } catch (JSONException e) {
            JSONObject error = new JSONObject();

            try {
                error.put("status", "failure");
                error.put("description", "cannot_parse_response");
                error.put("response", response);

                this.doCallback(LotusyCallbackStatus.FAILURE, error);
                return;
            } catch (JSONException e1) {}
        }

        LotusyCallbackStatus status = LotusyCallbackStatus.ERROR;

        try {
            if (jsonResponse.get("status")=="success") {
                status = LotusyCallbackStatus.SUCCESS;
            }
        } catch (JSONException e) {}

        this.doCallback(status, jsonResponse);
    }

    abstract protected void doCallback(LotusyCallbackStatus status, JSONObject response);
}
