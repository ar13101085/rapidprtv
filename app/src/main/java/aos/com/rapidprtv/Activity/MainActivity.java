package aos.com.rapidprtv.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.VideoView;

import java.util.concurrent.ExecutionException;

import aos.com.rapidprtv.R;
import aos.com.rapidprtv.ServerInfo.MySharePrefference;
import aos.com.rapidprtv.ServerInfo.ServerInfo;
import microsoft.aspnet.signalr.client.Credentials;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.Request;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;

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


        Platform.loadPlatformComponent(new AndroidPlatformComponent());
        HubConnection connection = new HubConnection(ServerInfo.HOST_ADDRESS);
        HubProxy hub = connection.createHubProxy("TvHub");


        Credentials credentials=new Credentials() {
            @Override
            public void prepareRequest(Request request) {
               // request.addHeader(MySharePrefference.TOKEN, MySharePrefference.getData(MySharePrefference.TOKEN));
                request.addHeader("authorization","bearer rLQd1-q-5CdheRQ5l5envaEZfdTWpPX4RIzvTDelURIw6ITegpEbD1U6XZrVrYcODUGYA8pH16vc4MXA_XHONtvJDkCEQahDDksw-oxBENuZH4k0F7vm0rtgdoRm89xwqSlu119JA2BBuHTg1apVo9vv8YSN3ke7SAR8Hzz_QFJ3m4tu-PFlatsrANLdRQAWxxuMYlPUSMG_Crfd46JJVa-h9Yvgz0pPs2oYDFsOJqp54wUsFLPOhnSGD-kp2rOvm16kOx9Uz3qxBai_pYYPmbzvr_e5d-pvRxGqQFMVtXr2wl8Ar-2_eUjqwCMDNmh3AMEF5s7lUOxSn9q3c59Qaf7cSd6KWfop9pclbMqJFQITwK9bXe_5V676_r1cHwEdY-nf97gM8t0TuGCxmJlV2RvgRx1oYMHpeS1NNZcHVITu2bpyP1eoE-9lrx80-Sd8gRGYeAC0QwpNHG8BQRbSmv3D0B683f1Z_r1EkgTjwGs");
            }
        };
        connection.setCredentials(credentials);
        SignalRFuture<Void> awaitConnection = connection.start();
        try {
            awaitConnection.get();
        } catch (InterruptedException e) {
            // Handle ...
        } catch (ExecutionException e) {

        }
        try {
            hub.invoke("hello");
        } catch (Exception e) {
            e.printStackTrace();
        }
        hub.on("hello",new SubscriptionHandler1<String>() {
            @Override
            public void run(String o) {
                System.out.println(o);
            }
        },String.class);
    }



}
