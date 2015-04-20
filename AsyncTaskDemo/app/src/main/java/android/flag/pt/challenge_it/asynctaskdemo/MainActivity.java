package android.flag.pt.challenge_it.asynctaskdemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Challenge.IT
 */
public class MainActivity extends Activity
{
    private static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main_activity);

        Button btnGet = (Button)findViewById(R.id.btnGet);
        final TextView txtTemperature = (TextView)findViewById(R.id.txtTemperature);

        btnGet.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v)
            {
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
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

                            txtTemperature.setText(temp + " ºC");
                        }
                        catch(Exception e)
                        {
                            txtTemperature.setText(e.toString());
                        }
                    }
                });
                thread.start();
                return true;
            }
        });
    }
}
