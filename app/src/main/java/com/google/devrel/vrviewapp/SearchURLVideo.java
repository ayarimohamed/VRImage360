package com.google.devrel.vrviewapp;


import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.vr.sdk.widgets.video.VrVideoView;
import java.io.IOException;

public class SearchURLVideo extends AppCompatActivity {
    EditText etVidUrl;
    Button btnViewUrlVid;


    private VrVideoView videoWidgetView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_urlvideo);

        videoWidgetView = (VrVideoView) findViewById(R.id.video_view1);
        etVidUrl = (EditText) findViewById(R.id.searchVidEdt);
        btnViewUrlVid = (Button) findViewById(R.id.btnVidSearch);

        btnViewUrlVid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String chaine = etVidUrl.getText().toString();
                Log.v("Essai", chaine);

                new AsyncTask<Void, Void, Void>() {
                     Uri url;

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            //url = Uri.parse("https://www.demonuts.com/Demonuts/smallvideo.mp4");
                            url = Uri.parse( chaine );
                        } catch (Exception e) {
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {

                        try {
                            if (videoWidgetView.getDuration() <= 0) {
                            /*VrVideoView.Options options = new VrVideoView.Options();
                            options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
                            videoWidgetView.loadVideo(url, options);  }*/

                                //*if (videoWidgetView.getDuration() <= 0) {
                                VrVideoView.Options options = new VrVideoView.Options();
                                options.inputFormat = VrVideoView.Options.FORMAT_HLS;
                                options.inputType = VrVideoView.Options.TYPE_MONO; // for 360 video
                                videoWidgetView.loadVideo(url, options);

                                /*try {
                                    videoWidgetView.loadVideo(url, new VrVideoView.Options());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }*/
                            }else {
                                Toast.makeText(getApplicationContext(), "cannot open URL " + chaine,
                                        Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        }



                }.execute();
            }
        });


    }


}
