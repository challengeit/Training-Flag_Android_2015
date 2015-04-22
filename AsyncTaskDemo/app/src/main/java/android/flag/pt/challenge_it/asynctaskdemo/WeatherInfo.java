package android.flag.pt.challenge_it.asynctaskdemo;

/**
 * Class that contains the fields with the weather information.
 *
 * @author Challenge.IT
 */
public class WeatherInfo
{
    private double _temp;
    private final String _city;

    /**
     * Creates an instance of WeatherInfo.
     * @param city The name of the city.
     */
    public WeatherInfo(String city)
    {
        _city = city;
    }

    /**
     * @return The weather's temperature.
     */
    public double getTemp() { return _temp; }

    /**
     * Set the weather's temperature.
     * @param temp The new temperature for update.
     */
    public void setTemp(double temp) { _temp = temp; }

    /**
     * @return The weather's city.
     */
    public String getCity() { return _city; }
}
