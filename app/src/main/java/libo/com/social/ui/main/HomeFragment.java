package libo.com.social.ui.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import libo.com.social.R;
import libo.com.social.ui.widget.abilityMapView.AbilityBean;
import libo.com.social.ui.widget.abilityMapView.AbilityMapView;
import libo.com.social.ui.widget.MarqueeView;

/**
 * Created by liaodp on 2017/11/8.
 */

public class HomeFragment extends Fragment {

    AbilityMapView abilitymapview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        this.abilitymapview = (AbilityMapView) rootView.findViewById(R.id.ability_map_view);
        abilitymapview.setData(new AbilityBean(5, 70, 80, 70, 80, 80, 80));
        initMarqueeView(rootView);
        return rootView;
    }


    private void initMarqueeView(View rootView) {
        MarqueeView marqueeView = rootView.findViewById(R.id.marqueeview);
        final MarqueeView marqueeView1 = (MarqueeView) rootView.findViewById(R.id.marqueeView1);
        MarqueeView marqueeView3 = (MarqueeView) rootView.findViewById(R.id.marqueeView3);
        List<CharSequence> list = new ArrayList<>();
        SpannableString ss1 = new SpannableString("1、MarqueeView开源项目");
        ss1.setSpan(new ForegroundColorSpan(Color.RED), 2, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss1);
        SpannableString ss2 = new SpannableString("2、GitHub：sfsheng0322");
        ss2.setSpan(new ForegroundColorSpan(Color.GREEN), 9, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss2);
        SpannableString ss3 = new SpannableString("3、个人博客：sunfusheng.com");
        ss3.setSpan(new URLSpan("http://sunfusheng.com/"), 7, 21, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        list.add(ss3);
        list.add("4、新浪微博：@孙福生微博");

        marqueeView.startWithList(list);

        marqueeView.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getContext(), textView.getText() + "", Toast.LENGTH_SHORT).show();
            }
        });


        marqueeView1.startWithText(getString(R.string.marquee_texts), R.anim.anim_top_in, R.anim.anim_bottom_out);
        marqueeView1.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
            @Override
            public void onItemClick(int position, TextView textView) {
                Toast.makeText(getContext(), String.valueOf(marqueeView1.getPosition()) + ". " + textView.getText(), Toast.LENGTH_SHORT).show();
            }
        });

        marqueeView3.startWithList(list);
//        marqueeView3.startWithText(getString(R.string.marquee_texts));

    }


}
