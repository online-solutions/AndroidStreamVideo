package plus.studio.beta.streamvideo.activities;

import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.sprylab.android.widget.TextureVideoView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import plus.studio.beta.streamvideo.R;

public class ThirdActivity extends ActionBarActivity {
    private static final String TAG = ThirdActivity.class.getName();

    private TextureVideoView mVideoView;

    private Button mCaptureFrameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);

        mVideoView = (TextureVideoView) findViewById(R.id.video_view);
        mCaptureFrameButton = (Button) findViewById(R.id.btn_capture_frame);
        mCaptureFrameButton.setOnClickListener(new View.OnClickListener() {
                                                   @Override
                                                   public void onClick(final View v) {
                                                       saveCurrentFrame();
                                                   }
                                               }
        );

        initVideoView();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mVideoView != null) {
            mVideoView.stopPlayback();
            mVideoView = null;
        }
    }

    private void initVideoView() {
        mVideoView.setVideoPath(getVideoPath());
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(final MediaPlayer mp) {
                startVideoPlayback();
//                startVideoAnimation();
            }
        });
    }

    private void startVideoPlayback() {
        // "forces" anti-aliasing - but increases time for taking frames - so keep it disabled
        // mVideoView.setScaleX(1.00001f);
        mVideoView.start();
        // test fullscreen
//        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
//        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) mVideoView.getLayoutParams();
//        params.width =  metrics.widthPixels;
//        params.height = metrics.heightPixels;
//        params.leftMargin = 0;
//        mVideoView.setLayoutParams(params);
    }

    private void startVideoAnimation() {
        mVideoView.animate().rotationBy(360.0f).setDuration(mVideoView.getDuration()).start();
    }

    private String getVideoPath() {
        return "android.resource://" + getPackageName() + "/" + R.raw.video;
    }

    private void saveCurrentFrame() {
        final Bitmap currentFrameBitmap = mVideoView.getBitmap();

        final File currentFrameFile = new File(getExternalFilesDir("frames"),
                "frame" + System.currentTimeMillis() + ".jpg");
        writeBitmapToFile(currentFrameBitmap, currentFrameFile);

        currentFrameBitmap.recycle();

        Toast.makeText(this, "Frame saved as " + currentFrameFile.getAbsolutePath() + ".", Toast.LENGTH_SHORT).show();
    }

    private void writeBitmapToFile(final Bitmap bitmap, final File file) {
        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.close();
        } catch (final IOException e) {
            Log.e(TAG, "Error writing bitmap to file.", e);
        }
    }
}
