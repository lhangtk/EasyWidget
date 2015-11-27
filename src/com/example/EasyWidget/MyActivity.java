package com.example.EasyWidget;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.widget.SeekBar;
import com.example.EasyWidget.widgets.CircleProgressBar;
import com.example.EasyWidget.widgets.WaveView;

public class MyActivity extends Activity {
    private CircleProgressBar circleProgressBar;
    private WaveView waveView;
    private SeekBar seekBar;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        circleProgressBar = (CircleProgressBar) findViewById(R.id.circle);
        waveView = (WaveView) findViewById(R.id.waveview);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setMax(100);
        circleProgressBar.setUseCenter(true);
        circleProgressBar.setStyle(CircleProgressBar.FILL);
        ObjectAnimator animator = ObjectAnimator.ofFloat(circleProgressBar,"progress",0.f,1.f);
        animator.setDuration(3000);
        animator.start();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                waveView.setProgress((float)(seekBar.getProgress())/(float)(seekBar.getMax()));
                circleProgressBar.setProgress((float)(seekBar.getProgress())/(float)(seekBar.getMax()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
