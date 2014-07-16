package com.lotusy.android.sdk.object;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyUser {

    private static LotusyUser current;

    public static LotusyUser current() {
        return LotusyUser.current;
    }
}
