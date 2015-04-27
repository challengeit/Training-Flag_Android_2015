package android.flag.pt.challenge_it.weathernotifier;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherService extends Service
{
    private int count = 0;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new Thread(new Runnable() {
            @Override
            public void run()
            {
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
                    setNotification("actual weather", temp + "ºC ");
                }
                catch(Exception e)
                {
                    setNotification("Error", e.getMessage());
                }
            }
        }).start();

        return START_STICKY;
    }

    private void setNotification(String title, String text)
    {
        Notification notification = new NotificationCompat.Builder(getApplicationContext()).setSmallIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
                .setContentTitle(title)
                .setContentText(text)
                .build();

        NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(count++, notification);
    }
}
