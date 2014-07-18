package com.lotusy.android.sdk.task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pshen on 2014-07-17.
 */
public class LotusyTaskResult {

    protected boolean success;
    protected int statusCode;
    protected List<String> errors;

    public LotusyTaskResult() {
        this.errors = new ArrayList<String>();
    }

    public boolean isSuccess() {
        return this.success;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean hasError() {
        return this.errors.size()>0;
    }

    public List<String> getErrors() {
        return this.errors;
    }
}
