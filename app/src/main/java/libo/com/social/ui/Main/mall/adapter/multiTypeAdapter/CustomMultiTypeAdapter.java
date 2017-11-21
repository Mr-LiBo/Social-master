package libo.com.social.ui.Main.mall.adapter.multiTypeAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import libo.com.social.ui.Main.mall.adapter.multiTypeAdapter.viewHolder.BaseViewHolder;


/**
 * @Author LiBo on 2017/11/20.
 * @Email libo205@qq.com
 * @Describe :
 */
public class CustomMultiTypeAdapter extends RecyclerAdapter {
    int mViewCount = 0;

    private List<Object> mViewsData;
    private ViewTypeManager mViewHolderManager;

    public CustomMultiTypeAdapter(Context context) {
        super(context);
        this.mViewsData = new ArrayList<>();
        mViewHolderManager = new ViewTypeManager();
    }

    public void setViewHolderFactory(IViewHolderFactory factory){
        if (factory != null){
            mViewHolderManager.setViewHolderFactory(factory);
        }
    }


    public <T> void addAll(List<T> data,int viewType){
        mViewsData.addAll(data);
        int count= mViewsData.size();
        int size = data.size();
        for (int i=0;i<size;i++){
            mViewHolderManager.putViewType(mViewCount ,viewType);
            mViewCount++;
        }
        notifyDataSetChanged();
    }


    /***
     *  创建一个新的 ViewHolder 并初始化一些私有字段，以供回收视图使用。
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return mViewHolderManager.getViewHolder(parent,viewType);
    }

    /**
     * 给ViewHOlder 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindBaseViewHolder(BaseViewHolder holder, int position) {
        if (position  >0) {
            holder.setData(mViewsData.get(position));
        }
    }

    /**
     *获取指定视图类型
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return mViewHolderManager.getViewType(position);
    }

    @Override
    public int getItemCount() {
        return mViewsData.size();
    }




}
