package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.utility.LotusyProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by indochino on 2014-07-16.
 */
abstract public class LotusySDK {

    protected Map<String, LotusySDK> sdks;

    protected LotusySDK() {
        sdks = new HashMap<String, LotusySDK>();
    }

    public void registerSDK(String key, LotusySDK sdk) {
        sdks.put(key, sdk);
    }

    protected LotusySDK lookupSDK(String key) {
        return sdks.get(key);
    }

    public static Map<String, String> getDefaultHeaders() {
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");
        headers.put("app-key", LotusyProperties.getAppKey());

        if (LotusyToken.current()!=null) {
            headers.put("Authorization", "Bearer "+LotusyToken.current().getAccessToken());
        }

        return headers;
    }
}
