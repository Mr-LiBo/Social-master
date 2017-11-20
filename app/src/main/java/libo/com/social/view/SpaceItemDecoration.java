package libo.com.social.view;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @Author LiBo on 2017/11/20.
 * @Email libo205@qq.com
 * @Describe :
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration  {
    private int top;
    private int left;
    private int right;
    private int bottom;

    public SpaceItemDecoration(int top, int left, int right, int bottom) {
        this.top = top;
        this.left = left;
        this.right = right;
        this.bottom = bottom;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
         outRect.left = left;
         outRect.top = top;
         outRect.right = right;
         outRect.bottom = bottom;
    }
}
