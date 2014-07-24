package com.lotusy.android.sdk.domain.account;

import java.util.Date;

/**
 * Created by pshen on 2014-07-14.
 */
public class LotusyUser {

    private static LotusyUser current;

    public static LotusyUser current() {
        return current;
    }

    protected int id;
    protected String externalType;
    protected String externalRef;
    protected String userName;
    protected String nickName;
    protected String picture;
    protected String description;
    protected Date lastLogin;
    protected boolean superUser;
    protected boolean blocked;

    public int getId() {
        return id;
    }

    public String getExternalType() {
        return externalType;
    }

    public String getExternalRef() {
        return externalRef;
    }

    public String getUserName() {
        return userName;
    }

    public String getNickName() {
        return nickName;
    }

    public String getPicture() {
        return picture;
    }

    public String getDescription() {
        return description;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public boolean isSuperUser() {
        return superUser;
    }

    public boolean isBlocked() {
        return blocked;
    }
}
