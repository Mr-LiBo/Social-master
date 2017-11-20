package libo.com.social.http;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import libo.com.social.entry.VideoItem;
import libo.com.social.http.response.Query;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * @Author LiBo on 2017/11/16.
 * @Email libo205@qq.com
 * @Describe :
 */

public class NetUtil {
    private static List<VideoItem> videoItemList;




    public static List<VideoItem> getVideoItemList() {
        return videoItemList;
    }

    public static void getData() {
        ApiService.getInstance().getData(null, new CommonCallback<Query>() {
            @Override
            public void onSuccess(Query query, String id) {
                if (query != null && query.content != null) {
                    videoItemList = query.content;

                }
            }

            @Override
            public void onFail(Call call, Exception e, String id) {
                Log.i(TAG, "onSuccess object:" + e.toString());
            }

        });
    }


    public static void OkHttpGet(String httpUtl) {
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(httpUtl).build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (request == null) {

                }

                try {
                    JSONObject object = new JSONObject(request.body().toString());
                    Log.i(TAG, "object:" + object.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                JSONObject obj = new JSONObject(s);
//                String content = obj.getString("content");
//                List<VideoItem> videoItems = JSON.parseArray(content, VideoItem.class);
            }
        });


    }


}
