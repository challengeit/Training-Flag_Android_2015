package android.flag.pt.challenge_it.myfirstapplication;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity for the first Android application.
 * Contains two buttons and one text view.
 * The buttons increments and decrements the integer value of the text view.
 *
 * @author Challenge.IT
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener
{
    private final static String LOG_TAG = "LOG_TAG";

    private TextView txtCounter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(LOG_TAG, "onCreate");

        // Always call the method from the super class!!!
        super.onCreate(savedInstanceState);

        // This line "connects" the layout with this activity.
        setContentView(R.layout.activity_main);

        // Get the text view for update the integer value.
        txtCounter = (TextView)findViewById(R.id.txtCounter);
        txtCounter.setText("0");

        // Get the buttons and set the click listener.
        // In this case, the listener is the "this", so this Activity class
        // have to implements the interface View.OnClickListener.
        Button btnIncr = (Button)findViewById(R.id.btnIncr);
        Button btnDecr = (Button)findViewById(R.id.btnDecr);
        btnIncr.setOnClickListener(this);
        btnDecr.setOnClickListener(this);

        // The next code demonstrates the usage of the anonymous class for define
        // the click listeners for the buttons, instead of use the Activity itself.
        /*
        btnIncr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(txtCounter.getText().toString());
                txtCounter.setText((current + 1) + "");
            }
        });

        btnDecr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int current = Integer.parseInt(txtCounter.getText().toString());
                txtCounter.setText((current - 1) + "");
            }
        });
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onClick(View v)
    {
        // Verify which the button was clicked.
        if(v.getId() == R.id.btnIncr)
        {
            int current = Integer.parseInt(txtCounter.getText().toString());
            txtCounter.setText((current + 1) + "");
        }

        if(v.getId() == R.id.btnDecr)
        {
            int current = Integer.parseInt(txtCounter.getText().toString());
            txtCounter.setText((current - 1) + "");
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Log.d(LOG_TAG, "onSaveInstanceState");

        super.onSaveInstanceState(outState);
        outState.putString("COUNTER_VALUE", txtCounter.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.d(LOG_TAG, "onRestoreInstanceState");

        super.onRestoreInstanceState(savedInstanceState);
        txtCounter.setText(savedInstanceState.getString("COUNTER_VALUE"));
    }

    @Override
    protected void onStart()
    {
        Log.d(LOG_TAG, "onStart");

        super.onStart();
    }

    @Override
    protected void onResume()
    {
        Log.d(LOG_TAG, "onResume");

        super.onResume();
    }

    @Override
    protected void onPause()
    {
        Log.d(LOG_TAG, "onPause");

        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Log.d(LOG_TAG, "onStop");

        super.onStop();
    }

    @Override
    protected void onRestart()
    {
        Log.d(LOG_TAG, "onRestart");

        super.onRestart();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(LOG_TAG, "onDestroy");

        super.onDestroy();
    }
}
