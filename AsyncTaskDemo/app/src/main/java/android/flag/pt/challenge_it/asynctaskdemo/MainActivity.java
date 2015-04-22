package android.flag.pt.challenge_it.asynctaskdemo;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Demonstration of the AsyncTask for make I/O, slow operations that not
 * blocks the UI.
 *
 * @author Challenge.IT
 */
public class MainActivity extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);

        findViewById(R.id.btnGet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                // Execute the async task.
                new GetWeather().execute(new WeatherInfo("Lisbon"));
            }
        });
    }

    /**
     * Async task that performs one HTTP request to "http://openweathermap.org/"
     * to get the current weather in Lisbon.
     */
    private class GetWeather extends AsyncTask<WeatherInfo, Integer, WeatherInfo>
    {
        @Override
        protected WeatherInfo doInBackground(WeatherInfo... params)
        {
            WeatherInfo info = params[0];
            try
            {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + info.getCity() + "&mode=json&units=metric");

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                String res = "";
                BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null)
                    res += line;

                JSONObject response = new JSONObject(res);
                double temp = response.getJSONObject("main").getDouble("temp");

                info.setTemp(temp);
                return info;
            }
            catch(Exception e)
            {
                return null;
            }
        }

        @Override
        protected void onPostExecute(WeatherInfo info)
        {
            super.onPostExecute(info);

            /*
             * Same thing as if-else!
             * Ternary operation.
             */
            String txt = (info != null)? info.getTemp() + "ºC" : "Error";
            ((TextView)findViewById(R.id.txtTemperature)).setText(txt);
        }
    }
}
