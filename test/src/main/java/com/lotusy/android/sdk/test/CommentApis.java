package com.lotusy.android.sdk.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.lotusy.android.sdk.test.controller.CommentApisController;

public class CommentApis extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_apis);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.comment_apis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onCreateCommentClick(View view) {
        CommentApisController.createComment(this);
    }

    public void onGetCommentClick(View view) {
        CommentApisController.getCommentDetail(this);
    }

    public void onLikeCommentClick(View view) {
        CommentApisController.likeComment(this);
    }

    public void onDislikeCommentClick(View view) {
        CommentApisController.dislikeComment(this);
    }

    public void onDeleteCommentClick(View view) {
        CommentApisController.deleteComment(this);
    }

    public void onUserCommentClick(View view) {
        CommentApisController.userComments(this);
    }

    public void onBusinessCommentClick(View view) {
        CommentApisController.businessComments(this);
    }

    public void onLocationCommentClick(View view) {
        CommentApisController.locationComments(this);
    }

    public void onCreateReplyClick(View view) {
        CommentApisController.createReply(this);
    }

    public void onGetRepliesClick(View view) {
        CommentApisController.commentReplies(this);
    }
}
