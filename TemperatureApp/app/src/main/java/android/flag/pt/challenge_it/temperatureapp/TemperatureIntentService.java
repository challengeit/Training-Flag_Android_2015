package android.flag.pt.challenge_it.temperatureapp;

import android.app.IntentService;
import android.content.Intent;
import android.flag.pt.challenge_it.temperatureapp.model.Temperature;
import android.flag.pt.challenge_it.temperatureapp.provider.TemperatureManager;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * An {@link IntentService} subclass for handling asynchronous work to get the current temperature for Lisbon, Portugal.
 *
 * @author Challenge.IT
 */
public class TemperatureIntentService extends IntentService {

    private static final String INTENT_SERVICE_LOG = "INTENT_SERVICE_LOG";
    private final TemperatureManager manager;

    public TemperatureIntentService()
    {
        super("TemperatureIntentService");
        this.manager = new TemperatureManager(this);
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(INTENT_SERVICE_LOG, "onCreate");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        /**
         * In IntentServices there's no need to create an alternative thread to execute the specified work because
         * the IntentService executes on is own worker thread.
         *
         * This type of Services are destroyed by Android system when the queue associated to them becomes empty.
         * We don't need to call the method stopSelf or stopService to destroy an IntentService.
         */
        try
        {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Lisbon&mode=json&units=metric");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String res = "";
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            while ((line = rd.readLine()) != null)
                res += line;

            JSONObject response = new JSONObject(res);
            double temp = response.getJSONObject("main").getDouble("temp");

            Log.i(INTENT_SERVICE_LOG, temp + " ºC");

            /**
             * Save persistently the temperature in database.
             *
             * @pt Guardar na base de dados a informação da temperatura.
             */
            manager.save(new Temperature(temp));
        }
        catch(Exception e)
        {
            Log.i(INTENT_SERVICE_LOG, "Cannot get temperature.");
        }
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(INTENT_SERVICE_LOG, "onDestroy");
    }
}
