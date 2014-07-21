package com.lotusy.android.sdk.task;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * Created by pshen on 2014-07-17.
 */
abstract public class LotusyCallback {

    protected enum LotusyCallbackStatus { SUCCESS, ERROR, FAILURE }

    protected void parseResponse(String response) {
        JsonObject jsonResponse = null;
        JsonParser parser = new JsonParser();
        try {
            jsonResponse = (JsonObject) parser.parse(response);
        } catch (JsonSyntaxException e) {
            JsonObject error = new JsonObject();

            error.addProperty("status", "failure");
            error.addProperty("description", "cannot_parse_response");
            error.addProperty("response", response);

            this.doCallback(LotusyCallbackStatus.FAILURE, error);
            return;
        }

        LotusyCallbackStatus status = LotusyCallbackStatus.ERROR;

        if (jsonResponse.get("status").getAsString().equals("success")) {
            status = LotusyCallbackStatus.SUCCESS;
        }

        this.doCallback(status, jsonResponse);
    }

    abstract protected void doCallback(LotusyCallbackStatus status, JsonObject response);
}
