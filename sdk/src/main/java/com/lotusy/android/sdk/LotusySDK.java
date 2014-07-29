package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.account.LotusyToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by indochino on 2014-07-16.
 */
abstract public class LotusySDK {

    public enum ENVIRONMENT { DEV, TEST, INT, STAG, PROD }

    public static Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("app-key", appKey);

        if (LotusyToken.current()!=null) {
            headers.put("Authorization", "Bearer "+LotusyToken.current().getAccessToken());
        }

        return headers;
    }

    public static Map<String, String> getImageHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "image/png");
        headers.put("app-key", appKey);

        if (LotusyToken.current()!=null) {
            headers.put("Authorization", "Bearer "+LotusyToken.current().getAccessToken());
        }

        return headers;
    }

    private static String appKey;
    private static ENVIRONMENT env;

    public static void setup(String applicationKey, ENVIRONMENT environment) {
        appKey = applicationKey;
        env = environment;
    }

    protected static ENVIRONMENT env() {
        return env;
    }
}
