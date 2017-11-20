package libo.com.social.http.auth;

import android.util.Base64;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class AutuInterceptor implements Interceptor {
    public static final String APP_SECRET = "7e207930167d1447";
    public static final String APP_KEY = "dc661962bd4f4bb6b9c47f21decfa9a4";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String nonce = getNonce();
        String created = getCreated();

        String passwordDigedt = genetatePasswordDigest(nonce, created, APP_SECRET);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("UsernameToken Username").append("=\"").append(APP_KEY).append("\",")
                .append("PasswordDigest").append("=\"").append(passwordDigedt).append("\",")
                .append("Nonce").append("=\"").append(nonce).append("\",")
                .append("Created").append("=\"").append(created).append("\"");

        Request newRequest = request.newBuilder()
                .addHeader("Authorization", "WSSE realm=\"SDP\",profile=\"UsernameToken\",type=\"Appkey\"")
                .addHeader("X-WSSE", stringBuffer.toString())
                .build();

        Response response = chain.proceed(newRequest);
        return response;
    }

    private String genetatePasswordDigest(String nonce, String created, String appSecret) {
        String digest = null;
        String target = nonce + created + appSecret;
        byte[] bytes = new byte[1];
        try {
            bytes = Base64.encode(sha256(target), Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        digest = new String(bytes, Charset.forName("UTF-8"));
        return digest;
    }

    private String getCreated() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM-dd'T'HH:mm:ss'Z'");
        Calendar calendar = Calendar.getInstance();
        int offset = calendar.get(Calendar.ZONE_OFFSET);
        calendar.add(Calendar.MILLISECOND, -offset);
        return dateFormat.format(calendar.getTime());
    }

    private String getNonce() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static byte[] sha256(String target) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(target.getBytes());
        return messageDigest.digest();
    }
}
