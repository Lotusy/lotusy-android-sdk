package com.lotusy.android.sdk;

import com.lotusy.android.sdk.object.LotusyComment;
import com.lotusy.android.sdk.object.LotusyLatLng;
import com.lotusy.android.sdk.object.LotusyReply;

/**
 * Created by pshen on 2014-07-14.
 */
public class CommentSDK {

    private static CommentSDK defaultSDK=null;

// ==========================================================================================================

    public LotusyComment createComment(LotusyLatLng latlng, int business_id, String message) {
        return null;
    }

    public LotusyComment getComment(int commentId) {
        return null;
    }

    public boolean deleteComment(int commentId) {
        return true;
    }

    public boolean likeComment(int commentId) {
        return true;
    }

    public boolean dislikeComment(int commentId) {
        return true;
    }

    public LotusyComment[] getCommentsNearLocation(LotusyLatLng latlng, int start, int size) {
        return null;
    }

    public LotusyComment[] getUserComments(int userId, int start, int size) {
        return null;
    }

    public LotusyComment[] getBusinessComments(int businessId, int start, int size) {
        return null;
    }

    public LotusyReply createReply(int commentId, String message) {
        return null;
    }

    public LotusyReply[] getCommentReplies(int commentId, int start, int size) {
        return null;
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
