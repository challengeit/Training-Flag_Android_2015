package android.flag.pt.challenge_it.weathernotifier;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherIntentService extends IntentService
{
    public WeatherIntentService()
    {
        super("WeatherIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        int count = 0;
        try
        {
            URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Lisbon&mode=json&units=metric");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            String res = "";
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null)
                res += line;

            JSONObject response = new JSONObject(res);
            double temp = response.getJSONObject("main").getDouble("temp");

            // Use notification bar.
            Notification notification = new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                                                                                               .setContentTitle("actual weather")
                                                                                               .setContentText(temp + "ºC " + count)
                                                                                               .build();

            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

            manager.notify(count, notification);
        }
        catch(Exception e)
        {
            //
        }
    }
}
