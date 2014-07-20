package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.image.LotusyImageLinksCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyProperties;

import java.io.InputStream;

/**
 * Created by pshen on 2014-07-14.
 */
public class ImageSDK {


    public void getCommentImages(int commentId, LotusyImageLinksCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void getBusinessCommentImages(int businessId, LotusyImageLinksCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void getUserCommentImages(int userId, LotusyImageLinksCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void uploadCommentImage( int commentId,
                                    InputStream stream,
                                    LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


    public void uploadBusinessProfileImage( int businessId,
                                            InputStream stream,
                                            LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


    public void uploadUserProfileImage(InputStream stream, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


// ==========================================================================================================


    private static ImageSDK defaultSDK=null;

    public static ImageSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new ImageSDK();
        }
        return defaultSDK;
    }

    private static String getHost() {
        return LotusyProperties.getHost("image");
    }

    private ImageSDK() {}
}
