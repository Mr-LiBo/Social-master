package libo.com.social.ui.Logo;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by liaodp on 2017/11/7.
 */

class CustomPagerAdapter extends PagerAdapter {
    private List<View> viewList;


    public void setViewList(List<View> viewList) {
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList== null ? 0 : viewList.size();
    }


    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }


//        @Override
//    public Object instantiateItem(ViewGroup container, int position) {
////            ((ViewPager) container).addView(getItem(position));
//        return getItem(position);
////            return super.instantiateItem(container, position);
//    }

    @Override
    public Object instantiateItem(View collection, int position) {
        ((ViewPager) collection).addView(getItem(position));
        return getItem(position);
    }

    private View getItem(int position) {
      if (viewList != null && position >= 0 && position < viewList.size())
      {
         return viewList.get(position);
      }
        return null;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
