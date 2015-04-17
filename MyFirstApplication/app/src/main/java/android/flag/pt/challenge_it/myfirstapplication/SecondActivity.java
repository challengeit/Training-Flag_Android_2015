package android.flag.pt.challenge_it.myfirstapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Second activity that shows the actual counter value from the
 * first activity.
 *
 * @author Challenge.IT
 */
public class SecondActivity extends Activity
{
    public static final String COUNTER_VALUE_KEY_EXTRA = "COUNTER_VALUE_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String counter = getIntent().getStringExtra(COUNTER_VALUE_KEY_EXTRA);
        ((TextView)findViewById(R.id.txtCounterResult)).setText(counter);
    }
}
