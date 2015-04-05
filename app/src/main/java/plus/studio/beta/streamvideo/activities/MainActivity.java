package plus.studio.beta.streamvideo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import plus.studio.beta.streamvideo.R;

// I make 3 demo for run video
// 1. SecondActivity: using VideoView to display video, and MediaController to controller video
// 2. ThirdActivity: using TextureVideoView from srylab (tut from android-arsenal)
// 3. FourActivity: using SurfaceView to display video and a FrameLayout to display Video Control
public class MainActivity extends Activity {

    private Button btnSecondActivity;
    private Button btnThirdActivity;
    private Button btnFourthActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        btnSecondActivity = (Button) findViewById(R.id.btnSecondActivity);
        btnThirdActivity = (Button) findViewById(R.id.btnThirdActivity);
        btnFourthActivity = (Button) findViewById(R.id.btnFourthActivity);

        // start custom activities
        btnSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), SecondActivity.class));
            }
        });
        btnThirdActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ThirdActivity.class));
            }
        });
        btnFourthActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), FourthActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
