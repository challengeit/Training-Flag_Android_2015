package android.flag.pt.challenge_it.temperatureapp.provider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.flag.pt.challenge_it.temperatureapp.model.Temperature;

import java.util.ArrayList;

/**
 * Manager for {@link android.flag.pt.challenge_it.temperatureapp.model.Temperature} table.
 * @author Challenge.IT
 */
public class TemperatureManager
{
    private Context _context;

    public TemperatureManager(Context context)
    {
        _context = context;
    }

    /**
     * Store temperature in database.
     * @param temperature
     */
    public void save(Temperature temperature)
    {
        ContentValues values = new ContentValues();
        values.put(TemperatureContract.VALUE, temperature.getValue());
        _context.getContentResolver().insert(TemperatureProvider.CONTENT_URI, values);
    }

    /**
     * Get all temperatures stored in database.
     * @return list of temperatures
     */
    public ArrayList<Temperature> getAll()
    {
        Cursor cursor = _context.getContentResolver().query(TemperatureProvider.CONTENT_URI, null, null, null, null);
        ArrayList<Temperature> temperatures = new ArrayList<>();
        while(cursor.moveToNext())
        {
            temperatures.add(new Temperature(cursor.getDouble(cursor.getColumnIndex(TemperatureContract.VALUE))));
        }
        cursor.close();
        return temperatures;
    }
}
