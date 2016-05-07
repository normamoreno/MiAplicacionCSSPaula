package mx.edu.utng.css;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

/**
 * Created by user on 05/05/2016.
 */
public class AudioActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton btnPlay,btnStop;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.audio_layout);
        initComponents();
    }

    private void initComponents() {
        mediaPlayer =new MediaPlayer().create(this,R.raw.heart);
        btnPlay=(ImageButton)findViewById(R.id.btn_play);
        btnStop=(ImageButton)findViewById(R.id.btn_stop);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_play:
                if (!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }
                break;
            case R.id.btn_stop:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }
        super.onDestroy();
    }
}
