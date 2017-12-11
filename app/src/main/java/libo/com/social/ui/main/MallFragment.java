package libo.com.social.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

import libo.com.social.R;
import libo.com.social.http.NetUtil;
import libo.com.social.ui.main.mall.MultiTypeFragment;
//import libo.com.social.ui.Main.mall.VideoFragment;
import libo.com.social.ui.main.mall.SportsFragment;
import libo.com.social.ui.main.mall.VideoFragment;
import libo.com.social.ui.main.mall.adapter.PageFragmentAdapter;

import libo.com.social.ui.main.mall.NewsFragment;
import libo.com.social.ui.main.mall.WaterFragment;

/**
 * Created by liaodp on 2017/11/8.
 */

public class MallFragment extends Fragment {

    private HorizontalScrollView horizontalScrollView;
    private RadioGroup radioGroup;

    private ViewPager viewPager;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NetUtil.getData();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_main2, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View rootView) {
        radioGroup = rootView.findViewById(R.id.rgChannel);
        viewPager = rootView.findViewById(R.id.vpNewsList);
        horizontalScrollView = rootView.findViewById(R.id.hvChannel);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                   viewPager.setCurrentItem(checkedId);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                RadioButton rb = (RadioButton) radioGroup.getChildAt(position);
                rb.setChecked(true);
                int left = rb.getLeft();
                int width = rb.getMeasuredWidth();
                DisplayMetrics metrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
                int screenWidth = metrics.widthPixels;
                int len = left + width /2 - screenWidth /2;
                horizontalScrollView.smoothScrollTo(len,0);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        initChannelData();
        initTab();
        initViewPager();
        radioGroup.check(0);
    }

    private void initViewPager() {
        List<Fragment> fragmentList = new ArrayList<Fragment>();

        WaterFragment waterFragment = new WaterFragment();
        Bundle bundle1 = new Bundle();
        bundle1.putString("weburl",channelList.get(0).weburl);
        bundle1.putString("name",channelList.get(0).name);
        waterFragment.setArguments(bundle1);
        fragmentList.add(waterFragment);

        MultiTypeFragment multiTypeFragment = new MultiTypeFragment();
        fragmentList.add(multiTypeFragment);

        SportsFragment sportsFragment = new SportsFragment();
        fragmentList.add(sportsFragment);

        for (int i=0;i<channelList.size()-3;i++)
        {
            if (i== 5){
                VideoFragment entertainmentFragment = new VideoFragment();
                Bundle bl = new Bundle();
                bl.putString("weburl",channelList.get(0).weburl);
                bl.putString("name",channelList.get(0).name);
                entertainmentFragment.setArguments(bl);
                fragmentList.add(entertainmentFragment);
                continue;
            }
            NewsFragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("weburl",channelList.get(i).weburl);
            bundle.putString("name",channelList.get(i).name);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }
        PageFragmentAdapter adapter = new PageFragmentAdapter(getActivity().getSupportFragmentManager(),fragmentList);
        viewPager.setAdapter(adapter);
    }

    private void initTab() {
        for (int i =0;i<channelList.size();i++)
        {
            RadioButton radioButton = (RadioButton) LayoutInflater.from(getActivity()).inflate(R.layout.tab_rb,null);
            radioButton.setId(i);
            radioButton.setText(channelList.get(i).name);
            RadioGroup.LayoutParams params = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,RadioGroup.LayoutParams.WRAP_CONTENT);
            radioGroup.addView(radioButton,params);
        }
    }
    List<Channel> channelList = new ArrayList<>();
    private  void initChannelData() {
        channelList.add(new Channel("1","头条",0,"www.baidu.com","baidu"));
        channelList.add(new Channel("","娱乐",0,"",""));
        channelList.add(new Channel("","体育",0,"",""));
        channelList.add(new Channel("","财经",0,"",""));
        channelList.add(new Channel("","热点",0,"",""));
        channelList.add(new Channel("","科技",0,"",""));
        channelList.add(new Channel("","图片",0,"",""));
        channelList.add(new Channel("","汽车",0,"",""));
        channelList.add(new Channel("","时尚",0,"",""));

    }

    public class Channel
    {
        private String id;
        private String name;
        private int order;
        private String weburl;
        private String hweburl;

        public Channel(String id, String name, int order, String weburl, String hweburl) {
            this.id = id;
            this.name = name;
            this.order = order;
            this.weburl = weburl;
            this.hweburl = hweburl;
        }
    }
}
