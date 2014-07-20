package com.lotusy.android.sdk.task;

import com.lotusy.android.sdk.LotusySDK;

import java.io.InputStream;
import java.util.Map;

/**
 * Created by pshen on 2014-07-17.
 */
public class LotusyTaskParam {

    private String uri;
    private String method;
    private Map<String, String> headers;
    private String body;
    private InputStream file;

    public LotusyTaskParam() {
        this.headers =  LotusySDK.getDefaultHeaders();
    }

    public String getUri() {
        return this.uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void addHeader(String key, String value) {
        this.headers.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }
}
