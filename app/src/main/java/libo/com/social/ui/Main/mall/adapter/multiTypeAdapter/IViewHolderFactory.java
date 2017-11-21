package libo.com.social.ui.Main.mall.adapter.multiTypeAdapter;

import android.view.ViewGroup;

import libo.com.social.ui.Main.mall.adapter.multiTypeAdapter.viewHolder.BaseViewHolder;

/**
 * 为避免反射，ViewType 交给开发者自己管理
 *
 * Created by linlongxin on 2017/9/20.
 */

public interface IViewHolderFactory {

    <V extends BaseViewHolder> V getViewHolder(ViewGroup parent, int viewType);
}
