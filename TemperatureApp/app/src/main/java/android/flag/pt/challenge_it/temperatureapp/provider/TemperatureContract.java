package android.flag.pt.challenge_it.temperatureapp.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Contract to be used in {@link TemperatureProvider}.
 * @pt Contrato que define constantes a serem utilizadas pelo ContentProvider.
 *
 * @author Challenge.IT
 */
public class TemperatureContract
{
    // table name
    public static final String TABLE = "TEMPERATURE";

    // columns names
    public static final String _ID = BaseColumns._ID;
    public static final String VALUE = "value";

    // content URI for subset of provided data from temperature provider.
    public static Uri CONTENT_PROVIDER = Uri.withAppendedPath(TemperatureProvider.CONTENT_URI, TABLE);
}
