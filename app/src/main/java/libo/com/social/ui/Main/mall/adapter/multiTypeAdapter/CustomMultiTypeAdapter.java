package libo.com.social.ui.Main.mall.adapter.multiTypeAdapter;

import android.content.Context;
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

    /***
     *  创建一个新的 ViewHolder 并初始化一些私有字段，以供回收视图使用。
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            return mViewHolderManager.getViewHolder(parent,viewType);

    }

    @Override
    public BaseViewHolder onCreateBaseViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 给ViewHOlder 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (position != 0 && mViewCount != 1) {
            Log.i("TAG","onBindViewHolder :"+position+"  mViewCount:"+mViewCount);
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


    public <T> void add(T data,int viewType){
        if ( data == null){
            return;
        }
        mViewsData.add(data);
        mViewHolderManager.putViewType(mViewCount -1,viewType);// mViewCount从1开始
        int positionStart = mViewCount -1;
        mViewCount ++;
        notifyItemRangeChanged(positionStart,1);
    }

    public <T> void addAll(T[] data,int viewType){
        addAll(Arrays.asList(data),viewType);
    }

    public <T> void addAll(List<T> data,int viewType){
        if ( data == null || data.size() ==0){
            return;
        }
        int size = data.size();
        mViewsData.addAll(data);
        int positionStart = mViewCount -1;
        for (int i=0;i<size;i++){
            mViewHolderManager.putViewType(mViewCount - 1,viewType);
            mViewCount++;
        }
        notifyItemRangeInserted(positionStart,size);
    }

}
