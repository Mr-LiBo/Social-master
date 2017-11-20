package libo.com.social.ui.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import libo.com.social.R;

/**
 * Created by liaodp on 2017/11/9.
 */

public class BottomMenuFragment extends DialogFragment {

    private View view;
    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_left_btn;
    private TextView tv_right_btn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置透明色
        getDialog().getWindow().setWindowAnimations(R.style.menu_animation);//添加一组进出动画

        view = inflater.inflate(R.layout.dialog_fragment_menu, container, false);

        initView();
        return view;
    }

    private void initView() {
        tv_title = view.findViewById(R.id.tv_title);
        tv_content = view.findViewById(R.id.tv_content);
        tv_left_btn = view.findViewById(R.id.tv_left_btn);
        tv_left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItemOnClickListener.leftOnClick();

            }
        });
        tv_right_btn = view.findViewById(R.id.tv_right_btn);
        tv_right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menuItemOnClickListener.rightOnClick();
            }
        });
    }

    public void setTv_title(String title) {
        tv_title.setText(title);
    }

    public void setTv_content(String content) {
        tv_title.setText(content);
    }

    @Override
    public void onStart() {
        super.onStart();

        //设置弹出框宽屏显示，适应屏幕宽度
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        getDialog().getWindow().setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);

        //移动弹出菜单到底部
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        // wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(wlp);

    }

    @Override
    public void onStop() {
        this.getView().setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.menu_disappear));
        super.onStop();
    }

    public MenuItemOnClickListener menuItemOnClickListener;

    public MenuItemOnClickListener getMenuItemOnClickListener() {
        return menuItemOnClickListener;
    }

    public void setMenuItemOnClickListener(MenuItemOnClickListener menuItemOnClickListener) {
        this.menuItemOnClickListener = menuItemOnClickListener;
    }

    public interface MenuItemOnClickListener {
        public void leftOnClick();

        public void rightOnClick();
    }
}
