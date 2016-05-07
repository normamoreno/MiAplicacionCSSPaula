package mx.edu.utng.css;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by MD on 3/6/2016.
 */
public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity_layout);
        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        String url = getIntent().getStringExtra("url");

        switch (url) {
            case "Curso basico 1":
                Uri uri = Uri.parse("rtsp://r2---sn-q4fl6ne7.googlevideo.com/Cj0LENy73wIaNAmc6jI3hQ2I2xMYDSANFC3rLidXMOCoAUIASARg3cqOudaC2vNWigELbEhHaHlnRWVESHMM/CE63C8CA2C55A9D5F5A6D874901F1F6AEF7959D3.6B2B0C54616ABCAEA398D1977BE10B15842991B1/yt6/1/video.3gp");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri);
                videoView.requestFocus();
                videoView.start();
                break;
            case "Curso basico 2":
                Uri uri1 = Uri.parse("rtsp://r2---sn-q4fl6n7l.googlevideo.com/Cj0LENy73wIaNAnbiTt07COpuhMYDSANFC1ELydXMOCoAUIASARg3cqOudaC2vNWigELbEhHaHlnRWVESHMM/59DA1E8EFEDC66E5D3FC5B41D801A5B20DBF2DE0.97A62996831F1A6BDA953F916D94E5F09E4A768E/yt6/1/video.3gp");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri1);
                videoView.requestFocus();
                videoView.start();
                break;
            case "Curso basico 3":
                Uri uri2 = Uri.parse("rtsp://r4---sn-q4f7sn7d.googlevideo.com/Cj0LENy73wIaNAkNh5zWEvk7BRMYDSANFC2TLydXMOCoAUIASARg3cqOudaC2vNWigELbEhHaHlnRWVESHMM/8E5B7D18D302F761DAF6FE9248F2DF3CDA0E7290.9D8BFB31C93ECD3AD3ABC5FD1C7512A73C7D15D4/yt6/1/video.3gp");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri2);
                videoView.requestFocus();
                videoView.start();
                break;
            case "Promoción":
                Uri uri3 = Uri.parse("rtsp://r4---sn-q4fl6n76.googlevideo.com/Cj0LENy73wIaNAnawApchF8ZqRMYDSANFC2m_yhXMOCoAUIASARgi6TIybL9yN5WigELUG9KS0VmWFlZUkkM/1F93C437110148AB3EB2A76720C44BE02D3AAFD3.2495DDFFE53A333625F5D0D18547ADB7E9193A37/yt6/1/video.3gp");
                videoView.setMediaController(mediaController);
                videoView.setVideoURI(uri3);
                videoView.requestFocus();
                videoView.start();
                break;
        }
    }
}