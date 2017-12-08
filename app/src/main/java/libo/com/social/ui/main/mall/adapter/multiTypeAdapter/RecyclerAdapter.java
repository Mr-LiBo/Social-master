package libo.com.social.ui.main.mall.adapter.multiTypeAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import libo.com.social.ui.main.mall.adapter.multiTypeAdapter.viewHolder.BaseViewHolder;


/**
 * Created by linlongxin on 2015/12/19.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private Context mContext;

    public RecyclerAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return onCreateBaseViewHolder(parent, viewType);
    }

    public abstract BaseViewHolder<T> onCreateBaseViewHolder(ViewGroup parent, int viewType);


    /* ViewHolder 绑定数据，这里的 position 和 getItemViewType() 方法的 position 不一样
        这里的 position 指当前可见的 item 的 position 的位置。
        注意 ：每个 ViewHolder 绑定数据时值调用此方法一次
     */
    @Override
    public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        onBindBaseViewHolder(holder,position);
    }
    public abstract void onBindBaseViewHolder(BaseViewHolder<T> holde, int position);
    /**
     * ViewHolder 更新 Item 的位置选择 ViewType , 和 UI 是同步的
     */
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }





}
