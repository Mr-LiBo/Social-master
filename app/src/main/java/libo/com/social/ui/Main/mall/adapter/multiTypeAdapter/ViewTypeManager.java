package libo.com.social.ui.Main.mall.adapter.multiTypeAdapter;

import android.util.SparseIntArray;
import android.view.ViewGroup;

import libo.com.social.ui.Main.mall.adapter.multiTypeAdapter.viewHolder.BaseViewHolder;

/**
 * @Author LiBo on 2017/11/20.
 * @Email libo205@qq.com
 * @Describe :
 */

class ViewTypeManager {
    private final String TAG = "ViewTypeManager";
    // position to Type
    private SparseIntArray mPositionToTypeMap;  //position --> ViewType

    private IViewHolderFactory mViewHolderFactory;

    public ViewTypeManager() {
        mPositionToTypeMap = new SparseIntArray();
    }

    protected int getViewType(int position) {
        return this.mPositionToTypeMap.get(position);
    }

    protected void putViewType(int position, int viewType) {
        mPositionToTypeMap.put(position, viewType);
    }

    public void setViewHolderFactory(IViewHolderFactory factory){
        this.mViewHolderFactory = factory;
    }

    protected <T extends BaseViewHolder> T getViewHolder(ViewGroup parent, int viewType){
        if (mViewHolderFactory != null) {
            return mViewHolderFactory.getViewHolder(parent, viewType);
        } else {
            return null;
        }
    }



}
