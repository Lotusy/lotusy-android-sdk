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
        headers.put("Language", lang);

        if (LotusyToken.current()!=null) {
            headers.put("Authorization", "Bearer "+LotusyToken.current().getAccessToken());
        }

        return headers;
    }

    public static Map<String, String> getImageHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "image/png");
        headers.put("app-key", appKey);
        headers.put("Language", lang);

        if (LotusyToken.current()!=null) {
            headers.put("Authorization", "Bearer "+LotusyToken.current().getAccessToken());
        }

        return headers;
    }

    private static String appKey;
    private static ENVIRONMENT env;
    private static String lang;

    public static void setup(String applicationKey, ENVIRONMENT environment, String language) {
        appKey = applicationKey;
        env = environment;
        lang = language;
    }

    protected static ENVIRONMENT env() {
        return env;
    }

    protected static String language() { return lang; }

    protected static String getHost() {

        String host = "";

        switch (env()) {
            case DEV:
                host = "http://local.api.foodster.club/rest";
                break;
            case TEST:
                host = "http://test.api.foodster.club/rest";
                break;
            case INT:
                host = "http://int.api.foodster.club/rest";
                break;
            case STAG:
                host = "http://staging.api.foodstser.club/rest";
                break;
            case PROD:
                host = "https://api.foodster.club/rest";
                break;
        }

        return host;
    }
}
