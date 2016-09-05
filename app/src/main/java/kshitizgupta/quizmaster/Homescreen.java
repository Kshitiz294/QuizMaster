package kshitizgupta.quizmaster;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class Homescreen extends AppCompatActivity {

    CountDownTimer timer;
    TextView textView;

    public static int screenWidth,screenHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homescreen);

        textView= (TextView)findViewById(R.id.textView);
        Button button= (Button)findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Homescreen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        screenWidth= Resources.getSystem().getDisplayMetrics().widthPixels;
        screenHeight= Resources.getSystem().getDisplayMetrics().heightPixels;

        textView.setText(screenWidth + "  " + screenHeight);


    }

    public void startClock(View view){
        timer= new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long l) {
                textView.setText("" + l/1000);
            }

            @Override
            public void onFinish() {
                textView.setText("Done!");
            }
        };
        timer.start();
    }

    public void stopClock(View view){
        if(timer != null)
            timer.cancel();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_homescreen, menu);
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
