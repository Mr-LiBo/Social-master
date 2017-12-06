package libo.com.social.ui.Main.my;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import libo.com.social.R;
import libo.com.social.ui.view.MarqueeText;

/**
 * Created by liaodp on 2017/11/15.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    public static Intent getLauncherIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initMarqueeTextView();
    }
    private MarqueeText marqueeText_left;

    private void initMarqueeTextView() {
        marqueeText_left = findViewById(R.id.marqueeview_left);
        findViewById(R.id.start_btn).setOnClickListener(this);
        findViewById(R.id.pause_btn).setOnClickListener(this);
        findViewById(R.id.restart_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.start_btn:
                marqueeText_left.startScroll();
                break;
            case R.id.pause_btn:
                marqueeText_left.pauseScroll();
                break;
            case R.id.restart_btn:
                marqueeText_left.restartScroll();
                break;
        }
    }
}
