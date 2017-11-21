package libo.com.social.ui.Main.mall.adapter.multiTypeAdapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import libo.com.social.R;
import libo.com.social.ui.Main.mall.adapter.multiTypeAdapter.viewHolder.BaseViewHolder;


/**
 * Created by linlongxin on 2015/12/19.
 */
public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {


    int mViewCount = 0;
    private List<T> mData = new ArrayList<>();

    protected View mStatusView;
    protected LinearLayout mLoadMoreView;
    public TextView mNoMoreView;

    private Context mContext;

    public RecyclerAdapter(Context context) {
        this.mContext = context;
        initStatusView(context);
    }


    private void initStatusView(Context context) {
        mStatusView = LayoutInflater.from(context).inflate(R.layout.view_status_last, null);
        mStatusView.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mLoadMoreView = (LinearLayout) mStatusView.findViewById(R.id.load_more_view);
        mNoMoreView = (TextView) mStatusView.findViewById(R.id.no_more_view);
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
        if (holder == null || position < 0) {
            return;
        }
        int dataSize = mData.size();
        if (position == 0) {
           if (dataSize > 0) {
                holder.setData(mData.get(0));
            }
        }
        // 最后一个可见的 item 时 加载更多。解决 remove 时 bug
            setViewVisible(mLoadMoreView, true);
    }

    /**
     * ViewHolder 更新 Item 的位置选择 ViewType , 和 UI 是同步的
     */
    @Override
    public int getItemViewType(int position) {

        return super.getItemViewType(position);
    }

    /**
     * 包含了 Header , Footer , 状态显示 Item
     */
    @Override
    public int getItemCount() {
        return mViewCount;
    }





    public void add(T object) {
        if (   object != null) {
            mData.add(object);
            mViewCount++;
            notifyDataSetChanged();
        }
    }

    public List<T> getData() {
        return mData;
    }

    protected void setViewVisible(View view, boolean visibile){
        if (view != null) {
            view.setVisibility(visibile ? View.VISIBLE : View.GONE);
        }
    }
}
