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

    private int userId;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private Date expiresAt;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
