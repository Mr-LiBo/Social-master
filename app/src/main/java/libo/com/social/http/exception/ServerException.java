package libo.com.social.http.exception;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class ServerException extends Exception {
    public String code;
    public String desc;

    public ServerException() {

    }

    public ServerException(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ServerException{" +
                "\"code\":\"" + code + "\"" +
                ", \"desc\":\"" + desc + "\"" +
                "}";
    }
}