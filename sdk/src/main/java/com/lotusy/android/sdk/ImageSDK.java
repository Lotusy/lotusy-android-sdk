package com.lotusy.android.sdk;

import java.io.InputStream;

/**
 * Created by pshen on 2014-07-14.
 */
public class ImageSDK {

    private static ImageSDK defaultSDK=null;

// ==========================================================================================================

    public String[] getCommentImages(int commentId) {
        return null;
    }

    public String[] getBusinessCommentImages(int businessId) {
        return null;
    }

    public String[] getUserCommentImages(int userId) {
        return null;
    }

    public boolean uploadCommentImage(int commentId, InputStream stream) {
        return true;
    }

    public boolean uploadBusinessProfileImage(int businessId, InputStream stream) {
        return true;
    }

    public boolean uploadUserProfileImage(InputStream stream) {
        return true;
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
