package com.lotusy.android.sdk.test.controller;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lotusy.android.sdk.test.AccountApis;
import com.lotusy.android.sdk.test.R;

/**
 * Created by indochino on 2014-07-25.
 */
public class MainActivityController {

    private static Activity myActivity = null;

    public static void init(final Activity activity) {
        if (myActivity==null) {
            myActivity = activity;

            TextView accountApi = (TextView) activity.findViewById(R.id.accountClick);
            accountApi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(myActivity, AccountApis.class);
                    myActivity.startActivity(intent);
                }
            });

            TextView businessApi = (TextView) activity.findViewById(R.id.businessClick);
            businessApi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(myActivity, "Business Api clicked", Toast.LENGTH_SHORT).show();
                }
            });

            TextView commentApi = (TextView) activity.findViewById(R.id.commentClick);
            commentApi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(myActivity, "Comment Api clicked", Toast.LENGTH_SHORT).show();
                }
            });

            TextView imageApi = (TextView) activity.findViewById(R.id.imageClick);
            imageApi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Toast.makeText(myActivity, "Image Api clicked", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
