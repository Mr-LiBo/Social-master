package libo.com.social.ui.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import libo.com.social.R;
import libo.com.social.ui.widget.RoundProgressBar;
import libo.com.social.ui.widget.WaterProgressView;

/**
 * Created by liaodp on 2017/11/8.
 */

public class InternetFragment extends Fragment {

    private static final String TAG = "InternetFragment";
    private WaterProgressView waterProgressView;
    private RoundProgressBar barStroke;
    private TextView tvProgress;
    private ProgressBar progressBar;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_internet,container,false);
        barStroke = (RoundProgressBar) rootView.findViewById(R.id.barStroke);
        barStroke.setMax(100);


        waterProgressView = rootView.findViewById(R.id.wave_view);
        waterProgressView.setMaxProgress(100);
        waterProgressView.setWaveColor("#effcff");

        DisplayMetrics dm = new DisplayMetrics();
        this.getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        int waveWidth =dm.widthPixels/5;
        int waveHeight = waveWidth/4;
        waterProgressView.setWave(waveHeight,waveWidth);
        waterProgressView.setWaveSpeed(45);
        waterProgressView.allowProgressInBothDirections(true);

        SeekBar seekBar = rootView.findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progres, boolean b) {
                Log.e(TAG,"onProgressChanged :"+ progres + "boolean："+b);
                setProgress(progres);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        tvProgress = rootView.findViewById(R.id.tv_progress);

        progressBar = rootView.findViewById(R.id.progressBar);

        return rootView;
    }

    private void setProgress(int progres) {
        barStroke.setValue(progres);
        if (waterProgressView.getCurrent() != progres){
            waterProgressView.setCurrent(progres,"");
            tvProgress.setText(progres+"%");
            progressBar.setProgress(progres);
        }
    }
}
