package android.flag.pt.challenge_it.myfirstapplication;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Result activity to ask for form value into {@link MainActivity}.
 * Contains one edit text and one button.
 * The button save the value from edit text and submit it into main activity.
 *
 * @author Challenge.IT
 */
public class ResultActivity extends ActionBarActivity {

      public static final String NAME = "NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Get button and edit text references
        Button btnSave = (Button) findViewById(R.id.btnSave);
        final EditText txtName = (EditText) findViewById(R.id.txtName);

        /**
         * Button listener to response to MainActivity via startActivityForResult.
         */
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtain the value from edit text
                String text = txtName.getText().toString();

                // Include value into intent
                Intent intent = getIntent();
                intent.putExtra(NAME, text);

                // Set the result with success flag and finish activity
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }
}
