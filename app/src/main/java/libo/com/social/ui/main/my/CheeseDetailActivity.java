package libo.com.social.ui.main.my;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import libo.com.social.R;
import libo.com.social.ui.utils.XStatusBarHelper;

/**
 * @Author LiBo on 2017/12/6.
 * @Email libo205@qq.com
 * @Describe :
 */

public class CheeseDetailActivity extends AppCompatActivity {

    public static Intent getLauncherIntent(FragmentActivity context) {
        Intent intent = new Intent(context, CheeseDetailActivity.class);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_manager);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
        XStatusBarHelper.forceFitsSystemWindows(this);
        XStatusBarHelper.immersiveStatusBar(this);
        XStatusBarHelper.setHeightAndPadding(this, toolbar);

        NestedScrollView scrollView = (NestedScrollView) findViewById(R.id.nested_scrollview);
        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int color = getResources().getColor(R.color.colorPrimary);
                toolbar.setBackgroundColor(Color.argb(scrollY > 255 ? 255 : scrollY, Color.red(color), Color.green
                        (color), Color.blue(color)));
            }
        });

    }
}
