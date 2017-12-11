package libo.com.social.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import libo.com.social.R;
import libo.com.social.config.UserInfo;
import libo.com.social.ui.main.MainActivity;
import libo.com.social.ui.widget.gestureLock.cache.ACache;
import libo.com.social.ui.widget.gestureLock.util.GestureLockUtil;
import libo.com.social.ui.widget.gestureLock.widget.GestureLockView;

public class GestureLoginActivity extends Activity {

    private static final long DELAYTIME = 1001;
    Button btnForgetGesture;
    GestureLockView gestureLockView;
    TextView tvMessage;

    private ACache aCache;
    private byte[] getturePassword;

    public static Intent getLauncherIntent(Activity context) {
        Intent intent = new Intent(context, GestureLoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_login);

        initView();
    }

    private void initView() {
        btnForgetGesture = findViewById(R.id.gesture_login_forget_gesture_btn);
        gestureLockView =findViewById(R.id.gesture_login_gesturelockview);
        gestureLockView.setOnGestureListener(new GestureLockView.OnGestureListener() {
            @Override
            public void onGestureStart() {
                gestureLockView.removePostClearPatternRunnable();
            }

            @Override
            public void onGestureComplete(List<GestureLockView.Cell> cells) {
                if (cells != null){
                    if (GestureLockUtil.checkGesture(cells,getturePassword)){
                        updateStatus(Status.CORRECT);
                    }else {
                        updateStatus(Status.ERROR);
                    }
                }
            }
        });
        tvMessage=findViewById(R.id.gesture_login_mesage_tv);

        aCache = ACache.get(GestureLoginActivity.this);
        getturePassword = aCache.getAsBinary(UserInfo.GESTURE_PASSWORD);
        updateStatus(Status.DEFAULT);
    }

    /**
     * 更新状态
     * @param status
     */
    private void updateStatus(Status status) {
        tvMessage.setText(status.strId);
        tvMessage.setTextColor(getResources().getColor(status.colorId));
        switch (status){
            case DEFAULT:
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
            break;
            case ERROR:
                gestureLockView.setGesture(GestureLockView.DisplayMode.ERROR);
                gestureLockView.postClearPatternRunnable(DELAYTIME);
                break;
            case CORRECT:
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
                loginGestureSuccess();
                break;
        }
    }

    private void loginGestureSuccess() {
        Toast.makeText(GestureLoginActivity.this,"登录成功",Toast.LENGTH_SHORT);
        startActivity(MainActivity.getLauncherIntent(GestureLoginActivity.this));
    }


    private enum Status {
        //默认的状态
        DEFAULT(R.string.gesture_login_default, R.color.black),
        //密码输入错误 gesture_login_error
        ERROR(R.string.gesture_login_error, R.color.red),
        //密码输入正确
        CORRECT(R.string.gesture_login_correct, R.color.graye);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }
        private int strId;
        private int colorId;
    }
}
