package libo.com.social.ui.main.mall;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import libo.com.social.R;
import libo.com.social.ui.widget.WaterView;

public class WaterFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "WaterFragment";
    private String weburl;
    private String channelName;
    private WaterView waterView;

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
    }

    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_water,null);
        initViw(view);
        return view;
    }

    @Override
    public void setArguments(Bundle bundle) {
        // TODO Auto-generated method stub
        weburl = bundle.getString("weburl");
        channelName = bundle.getString("name");

    }

    private void initViw(View rootView) {

        waterView = (WaterView) rootView.findViewById(R.id.waterview);

        waterView.setListener(new WaterView.Listener() {
            @Override
            public void finish() {
                Toast.makeText(getActivity(), "已经满了！！！", Toast.LENGTH_SHORT).show();
            }
        });
        waterView.start();

        SeekBar seekBar= rootView.findViewById(R.id.seekBar);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
//                Log.i(TAG,"progress:"+progress);
                waterView.setH(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

     }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

        }
    }
}
