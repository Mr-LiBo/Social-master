package libo.com.social.ui.Main.mall;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import libo.com.social.R;
import libo.com.social.entry.VideoItem;
import libo.com.social.http.NetUtil;
import libo.com.social.ui.Main.mall.adapter.VideoAdapter;


public class VideoFragment extends Fragment {
    private String weburl;
    private String channelName;

    private VideoAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_entertainment, null);
        RecyclerView recyclerView = rootView.findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new VideoAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        List<VideoItem> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            VideoItem videoItem = new VideoItem();
            videoItem.title = "XXX+_" + i;
            videoItem.url = "https://gss0.bdstatic.com/-4o3dSag_xI4khGkpoWK1HF6hhy/baike/crop%3D0%2C0%2C485%2C660%3Bh%3D436%3Bq%3D95/sign=c7f15e64d958ccbf0ff3ef7a24e8900e/472309f7905298220621ce15dfca7bcb0b46d4d2.jpg";
            list.add(videoItem);
        }
        adapter.setData(list);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.setData(NetUtil.getVideoItemList());
    }


    @Override
    public void setArguments(Bundle bundle) {
        // TODO Auto-generated method stub
        weburl = bundle.getString("weburl");
        channelName = bundle.getString("name");

    }


}
