package libo.com.social.http;

import com.google.gson.Gson;

import java.security.GeneralSecurityException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;

import libo.com.social.http.auth.AutuInterceptor;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class OkhttpSender {
    private final Gson goson;
    private OkHttpClient okHttpClient;

    public OkhttpSender() {
        this.goson = new Gson();
        getDefaultClient();
    }

    public void getDefaultClient() {
        X509TrustManager x509TrustManager = new X509TrustManager() {

            @Override
            public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                X509Certificate[] x509Certificates = new X509Certificate[0];
                return new X509Certificate[0];
            }
        };

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSL");

            sslContext.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        if (okHttpClient == null){
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.addNetworkInterceptor(new AutuInterceptor())
                    .connectTimeout(30L, TimeUnit.SECONDS)
                    .readTimeout(30L,TimeUnit.SECONDS)
                    .followRedirects(true)
                    .followRedirects(true)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String s, SSLSession sslSession) {
                            return true;
                        }
                    });
            if (x509TrustManager != null)
            {
                builder.sslSocketFactory(new SSLSocketFactoryCompat(x509TrustManager),x509TrustManager);
            }
            okHttpClient=  builder.build();
        }

    }


    public void send(String url, Object object, CommonCallback commonCallback) {
        String content = goson.toJson(object);

        StringRequestBody requestBody = new StringRequestBody(content);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(commonCallback);

    }


}
