package libo.com.social.ui.Logo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import libo.com.social.R;
import libo.com.social.ui.utils.Code;

/**
 * Created by liaodp on 2017/11/7.
 */

public class LogoActivity extends AppCompatActivity  implements View.OnClickListener {


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
                startActivity(new Intent(LogoActivity.this,GuideActivity.class));
            }
        }, 1000);

        String tempCode = "1234";
//        ImageView ivCode = findViewById(R.id.iv_code);
//        ivCode.setImageBitmap(Code.getInstance().createBitmap(tempCode) );
    }

    @Override
    public void onClick(View view) {

    }
}
