package com.lotusy.android.sdk.utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by pshen on 2014-07-19.
 */
public class LotusyProperties {

    private static Properties prop;

    private Properties init() {
        if (prop==null) {
            prop = new Properties();
            String propFileName = "lotusysdk.properties";
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            try {
                prop.load(inputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return prop;
    }

    public static String getHost(String service) {
        return prop.getProperty("host");
    }

    public static String getAppKey() {
        return prop.getProperty("appkey");
    }
}
