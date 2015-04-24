package android.flag.pt.challenge_it.temperatureapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Started Service used to pull today's weather from Lisbon, Portugal.
 *
 * @author Challenge.IT
 */
public class TemperatureService extends Service
{
    private static final String SERVICE_LOG = "SERVICE_LOG";

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.i(SERVICE_LOG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        /**
         * We need to create an alternative thread because the services run in main thread.
         * This provoke that the current running apps blocks when a service is in execution.
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
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

                    Log.i(SERVICE_LOG, temp + " ºC");

                    stopSelf();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                    Log.i(SERVICE_LOG, "Cannot get temperature.");
                }
            }
        }).start();

        /**
         * Return possible types:
         *  - START_NOT_STICKY: When the service is destroyed by Android, it doesn't get back up again.
         *  - START_STICKY: When the service is destroyed by Android, it's restarted and runs again.
         *  - START_REDELIVER_INTENT: When the service is destroyed by Android, it's restarted with the original intent.
         */
        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(SERVICE_LOG, "onDestroy");
    }
}
