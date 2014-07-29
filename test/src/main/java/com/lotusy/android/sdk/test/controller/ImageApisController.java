package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;

import com.lotusy.android.sdk.ImageSDK;
import com.lotusy.android.sdk.domain.LotusySimpleCallback;
import com.lotusy.android.sdk.task.LotusyTaskResult;
import com.lotusy.android.sdk.test.ResultActivity;

import java.io.InputStream;

/**
 * Created by pshen on 2014-07-28.
 */
public class ImageApisController {

    public static void uploadImage(final Activity activity, InputStream image) {
        ImageSDK.uploadCommentImage(CommentApisController.current(), image, new LotusySimpleCallback() {
            @Override
            public void callback(LotusyTaskResult result) {
                String msg = "result: " + result.isSuccess();

                Intent intent = new Intent(activity, ResultActivity.class);
                intent.putExtra("result", msg);
                activity.startActivity(intent);
            }
        });
    }
}
