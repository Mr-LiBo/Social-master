package libo.com.social.ui.Main.mall;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import libo.com.social.R;
import libo.com.social.entry.Consumption;
import libo.com.social.entry.TextImage;
import libo.com.social.ui.Main.mall.adapter.viewHolder.BaseViewHolder;
import libo.com.social.ui.Main.mall.adapter.CustomMultiTypeAdapter;
import libo.com.social.ui.Main.mall.adapter.IViewHolderFactory;
import libo.com.social.ui.Main.mall.adapter.viewHolder.CardRecordHolder;
import libo.com.social.ui.Main.mall.adapter.viewHolder.ImageViewHolder;
import libo.com.social.ui.Main.mall.adapter.viewHolder.TextImageViewHolder;
import libo.com.social.ui.Main.mall.adapter.viewHolder.TextViewHolder;


public class MultiTypeFragment extends Fragment implements IViewHolderFactory {
    private RecyclerView mRecyclerView;
    private CustomMultiTypeAdapter mAdapter;
    private int mPage = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_multi_type, null);


        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new CustomMultiTypeAdapter(getContext());
        mAdapter.setViewHolderFactory(this);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.add(getImageVirtualData(), VIEW_TYPE_IAMGE);
        mAdapter.addAll(getTextVirtualData(), VIEW_TYPE_TEXT);
        mAdapter.addAll(getTextImageVirualData(), VIEW_TYPE_TEXT_IMAGE);
        mAdapter.addAll(getRecordVirtualData(), VIEW_TYPE_CARD);
        mAdapter.showNoMore();
        return rootView;
    }


    public String getImageVirtualData() {
        return "http://i03.pictn.sogoucdn.com/3c28af542f2d49f7-fe9c78d2ff4ac332-73d7732e20e2fcfaa954979d623bcbe9_qq";
    }

    public String[] getTextVirtualData() {
        return new String[]{
                "算机相关知识科普博客还有他",
                "技术职级规律越来越摸"
        };
    }

    public TextImage[] getTextImageVirualData() {
        return new TextImage[]{
                new TextImage("http://i03.pictn.sogoucdn.com/3c28af542f2d49f7-9e7c5d699eaea93e-3f7e1bcc57cbe10e050bf58559b0d5ae_qq", "小猫咪"),
                new TextImage("http://i03.pictn.sogoucdn.com/3c28af542f2d49f7-8f0182a4cf50287e-ba43c5aef499c64e6a3297c5c500c7dc_qq", "那些年，我还是帅哥"),
                new TextImage("http://img02.sogoucdn.com/app/a/100520093/803d8006b5d521bb-2eb356b9e8bc4ae6-0725fb0ad48d8b3a32f08eb150380bba.jpg", "看片。。。")
        };
    }

    public Consumption[] getRecordVirtualData() {
        return new Consumption[]{
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼"),
                new Consumption("Demo", "2015-12-18 12:09", "消费", 9.7f, 24.19f, "兴业源三楼")
        };
    }

    private final int VIEW_TYPE_TEXT = 128 << 1;
    private final int VIEW_TYPE_IAMGE = 128 << 2;
    private final int VIEW_TYPE_TEXT_IMAGE = 128 << 3;
    private final int VIEW_TYPE_CARD = 128 << 4;


    @Override
    public <V extends BaseViewHolder> V getViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_TEXT:
                return (V) new TextViewHolder(parent);
            case VIEW_TYPE_IAMGE:
                return (V) new ImageViewHolder(parent);
            case VIEW_TYPE_TEXT_IMAGE:
                return (V) new TextImageViewHolder(parent);
            case VIEW_TYPE_CARD:
                return (V) new CardRecordHolder(parent);
            default:
                return (V) new TextViewHolder(parent);
        }
    }
}
