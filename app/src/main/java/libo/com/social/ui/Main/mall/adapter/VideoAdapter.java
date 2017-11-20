package libo.com.social.ui.Main.mall.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import libo.com.social.R;
import libo.com.social.entry.VideoItem;

/**
 * @Author LiBo on 2017/11/17.
 * @Email libo205@qq.com
 * @Describe :
 */

public class VideoAdapter
        extends RecyclerView.Adapter<VideoAdapter.CusstomerViewHolder>
 {
    private Context context;

    public VideoAdapter(Context context) {
        this.context = context;
    }

    private List<VideoItem> videoItemList = new ArrayList<>();

    public void setData(List<VideoItem> videoItemList) {
        this.videoItemList = videoItemList;
        notifyDataSetChanged();
    }



    @Override
    public CusstomerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.itev_videos, parent, false);
        CusstomerViewHolder viewHolder = new CusstomerViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CusstomerViewHolder holder, int position) {
        for (int i = 0; i < videoItemList.size(); i++) {
            VideoItem item = videoItemList.get(position);
            holder.mTv.setText(item.title);
            Glide.with(context).load(item.img).into(holder.mIv);
        }
    }

    @Override
    public int getItemCount() {
        return videoItemList == null ? 0 : videoItemList.size();
    }

    public static class CusstomerViewHolder extends RecyclerView.ViewHolder {
        TextView mTv;
        ImageView mIv;
        public CusstomerViewHolder(View itemView) {
            super(itemView);
            mTv = itemView.findViewById(R.id.topic_init_title);
            mIv = itemView.findViewById(R.id.topic_init_img);
        }
    }
}
