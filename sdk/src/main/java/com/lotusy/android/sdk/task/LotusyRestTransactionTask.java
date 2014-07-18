package com.lotusy.android.sdk.task;

public class LotusyRestTransactionTask implements Runnable {

    private LotusyTaskParam param;
    private LotusyCallback callback;

    public LotusyRestTransactionTask(LotusyTaskParam param, LotusyCallback callback) {
        this.param = param;
        this.callback = callback;
    }

    @Override
    public void run() {

        Object response = null;

        this.callback.doCallback(response);
    }
}
