package com.google.devrel.vrviewapp;

import android.widget.Toast;
import com.google.vr.sdk.widgets.video.VrVideoView;
import java.io.IOException;

public class VideoLoaderTask {
    protected VrVideoView videoWidgetView;

        protected Boolean doInBackground(String... fileInformation) {
            try {
                VrVideoView.Options options = new VrVideoView.Options();
                options.inputType = VrVideoView.Options.TYPE_STEREO_OVER_UNDER;
                videoWidgetView.loadVideoFromAsset(fileInformation[0], options);
            }
            catch (IOException e) {
                // An error here is normally due to being unable to locate the file.
                // Since this is a background thread, we need to switch to the main thread to show a toast.
                videoWidgetView.post(new Runnable() {
                    @Override
                    public void run() {
                       // Toast.makeText(this, "Error opening file. ", Toast.LENGTH_LONG).show();
                    }
                });
            }

            return true;
        }
    }


