package com.lotusy.android.sdk;

import com.lotusy.android.sdk.callback.LotusySimpleCallback;
import com.lotusy.android.sdk.callback.business.*;
import com.lotusy.android.sdk.object.LotusyBusiness;
import com.lotusy.android.sdk.object.LotusyLatLng;
import com.lotusy.android.sdk.object.LotusyRating;

/**
 * Created by pshen on 2014-07-14.
 */
public class BusinessSDK extends LotusySDK {

    private static BusinessSDK defaultSDK=null;

// ==========================================================================================================

    public void createBusiness(LotusyBusiness business, LotusyBusinessCallback callback) {

    }

    public void getBusinessProfile(int businessId, LotusyBusinessCallback callback) {

    }

    public void getBusinessesNearLocation( LotusyLatLng latlng,
                                           int radius,
                                           int start,
                                           int size,
                                           LotusyBusinessListCallback callback ) {

    }

    public void rateBusiness( int businessId,
                              LotusyRating rating,
                              LotusySimpleCallback callback ) {

    }

    public void getUserRating(int businessId, LotusyRatingCallback callback) {

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
