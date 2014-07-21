package com.lotusy.android.sdk.task;

import com.google.gson.JsonObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Map;

public class LotusyRestTransactionTask implements Runnable {

    private LotusyTaskParam param;
    private LotusyCallback callback;

    public LotusyRestTransactionTask(LotusyTaskParam param, LotusyCallback callback) {
        this.param = param;
        this.callback = callback;
    }

    @Override
    public void run() {

        HttpClient httpClient = new DefaultHttpClient();

        try {
            HttpRequestBase request = this.prepareRequest();
            HttpResponse httpResponse = httpClient.execute(request);
            int status = httpResponse.getStatusLine().getStatusCode();

            HttpEntity entity = httpResponse.getEntity();
            if( entity != null ) {
                InputStream instream = entity.getContent();
                String response = convertStreamToString(instream);
                instream.close();

                this.callback.parseResponse(response);
            }
        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (ClientProtocolException e) {
        } catch (IOException e) {}
    }


    private HttpRequestBase prepareRequest()
            throws FileNotFoundException, UnsupportedEncodingException {

        String url = this.param.getUri();

        HttpRequestBase request = null;
        AbstractHttpEntity entity = null;

        if (this.param.getMethod().equals("GET")) {
            request = new HttpGet(url);
        }
        else if (this.param.getMethod().equals("PUT")) {
            HttpPut put = new HttpPut( url );
            entity = this.getReuqestEntity();
            if( entity!=null ) {
                put.setEntity( entity );
            }
            request = put;
        }
        else if (this.param.getMethod().equals("POST")) {
            HttpPost post = new HttpPost( url );
            entity = this.getReuqestEntity();
            if( entity!=null ) {
                post.setEntity( entity );
            }
            request = post;
        }
        else if (this.param.getMethod().equals("DELETE")) {
            request = new HttpDelete( url );
        }

        this.prepareHeader(request);

        return request;
    }

    private AbstractHttpEntity getReuqestEntity()
            throws FileNotFoundException, UnsupportedEncodingException {
        AbstractHttpEntity entity = null;
        Object requestBody = null;

        requestBody = this.prepareStringRequestBody();
        if( requestBody != null ) {
            entity = new StringEntity( (String)requestBody );
            entity.setContentType("application/json");
        }
        else {
            requestBody = this.prepareFileRequestBody();
            if( requestBody != null ) {
                entity = new InputStreamEntity( this.prepareFileRequestBody(), -1 );
                entity.setContentType("binary/octet-stream");
            }
        }

        return entity;
    }

    private void prepareHeader(HttpRequestBase request) {
        Map<String, String> headers = this.param.getHeaders();

        for (String key : headers.keySet()) {
            request.addHeader(key, headers.get(key));
        }
    }

    private String prepareStringRequestBody() {
        String[] keys = {	"bid",
                "lat",
                "lng",
                "img",
                "msg"
        };

        JsonObject body = new JsonObject();
        Map<String, String> params = null;

        for (String key : keys) {
            String value = params.get(key);
            body.addProperty(key, (value!=null ? value : ""));
        }

        return body.toString();
    }

    protected FileInputStream prepareFileRequestBody() throws FileNotFoundException {
        FileInputStream atReturn = null;

        atReturn = (FileInputStream)this.param.getFile();

        return atReturn;
    }


    private static String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line = null;

        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
        } catch (IOException e) { }

        return sb.toString();
    }
}
