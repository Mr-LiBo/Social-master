package libo.com.social.http;


import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.$Gson$Types;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import libo.com.social.entry.VideoItem;
import libo.com.social.http.exception.HttpException;
import libo.com.social.http.exception.ServerException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public abstract class CommonCallback<T> implements Callback {
    private static final String TAG = "CommonCallback";

    private static final String REQUSET_SUCCESS = "0";
    private Gson gson;

    public CommonCallback() {
        gson = new GsonBuilder().serializeNulls().create();
    }

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter");
        }
        ParameterizedType parameterizedType = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterizedType.getActualTypeArguments()[0]);
    }

    @Override
    public void onFailure(Call call, IOException e) {
        Log.e(TAG, "onFailure:" + e);
        onFail(call, e, "");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        if (response == null || response.body() == null) {
            onFail(call, new Exception("response boby is null"), "");
            return;
        }

        if (response.code() != 200) {
            HttpException httpException = new HttpException();
            httpException.httpCode = response.code();
            onFail(call, httpException, "");
        } else {
            String strResponse = response.body().string();
            String code = null;
            String desc = null;
            String content = null;
            try {
                JSONObject object = new JSONObject(strResponse);
                code = object.getString("status");
                desc=object.getString("message");
                content = object.getString("content");
                List<VideoItem> videoItems = JSON.parseArray(content, VideoItem.class);

//                StringReader stringReader = new StringReader(strResponse);
//                JsonReader jsonReader = gson.newJsonReader(stringReader);
//                jsonReader.beginObject();
//                while (jsonReader.hasNext()) {
//                    String name = jsonReader.nextName();
//                    if (name == null || name.equals("")) {
//                        jsonReader.skipValue();
//                    }
//                    if ("status".equals(name)) {
//                        code = jsonReader.nextString();
//                    } else if ("message".equals(name)) {
//                        desc = jsonReader.nextString();
//                    }  else {
//                        jsonReader.skipValue();
//                    }
//                }
//                jsonReader.endObject();
//                jsonReader.close();
            } catch (Exception e) {
                onFail(call, e, "");
            }
            T t = null;
            if (REQUSET_SUCCESS.equals(code)) {
                try {
                    Type type = getSuperclassTypeParameter(this.getClass());
                    t = gson.fromJson(strResponse, type);
                } catch (Exception e) {
                    onFail(call, e, "");
                }
                if (t != null) {
                    onSuccess(t, "");
                } else {
                    onFail(call, new Exception("gson transfer exception"), "");
                }
            } else {
                if (code == "null") {
                    code = "";
                }
                ServerException serverException = new ServerException(code, desc);
                onFail(call, serverException, "");
            }
        }
    }

    public abstract void onSuccess(T t, String id);

    public abstract void onFail(Call call, Exception e, String id);
}
