package libo.com.social.http;

import java.net.URI;

import libo.com.social.http.config.Url;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class ApiService {
    private static ApiService apiService;
    private final OkhttpSender okhttpSender;

    public ApiService() {
        okhttpSender = new OkhttpSender();
    }

    public static ApiService getInstance() {
        if (apiService == null) {
            apiService = new ApiService();
        }
        return apiService;
    }

    public void getData(Object object, CommonCallback commonCallback) {
        String url = Url.URL_Query;
        okhttpSender.send(url,object,commonCallback);
    }
}
