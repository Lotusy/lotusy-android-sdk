package com.lotusy.android.sdk;

import com.lotusy.android.sdk.object.LotusyBusiness;
import com.lotusy.android.sdk.object.LotusyRating;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK {

    public static int createBusiness(LotusyBusiness business) {
        return 0;
    }

    public static LotusyBusiness getBusinessProfile(int businessId) {
        return null;
    }

    public static LotusyBusiness[] getBusinessNearLocation(double lat, double lng, int radius, int start, int end) {
        return null;
    }

    public static boolean rateBusiness(int businessId) {
        return true;
    }

    public static LotusyRating getUserRating(int userid, int businessId) {
        return null;
    }
}
