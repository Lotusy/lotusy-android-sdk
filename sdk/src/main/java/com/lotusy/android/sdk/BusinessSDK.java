package com.lotusy.android.sdk;

import com.lotusy.android.sdk.object.LotusyBusiness;
import com.lotusy.android.sdk.object.LotusyLatLng;
import com.lotusy.android.sdk.object.LotusyRating;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK extends LotusySDK {

    private static BusinessSDK defaultSDK=null;

// ==========================================================================================================

    public int createBusiness(LotusyBusiness business)  {
        return 0;
    }

    public LotusyBusiness getBusinessProfile(int businessId)  {
        return null;
    }

    public LotusyBusiness[] getBusinessNearLocation( LotusyLatLng latlng,
                                                     int radius,
                                                     int start,
                                                     int end)  {
        return null;
    }

    public boolean rateBusiness( int businessId,
                                 LotusyRating rating) {
        return true;
    }

    public LotusyRating getUserRating(int businessId) {
        return null;
    }

// ==========================================================================================================

    public static BusinessSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new BusinessSDK();
        }
        return defaultSDK;
    }

    private BusinessSDK() {}
}
