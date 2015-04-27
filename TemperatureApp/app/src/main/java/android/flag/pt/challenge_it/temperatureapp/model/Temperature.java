package android.flag.pt.challenge_it.temperatureapp.model;

/**
 * Model class of Temperature.
 *
 * @author Challenge.IT
 */
public class Temperature
{
    private double _value;

    /**
     * Constructor of Temperature.
     * @param value
     */
    public Temperature(double value)
    {
        _value = value;
    }

    public double getValue() { return _value; }
}
