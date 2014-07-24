package com.lotusy.android.sdk.domain.account;

import java.util.Date;
/**
 * Created by indochino on 2014-07-16.
 */
public class LotusyToken {

    protected static LotusyToken current;

    public static LotusyToken current() {
        return current;
    }

    protected int userId;
    protected String accessToken;
    protected String refreshToken;
    protected String tokenType;
    protected Date expiresAt;

    public int getUserId() {
        return userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }
}
