package libo.com.social.ui.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import libo.com.social.R;
import libo.com.social.http.NetUtil;
import libo.com.social.http.config.Url;
import libo.com.social.ui.Logo.LogoActivity;
import libo.com.social.ui.Main.my.LoginActivity;
import libo.com.social.ui.dialog.BottomMenuFragment;

/**
 * Created by liaodp on 2017/11/8.
 */

public class MyFragment extends Fragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my,container,false);
        Button rl_out = rootView.findViewById(R.id.rl_out);
        rl_out.setOnClickListener(this);

        LinearLayout ll_login = rootView.findViewById(R.id.ll_login);
        ll_login.setOnClickListener(this);
        return rootView;
    }


    private void showOutDialog() {
        final BottomMenuFragment bottomMenuFragment = new BottomMenuFragment();
        bottomMenuFragment.show(getActivity().getFragmentManager(),"bottomMenuFragment");
        bottomMenuFragment.setMenuItemOnClickListener(new BottomMenuFragment.MenuItemOnClickListener() {
            @Override
            public void leftOnClick() {
                Log.i("TAG","点了确认");
//                NetUtil.OkHttpGet(Url.URL_Query);

                bottomMenuFragment.dismiss();

            }

            @Override
            public void rightOnClick() {
                Log.i("TAG","点了取消");
                bottomMenuFragment.dismiss();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ll_login:
                getActivity().startActivity(LoginActivity.getLauncherIntent(getActivity()));

                break;

            case R.id.rl_out:
                showOutDialog();
        }
    }
}
