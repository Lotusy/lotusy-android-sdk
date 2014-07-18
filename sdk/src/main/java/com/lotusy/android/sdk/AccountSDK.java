package com.lotusy.android.sdk;

import com.lotusy.android.sdk.callback.LotusySimpleCallback;
import com.lotusy.android.sdk.object.LotusyToken;
import com.lotusy.android.sdk.object.LotusyUser;
import com.lotusy.android.sdk.callback.account.LotusyTokenCallback;
import com.lotusy.android.sdk.callback.account.*;

public class AccountSDK extends LotusySDK {

    private static LotusyUser current;

    public static LotusyUser currentUser() {
        return current;
    }

    private static AccountSDK defaultSDK=null;

    public static void register( String externalType,
                                 String externalRef,
                                 String userName,
                                 String nickName,
                                 String picture,
                                 String description,
                                 LotusyTokenCallback callback) {

        LotusySDK.token = new LotusyToken();
        current = new LotusyUser();

    }

    public static void login( String externalType,
                              String externalRef,
                              LotusyTokenCallback callback) {

        LotusySDK.token = new LotusyToken();
        current = new LotusyUser();
    }

// ==========================================================================================================

    public void getProfile(LotusyUserCallback callback) {

    }

    public void getUserProfile(int userId, LotusyUserCallback callback) {

    }

    public void updateProfile( String userName,
                               String nickName,
                               String picture,
                               String description,
                               LotusySimpleCallback callback) {

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
