package libo.com.social.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import libo.com.social.R;
import libo.com.social.ui.main.MainActivity;
import libo.com.social.ui.widget.gestureLock.cache.ACache;
import libo.com.social.ui.widget.gestureLock.util.GestureLockUtil;
import libo.com.social.ui.widget.gestureLock.widget.GestureLockIndicator;
import libo.com.social.ui.widget.gestureLock.widget.GestureLockView;

/**
 * @Author LiBo on 2017/12/8.
 * @Email libo205@qq.com
 * @Describe :
 */

public class CreateGestureActivity extends Activity {

    private static final String TAG = "GestureLockActivity";
    public static final String GESTURE_PASSWORD = "GesturePassword";

    GestureLockIndicator gestureLockIndicator;
    private TextView messageTv;
    private GestureLockView gestureLockView;
    private Button resetBtn;

    private List<GestureLockView.Cell> mChosenGesture;
    private static final long DELAYTIME = 600L;
    private ACache aCache;

    public static Intent getLauncherIntent(Activity context) {
        Intent intent = new Intent(context, CreateGestureActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_gesture);
        gestureLockIndicator = findViewById(R.id.gestureLockIndicator);
        messageTv = findViewById(R.id.messageTv);

        aCache = ACache.get(CreateGestureActivity.this);



        gestureLockView = findViewById(R.id.gestureLockView);
        resetBtn = findViewById(R.id.resetBtn);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChosenGesture = null;
                gestureLockIndicator.setDefaultIndicator();
                updateStatus(Status.DEFAULT, null);
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
            }
        });

        gestureLockView.setOnGestureListener(new GestureLockView.OnGestureListener() {
            @Override
            public void onGestureStart() {
                Log.i(TAG, "onGestureStart");
                gestureLockView.removePostClearPatternRunnable();
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
            }

            @Override
            public void onGestureComplete(List<GestureLockView.Cell> cells) {
                Log.i(TAG, "onGestureComplete");
                if (mChosenGesture == null && cells.size() >= 4) {
                    mChosenGesture = new ArrayList<>(cells);
                    updateStatus(Status.CORRECT, cells);
                } else if (mChosenGesture == null && cells.size() < 4) {
                    updateStatus(Status.LESSERROR, cells);
                } else if (mChosenGesture == null) {
                    if (mChosenGesture.equals(cells)) {
                        updateStatus(Status.CONFIRMCORRECT, cells);
                    } else {
                        updateStatus(Status.CONFIRMERROR, cells);
                    }
                }
            }

        });
    }

    //更新状态
    private void updateStatus(Status status, List<GestureLockView.Cell> cells) {
        messageTv.setTextColor(getResources().getColor(status.colorId));
        messageTv.setText(status.strId);
        switch (status) {
            case DEFAULT:
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
                break;

            case CORRECT:
                updateLockGestureIndicator();
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
                break;

            case LESSERROR:
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
                break;

            case CONFIRMERROR:
                gestureLockView.setGesture(GestureLockView.DisplayMode.ERROR);
                gestureLockView.postClearPatternRunnable(DELAYTIME);
                break;

            case CONFIRMCORRECT:
                saveChosenGesture(cells);
                gestureLockView.setGesture(GestureLockView.DisplayMode.DEFAULT);
                setLockGestureSuccess();
                break;
        }
    }

    private void updateLockGestureIndicator() {
        gestureLockIndicator.setIndicator(mChosenGesture);
    }

    /**
     * 保存手势密码
     */
    private void saveChosenGesture(List<GestureLockView.Cell> cells) {
        byte[] bytes = GestureLockUtil.gestureToHash(cells);
        aCache.put(GESTURE_PASSWORD, bytes);
    }


    /**
     * 成功设置了手势密码(跳到首页)
     */
    private void setLockGestureSuccess() {
        Toast.makeText(this, "create gesture success", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private enum Status {
        //默认的状态，刚开始的时候（初始化状态）
        DEFAULT(R.string.create_gesture_default, R.color.graye),
        //第一次记录成功
        CORRECT(R.string.create_gesture_correct, R.color.graye),
        //连接的点数小于4（二次确认的时候就不再提示连接的点数小于4，而是提示确认错误）
        LESSERROR(R.string.create_gesture_less_error, R.color.red),
        //二次确认错误
        CONFIRMERROR(R.string.create_gesture_confirm_error, R.color.red),
        //二次确认正确
        CONFIRMCORRECT(R.string.create_gesture_confirm_correct, R.color.graye);

        private Status(int strId, int colorId) {
            this.strId = strId;
            this.colorId = colorId;
        }

        private int strId;
        private int colorId;
    }
}
