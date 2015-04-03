package plus.studio.beta.streamvideo.extra;

import android.content.Context;

import plus.studio.beta.streamvideo.R;

/**
 * Created by khanhphung on 30/3/15.
 */
public class Utils {

    public static String videoUrl = "https://archive.org/download/ksnn_compilation_master_the_internet/ksnn_compilation_master_the_internet_512kb.mp4";
// support video type: http://developer.android.com/guide/appendix/media-formats.html
    // 3GP, MP4, WEBM, and MKV...
    // stream via: RTSP, HTTP, and HTTPS (from Android 3.1).

    public static String getVideoPath(Context context) {
        return "android.resource://" + context.getPackageName() + "/" + R.raw.video;
    }

}
