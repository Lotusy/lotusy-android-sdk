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

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean hasError() {
        return this.errors.size()>0;
    }

    public void addError(String error) {
        this.errors.add(error);
    }

    public List<String> getErrors() {
        return this.errors;
    }

    private static LotusyTaskResult noAuthResult;

    public static LotusyTaskResult getNoAuthResult() {
        if (noAuthResult==null) {
            noAuthResult = new LotusyTaskResult();
            noAuthResult.setSuccess(false);
            noAuthResult.setStatusCode(401);
            noAuthResult.addError("unauthorized");
        }

        return noAuthResult;
    }
}
