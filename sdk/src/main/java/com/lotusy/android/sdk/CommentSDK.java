package com.lotusy.android.sdk;

import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.account.LotusyToken;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyListCallback;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.utility.LotusyProperties;

/**
 * Created by pshen on 2014-07-14.
 */
public class CommentSDK {


    public void createComment( LotusyLatLng latlng,
                               int business_id,
                               String message,
                               LotusyCommentCreateCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void getComment(int commentId, LotusyCommentCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void deleteComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


    public void likeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


    public void dislikeComment(int commentId, LotusySimpleCallback callback) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult());
        }

    }


    public void getCommentsNearLocation( LotusyLatLng latlng,
                                         int start,
                                         int size,
                                         LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void getUserComments( int userId,
                                 int start,
                                 int size,
                                 LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void getBusinessComments( int businessId,
                                     int start,
                                     int size,
                                     LotusyCommentListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


    public void createReply(int commentId, String message, LotusyReplyCallback callback) {

    }


    public void getCommentReplies( int commentId,
                                   int start,
                                   int size,
                                   LotusyReplyListCallback callback ) {
        if (LotusyToken.current()==null) {
            callback.callback(LotusyTaskResult.getNoAuthResult(), null);
        }

    }


// ==========================================================================================================


    private static CommentSDK defaultSDK=null;

    public static CommentSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new CommentSDK();
        }
        return defaultSDK;
    }

    private static String getHost() {
        return LotusyProperties.getHost("comment");
    }

    private CommentSDK() {}
}
