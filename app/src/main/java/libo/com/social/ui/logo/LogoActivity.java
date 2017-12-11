package libo.com.social.ui.logo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import libo.com.social.R;
import libo.com.social.ui.login.GestureLoginActivity;
import libo.com.social.ui.login.LoginActivity;
import libo.com.social.ui.main.MainActivity;
import libo.com.social.ui.widget.CircularImageView;
import libo.com.social.utils.LocalConfigUtil;


/**
 * Created by liaodp on 2017/11/7.
 */

public class LogoActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean isFirstJoin = LocalConfigUtil.getFirstJoinState(LogoActivity.this);
                if (isFirstJoin) {
                    LocalConfigUtil.setFirstJoinState(LogoActivity.this,false);
                    startActivity(GuideActivity.getLauncherIntent(LogoActivity.this));
                } else {
                    boolean isCreateGestureLock = LocalConfigUtil.getCreateGestureLockState(LogoActivity.this);
                    if (isCreateGestureLock) {
                        startActivity(GestureLoginActivity.getLauncherIntent(LogoActivity.this));
                    } else {
                        startActivity(MainActivity.getLauncherIntent(LogoActivity.this));
                    }
                }
            }
        }, 1000);

        CircularImageView imageView = (CircularImageView) findViewById(R.id.stick_img);
        imageView.setImageResource(R.drawable.guide1_1);
    }

    @Override
    public void onClick(View view) {

    }
}
