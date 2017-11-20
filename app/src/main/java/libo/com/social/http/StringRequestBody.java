package libo.com.social.http;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.BufferedSink;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class StringRequestBody extends RequestBody {
    String content;

    public StringRequestBody(String content) {
        this.content = content;
    }

    @Override
    public MediaType contentType() {
        return MediaType.parse("application/json;charset=UTF-8");
    }

    @Override
    public void writeTo(BufferedSink bufferedSink) throws IOException {
        bufferedSink.write(content.getBytes(Charset.forName("UTF-8")));
    }

    public String getContent() {
        return content;
    }
}
