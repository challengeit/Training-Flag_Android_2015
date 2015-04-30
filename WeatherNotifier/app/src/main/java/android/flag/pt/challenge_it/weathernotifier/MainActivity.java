package android.flag.pt.challenge_it.weathernotifier;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Actividade principal. Contem 2 botões.
 * Um para fazer start ao serviço, através de um AlarmManager e outro para o parar.
 *
 * @author Challenge.IT
 */
public class MainActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        // Definição da acção de click do botão de start ao service.
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                /*
                 * Vai buscar o valor do intervalo às SharedPreferences.
                 * As SharedPreferences guardam de forma persistente os dados num ficheiro,
                 * estilo contentor associativo.
                 */
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                Long interval = Long.parseLong(preferences.getString(PreferencesActivity.INTERVAL_KEY, "15"));

                /*
                    - O Troço de código seguinte descreve a forma de guardar os dados de forma
                    explícita nas SharedPreferences.
                    - Atenção! É necessário invocar o método commit para, de facto, guardar os dados.

                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong(PreferencesActivity.INTERVAL_KEY, 10);
                editor.putString("USERNAME", "ricardo");
                editor.commit();

                */

                manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), interval * 1000, getPendingIntentForAlarmManager());
            }
        });

        // Definição da acção de click do botão de stop ao service.
        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                manager.cancel(getPendingIntentForAlarmManager());
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        String username = ((WeatherApplication)getApplication()).getCredentials().getUsername();
        Toast.makeText(getApplicationContext(), username, Toast.LENGTH_LONG).show();
    }

    /**
     * Helper method para criar o PendingIntent de start/stop do serviço no AlarmManager.
     * @return O PendingIntent necessário.
     */
    private PendingIntent getPendingIntentForAlarmManager()
    {
        Intent intent = new Intent(getApplicationContext(), WeatherService.class);
        return PendingIntent.getService(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if (item.getItemId() == R.id.action_prefs)
        {
            startActivity(new Intent(getApplicationContext(), PreferencesActivity.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
