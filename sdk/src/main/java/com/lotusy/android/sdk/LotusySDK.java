package com.lotusy.android.sdk;

import com.lotusy.android.sdk.object.LotusyToken;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by indochino on 2014-07-16.
 */
abstract public class LotusySDK {

    protected static LotusyToken token;

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
}
