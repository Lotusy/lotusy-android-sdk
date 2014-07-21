package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.image.LotusyImageLinksCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
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
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId+"/links");
        param.setBody("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void getBusinessCommentImages(int businessId, LotusyImageLinksCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId+"/links");
        param.setBody("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void getUserCommentImages(int userId, LotusyImageLinksCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/user/"+userId+"/comment/links");
        param.setBody("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void uploadCommentImage( int commentId,
                                    InputStream stream,
                                    LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/comment/"+commentId);
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void uploadBusinessProfileImage( int businessId,
                                            InputStream stream,
                                            LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/business/"+businessId);
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public void uploadUserProfileImage(InputStream stream, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/user");
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
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
