package android.flag.pt.challenge_it.temperatureapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

/**
 * Main activity. Entry point of Application to test Services.
 *
 * @author Challenge.IT
 */
public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * Way to call a new service. Like the Activities, the Services are also invoked using intents.
                 */
                //startService(new Intent(getApplicationContext(), TemperatureService.class));

                /**
                 * An intent service is called in a same way of a Service with the method startService.
                 * If we invoke the same service several times, it's only the same reference that executes.
                 */
                //for(int i = 0; i < 3; i++)
                startService(new Intent(getApplicationContext(), TemperatureIntentService.class));
            }
        });

        Button btnList = (Button) findViewById(R.id.btnList);
        btnList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), TemperatureListActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
