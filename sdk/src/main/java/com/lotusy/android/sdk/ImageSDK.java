package com.lotusy.android.sdk;

import com.lotusy.android.sdk.callback.LotusySimpleCallback;
import com.lotusy.android.sdk.callback.image.LotusyImageLinksCallback;

import java.io.InputStream;

/**
 * Created by pshen on 2014-07-14.
 */
public class ImageSDK {

    private static ImageSDK defaultSDK=null;

// ==========================================================================================================

    public void getCommentImages(int commentId, LotusyImageLinksCallback callback) {

    }

    public void getBusinessCommentImages(int businessId, LotusyImageLinksCallback callback) {

    }

    public void getUserCommentImages(int userId, LotusyImageLinksCallback callback) {

    }

    public void uploadCommentImage( int commentId,
                                    InputStream stream,
                                    LotusySimpleCallback callback ) {

    }

    public void uploadBusinessProfileImage( int businessId,
                                            InputStream stream,
                                            LotusySimpleCallback callback ) {

    }

    public void uploadUserProfileImage(InputStream stream, LotusySimpleCallback callback) {

    }

// ==========================================================================================================

    public static ImageSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new ImageSDK();
        }
        return defaultSDK;
    }

    private ImageSDK() {}
}
