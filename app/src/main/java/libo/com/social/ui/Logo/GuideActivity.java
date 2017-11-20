package libo.com.social.ui.Logo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


import libo.com.social.R;
import libo.com.social.ui.Main.MainActivity;

/**
 * Created by liaodp on 2017/11/7.
 */

public class GuideActivity extends AppCompatActivity {

    private TextView tv_experience;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        Log.i("GuideActivity", "onCreate");
        initViewPager();

        tv_experience = findViewById(R.id.tv_experience);
        tv_experience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
            }
        });
    }


    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 3) {
                    tv_experience.setVisibility(View.VISIBLE);

                } else {
                    tv_experience.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        CustomPagerAdapter pagerAdapter = new CustomPagerAdapter();
        List<View> list = new ArrayList<>();
        View view = View.inflate(this, R.layout.activity_guide_normal, null);
        ImageView imageView = view.findViewById(R.id.iv_guide_bg);
        imageView.setImageResource(R.drawable.guide1_1);
        list.add(view);

        view = View.inflate(this, R.layout.activity_guide_normal, null);
        imageView = view.findViewById(R.id.iv_guide_bg);
        imageView.setImageResource(R.drawable.guide1_2);
        list.add(view);


        view = View.inflate(this, R.layout.activity_guide_normal, null);
        imageView = view.findViewById(R.id.iv_guide_bg);
        imageView.setImageResource(R.drawable.guide1_3);
        list.add(view);


        view = View.inflate(this, R.layout.activity_guide_normal, null);
        imageView = view.findViewById(R.id.iv_guide_bg);
        imageView.setImageResource(R.drawable.guide1_4);
        list.add(view);


        pagerAdapter.setViewList(list);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);

    }
}
