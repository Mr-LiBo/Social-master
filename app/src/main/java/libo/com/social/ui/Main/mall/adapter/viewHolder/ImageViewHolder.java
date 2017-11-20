package libo.com.social.ui.Main.mall.adapter.viewHolder;

import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import libo.com.social.R;


/**
 * Created by linlongxin on 2016/8/23.
 */

public class ImageViewHolder extends BaseViewHolder<String> {

    private ImageView mImage;

    public ImageViewHolder(ViewGroup parent) {
        super(parent, R.layout.holder_image);
    }

    @Override
    public void onInitializeView() {
        super.onInitializeView();
        mImage = findViewById(R.id.image);
    }

    @Override
    public void setData(String object) {
        super.setData(object);
        Glide.with(itemView.getContext())
                .load(object)
                .into(mImage);
    }

    @Override
    public void onItemViewClick(String object) {
        super.onItemViewClick(object);
        Log.i("ImageViewHolder","onItemViewClick");
    }
}
