package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;

import com.lotusy.android.sdk.CommentSDK;
import com.lotusy.android.sdk.domain.LotusyLatLng;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.domain.comment.LotusyComment;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentCreateCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyCommentLocationListCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReply;
import com.lotusy.android.sdk.domain.comment.LotusyReplyCallback;
import com.lotusy.android.sdk.domain.comment.LotusyReplyListCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.ResultActivity;

import java.util.List;
import java.util.Map;

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

        CommentSDK.createComment(latlng, BusinessApisController.current(), msg, new LotusyCommentCreateCallback() {
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
                    msg = msg+"is deleted: "+comment.isDeleted()+"\n\n";
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
                String msg = "result: " + result.isSuccess() + "\n\n";
                if (result.isSuccess()) {
                    commentId = comment.getId();
                    msg = msg + "comment id: " + comment.getId() + "\n\n";
                    msg = msg + "message: " + comment.getMessage() + "\n\n";
                    msg = msg + "like count: " + comment.getLikeCount() + "\n\n";
                    msg = msg + "dislike count: " + comment.getDislikeCount() + "\n\n";
                    msg = msg + "reply count: " + comment.getReplyCount() + "\n\n";
                    msg = msg + "is deleted: " + comment.isDeleted() + "\n\n";
                    msg = msg + "image uris: " + comment.getImageUris() + "\n\n";
                    msg = msg + "create time: " + comment.getCreateTime() + "\n\n";
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
                String msg = "result: " + result.isSuccess();

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
                String msg = "result: " + result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void deleteComment(final Activity activity) {
        CommentSDK.deleteComment(commentId, new LotusySimpleCallback() {
            @Override
            public void callback(LotusyTaskResult result) {
                String msg = "result: " + result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void locationComments(final Activity activity) {
        LotusyLatLng latlng = new LotusyLatLng();
        latlng.setLat(49);
        latlng.setLng(-123);

        CommentSDK.getCommentsNearLocation(latlng, 200, true, 0, 10, new LotusyCommentLocationListCallback() {
            @Override
            public void callback(LotusyTaskResult result, Map<Double, LotusyComment> comments) {
                String msg = "result: " + result.isSuccess() + "\n";
                if (result.isSuccess()) {
                    for (Double distance : comments.keySet()) {
                        LotusyComment comment = comments.get(distance);
                        msg = msg + "\n===================== " + distance + "\n";
                        msg = msg + "comment id: " + comment.getId() + "\n";
                        msg = msg + "message: " + comment.getMessage() + "\n";
                        msg = msg + "like count: " + comment.getLikeCount() + "\n";
                        msg = msg + "dislike count: " + comment.getDislikeCount() + "\n";
                        msg = msg + "reply count: " + comment.getReplyCount() + "\n";
                        msg = msg + "is deleted: " + comment.isDeleted() + "\n";
                        msg = msg + "create time: " + comment.getCreateTime() + "\n";
                    }
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void userComments(final Activity activity) {
        CommentSDK.getUserComments(AccountApisController.current(), 0, 10, new LotusyCommentListCallback() {
            @Override
            public void callback(LotusyTaskResult result, List<LotusyComment> comments) {
                String msg = "result: " + result.isSuccess() + "\n";
                if (result.isSuccess()) {
                    for (LotusyComment comment : comments) {
                        msg = msg + "\n============================= \n";
                        msg = msg + "comment id: " + comment.getId() + "\n";
                        msg = msg + "message: " + comment.getMessage() + "\n";
                        msg = msg + "like count: " + comment.getLikeCount() + "\n";
                        msg = msg + "dislike count: " + comment.getDislikeCount() + "\n";
                        msg = msg + "reply count: " + comment.getReplyCount() + "\n";
                        msg = msg + "is deleted: " + comment.isDeleted() + "\n";
                        msg = msg + "create time: " + comment.getCreateTime() + "\n";
                    }
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void businessComments(final Activity activity) {
        CommentSDK.getBusinessComments(BusinessApisController.current(), 0, 10, new LotusyCommentListCallback() {
            @Override
            public void callback(LotusyTaskResult result, List<LotusyComment> comments) {
                String msg = "result: " + result.isSuccess() + "\n";
                if (result.isSuccess()) {
                    for (LotusyComment comment : comments) {
                        msg = msg + "\n============================= \n";
                        msg = msg + "comment id: " + comment.getId() + "\n";
                        msg = msg + "message: " + comment.getMessage() + "\n";
                        msg = msg + "like count: " + comment.getLikeCount() + "\n";
                        msg = msg + "dislike count: " + comment.getDislikeCount() + "\n";
                        msg = msg + "reply count: " + comment.getReplyCount() + "\n";
                        msg = msg + "is deleted: " + comment.isDeleted() + "\n";
                        msg = msg + "create time: " + comment.getCreateTime() + "\n";
                    }
                }

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }


    public static void createReply(final Activity activity) {
        String msg = "Test Reply Message " + Math.round(Math.random()*1000);
        CommentSDK.createReply(commentId, msg, new LotusyReplyCallback() {
            @Override
            public void callback(LotusyTaskResult result, LotusyReply reply) {
                String msg = "result: " + result.isSuccess() + "\n\n";
                if (result.isSuccess()) {
                    msg = msg+"reply id: "+reply.getId()+"\n\n";
                    msg = msg+"message: "+reply.getMessage()+"\n\n";
                    msg = msg+"nick name: "+reply.getNickName()+"\n\n";
                    msg = msg+"create time: "+reply.getCreateTime()+"\n\n";

                    Intent intent = new Intent(activity, ResultActivity.class);
                    intent.putExtra("result", msg);
                    activity.startActivity(intent);
                }
            }
        });
    }


    public static void commentReplies(final Activity activity) {
        CommentSDK.getCommentReplies(commentId, 0, 10, new LotusyReplyListCallback() {
            @Override
            public void callback(LotusyTaskResult result, List<LotusyReply> replies) {
                String msg = "result: " + result.isSuccess() + "\n";
                if (result.isSuccess()) {
                    for (LotusyReply reply : replies) {
                        msg = msg + "\n============================= \n";
                        msg = msg+"reply id: "+reply.getId()+"\n";
                        msg = msg+"message: "+reply.getMessage()+"\n";
                        msg = msg+"nick name: "+reply.getNickName()+"\n";
                        msg = msg+"create time: "+reply.getCreateTime()+"\n";
                    }

                    Intent intent = new Intent(activity, ResultActivity.class);
                    intent.putExtra("result", msg);
                    activity.startActivity(intent);
                }
            }
        });
    }
}
