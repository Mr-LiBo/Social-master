package libo.com.social.utils;

import android.content.Context;
import android.graphics.Bitmap;

import libo.com.social.config.SharedFileKey;

/**
 * @Author LiBo on 2017/12/11.
 * @Email libo205@qq.com
 * @Describe :
 */

public class LocalConfigUtil {


    public static boolean getFirstJoinState(Context context)
    {
        return ConfigUtil.LocalConfigUtil.getBoolean(context, SharedFileKey.IS_FIRST_JOIN, false);
    }

    public static void setFirstJoinState(Context context, boolean state)
    {
        ConfigUtil.LocalConfigUtil.putBoolean(context,  SharedFileKey.IS_FIRST_JOIN, state);
    }

    public static boolean getCreateGestureLockState(Context context){
        return ConfigUtil.LocalConfigUtil.getBoolean(context,SharedFileKey.IS_CREATE_GESTURELOCK,false);
    }

    public static void setCreateGestureLockState(Context context, boolean state)
    {
        ConfigUtil.LocalConfigUtil.putBoolean(context,  SharedFileKey.IS_CREATE_GESTURELOCK, state);
    }
}
