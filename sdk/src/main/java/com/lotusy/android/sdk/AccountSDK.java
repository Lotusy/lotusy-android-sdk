package com.lotusy.android.sdk;

import com.lotusy.android.sdk.exception.LotusyUserAlreadyExistException;
import com.lotusy.android.sdk.exception.LotusyUserNotExistException;
import com.lotusy.android.sdk.object.LotusyToken;
import com.lotusy.android.sdk.object.LotusyUser;

public class AccountSDK extends LotusySDK {

    private static AccountSDK defaultSDK=null;

    public static boolean register( String externalType,
                                    String externalRef,
                                    String userName,
                                    String nickName,
                                    String picture,
                                    String description) throws LotusyUserAlreadyExistException {
        LotusySDK.token = new LotusyToken();
        return true;
    }

    public static boolean login( String externalType,
                                 String externalRef) throws LotusyUserNotExistException {
        LotusySDK.token = new LotusyToken();
        return true;
    }

// ==========================================================================================================

    public LotusyUser getProfile() {
        return null;
    }

    public LotusyUser getUserProfile(int userId) {
        return null;
    }

    public boolean updateProfile( String userName,
                                  String nickName,
                                  String picture,
                                  String description) {
        return true;
    }

// ==========================================================================================================

    public static AccountSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new AccountSDK();
        }
        return defaultSDK;
    }

    private AccountSDK() {}
}
