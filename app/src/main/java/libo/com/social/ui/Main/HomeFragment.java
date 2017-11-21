package libo.com.social.ui.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import libo.com.social.R;
import libo.com.social.ui.view.AbilityMapView.AbilityBean;
import libo.com.social.ui.view.AbilityMapView.AbilityMapView;

/**
 * Created by liaodp on 2017/11/8.
 */

public class HomeFragment extends Fragment {

    AbilityMapView abilitymapview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        this.abilitymapview = (AbilityMapView) rootView.findViewById(R.id.ability_map_view);
        abilitymapview.setData(new AbilityBean(5, 70, 80, 70, 80, 80, 80));


        return rootView;
    }
}
