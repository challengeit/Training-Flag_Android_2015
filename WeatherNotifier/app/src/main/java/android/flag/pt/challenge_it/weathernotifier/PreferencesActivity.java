package android.flag.pt.challenge_it.weathernotifier;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class PreferencesActivity extends PreferenceActivity
{
    public static final String INTERVAL_KEY = "INTERVAL_KEY";
    public static final String USERNAME_KEY = "USERNAME_KEY";
    public static final String PASSWORD_KEY = "PASSWORD_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);
    }

    @Override
    protected void onPause()
    {
        WeatherApplication app = (WeatherApplication)getApplication();

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String username = prefs.getString(USERNAME_KEY, null);
        String password = prefs.getString(PASSWORD_KEY, null);

        app.setCredentials(new Credentials(username, password));
        super.onPause();
    }
}
