package android.flag.pt.challenge_it.myfirstapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main activity for the first Android application.
 * Contains five buttons and one text view.
 * Buttons behavior:
 *  - increments and decrements the integer value of the text view;
 *  - show a toast with value of the counter;
 *  - submit event to ask for an application that is capable of opening a browser with a specific URL;
 *  - submit intent that expects for a result from the activity that it calls.
 *
 * @author Challenge.IT
 */
public class MainActivity extends ActionBarActivity /*implements View.OnClickListener*/
{
    private final static String LOG_TAG = "LOG_TAG";
    private final static String COUNTER_VALUE_KEY = "COUNTER_VALUE";
    private final static int RESULT_FLAG = 1;

    private TextView txtCounter = null;

    private static MemoryLeak leak;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        Log.d(LOG_TAG, "MainActivity - onCreate");

        /*
         * Only for show the possible memory leaks.
         * Use the device rotation for demonstrate the possible memory leak caused by the
         * static leak reference.
         */
        if(leak == null)
            leak = new MemoryLeak(this);

        Log.d(LOG_TAG, "MEMORY LEAK - leak context: " + leak.getContext().toString());
        Log.d(LOG_TAG, "MEMORY LEAK - this: " + this.toString());

        // Always call the method from the super class!!!
        super.onCreate(savedInstanceState);

        // This line "connects" the layout with this activity.
        setContentView(R.layout.activity_main);

        // Get the text view for update the integer value.
        txtCounter = (TextView)findViewById(R.id.txtCounter);
        txtCounter.setText("0");

        // Get the buttons and set the click listener.
        Button btnIncr = (Button)findViewById(R.id.btnIncr);
        Button btnDecr = (Button)findViewById(R.id.btnDecr);
        Button btnShow = (Button)findViewById(R.id.btnShow);
        Button btnLaunchBrowser = (Button)findViewById(R.id.btnLaunchBrowser);
        Button btnResult = (Button) findViewById(R.id.btnResult);

        /*
         * In this case, the listener is the "this", so this Activity class
         * have to implements the interface View.OnClickListener.
         */
        // btnIncr.setOnClickListener(this);
        // btnDecr.setOnClickListener(this);

        /*
         * The next code demonstrates the usage of the anonymous class for define
         * the click listeners for the buttons, instead of use the Activity itself.
         */
        btnIncr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int current = Integer.parseInt(txtCounter.getText().toString());
                txtCounter.setText((current + 1) + "");
            }
        });

        btnDecr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                int current = Integer.parseInt(txtCounter.getText().toString());
                txtCounter.setText((current - 1) + "");
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(), "counter: " + txtCounter.getText(), Toast.LENGTH_SHORT)
                     .show();
            }
        });

        btnLaunchBrowser.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String url = "http://www.google.pt";
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });

        /*
         * Demonstration for create programmatically one View in the layout.
         * For this purpose, We create one Button that will display, when clicked,
         * in another activity the current counter's value.
         */
        Button btnForSecondActivity = new Button(this);
        btnForSecondActivity.setText("Go to next activity");
        btnForSecondActivity.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                                                                           LinearLayout.LayoutParams.WRAP_CONTENT));

        LinearLayout rootLayout = (LinearLayout) findViewById(R.id.rootLayout);
        rootLayout.addView(btnForSecondActivity);

        btnForSecondActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // BAD!!!!!!!!! Remember the iOc pattern.
                // Activity act = new SecondActivity();

                Intent intentToOpenSecondActivity = new Intent(getApplicationContext(), SecondActivity.class);
                intentToOpenSecondActivity.putExtra(SecondActivity.COUNTER_VALUE_KEY_EXTRA, txtCounter.getText());
                startActivity(intentToOpenSecondActivity);
            }
        });

        btnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start an activity expecting a result from it, using startActivityForResult.
                // RESULT_FLAG is a constant defined by us to identify this specific startActivityForResult,
                // because an Activity can start multiple startActivityForResult and only exists one callback (onActivityResult method) to handle each result.
                Intent resultIntent = new Intent(getApplicationContext(), ResultActivity.class);
                startActivityForResult(resultIntent, RESULT_FLAG);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu, this adds the items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /*
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
    */

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        Log.d(LOG_TAG, "MainActivity - onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putString(COUNTER_VALUE_KEY, txtCounter.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState)
    {
        Log.d(LOG_TAG, "MainActivity - onRestoreInstanceState");
        super.onRestoreInstanceState(savedInstanceState);
        txtCounter.setText(savedInstanceState.getString(COUNTER_VALUE_KEY));
    }

    @Override
    protected void onStart()
    {
        Log.d(LOG_TAG, "MainActivity - onStart");
        super.onStart();
    }

    @Override
    protected void onResume()
    {
        Log.d(LOG_TAG, "MainActivity - onResume");
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        Log.d(LOG_TAG, "MainActivity - onPause");
        super.onPause();
    }

    @Override
    protected void onStop()
    {
        Log.d(LOG_TAG, "MainActivity - onStop");
        super.onStop();
    }

    @Override
    protected void onRestart()
    {
        Log.d(LOG_TAG, "MainActivity - onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy()
    {
        Log.d(LOG_TAG, "MainActivity - onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        /**
         * This method is called when an activity returns to this activity, when it was called by startActivityForResult.
         *
         * Parameter description:
         *  requestCode - identifier code for the startActivityForResult invoked
         *  resultCode - status code of the result from another activity. Can be RESULT_OK or RESULT_CANCELED.
         *  data - intent with data passed thru activities.
         */
        if(requestCode == RESULT_FLAG && resultCode == RESULT_OK) {
            Toast.makeText(getApplicationContext(), data.getStringExtra(ResultActivity.NAME), Toast.LENGTH_LONG).show();
        }
    }
}
