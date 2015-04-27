package android.flag.pt.challenge_it.temperatureapp;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.flag.pt.challenge_it.temperatureapp.provider.TemperatureContract;
import android.flag.pt.challenge_it.temperatureapp.provider.TemperatureProvider;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

/**
 * ListActivity with all temperatures in database.
 *
 * @pt Listagem de temperaturas presentes na base de dados.
 * @author Challenge.IT
 */
public class TemperatureListActivity extends ListActivity
{
    private CursorAdapter _adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        /**
         * Create AsyncTask to obtain values from database and show them in list view.
         *
         * @pt Criar uma Asynctask para obter os dados da base de dados e mostrar na vista.
         */
        new TemperatureAsyncTask().execute();
    }

    /**
     * Temperature Adapter to inflate row view of temperature's list.
     *
     * @pt Adapter para criar cada linha de informação sobre a temperatura na listagem.
     */
    private class TemperatureAdapter extends CursorAdapter
    {
        /**
         * Constructor to build Adapter with given cursor of temperatures.
         * @param cursor
         *
         * @pt Construtor de Adapter que recebe o cursor com a informação a colocar na listagem de temperaturas.
         */
        public TemperatureAdapter(Cursor cursor)
        {
            super(TemperatureListActivity.this, cursor, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        }

        @Override
        public boolean areAllItemsEnabled()
        {
            return true;
        }

        @Override
        public boolean isEnabled(int position)
        {
            return true;
        }

        @Override
        public void bindView(View v, final Context ctx, final Cursor cursor)
        {
            /**
             * Affect values of temperatures in each row of list.
             *
             * @pt Afectar valores de cada linha de registo na vista da Activity.
             */
            ((TextView) v.findViewById(R.id.txtTemperature))
                    .setText(cursor.getString(cursor.getColumnIndex(TemperatureContract.VALUE)));
        }

        @Override
        public View newView(Context ctx, Cursor cursor, ViewGroup vg)
        {
            return getLayoutInflater().inflate(R.layout.temperature_row, null);
        }
    }

    /**
     * AsyncTask to get values.
     *
     * @pt AsyncTask para obter os valores da base de dados.
     */
    private class TemperatureAsyncTask extends AsyncTask<Void, Void, Cursor>
    {
        @Override
        protected Cursor doInBackground(Void... params)
        {
            /**
             * Make the query. Start managing cursor and return it.
             *
             * @pt Realizar o pedido ao ContentProvider da listagem de temperaturas.
             */
            Cursor cursor = getContentResolver().query(TemperatureProvider.CONTENT_URI, null, null, null, null);

            /**
             * Tells that the MailActivity controls the life-cycle of the cursor.
             *
             * @pt Delegar na Activity responsabilidade de controlar o tempo de vida do cursor com os dados.
             */
            startManagingCursor(cursor);
            return cursor;
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            /**
             * If is the first time, creates the adapter.
             *
             * @pt Se foi a primeira chamada, criar o adapter com os dados do cursor.
             */
            if(_adapter == null)
            {
                _adapter = new TemperatureAdapter(cursor);
                // Set adapter in the list.
                TemperatureListActivity.this.setListAdapter(_adapter);
            }
            /**
             * If the adapter already exists, swap the cursor in adapter.
             *
             * @pt Se já existir, colocar o novo cursor com possíveis novos dados.
             */
            else
            {
                // Stop using the old version of the temperatures in the cursor.
                stopManagingCursor(_adapter.getCursor());
                _adapter.changeCursor(cursor);
            }
        }
    }
}
