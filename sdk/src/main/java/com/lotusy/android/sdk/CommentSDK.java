package com.lotusy.android.sdk;

import com.lotusy.android.sdk.callback.LotusySimpleCallback;
import com.lotusy.android.sdk.callback.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.callback.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.callback.comment.LotusyCommentListCallback;
import com.lotusy.android.sdk.callback.comment.LotusyReplyCallback;
import com.lotusy.android.sdk.callback.comment.LotusyReplyListCallback;
import com.lotusy.android.sdk.object.LotusyLatLng;

/**
 * Created by pshen on 2014-07-14.
 */
public class CommentSDK {

    private static CommentSDK defaultSDK=null;

// ==========================================================================================================

    public void createComment( LotusyLatLng latlng,
                               int business_id,
                               String message,
                               LotusyCommentCreateCallback callback ) {

    }

    public void getComment(int commentId, LotusyCommentCallback callback) {

    }

    public void deleteComment(int commentId, LotusySimpleCallback callback) {

    }

    public void likeComment(int commentId, LotusySimpleCallback callback) {

    }

    public void dislikeComment(int commentId, LotusySimpleCallback callback) {

    }

    public void getCommentsNearLocation( LotusyLatLng latlng,
                                         int start,
                                         int size,
                                         LotusyCommentListCallback callback ) {

    }

    public void getUserComments( int userId,
                                 int start,
                                 int size,
                                 LotusyCommentListCallback callback ) {

    }

    public void getBusinessComments( int businessId,
                                     int start,
                                     int size,
                                     LotusyCommentListCallback callback ) {

    }

    public void createReply(int commentId, String message, LotusyReplyCallback callback) {

    }

    public void getCommentReplies( int commentId,
                                   int start,
                                   int size,
                                   LotusyReplyListCallback calback ) {

    }

// ==========================================================================================================

    public static CommentSDK defaultSDK() {
        if (defaultSDK==null) {
            defaultSDK = new CommentSDK();
        }
        return defaultSDK;
    }

    private CommentSDK() {}
}
