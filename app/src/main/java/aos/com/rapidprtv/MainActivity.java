package aos.com.rapidprtv;

import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView TvVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_design);
        TvVideoView= (VideoView) findViewById(R.id.TvVideoView);

        /*MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(TvVideoView);
        TvVideoView.setMediaController(mediaController);*/
        TvVideoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.test_video));
        TvVideoView.start();


    }
}
