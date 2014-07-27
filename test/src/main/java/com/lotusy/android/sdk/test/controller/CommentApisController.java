package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;

import com.lotusy.android.sdk.CommentSDK;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.comment.LotusyComment;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.ResultActivity;

/**
 * Created by pshen on 2014-07-27.
 */
public class CommentApisController {

    private static int commentId = 1;

    public static void createComment(final Activity activity) {
        LotusyLatLng latlng = new LotusyLatLng();
        latlng.setLat(47+Math.random()*4);
        latlng.setLng(-125+Math.random()*4);

        String msg = "Test Comment "+Math.round(Math.random()*1000);

        CommentSDK.createComment(latlng, BusinessApisController.current(), "Comment", new LotusyCommentCreateCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyComment comment) {
                String msg = "result: "+result.isSuccess()+"\n\n";
                if (result.isSuccess()) {
                    commentId = comment.getId();
                    msg = msg+"comment id: "+comment.getId()+"\n\n";
                    msg = msg+"message: "+comment.getMessage()+"\n\n";
                    msg = msg+"like count: "+comment.getLikeCount()+"\n\n";
                    msg = msg+"dislike count: "+comment.getDislikeCount()+"\n\n";
                    msg = msg+"reply count: "+comment.getReplyCount()+"\n\n";
                    msg = msg+"image uris: "+comment.getImageUris()+"\n\n";
                    msg = msg+"create time: "+comment.getCreateTime()+"\n\n";
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void getCommentDetail(final Activity activity) {
        CommentSDK.getComment(commentId, new LotusyCommentCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyComment comment) {
                String msg = "result: "+result.isSuccess()+"\n\n";
                if (result.isSuccess()) {
                    commentId = comment.getId();
                    msg = msg+"comment id: "+comment.getId()+"\n\n";
                    msg = msg+"message: "+comment.getMessage()+"\n\n";
                    msg = msg+"like count: "+comment.getLikeCount()+"\n\n";
                    msg = msg+"dislike count: "+comment.getDislikeCount()+"\n\n";
                    msg = msg+"reply count: "+comment.getReplyCount()+"\n\n";
                    msg = msg+"image uris: "+comment.getImageUris()+"\n\n";
                    msg = msg+"create time: "+comment.getCreateTime()+"\n\n";
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void likeComment(final Activity activity) {
        CommentSDK.likeComment(commentId, new LotusySimpleCallback() {
            @Override
            public void callback(LotusyTaskResult result) {
                String msg = "result: "+result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void dislikeComment(final Activity activity) {
        CommentSDK.dislikeComment(commentId, new LotusySimpleCallback() {
            @Override
            public void callback(LotusyTaskResult result) {
                String msg = "result: "+result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }
}
