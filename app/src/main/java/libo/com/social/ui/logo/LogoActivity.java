package libo.com.social.ui.logo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import libo.com.social.R;
import libo.com.social.ui.widget.CircularImageView;

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

        CircularImageView imageView = (CircularImageView)  findViewById(R.id.stick_img);
        imageView.setImageResource(R.drawable.guide1_1);
    }

    @Override
    public void onClick(View view) {

    }
}
