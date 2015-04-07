package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.image.LotusyImageCallback;
import com.lotusy.android.sdk.domain.image.LotusyImageLinksCallback;
import com.lotusy.android.sdk.task.LotusyRestTransactionTask;
import com.lotusy.android.sdk.task.LotusyTaskParam;
import com.lotusy.android.sdk.task.LotusyTaskResult;

import java.io.InputStream;

/**
 * Created by pshen on 2014-07-14.
 */
public class ImageSDK extends LotusySDK {


    public static void getCommentImages(int commentId, LotusyImageLinksCallback callback) {
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


    public static void getBusinessCommentImages(int businessId, LotusyImageLinksCallback callback) {
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


    public static void getUserCommentImages(int userId, LotusyImageLinksCallback callback) {
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


    public static void uploadCommentImage( int commentId,
                                           InputStream stream,
                                           LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setHeader(LotusySDK.getImageHeaders());
        param.setUri(getHost()+"/comment/"+commentId);
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void uploadBusinessProfileImage( int businessId,
                                                   InputStream stream,
                                                   LotusySimpleCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setHeader(LotusySDK.getImageHeaders());
        param.setUri(getHost()+"/business/"+businessId);
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void uploadUserProfileImage(InputStream stream, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setHeader(LotusySDK.getImageHeaders());
        param.setUri(getHost()+"/user");
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void getDishFastImages( int dishId,
                                          int start,
                                          int size,
                                          LotusyImageLinksCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setUri(getHost()+"/dish/"+dishId+"/links?start="+start+"&size="+size);
        param.setBody("GET");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }


    public static void uploadFastImage( int dishId,
                                        int commentId,
                                        InputStream stream,
                                        LotusyImageCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), 0);
            return;
        }

        LotusyTaskParam param = new LotusyTaskParam();
        param.setHeader(LotusySDK.getImageHeaders());
        param.setUri(getHost()+"/dish/"+dishId+"/comment/"+commentId);
        param.setFile(stream);
        param.setMethod("POST");

        Thread task = new Thread(new LotusyRestTransactionTask(param, callback));
        task.start();
    }
}
