package android.flag.pt.challenge_it.weathernotifier;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Classe que define o Application da nossa Aplicação :-)
 * O seu tempo de vida, é o tempo de vida da própria aplicação.
 * Nomeadamente utilizada para afectar propriedades inicialmente quando a
 * aplicação é lançada.
 * Também utilizada para guardar estado em memória, que está disponível durante
 * toda a aplicação.
 * É criado um objecto singleton em memória desta classe, por parte do Android.
 *
 * @author Challenge.IT
 */
public class WeatherApplication extends Application
{
    private Credentials _credentials;

    @Override
    public void onCreate()
    {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String username = prefs.getString(PreferencesActivity.USERNAME_KEY, null);
        String password = prefs.getString(PreferencesActivity.PASSWORD_KEY, null);

        _credentials = new Credentials(username, password);

        super.onCreate();
    }

    public Credentials getCredentials() { return _credentials; }

    public void setCredentials(Credentials credentials)
    {
        _credentials = credentials;
    }
}
